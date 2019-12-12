package br.com.ges.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.ges.api.model.Empresa;
import br.com.ges.api.service.EmpresaService;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

	@Autowired
	private EmpresaService empresaService;

	
	/**
	 * 
	 * Exibe o registro da Empresa
	 * 
	 */
//	@ApiOperation(value = "Exibe o registro da Empresa")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Empresa.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@GetMapping("/{id}")
	public Empresa exibir(@PathVariable Long id) throws BusinessException {
		return empresaService.exibir(id);
	}
	
	
	/**
	 * 
	 * Lista todas Empresas
	 * 
	 */
//	@ApiOperation(value = "Lista todos os registros de Empresas")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Empresa.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@GetMapping(produces = "application/json")
	public List<Empresa> listar() {
		return empresaService.listar();
	}
	
	/**
	 * Deleta um registro de Empresa caso ele exista
	 * 
	 */
//	@ApiOperation(value = "Deleta um registro de Empresa")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Empresa.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id) throws BusinessException {
		return empresaService.deletar(id);
	}
	

	/**
	 * 
	 * Salva uma nova Empresa
	 * 
	 */
//	@ApiOperation(value = "Armazena um registro de Empresa")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Empresa.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PostMapping()
	public ResponseEntity<Empresa> salvar(@RequestBody Empresa empresa, HttpServletResponse response) throws BusinessException {
		return empresaService.salvar(empresa, response);
	}

	
	/**
	 * 
	 * Atualiza o registro de uma Empresa a partir de seu ID
	 * 
	 */
//	@ApiOperation(value = "Atualiza um registro de Empresa")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Empresa.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PutMapping("/{id}")
	public String atualizar(@PathVariable Long id, @RequestBody Empresa empresa) throws BusinessException {
		return empresaService.atualizar(id, empresa);
	}
	
	
	/**
	 * 
	 * Busca Empresa pela Razão Social
	 * 
	 */
//	@ApiOperation(value = "Busca Empresa pela Razão Social")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Empresa.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PostMapping("/busca-por-razao-social")
	public Empresa buscaPorRa(@RequestBody Empresa empresa) throws BusinessException {
		return empresaService.buscaEmpresaPorRazaoSocial(empresa);
	}
}
