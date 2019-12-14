package br.com.ges.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ges.api.model.Estagio;
import br.com.ges.api.repository.EstagioRepository;

@Service
public class EstagioService {

	@Autowired
	private EstagioRepository estagioRepository;
	
	
	public Estagio buscaEstagioPorContratoId(Long id) {
		
		Estagio estagio = estagioRepository.findByContratoId(id);
		
		return estagio;
	}
}
