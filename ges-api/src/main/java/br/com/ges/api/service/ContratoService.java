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
import br.com.ges.api.model.Contrato;
import br.com.ges.api.model.Estagio;
import br.com.ges.api.repository.AlunoRepository;
import br.com.ges.api.repository.ContratoRepository;
import br.com.ges.api.repository.EmpresaRepository;
import br.com.ges.api.repository.EstagioRepository;
import br.com.ges.api.util.Util;
import br.com.ges.api.wrapper.AssociarContratoWrapper;
import br.com.ges.api.wrapper.ContratoConsultaWrapper;

@Service
public class ContratoService {

	private static final String CNE = "Contrato não encontrado.";

	@Autowired
	private ContratoRepository contratoRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private EstagioRepository estagioRepository;

	@Autowired
	private EmpresaRepository empresaRepository;
	
	public Contrato exibir(Long id) {
		return contratoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CNE));
	}
	
	
	public List<Contrato> listar() {
		return contratoRepository.findAll();
	}
	
	
	public void deletar(Long id) throws BusinessException {
		Contrato contratoEncontrado = contratoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(CNE));
		
		if (possuiEstagioAtivoNoContrato(contratoEncontrado)) {
			throw new BusinessException("Não é possivel deletar um contrato com estágio ativo.");
			
		} else {
			try {
				contratoRepository.deleteById(id);

			} catch (Exception e) {
				throw new BusinessException("Erro ao deletar o Contrato.");
			}
		}
	}
	
	
	public ResponseEntity<Contrato> associarContrato(AssociarContratoWrapper associarContrato, HttpServletResponse response) throws BusinessException {

		Contrato contrato = new Contrato();
		Estagio estagio = new Estagio();

		try {
			
			verificaAlunoComContrato(associarContrato.getAluno());
			
			contrato = associarContrato.getContrato();
			contrato.setEmpresa(associarContrato.getEmpresa());
			contrato.getEmpresa().setQtdEstagiariosAtivos(contrato.getEmpresa().getQtdEstagiariosAtivos() + 1);
			empresaRepository.save(contrato.getEmpresa());
			
			Contrato contratoSalvo = contratoRepository.save(contrato);
			
			estagio.setContrato(contratoSalvo);
			estagio.setAluno(associarContrato.getAluno());
			estagio.setStatus(StatusEstagio.ATIVO);
			estagioRepository.save(estagio);
			
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
					.buildAndExpand(contratoSalvo.getId()).toUri();
			response.setHeader("Location", uri.toASCIIString());
			
			return ResponseEntity.created(uri).body(contratoSalvo);
			
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	private void verificaAlunoComContrato(Aluno aluno) throws BusinessException {

		Estagio estagioEncontrado = estagioRepository.findByAluno(aluno);
		
		if(estagioEncontrado != null)
			throw new BusinessException("Este Aluno já possui Contrato associado.");
		
	}
	
	public List<ContratoConsultaWrapper> listarContratosConsulta(){
		
		List<Estagio> todosEstagios = estagioRepository.findAll();
		List<Aluno> todosAlunos = alunoRepository.findAll();
		List<Contrato> todosContratos = contratoRepository.findAll();
	
		ContratoConsultaWrapper consultaContrato = new ContratoConsultaWrapper();
		List<ContratoConsultaWrapper> listaConsultaContrato = new ArrayList<>();
		
		for(int x = 0; x < todosEstagios.size(); x++) {
			
			consultaContrato.setStatus(verificaStatus(todosEstagios.get(x).getStatus()));
			
			for(int y = 0; y < todosAlunos.size(); y++) {
				
				//Adiciona dados do Aluno
				if(todosEstagios.get(x).getAluno().getId() == todosAlunos.get(y).getId()) {
					consultaContrato.setNomeAluno(todosAlunos.get(y).getNome());
					consultaContrato.setCurso(todosAlunos.get(y).getCurso());
				}
			}
			
			for(int j = 0; j < todosContratos.size(); j++) {
				
				if(todosEstagios.get(x).getContrato().getId() == todosContratos.get(j).getId()) {
					consultaContrato.setContratoId(todosContratos.get(j).getId());
					consultaContrato.setEmpresaAssociada(todosContratos.get(j).getEmpresa().getRazaoSocial());
					consultaContrato.setDataInicio(todosContratos.get(j).getDataInicio());
					consultaContrato.setDataFim(todosContratos.get(j).getDataFim());
					consultaContrato.setAgenteIntegracao(todosContratos.get(j).getAgenteIntegracao());
					consultaContrato.setSupervisor(todosContratos.get(j).getSupervisorEstagio());
				}
			}
			
			listaConsultaContrato.add(consultaContrato);
			consultaContrato = new ContratoConsultaWrapper();
		}
		
		return listaConsultaContrato;
	}


	private String verificaStatus(StatusEstagio status) {
		if(status == StatusEstagio.ATIVO) {
			return "Ativo";
		}
		
		if(status == StatusEstagio.CANCELADO) {
			return "Cancelado";
		}
		
		if(status == StatusEstagio.FINALIZADO) {
			return "Finalizado";
		}
		
		return "";
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


	public Contrato buscaContratoPorAluno(Aluno aluno) throws BusinessException {
		
		try {
			
			Estagio estagioEncontrado = estagioRepository.findByAluno(aluno);
			
			if(estagioEncontrado != null)
				return estagioEncontrado.getContrato();
			
		} catch (Exception e) {
			throw new BusinessException(CNE);
		}
		return null;
	}
}
