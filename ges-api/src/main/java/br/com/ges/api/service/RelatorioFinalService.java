package br.com.ges.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ges.api.enums.TipoAtividade;
import br.com.ges.api.exception.BusinessException;
import br.com.ges.api.model.Aluno;
import br.com.ges.api.model.Estagio;
import br.com.ges.api.model.RelatorioAtividade;
import br.com.ges.api.model.RelatorioFinal;
import br.com.ges.api.model.TipoAtividadeEstagiario;
import br.com.ges.api.repository.EstagioRepository;
import br.com.ges.api.repository.RelatorioAtividadeRepository;
import br.com.ges.api.repository.RelatorioFinalRepository;
import br.com.ges.api.repository.TipoAtividadeEstagiarioRepository;
import br.com.ges.api.wrapper.RelatoriosAlunoWrapper;

@Service
public class RelatorioFinalService {

	@Autowired
	RelatorioFinalRepository relatorioFinalRepository;

	@Autowired
	RelatorioAtividadeRepository relatorioAtividadeRepository;

	@Autowired
	EstagioRepository estagioRepository;

	@Autowired
	TipoAtividadeEstagiarioRepository tipoAtividadeEstagiarioRepository;

	private static final String RFNE = "Relatório Final não encontrado.";
	private String mensagem = null;

	/**
	 * 
	 * Busca Relatório Final a partir de seu ID
	 * 
	 */
	public RelatorioFinal exibir(Long id) {
		return relatorioFinalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RFNE));
	}
	
	/**
	 * 
	 * Lista todos Relatórios Finais do sistema
	 * 
	 */
	public List<RelatorioFinal> listarTodos() {
		return (List<RelatorioFinal>) relatorioFinalRepository.findAll();
	}

	/**
	 * 
	 * Lista todos Relatórios Finais de um Aluno
	 * 
	 */
	public List<RelatorioFinal> buscaRelatorioFinalDoAluno(Aluno aluno) {

		Estagio estagioAluno = estagioRepository.findByAluno(aluno);
		List<RelatorioFinal> relatoriosAluno = relatorioFinalRepository
				.findByEstagioRelatorioFinalId(estagioAluno.getId());

		return relatoriosAluno;
	}

	/**
	 * 
	 * Deleta o registro de Relatório Final a partir de seu ID
	 * 
	 */
	public String deletar(Long id) {
		RelatorioFinal relatorioFinal = relatorioFinalRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(RFNE));

		try {
			deletaTipoAtividadeEstagiario(id);
			relatorioFinalRepository.delete(relatorioFinal);

		} catch (Exception e) {
			// TODO: Criar Exception correta
		}

		return this.mensagem = "Relatório Final deletado com sucesso.";
	}

	private void deletaTipoAtividadeEstagiario(Long id) {

		try {

			TipoAtividadeEstagiario tipoAtividadeEstagiario = tipoAtividadeEstagiarioRepository
					.findByRelatorioFinalId(id);
			tipoAtividadeEstagiarioRepository.delete(tipoAtividadeEstagiario);

		} catch (Exception e) {
			// TODO: Criar Excepion correta
		}

	}

	/**
	 * 
	 * Salva um novo Relatório Final somente se o aluno não tenha
	 * registrado um Relatório Final
	 * 
	 */
	public String salvar(RelatoriosAlunoWrapper relatoriosAluno) throws BusinessException {

		Estagio estagioAluno = estagioRepository.findByAluno(relatoriosAluno.getAluno());

		if (jaExisteRelatorioFinalDoAluno(estagioAluno.getId())) {

			throw new BusinessException("Um estágio só pode ter apenas 1 (um) Relatório Final.");

		} else {

			if (verificaHorasCumpridasDoEstagio(relatoriosAluno.getAluno().getId())) {

				throw new BusinessException("Não foi possível salvar este Relatório Final pois a quantidade de horas "
						+ "registradas nos Relatórios de Atividades não atinge a quantidade de 200 horas.");

			} else {

				RelatorioFinal relatorioFinal = new RelatorioFinal();
				relatorioFinal = relatoriosAluno.getRelatorioFinal();
				relatorioFinal.setEstagioRelatorioFinal(estagioAluno);
				relatorioFinalRepository.save(relatorioFinal);

				if (relatoriosAluno.getTipoAtividadeEstagiario() != null
						&& relatoriosAluno.getRelatorioFinal().getTipoAtividade() == TipoAtividade.ESTAGIARIO) {

					salvarTipoAtividadeEstagiario(relatorioFinal, relatoriosAluno);

				}
			}
		}

		return this.mensagem = "Relatório Final salvo com sucesso.";
	}

	/**
	 *
	 * Verifica quantidade de horas registradas nos relatórios de atividades do
	 * aluno
	 * 
	 */
	private boolean verificaHorasCumpridasDoEstagio(Long idAluno) {
		Estagio estagioAluno = estagioRepository.findByAlunoId(idAluno);
		List<RelatorioAtividade> relatoriosAluno = relatorioAtividadeRepository
				.findByEstagioRelatorioAtividadeId(estagioAluno.getId());

		int qtdHorasAtividades = 0;

		for (RelatorioAtividade relatorioAtividade : relatoriosAluno) {
			qtdHorasAtividades = qtdHorasAtividades + relatorioAtividade.getQtdHoras();
		}

		if (qtdHorasAtividades != 200) {
			return true;
		}

		return false;
	}

	/**
	 * 
	 * Cria um novo registro se o tipo de atividade é Estagiário
	 * 
	 */
	private void salvarTipoAtividadeEstagiario(RelatorioFinal relatorioFinal, RelatoriosAlunoWrapper relatoriosAluno) {

		TipoAtividadeEstagiario tipoAtividadeEstagiario = new TipoAtividadeEstagiario();
		tipoAtividadeEstagiario = relatoriosAluno.getTipoAtividadeEstagiario();
		tipoAtividadeEstagiario.setRelatorioFinal(relatorioFinal);
		tipoAtividadeEstagiarioRepository.save(tipoAtividadeEstagiario);

	}

	/**
	 * 
	 * Verifica duplicidade de Relatório Final por aluno
	 * 
	 */
	private boolean jaExisteRelatorioFinalDoAluno(Long id) {
		List<RelatorioFinal> relatorioFinalAluno = relatorioFinalRepository.findByEstagioRelatorioFinalId(id);

		if (relatorioFinalAluno.isEmpty())
			return false;

		return true;
	}
}
