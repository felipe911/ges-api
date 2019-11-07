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
	private static final String EMPRESA_JA_REGISTRADA = "Já existe uma empresa registrado com este CNPJ.";
	private String mensagem = null;

	@Autowired
	private EmpresaRepository empresaRepository;

	
	public Empresa exibir(Long id) {
		return empresaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ENE));
	}

	
	public List<Empresa> listar() {
		return (List<Empresa>) empresaRepository.findAll();
	}
	
	
	public String deletar(Long id) throws BusinessException {
		empresaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ENE));

		try {
			
			empresaRepository.deleteById(id);
			
		} catch (Exception e) {
			// TODO: Criar Exception correta
		}

		return this.mensagem = "Empresa deletada com sucesso.";
	}

	
	public String salvar(Empresa empresa) throws BusinessException {

		try {
			
			verificaDuplicidadeCnpj(empresa, empresa.getId());
			empresaRepository.save(empresa);
			
		} catch (Exception e) {
			// TODO: Verificar mensagem de erro retornada para o usuário.
		}
		
		return this.mensagem = "Empresa registrada com sucesso.";
	}

	
	public String atualizar(Long id, Empresa empresa) throws BusinessException {

		empresaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ENE));
		
		try {
			verificaDuplicidadeCnpj(empresa, id);
			empresa.setId(id);
			empresaRepository.save(empresa);

		} catch (Exception e) {
			// TODO: Criar Exception correta
		}

		return this.mensagem = "Empresa atualizada com sucesso.";
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
}
