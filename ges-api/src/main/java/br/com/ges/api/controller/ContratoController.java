package br.com.ges.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ges.api.exception.BusinessException;
import br.com.ges.api.model.Contrato;
import br.com.ges.api.service.ContratoService;
import br.com.ges.api.wrapper.AssociarContratoWrapper;

@RestController
@RequestMapping("/contrato")
public class ContratoController {

	@Autowired
	private ContratoService contratoService;

	/**
	 * 
	 * Salva um novo Contrato
	 * 
	 */
//	@ApiOperation(value = "Armazena um registro de Contrato")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Contrato.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
	@PostMapping()
	public String salvar(@RequestBody AssociarContratoWrapper associarContrato) throws BusinessException {
		return contratoService.associarContrato(associarContrato);
	}

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
	 * Salva um novo Contrato
	 * 
	 */
//	@ApiOperation(value = "Armazena um registro de Contrato")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = Contrato.class),
//			@ApiResponse(code = 201, message = "Criado"), @ApiResponse(code = 204, message = "Sem conteúdo"),
//			@ApiResponse(code = 401, message = "Sem autorização"), @ApiResponse(code = 403, message = "Proibido"),
//			@ApiResponse(code = 404, message = "Não encontrado"),
//			@ApiResponse(code = 500, message = "Erro interno no servidor") })
//	@PostMapping()
//	public String salvar(@RequestBody Contrato contrato) throws BusinessException {
//		return contratoService.salvar(contrato);
//	}
}
