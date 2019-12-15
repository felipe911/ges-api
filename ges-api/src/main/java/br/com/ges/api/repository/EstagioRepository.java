package br.com.ges.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ges.api.model.Aluno;
import br.com.ges.api.model.Estagio;

public interface EstagioRepository extends JpaRepository<Estagio, Long>{

	Estagio findByContratoId(Long id);
	
	Estagio findByAluno(Aluno aluno);

//	Estagio findByAlunoId(Long idAluno);
	
	List<Estagio> findByContratoEmpresaId(Long id);
	
	List<Estagio> findByAlunoId(Long id);
}
