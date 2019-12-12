package br.com.ges.api.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ges.api.exception.BusinessException;
import br.com.ges.api.model.Aluno;
import br.com.ges.api.model.Empresa;
import br.com.ges.api.repository.EmpresaRepository;

@Service
public class EmpresaService {

	private static final String ENE = "Empresa não encontrada.";
	private static final String EMPRESA_JA_REGISTRADA = "Já existe uma empresa registrado com este CNPJ.";

	@Autowired
	private EmpresaRepository empresaRepository;

	
	public Empresa exibir(Long id) {
		return empresaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ENE));
	}

	
	public List<Empresa> listar() {
		return empresaRepository.findAll();
	}
	
	public String deletar(Long id) throws BusinessException {
		empresaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ENE));

		try {
			
			empresaRepository.deleteById(id);
			return "Empresa deletada com sucesso.";
			
		} catch (Exception e) {
			throw new BusinessException("Erro ao deletar a empresa.");
		}

	}

	
	public ResponseEntity<Empresa> salvar(Empresa empresa, HttpServletResponse response) throws BusinessException {

		try {
			
			verificaDuplicidadeCnpj(empresa, empresa.getId());
			Empresa empresaSalva = empresaRepository.save(empresa);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
					.buildAndExpand(empresaSalva.getId()).toUri();
			response.setHeader("Location", uri.toASCIIString());
			return ResponseEntity.created(uri).body(empresaSalva);
			
			
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	
	public String atualizar(Long id, Empresa empresa) throws BusinessException {

		empresaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ENE));
		
		try {
			verificaDuplicidadeCnpj(empresa, id);
			empresa.setId(id);
			empresaRepository.save(empresa);
			return "Empresa atualizada com sucesso.";

		} catch (Exception e) {
			throw new BusinessException(EMPRESA_JA_REGISTRADA);
		}

	}
	
	
	public void verificaDuplicidadeCnpj(Empresa empresa, Long id) throws BusinessException {

		List<Empresa> empresaCnpj = empresaRepository.findByCnpj(empresa.getCnpj());

		if (!empresaCnpj.isEmpty()) {

			if (id != null) {

				if (empresaCnpj.get(0).getId() != id)
					throw new BusinessException(EMPRESA_JA_REGISTRADA);

			} else {
				throw new BusinessException(EMPRESA_JA_REGISTRADA);
			}

		}
	}


	public Empresa buscaEmpresaPorRazaoSocial(Empresa empresa) {
		
		Empresa empresaEncontrada = empresaRepository.findByRazaoSocial(empresa.getRazaoSocial());
		
		return empresaEncontrada;
	}
}
