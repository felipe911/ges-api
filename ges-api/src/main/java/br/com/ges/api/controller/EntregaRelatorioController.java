package br.com.ges.api.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ges.api.exception.BusinessException;
import br.com.ges.api.service.EntregaRelatorioService;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import br.com.ges.api.wrapper.EntregaRelatorioWrapper;

@RestController
@RequestMapping("/entrega-relatorio")
public class EntregaRelatorioController {
	
	@Autowired
	private EntregaRelatorioService entregaRelatorioService;
	
	/**
	 * 
	 * Busca todos os dados relacionados ao estagiário
	 * 
	 */
//	@ApiOperation(value = "Busca todos os dados relacionados ao estagiário")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = EntregaRelatorioWrapper.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@GetMapping(produces = "application/json")
	public List<EntregaRelatorioWrapper> buscaDadosEstagiarios() throws BusinessException {
		return entregaRelatorioService.buscaDadosEstagiarios();
	}

	
	/**
	 * 
	 * Busca Estagios de um Aluno
	 * 
	 */
//	@ApiOperation(value = "Busca Estagios de um Aluno")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Estagio.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@GetMapping("/buscar-por-aluno/{id}")
	public EntregaRelatorioWrapper buscarPorAlunoId(@PathVariable Long id) throws BusinessException {
		return entregaRelatorioService.buscarPorAlunoId(id);
	}
}
