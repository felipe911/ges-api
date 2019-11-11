package br.com.ges.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ges.api.exception.BusinessException;
import br.com.ges.api.model.Aluno;
import br.com.ges.api.model.Contrato;
import br.com.ges.api.model.Estagio;
import br.com.ges.api.model.RelatorioFinal;
import br.com.ges.api.model.RelatorioParcial;
import br.com.ges.api.repository.EstagioRepository;
import br.com.ges.api.repository.RelatorioParcialRepository;
import br.com.ges.api.wrapper.RelatoriosAlunoWrapper;

@Service
public class RelatorioParcialService {

	@Autowired
	private RelatorioParcialRepository relatorioParcialRepository;

	@Autowired
	private EstagioRepository estagioRepository;

	private static final String RPNE = "Relatório Parcial não encontrado.";
	private String mensagem = null;

	/**
	 * 
	 * Busca Relatório Parcial a partir de seu ID
	 * 
	 */
	public RelatorioParcial exibir(Long id) {
		return relatorioParcialRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RPNE));
	}
	
	/**
	 * 
	 * Lista todos Relatórios Parciais de um Aluno
	 * 
	 */
	public List<RelatorioParcial> buscaRelatorioParcialDoAluno(Aluno aluno) {

		Estagio estagioAluno = estagioRepository.findByAluno(aluno);
		List<RelatorioParcial> relatoriosAluno = relatorioParcialRepository
				.findByEstagioRelatorioParcialId(estagioAluno.getId());

		return relatoriosAluno;
	}
	
	/**
	 * 
	 * Lista todos Relatórios Parciais do sistema
	 * 
	 */
	public List<RelatorioParcial> listarTodos() {
		return (List<RelatorioParcial>) relatorioParcialRepository.findAll();
	}
	
	
	/**
	 * 
	 * Deleta o registro de Relatório Final a partir de seu ID
	 * 
	 */
	public String deletar(Long id) {
		RelatorioParcial relatorioParcial = relatorioParcialRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(RPNE));

		try {
			relatorioParcialRepository.delete(relatorioParcial);

		} catch (Exception e) {
			// TODO: Criar Exception correta
		}

		return this.mensagem = "Relatório Parcial deletado com sucesso.";
	}

	/**
	 * 
	 * Salva um novo Relatório Parcial
	 * 
	 */
	public String salvar(RelatoriosAlunoWrapper relatoriosAluno) throws BusinessException {

		Estagio estagioAluno = estagioRepository.findByAluno(relatoriosAluno.getAluno());

		verificaDatasRelatoriosParciais(buscaRelatorioParcialDoAluno(relatoriosAluno.getAluno()),
				relatoriosAluno.getRelatorioParcial());

		if (verificaPeriodosDoContrato(relatoriosAluno.getRelatorioParcial(), estagioAluno.getContrato())) {

			throw new BusinessException(
					"As datas de período do Relatório Parcial não podem conflitar com a Data de Início e Data Fim do Contrato de Estágio.");

		} else {

			RelatorioParcial relatorioParcial = new RelatorioParcial();
			relatorioParcial = relatoriosAluno.getRelatorioParcial();
			relatorioParcial.setEstagioRelatorioParcial(estagioAluno);
			relatorioParcialRepository.save(relatorioParcial);
		}

		return this.mensagem = "Relatório Parcial salvo com sucesso.";
	}

	private void verificaDatasRelatoriosParciais(List<RelatorioParcial> buscaRelatorioParcialDoAluno,
			RelatorioParcial relatorioParcialAtual) throws BusinessException {

		boolean conflito = false;

		// Relatório Parcial Atual <=
		for (RelatorioParcial relatorioParcial : buscaRelatorioParcialDoAluno) {
			if(relatorioParcialAtual.getPeriodoDe().isBefore(relatorioParcial.getPeriodoAte())
			|| relatorioParcialAtual.getPeriodoDe().isEqual(relatorioParcial.getPeriodoAte()))
				conflito = true;
		}
		
		//TODO Pode ser necessário outras verificações

		if(conflito)
			throw new BusinessException("As datas deste Relatório Parcial conflitam com outro Relatório Parcial já registrado.");
	}

	private boolean verificaPeriodosDoContrato(RelatorioParcial relatorioParcial, Contrato contrato) {

		if (relatorioParcial.getPeriodoDe().isBefore(contrato.getDataInicio())
				|| relatorioParcial.getPeriodoAte().isBefore(contrato.getDataInicio()))
			return true;

		if (relatorioParcial.getPeriodoDe().isAfter(contrato.getDataFim())
				|| relatorioParcial.getPeriodoAte().isAfter(contrato.getDataFim()))
			return true;

		return false;
	}

}
