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
import br.com.ges.api.model.Contrato;
import br.com.ges.api.service.ContratoService;
import br.com.ges.api.wrapper.AssociarContratoWrapper;
import br.com.ges.api.wrapper.ContratoConsultaWrapper;
import br.com.ges.api.wrapper.EntregaRelatorioWrapper;

@RestController
@RequestMapping("/contrato")
public class ContratoController {

	@Autowired
	private ContratoService contratoService;
	
	
	/**
	 * 
	 * Exibe o registro de Contrato
	 * 
	 */
//	@ApiOperation(value = "Exibe o registro de Contrato")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Contrato.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@GetMapping("/{id}")
	public Contrato exibir(@PathVariable Long id) throws BusinessException {
		return contratoService.exibir(id);
	}
	
	
	/**
	 * 
	 * Lista todos Contratos
	 * 
	 */
//	@ApiOperation(value = "Lista todos os registros de Contratos")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Contrato.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@GetMapping(produces = "application/json")
	public List<Contrato> listar() {
		return contratoService.listar();
	}
	
	/**
	 * 
	 * Lista todos Contratos para consulta
	 * 
	 */
//	@ApiOperation(value = "Lista todos os registros de Contratos para consulta")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Contrato.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@GetMapping("/contratos-consulta")
	public List<ContratoConsultaWrapper> listarlistarContratosConsulta() {
		return contratoService.listarContratosConsulta();
	}
	
	
	/**
	 * Deleta um registro de Contrato caso ele exista
	 * 
	 */
//	@ApiOperation(value = "Deleta um registro de Empresa")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Contrato.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id) throws BusinessException {
		return contratoService.deletar(id);
	}
	

	/**
	 * 
	 * Associa um novo Contrato com Aluno e Empresa
	 * 
	 */
//	@ApiOperation(value = "Associa um novo Contrato")
//	@ApiOperation(value = "Lista todos os registros de Empresas")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Contrato.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PostMapping()
	public ResponseEntity<Contrato> associar(@RequestBody AssociarContratoWrapper associarContrato, HttpServletResponse response) throws BusinessException {
		return contratoService.associarContrato(associarContrato, response);

	}	
	
	/**
	 * 
	 * Atualiza o registro de um Contrato a partir de seu ID
	 *  
	 */
//	@ApiOperation(value = "Atualiza um registro de Contrato")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Contrato.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PutMapping("/{id}")
	public String atualizar(@PathVariable Long id, @RequestBody Contrato contrato) throws BusinessException {
		return contratoService.atualizar(id, contrato);
	}
	
	
	/**
	 * 
	 * Busca Contrato pelo Aluno
	 * 
	 */
//	@ApiOperation(value = "Busca Contrato pelo Aluno")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Contrato.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PostMapping("/busca-por-aluno")
	public Contrato buscaPorAluno(@RequestBody Aluno aluno) throws BusinessException {
		return contratoService.buscaContratoPorAluno(aluno);
	}
	
}
