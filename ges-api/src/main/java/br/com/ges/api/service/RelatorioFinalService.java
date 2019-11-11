package br.com.ges.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ges.api.repository.RelatorioFinalRepository;

@Service
public class RelatorioFinalService {

	@Autowired
	RelatorioFinalRepository relatorioFinalRepository;
	
	
}
