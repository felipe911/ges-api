package br.com.ges.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ges.api.model.RelatorioFinal;

public interface RelatorioFinalRepository extends JpaRepository<RelatorioFinal, Long>{

	List<RelatorioFinal> findByEstagioRelatorioFinalId(Long id);

}
