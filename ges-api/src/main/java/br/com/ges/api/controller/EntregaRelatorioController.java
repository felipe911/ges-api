package br.com.ges.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ges.api.exception.BusinessException;
import br.com.ges.api.model.Aluno;
import br.com.ges.api.model.Estagio;
import br.com.ges.api.service.AlunoService;
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

}
