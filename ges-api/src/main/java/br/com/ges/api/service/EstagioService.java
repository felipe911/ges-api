package br.com.ges.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ges.api.exception.BusinessException;
import br.com.ges.api.model.Estagio;
import br.com.ges.api.model.RelatorioAtividade;
import br.com.ges.api.repository.EstagioRepository;
import br.com.ges.api.repository.RelatorioAtividadeRepository;
import br.com.ges.api.wrapper.EstagiosQtdHorasWrapper;

@Service
public class EstagioService {

	@Autowired
	private EstagioRepository estagioRepository;
	
	@Autowired
	private RelatorioAtividadeRepository relatorioAtividadeRepository;
	
	
	public Estagio buscaEstagioPorContratoId(Long id) {
		
		Estagio estagio = estagioRepository.findByContratoId(id);
		
		return estagio;
	}


	public Estagio exibirEstagioPorAluno(Long id) throws BusinessException {
		
		List<Estagio> estagio = estagioRepository.findByAlunoId(id);
		
		if(!estagio.isEmpty()) {
			return estagio.iterator().next();
		} else {
			throw new BusinessException("Aluno não Encontrado.");
		}
	}
	
	public EstagiosQtdHorasWrapper exibirEstagiosPorAluno(Long id) throws BusinessException {
		
		EstagiosQtdHorasWrapper estagiosQtdHoras = new EstagiosQtdHorasWrapper();
		estagiosQtdHoras.setEstagios(new ArrayList<>());
		estagiosQtdHoras.setQtdHoras(new ArrayList<>());
		
		Integer qtdHoras = 0;
		
		List<Estagio> estagios = estagioRepository.findByAlunoId(id);
		
		for (Estagio estagio : estagios) {
			
			List<RelatorioAtividade> relatoriosAluno = relatorioAtividadeRepository.findByEstagioRelatorioAtividadeId(estagio.getId());
			
			for (RelatorioAtividade relatorioAtividadeF : relatoriosAluno) {
				qtdHoras+= relatorioAtividadeF.getQtdHoras();
				
			}
			estagiosQtdHoras.getQtdHoras().add(qtdHoras);
			qtdHoras = 0;
			
			estagiosQtdHoras.getEstagios().add(estagio);
		}
		
		
		if(!estagiosQtdHoras.getEstagios().isEmpty()) {
			
			return estagiosQtdHoras;
			
		} else {
			throw new BusinessException("Estágio não Encontrado.");
		}
	}
}
