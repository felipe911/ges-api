package br.com.ges.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ges.api.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

	
}
