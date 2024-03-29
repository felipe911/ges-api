package br.com.ges.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ges.api.model.RelatorioParcial;

public interface RelatorioParcialRepository extends JpaRepository<RelatorioParcial, Long>{

	List<RelatorioParcial> findByEstagioRelatorioParcialId(Long id);

}
