package br.com.ges.api.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ges.api.enums.StatusEstagio;
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

	public ResponseEntity<RelatorioAtividade> salvar(RelatoriosAlunoWrapper relatoriosAluno, HttpServletResponse response) throws BusinessException {

		Estagio estagioAluno = estagioRepository.findByAluno(relatoriosAluno.getAluno());
		RelatorioAtividade relatorioAtividade = new RelatorioAtividade();

		relatorioAtividade = relatoriosAluno.getRelatorioAtividade();
		
		if(verificaQtdHorasAtividade(relatorioAtividade.getQtdHoras(), relatoriosAluno.getAluno())) {
			relatorioAtividade.setEstagioRelatorioAtividade(estagioAluno);
			RelatorioAtividade relatorioAtividadeSalvo = relatorioAtividadeRepository.save(relatorioAtividade);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
					.buildAndExpand(relatorioAtividadeSalvo.getId()).toUri();
			response.setHeader("Location", uri.toASCIIString());
			return ResponseEntity.created(uri).body(relatorioAtividadeSalvo);
			
		} else {
			
			throw new BusinessException("Não foi possível adicionar este Relatório de Atividade, a quantidade de horas totais não poderá ultrapassar 240 Horas.");
		}
		
		

	}

	private boolean verificaQtdHorasAtividade(int qtdHoras, Aluno aluno) {
		
		Estagio estagioAluno = estagioRepository.findByAluno(aluno);
		List<RelatorioAtividade> relatoriosAluno = relatorioAtividadeRepository
				.findByEstagioRelatorioAtividadeId(estagioAluno.getId());
		
		int totalHorasAtuais = 0;
		
		for (RelatorioAtividade relatorioAtividade : relatoriosAluno) {
			totalHorasAtuais+= relatorioAtividade.getQtdHoras();
		}
		
		if(totalHorasAtuais + qtdHoras > 240)
			return false;
		
		return true;
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
	
	public List<RelatorioAtividade> buscaRelatoriosAtividadePorAlunoId(Long id){
		
		Estagio estagioAtivo = new Estagio();
		List<Estagio> listaEstagioPorIdAluno = estagioRepository.findByAlunoId(id);
		List<RelatorioAtividade> relatoriosAtividadeDoAluno = new ArrayList<>();
		
		for (Estagio estagio : listaEstagioPorIdAluno) {
			if(estagio.getStatus() == StatusEstagio.ATIVO)
				estagioAtivo = estagio;
		}
		
		relatoriosAtividadeDoAluno = relatorioAtividadeRepository.findByEstagioRelatorioAtividadeId(estagioAtivo.getId());

		return relatoriosAtividadeDoAluno;
	}

}
