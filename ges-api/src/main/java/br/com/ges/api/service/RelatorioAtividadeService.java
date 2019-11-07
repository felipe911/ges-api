package br.com.ges.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ges.api.model.Aluno;
import br.com.ges.api.model.Estagio;
import br.com.ges.api.model.EstagioRelatorio;
import br.com.ges.api.model.RelatorioAtividade;
import br.com.ges.api.repository.EstagioRelatorioRepository;
import br.com.ges.api.repository.EstagioRepository;
import br.com.ges.api.repository.RelatorioAtividadeRepository;
import br.com.ges.api.wrapper.RelatoriosAlunoWrapper;

@Service
public class RelatorioAtividadeService {

	private static final String RANE = "Relatório de Atividade não encontrado.";
	private String mensagem = null;

	@Autowired
	private RelatorioAtividadeRepository relatorioAtividadeRepository;
	
	@Autowired
	private EstagioRepository estagioRepository;
	
	@Autowired
	private EstagioRelatorioRepository estagioRelatorioRepository;
	
	public RelatorioAtividade exibir(Long id) {
		return relatorioAtividadeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RANE));
	}
	
//	public List<RelatorioAtividade> buscaRelatoriosAtividadeDoAluno(Aluno aluno){
//		
//		List<RelatorioAtividade> listaRelatorioAtividadeDoAluno = relatorioAtividadeRepository.findByAluno(aluno);
//		
//		return listaRelatorioAtividadeDoAluno;
//	}
	
	
//	public String salvar(RelatoriosAlunoWrapper relatoriosAluno) {
//		
//		Estagio estagioAluno = estagioRepository.findByAluno(relatoriosAluno.getAluno());
//		RelatorioAtividade relatorioAtividade = relatoriosAluno.getRelatorioAtividade();
//		
//		try {
//			
//			EstagioRelatorio estagioRelatorio = estagioRelatorioRepository.save(new EstagioRelatorio());
//			
//			
//			
////			relatorioAtividade.get
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//		
//		
//		return this.mensagem;
//		
//	}
}
