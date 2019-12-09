package br.com.ges.api.service;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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


	public RelatorioFinal exibir(Long id) {
		return relatorioFinalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RFNE));
	}
	
	public List<RelatorioFinal> listarTodos() {
		return (List<RelatorioFinal>) relatorioFinalRepository.findAll();
	}

	public List<RelatorioFinal> buscaRelatorioFinalDoAluno(Aluno aluno) {

		Estagio estagioAluno = estagioRepository.findByAluno(aluno);
		List<RelatorioFinal> relatoriosAluno = relatorioFinalRepository
				.findByEstagioRelatorioFinalId(estagioAluno.getId());

		return relatoriosAluno;
	}

	public String deletar(Long id) throws BusinessException {
		RelatorioFinal relatorioFinal = relatorioFinalRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(RFNE));

		try {
			deletaTipoAtividadeEstagiario(id);
			relatorioFinalRepository.delete(relatorioFinal);
			return "Relatório Final deletado com sucesso.";

		} catch (Exception e) {
			throw new BusinessException("Erro ao deletar Relatório Final.");
		}

	}

	private void deletaTipoAtividadeEstagiario(Long id) {

		try {

			TipoAtividadeEstagiario tipoAtividadeEstagiario = tipoAtividadeEstagiarioRepository
					.findByRelatorioFinalId(id);
			tipoAtividadeEstagiarioRepository.delete(tipoAtividadeEstagiario);

		} catch (Exception e) {
			throw new ResourceNotFoundException();
		}

	}

	/**
	 * 
	 * Salva um novo Relatório Final somente se o aluno não tenha
	 * registrado um Relatório Final
	 * 
	 */
	public ResponseEntity<RelatorioFinal> salvar(RelatoriosAlunoWrapper relatoriosAluno, HttpServletResponse response) throws BusinessException {

		Estagio estagioAluno = estagioRepository.findByAluno(relatoriosAluno.getAluno());

		if (jaExisteRelatorioFinalDoAluno(estagioAluno.getId())) {

			throw new BusinessException("Um estágio só pode ter apenas 1 (um) Relatório Final.");

		} else {

			if (verificaHorasCumpridasDoEstagio(relatoriosAluno.getAluno().getId())) {

				throw new BusinessException("Não foi possível salvar este Relatório Final pois a quantidade de horas "
						+ "registradas nos Relatórios de Atividades não atinge a quantidade de 200 horas.");

			} else {
				
				try {
					
					RelatorioFinal relatorioFinal = new RelatorioFinal();
					relatorioFinal = relatoriosAluno.getRelatorioFinal();
					relatorioFinal.setEstagioRelatorioFinal(estagioAluno);
					RelatorioFinal relatorioFinalSalvo = relatorioFinalRepository.save(relatorioFinal);
					
					URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
							.buildAndExpand(relatorioFinalSalvo.getId()).toUri();
					response.setHeader("Location", uri.toASCIIString());
					
					
					if (relatoriosAluno.getRelatorioFinal().getTipoAtividade() != null
							&& relatoriosAluno.getRelatorioFinal().getTipoAtividade() == TipoAtividade.ESTAGIARIO) {
						
						salvarTipoAtividadeEstagiario(relatorioFinal, relatoriosAluno);
						
					}
					
					return ResponseEntity.created(uri).body(relatorioFinalSalvo);
					
				} catch (Exception e) {
					throw new BusinessException(e.getMessage());
				}

			}
		}

	}

	/**
	 *
	 * Verifica quantidade de horas registradas nos relatórios de atividades do
	 * aluno
	 * 
	 */
	private boolean verificaHorasCumpridasDoEstagio(Long idAluno) {
//		Estagio estagioAluno = estagioRepository.findByAlunoId(idAluno);
//		List<RelatorioAtividade> relatoriosAluno = relatorioAtividadeRepository
//				.findByEstagioRelatorioAtividadeId(estagioAluno.getId());
//
//		int qtdHorasAtividades = 0;
//
//		for (RelatorioAtividade relatorioAtividade : relatoriosAluno) {
//			qtdHorasAtividades = qtdHorasAtividades + relatorioAtividade.getQtdHoras();
//		}
//
//		if (qtdHorasAtividades != 200) {
//			return true;
//		}

//		return false;
		
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
