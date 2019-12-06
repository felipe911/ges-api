package br.com.ges.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ges.api.exception.BusinessException;
import br.com.ges.api.model.Aluno;
import br.com.ges.api.model.RelatorioParcial;
import br.com.ges.api.service.RelatorioParcialService;
import br.com.ges.api.wrapper.RelatoriosAlunoWrapper;

@RestController
@RequestMapping("/relatorio-parcial")
public class RelatorioParcialController {

	@Autowired
	private RelatorioParcialService relatorioParcialService;

	/**
	 * 
	 * Exibe o registro da Relatório Final a partir de seu ID
	 * 
	 */
//	@ApiOperation(value = "Exibe o registro de Relatório Final")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = RelatorioFinal.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@GetMapping("/{id}")
	public RelatorioParcial exibir(@PathVariable Long id) throws BusinessException {
		return relatorioParcialService.exibir(id);
	}

	/**
	 * 
	 * Busca o Relatório Parcial por Aluno específico
	 * 
	 */
//	@ApiOperation(value = "Busca o Relatório Parcial de um aluno")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = RelatorioParcial.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PostMapping(value = "/buscar-por-aluno")
	public List<RelatorioParcial> listar(@RequestBody Aluno aluno) {
		return relatorioParcialService.buscaRelatorioParcialDoAluno(aluno);
	}

	/**
	 * 
	 * Lista todos Relatórios Parciais cadastrados no sistema.
	 * 
	 */
//	@ApiOperation(value = "Lista todos os registros de Relatórios Parciais cadastrados no sistema")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = RelatorioParcial.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@GetMapping(produces = "application/json")
	public List<RelatorioParcial> listarTodos() {
		return relatorioParcialService.listarTodos();
	}

	/**
	 * Deleta um registro de Relatório Parcial caso ele exista
	 * 
	 */
//	@ApiOperation(value = "Deleta um registro de Relatório Parcial")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = RelatorioParcial.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id) throws BusinessException {
		return relatorioParcialService.deletar(id);
	}

	/**
	 * 
	 * Salva um no Relatório Parcial
	 * 
	 */
//	@ApiOperation(value = "Armazena um registro de Relatório Parcial")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = RelatorioParcial.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PostMapping()
	public ResponseEntity<RelatorioParcial> salvar(@RequestBody RelatoriosAlunoWrapper relatoriosAlunoWrapper, HttpServletResponse response) throws BusinessException {
		return relatorioParcialService.salvar(relatoriosAlunoWrapper, response);
	}

}
