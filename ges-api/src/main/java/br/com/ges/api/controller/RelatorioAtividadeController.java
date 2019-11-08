package br.com.ges.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ges.api.exception.BusinessException;
import br.com.ges.api.model.Aluno;
import br.com.ges.api.model.Empresa;
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
	 * Lista todos Relatórios de Atividade de um Aluno específico
	 * 
	 */
//	@ApiOperation(value = "Lista todos os registros de Alunos")
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
	 * Salva uma novo Aluno
	 * 
	 */
//	@ApiOperation(value = "Armazena um registro de Aluno")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Aluno.class),
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
	public String atualizar(@PathVariable Long id, @RequestBody RelatorioAtividade relatorioAtividade) throws BusinessException {
		return relatorioAtividadeService.atualizar(relatorioAtividade);
	}
	
}
