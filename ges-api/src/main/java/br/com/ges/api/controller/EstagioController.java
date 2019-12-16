package br.com.ges.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ges.api.exception.BusinessException;
import br.com.ges.api.model.Estagio;
import br.com.ges.api.service.EstagioService;


@RestController
@RequestMapping("/estagio")
public class EstagioController {

	
	@Autowired
	private EstagioService estagioService;
	
	/**
	 * 
	 * Exibe o registro do estagio a partir do contrato
	 * 
	 */
//	@ApiOperation(value = "Exibe o registro do estagio a partir do contrato")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Estagio.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@GetMapping("/{id}")
	public Estagio exibir(@PathVariable Long id) throws BusinessException {
		return estagioService.buscaEstagioPorContratoId(id);
	}
	
	/**
	 * 
	 * Exibe o registro do estagio a partir do Aluno
	 * 
	 */
//	@ApiOperation(value = "Exibe o registro do estagio a partir do Aluno")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Estagio.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@GetMapping("/busca-por-aluno/{id}")
	public Estagio exibirEstagioPorAluno(@PathVariable Long id) throws BusinessException {
		return estagioService.exibirEstagioPorAluno(id);
	}
	
}
