package br.com.ges.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ges.api.exception.BusinessException;
import br.com.ges.api.model.Aluno;
import br.com.ges.api.service.AlunoService;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	
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
	 * Busca Aluno pelo RA
	 * 
	 */
//	@ApiOperation(value = "Busca Aluno pelo RA")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Aluno.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PostMapping("/busca-por-ra")
	public Aluno buscaPorRa(@RequestBody Aluno aluno) throws BusinessException {
		return alunoService.buscaAlunoPorRa(aluno);
	}
	
	
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
	 * Deleta um registro de Aluno caso ele exista
	 * 
	 */
//	@ApiOperation(value = "Deleta um registro de Aluno")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Aluno.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) throws BusinessException {
		alunoService.deletar(id);
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
	public ResponseEntity<Aluno> salvar(@RequestBody Aluno aluno, HttpServletResponse response) throws BusinessException {
		return alunoService.salvar(aluno, response);
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
	public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @RequestBody Aluno aluno, HttpServletResponse response) throws BusinessException {
		return alunoService.atualizar(id, aluno, response);
	}
}
