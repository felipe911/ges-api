package br.com.ges.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ges.api.model.TipoAtividadeEstagiario;

public interface TipoAtividadeEstagiarioRepository extends JpaRepository<TipoAtividadeEstagiario, Long>{

	TipoAtividadeEstagiario findByRelatorioFinalId(Long id);

}
