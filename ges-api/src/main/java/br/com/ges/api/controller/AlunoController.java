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
import br.com.ges.api.service.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	/**
	 * 
	 * Lista todos Alunos
	 * 
	 */
//	@ApiOperation(value = "Lista todos os registros de Alunos")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Aluno.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@GetMapping(produces = "application/json")
	public List<Aluno> listar() {
		return alunoService.listar();
	}
	
	/**
	 * 
	 * Exibe o registro do aluno
	 * 
	 */
//	@ApiOperation(value = "Exibe o registro do aluno")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Aluno.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@GetMapping("/{id}")
	public Aluno exibir(@PathVariable Long id) throws BusinessException {
		return alunoService.exibir(id);
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
	public String salvar(@RequestBody Aluno aluno) throws BusinessException {
		return alunoService.salvar(aluno);
	}

	
	/**
	 * 
	 * Atualiza o registro de um Aluno a partir de seu ID
	 *  
	 */
//	@ApiOperation(value = "Atualiza um registro de Aluno")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Aluno.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PutMapping("/{id}")
	public String atualizar(@PathVariable Long id, @RequestBody Aluno aluno) throws BusinessException {
		return alunoService.atualizar(id, aluno);
	}
	
	
	/**
	 * Deleta um registro Aluno caso ele exista
	 * 
	 */
//	@ApiOperation(value = "Deleta um registro de Aluno")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Aluno.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id) throws BusinessException {
		return alunoService.deletar(id);
	}
}
