package br.com.ges.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ges.api.enums.StatusEstagio;
import br.com.ges.api.exception.BusinessException;
import br.com.ges.api.model.Contrato;
import br.com.ges.api.model.Estagio;
import br.com.ges.api.repository.ContratoRepository;
import br.com.ges.api.repository.EstagioRepository;
import br.com.ges.api.util.Util;
import br.com.ges.api.wrapper.AssociarContratoWrapper;

@Service
public class ContratoService {

	private static final String CNE = "Contrato não encontrado.";

	@Autowired
	private ContratoRepository contratoRepository;

	@Autowired
	private EstagioRepository estagioRepository;

	
	public Contrato exibir(Long id) {
		return contratoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CNE));
	}
	
	
	public Page<Contrato> listar(Pageable paginacao) {
		return (Page<Contrato>) contratoRepository.findAll(paginacao);
	}
	
	
	public String deletar(Long id) throws BusinessException {
		Contrato contratoEncontrado = contratoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(CNE));
		
		if (possuiEstagioAtivoNoContrato(contratoEncontrado)) {
			return "Não é possivel deletar um contrato com estágio ativo.";
			
		} else {
			try {
				contratoRepository.deleteById(id);
				return "Contrato deletado com sucesso.";
			} catch (Exception e) {
				throw new BusinessException("Erro ao deletar o Contrato.");
			}
		}
	}
	
	
	public String associarContrato(AssociarContratoWrapper associarContrato) throws BusinessException {

		Contrato contrato = new Contrato();
		Estagio estagio = new Estagio();

		try {
			
			contrato = associarContrato.getContrato();
			contrato.setEmpresa(associarContrato.getEmpresa());
			
			Contrato contratoSalvo = contratoRepository.save(contrato);
			estagio.setContrato(contratoSalvo);
			estagio.setAluno(associarContrato.getAluno());
			estagio.setStatus(StatusEstagio.ATIVO);
			estagioRepository.save(estagio);
			
			return "Contrato Associado com sucesso.";
			
		} catch (Exception e) {
			throw new BusinessException("Erro ao Associar Contrato.");
		}
		
		
	}


	private boolean possuiEstagioAtivoNoContrato(Contrato contratoEncontrado) {

		try {
			
			Estagio estagio = estagioRepository.findByContratoId(contratoEncontrado.getId());
			
			if(estagio.getStatus() == StatusEstagio.ATIVO) {
				return true;
			}
			
		} catch (Exception e) {
			throw new ResourceNotFoundException();
		}
		
		return false;
	}
	
	
	public String atualizar(Long id, Contrato contrato) throws BusinessException {
		
		contratoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CNE));
		String dtProrrogado = null;
		
		try {
			
			dtProrrogado = Util.localDateParaString(contrato.getProrrogadoAte());
			contratoRepository.save(contrato);
			return "Contrato prorrogado até: " + dtProrrogado;
			
		} catch (Exception e) {
			throw new BusinessException("Erro ao atualizar o Contrato.");
		}
		
	}
}
