package br.com.ges.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ges.api.model.Aluno;
import br.com.ges.api.repository.AlunoRepository;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	public Aluno exibir(Long id) {
		return alunoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));
	}
}
