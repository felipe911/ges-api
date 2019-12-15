package br.com.ges.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ges.api.model.Contrato;

public interface ContratoRepository extends JpaRepository<Contrato, Long>{
	
	Contrato findByEmpresaId(Long id);

}
