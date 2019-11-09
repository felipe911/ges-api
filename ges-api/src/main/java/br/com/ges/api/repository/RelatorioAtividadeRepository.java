package br.com.ges.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ges.api.model.RelatorioAtividade;

public interface RelatorioAtividadeRepository extends JpaRepository<RelatorioAtividade, Long>{

	List<RelatorioAtividade> findByEstagioRelatorioAtividadeId(Long id);

//	RelatorioAtividade findByRelatorioAtividade(RelatorioAtividade relatorioAtividade);
	
}
