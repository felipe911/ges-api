package br.com.ges.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.ges.api.model.RelatorioAtividade;
import br.com.ges.api.service.RelatorioAtividadeService;
import br.com.ges.api.wrapper.RelatoriosAlunoWrapper;

@RestController
@RequestMapping("/relatorio-atividade")
public class RelatorioAtividadeController {

	@Autowired
	private RelatorioAtividadeService relatorioAtividadeService;

	/**
	 * 
	 * Exibe o registro da Relatório de Atividade
	 * 
	 */
//	@ApiOperation(value = "Exibe o registro de Relatório de Atividade")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = RelatorioAtividade.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@GetMapping("/{id}")
	public RelatorioAtividade exibir(@PathVariable Long id) throws BusinessException {
		return relatorioAtividadeService.exibir(id);
	}

	/**
	 * 
	 * Lista todos Relatórios de Atividade de um Aluno específico
	 * 
	 */
//	@ApiOperation(value = "Lista todos os registros de Relatórios de Atividade de um determinado Aluno")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = RelatorioAtividade.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PostMapping(value = "/buscar-por-aluno")
	public List<RelatorioAtividade> listar(@RequestBody Aluno aluno) {
		return relatorioAtividadeService.buscaRelatoriosAtividadeDoAluno(aluno);
	}

	/**
	 * 
	 * Lista todos Relatórios de Atividade cadastrados no sistema.
	 * 
	 */
//	@ApiOperation(value = "Lista todos os registros Relatórios de Atividade cadastrados no sistema")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = RelatorioAtividade.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@GetMapping(produces = "application/json")
	public List<RelatorioAtividade> listarTodos() {
		return relatorioAtividadeService.listarTodos();
	}

	/**
	 * Deleta um registro de Relatório de Atividade caso ele exista
	 * 
	 */
//	@ApiOperation(value = "Deleta um registro de Relatório de Atividade")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = RelatorioAtividade.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id) throws BusinessException {
		return relatorioAtividadeService.deletar(id);
	}

	/**
	 * 
	 * Salva uma novo Relatório de Atividade
	 * 
	 */
//	@ApiOperation(value = "Armazena um registro de Relatório de Atividade")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = RelatorioAtividade.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PostMapping()
	public String salvar(@RequestBody RelatoriosAlunoWrapper relatoriosAlunoWrapper) throws BusinessException {
		return relatorioAtividadeService.salvar(relatoriosAlunoWrapper);
	}

	/**
	 * 
	 * Atualiza o registro de Relatório de Atividade a partir de seu ID
	 * 
	 */
//	@ApiOperation(value = "Atualiza um registro Relatório de Atividade")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = RelatorioAtividade.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PutMapping("/{id}")
	public String atualizar(@PathVariable Long id, @RequestBody RelatorioAtividade relatorioAtividade)
			throws BusinessException {
		return relatorioAtividadeService.atualizar(id, relatorioAtividade);
	}

}
