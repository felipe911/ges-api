package br.com.ges.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ges.api.model.Aluno;
import br.com.ges.api.model.Estagio;
import br.com.ges.api.model.RelatorioAtividade;
import br.com.ges.api.model.RelatorioFinal;
import br.com.ges.api.model.RelatorioParcial;
import br.com.ges.api.repository.AlunoRepository;
import br.com.ges.api.repository.EstagioRepository;
import br.com.ges.api.repository.RelatorioAtividadeRepository;
import br.com.ges.api.repository.RelatorioFinalRepository;
import br.com.ges.api.repository.RelatorioParcialRepository;
import br.com.ges.api.wrapper.EntregaRelatorioWrapper;

@Service
public class EntregaRelatorioService {
	
	@Autowired
	private EstagioRepository estagioRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private RelatorioAtividadeRepository relatorioAtividadeRepository;
	
	@Autowired
	private RelatorioParcialRepository relatorioParcialRepository;
	
	@Autowired
	private RelatorioFinalRepository relatorioFinalRepository;
	
	public List<EntregaRelatorioWrapper> buscaDadosEstagiarios() {
		
		EntregaRelatorioWrapper entregaRelatorio = new EntregaRelatorioWrapper();
		List<EntregaRelatorioWrapper> listaEntregaRelatorios = new ArrayList<>();
		List<Estagio> todosEstagios = estagioRepository.findAll();
		
		for (Estagio estagio : todosEstagios) {
			
			List<RelatorioAtividade> listaRelatorioAtividades = relatorioAtividadeRepository.findByEstagioRelatorioAtividadeId(estagio.getId());
			List<RelatorioParcial> listaRelatorioParcial = relatorioParcialRepository.findByEstagioRelatorioParcialId(estagio.getId());
			RelatorioFinal relatorioFinal = relatorioFinalRepository.findByEstagioRelatorioFinalId(estagio.getId());
			
			entregaRelatorio.setEstagio(estagio);
			entregaRelatorio.setRelatorioAtividade(listaRelatorioAtividades);
			entregaRelatorio.setRelatorioParcial(listaRelatorioParcial);
			entregaRelatorio.setRelatorioFinal(relatorioFinal);
			
			listaEntregaRelatorios.add(entregaRelatorio);
			entregaRelatorio = new EntregaRelatorioWrapper();
		}
		
		return listaEntregaRelatorios;
	}

	public EntregaRelatorioWrapper buscarPorAlunoId(Long id) {
		
		EntregaRelatorioWrapper entregaRelatorio = new EntregaRelatorioWrapper();
	
		Optional<Aluno> aluno = alunoRepository.findById(id);
		
		Estagio estagioDoAluno = estagioRepository.findByAluno(aluno.get());
		List<RelatorioParcial> listaRelatorioParcial = relatorioParcialRepository.findByEstagioRelatorioParcialId(estagioDoAluno.getId());
		RelatorioFinal relatoriFinal = relatorioFinalRepository.findByEstagioRelatorioFinalId(estagioDoAluno.getId());
		
		entregaRelatorio.setEstagio(estagioDoAluno);
		entregaRelatorio.setRelatorioParcial(listaRelatorioParcial);
		entregaRelatorio.setRelatorioFinal(relatoriFinal);
		
		return entregaRelatorio;
	}
}
