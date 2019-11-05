package br.com.ges.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ges.api.exception.BusinessException;
import br.com.ges.api.model.Empresa;
import br.com.ges.api.repository.EmpresaRepository;

@Service
public class EmpresaService {

	private static final String ENE = "Empresa não encontrada.";
	private String mensagem = null;

	@Autowired
	private EmpresaRepository empresaRepository;

	public Empresa exibir(Long id) {
		return empresaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ENE));
	}

	public List<Empresa> listar() {
		return (List<Empresa>) empresaRepository.findAll();
	}

	public String salvar(Empresa empresa) throws BusinessException {

		verificaDuplicidadeCnpj(empresa);

		// TODO: Verificar mensagem de erro retornada para o usuário.

		empresaRepository.save(empresa);
		this.mensagem = "Empresa registrada com sucesso.";

		return this.mensagem;
	}

	public String atualizar(Long id, Empresa empresa) throws BusinessException {

		empresaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ENE));

		verificaDuplicidadeCnpj(empresa);
		
		try {
			empresa.setId(id);
			empresaRepository.save(empresa);
			this.mensagem = "Empresa atualizada com sucesso.";

		} catch (Exception e) {
			// TODO: Criar Exception correta
		}

		return this.mensagem;
	}
	
	
	public String deletar(Long id) throws BusinessException {
		empresaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ENE));

		try {
			empresaRepository.deleteById(id);
			this.mensagem = "Empresa deletada com sucesso.";
		} catch (Exception e) {
			// TODO: Criar Exception correta
		}

		return this.mensagem;
	}

	public void verificaDuplicidadeCnpj(Empresa empresa) throws BusinessException {

		List<Empresa> empresaCnpj = empresaRepository.findByCnpj(empresa.getCnpj());

		if (!empresaCnpj.isEmpty()) {
			throw new BusinessException("Já existe uma empresa registrado com este CNPJ.");
		}
	}
}
