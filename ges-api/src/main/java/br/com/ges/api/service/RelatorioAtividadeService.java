package br.com.ges.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ges.api.exception.BusinessException;
import br.com.ges.api.model.Aluno;
import br.com.ges.api.model.Estagio;
import br.com.ges.api.model.RelatorioAtividade;
import br.com.ges.api.repository.EstagioRepository;
import br.com.ges.api.repository.RelatorioAtividadeRepository;
import br.com.ges.api.wrapper.RelatoriosAlunoWrapper;

@Service
public class RelatorioAtividadeService {

	private static final String RANE = "Relatório de Atividade não encontrado.";

	@Autowired
	private RelatorioAtividadeRepository relatorioAtividadeRepository;

	@Autowired
	private EstagioRepository estagioRepository;

	public RelatorioAtividade exibir(Long id) {
		return relatorioAtividadeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RANE));
	}

	public List<RelatorioAtividade> buscaRelatoriosAtividadeDoAluno(Aluno aluno) {

		Estagio estagioAluno = estagioRepository.findByAluno(aluno);
		List<RelatorioAtividade> relatoriosAluno = relatorioAtividadeRepository
				.findByEstagioRelatorioAtividadeId(estagioAluno.getId());

		return relatoriosAluno;
	}

	public List<RelatorioAtividade> listarTodos() {
		return (List<RelatorioAtividade>) relatorioAtividadeRepository.findAll();
	}

	public String deletar(Long id) throws BusinessException {
		relatorioAtividadeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RANE));

		try {

			relatorioAtividadeRepository.deleteById(id);
			return "Relatório de Atividade deletado com sucesso.";

		} catch (Exception e) {
			throw new BusinessException("Erro ao deletar o Relatório de Atividade.");
		}

	}

	public String salvar(RelatoriosAlunoWrapper relatoriosAluno) {

		Estagio estagioAluno = estagioRepository.findByAluno(relatoriosAluno.getAluno());
		RelatorioAtividade relatorioAtividade = new RelatorioAtividade();

		relatorioAtividade = relatoriosAluno.getRelatorioAtividade();
		relatorioAtividade.setEstagioRelatorioAtividade(estagioAluno);
		relatorioAtividadeRepository.save(relatorioAtividade);

		return "Relatório de atividade salvo com sucesso.";

	}

	public String atualizar(Long id, RelatorioAtividade relatorioAtividade) throws BusinessException {

		relatorioAtividadeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RANE));
		
		try {
			relatorioAtividade.setId(id);
			relatorioAtividadeRepository.save(relatorioAtividade);
			return "Relatório de Atividade alterado com sucesso.";

		} catch (Exception e) {
			throw new BusinessException("Erro ao alterar o Relatório de Atividade.");
		}


	}

}
