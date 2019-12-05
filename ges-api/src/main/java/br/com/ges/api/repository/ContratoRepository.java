package br.com.ges.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ges.api.model.Aluno;
import br.com.ges.api.model.Contrato;

public interface ContratoRepository extends JpaRepository<Contrato, Long>{

}
