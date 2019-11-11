package br.com.ges.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ges.api.exception.BusinessException;
import br.com.ges.api.model.Aluno;
import br.com.ges.api.model.RelatorioFinal;
import br.com.ges.api.service.RelatorioFinalService;
import br.com.ges.api.wrapper.RelatoriosAlunoWrapper;

@RestController
@RequestMapping("/relatorio-final")
public class RelatorioFinalController {

	@Autowired
	RelatorioFinalService relatorioFinalService;

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
	public RelatorioFinal exibir(@PathVariable Long id) throws BusinessException {
		return relatorioFinalService.exibir(id);
	}

	/**
	 * 
	 * Busca o Relatório Final por Aluno específico
	 * 
	 */
//	@ApiOperation(value = "Busca o Relatório Final de um aluno")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = RelatorioFinal.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PostMapping(value = "/buscar-por-aluno")
	public List<RelatorioFinal> listar(@RequestBody Aluno aluno) {
		return relatorioFinalService.buscaRelatorioFinalDoAluno(aluno);
	}

	/**
	 * 
	 * Lista todos Relatórios Finais cadastrados no sistema.
	 * 
	 */
//	@ApiOperation(value = "Lista todos os registros Finais cadastrados no sistema")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = RelatorioFinal.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@GetMapping(produces = "application/json")
	public List<RelatorioFinal> listarTodos() {
		return relatorioFinalService.listarTodos();
	}

	/**
	 * Deleta um registro de Relatório Final caso ele exista
	 * 
	 */
//	@ApiOperation(value = "Deleta um registro de Relatório Final")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = RelatorioFinal.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id) throws BusinessException {
		return relatorioFinalService.deletar(id);
	}

	/**
	 * 
	 * Salva uma novo Relatório Final
	 * 
	 */
//	@ApiOperation(value = "Armazena um registro de Relatório Final")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = RelatorioFinal.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PostMapping()
	public String salvar(@RequestBody RelatoriosAlunoWrapper relatoriosAlunoWrapper) throws BusinessException {
		return relatorioFinalService.salvar(relatoriosAlunoWrapper);
	}
}
