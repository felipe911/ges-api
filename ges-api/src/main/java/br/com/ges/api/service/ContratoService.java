package br.com.ges.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ges.api.enums.StatusEnum;
import br.com.ges.api.exception.BusinessException;
import br.com.ges.api.model.Aluno;
import br.com.ges.api.model.Contrato;
import br.com.ges.api.model.Empresa;
import br.com.ges.api.model.Estagio;
import br.com.ges.api.repository.ContratoRepository;
import br.com.ges.api.repository.EstagioRepository;
import br.com.ges.api.wrapper.AssociarContratoWrapper;

@Service
public class ContratoService {

	private static final String CNE = "Contrato não encontrado.";
	private String mensagem = null;
	
	@Autowired
	private ContratoRepository contratoRepository;
	
	@Autowired
	private EstagioRepository estagioRepository;
	
	public String associarContrato(AssociarContratoWrapper associarContrato) {
		
		Contrato contrato = new Contrato();
		Estagio estagio = new Estagio();
		
		contrato = associarContrato.getContrato();
		contrato.setEmpresa(associarContrato.getEmpresa());
		estagio.setIdAluno(associarContrato.getAluno().getId());
//		estagio.setIdContrato(associarContrato.get);
//		estagio.setAluno(associarContrato.getAluno());
//		estagio.setContrato(contrato);
		
		
		Contrato contratoSalvo = contratoRepository.save(contrato);
		Long id = contratoSalvo.getId();
		estagio.setIdContrato(id);
		estagio.setStatus(StatusEnum.ATIVO);
		estagioRepository.save(estagio);
		
		
		return this.mensagem = "Contrato Associado com sucesso.";
		
	}
	
	public Contrato exibir(Long id) {
		return contratoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CNE));
	}
	
	
	public String salvar(Contrato contrato) throws BusinessException {


		// TODO: Verificar mensagem de erro retornada para o usuário.

		contratoRepository.save(contrato);
		this.mensagem = "Contrato registrado com sucesso.";

		return this.mensagem;
	}
	
}
