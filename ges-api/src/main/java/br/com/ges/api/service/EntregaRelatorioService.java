package br.com.ges.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ges.api.model.Estagio;
import br.com.ges.api.model.RelatorioAtividade;
import br.com.ges.api.model.RelatorioFinal;
import br.com.ges.api.model.RelatorioParcial;
import br.com.ges.api.repository.AlunoRepository;
import br.com.ges.api.repository.ContratoRepository;
import br.com.ges.api.repository.EmpresaRepository;
import br.com.ges.api.repository.EstagioRepository;
import br.com.ges.api.repository.RelatorioAtividadeRepository;
import br.com.ges.api.repository.RelatorioFinalRepository;
import br.com.ges.api.repository.RelatorioParcialRepository;
import br.com.ges.api.wrapper.EntregaRelatorioWrapper;

@Service
public class EntregaRelatorioService {


	@Autowired
	private ContratoRepository contratoRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private EstagioRepository estagioRepository;

	@Autowired
	private EmpresaRepository empresaRepository;
	
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
			List<RelatorioFinal> listaRelatorioFinal = relatorioFinalRepository.findByEstagioRelatorioFinalId(estagio.getId());
			
			entregaRelatorio.setEstagio(estagio);
			entregaRelatorio.setRelatorioAtividade(listaRelatorioAtividades);
			entregaRelatorio.setRelatorioParcial(listaRelatorioParcial);
			entregaRelatorio.setRelatorioFinal(listaRelatorioFinal);
			
			listaEntregaRelatorios.add(entregaRelatorio);
			entregaRelatorio = new EntregaRelatorioWrapper();
		}
		
		return listaEntregaRelatorios;
	}
}
