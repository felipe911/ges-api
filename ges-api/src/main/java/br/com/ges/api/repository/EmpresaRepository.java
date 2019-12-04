package br.com.ges.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ges.api.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

	List<Empresa> findByCnpj(String cnpj);
	
	Empresa findByRazaoSocial(String razaoSocial);
}
