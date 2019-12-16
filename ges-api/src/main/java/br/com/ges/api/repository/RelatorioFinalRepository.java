package br.com.ges.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ges.api.model.RelatorioFinal;

public interface RelatorioFinalRepository extends JpaRepository<RelatorioFinal, Long>{

	RelatorioFinal findByEstagioRelatorioFinalId(Long id);

	RelatorioFinal findByEstagioRelatorioFinalIdAndRelatorioEntregue(Long id, boolean b);

}
