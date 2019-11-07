package br.com.ges.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	private String mensagem = null;

	@Autowired
	private ContratoRepository contratoRepository;

	@Autowired
	private EstagioRepository estagioRepository;

	
	public Contrato exibir(Long id) {
		return contratoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CNE));
	}
	
	
	public List<Contrato> listar() {
		return (List<Contrato>) contratoRepository.findAll();
	}
	
	
	public String deletar(Long id) throws BusinessException {
		Contrato contratoEncontrado = contratoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(CNE));
		
		if (possuiEstagioAtivoNoContrato(contratoEncontrado)) {
			return this.mensagem;
			
		} else {
			try {
				contratoRepository.deleteById(id);
				this.mensagem = "Contrato deletado com sucesso.";
			} catch (Exception e) {
				// TODO: Criar Exception correta
			}
		}
		return this.mensagem;
	}
	
	
	public String associarContrato(AssociarContratoWrapper associarContrato) {

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
			
		} catch (Exception e) {
			// TODO: Criar exception de erro ao associar Contrato
		}
		
		
		return this.mensagem = "Contrato Associado com sucesso.";
	}


	private boolean possuiEstagioAtivoNoContrato(Contrato contratoEncontrado) {

		try {
			
			Estagio estagio = estagioRepository.findByContratoId(contratoEncontrado.getId());
			
			if(estagio.getStatus() == StatusEstagio.ATIVO) {
				this.mensagem = "Não é possivel deletar um contrato com estágio ativo";
				return true;
			}
			
		} catch (Exception e) {
			// TODO: Criar Exception Erro ao encontrar Contrato.
		}
		
		return false;
	}
	
	
	public String atualizar(Long id, Contrato contrato) {
		
		contratoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CNE));
		String dtProrrogado = null;
		
		try {
			
			dtProrrogado = Util.localDateParaString(contrato.getProrrogadoAte());
			contratoRepository.save(contrato);
			
		} catch (Exception e) {
			// TODO: Criar exception de erro ao atualizar Contrato
		}
		
		return this.mensagem = "Contrato prorrogado até " + dtProrrogado;
	}
}
