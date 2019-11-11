package br.com.ges.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ges.api.exception.BusinessException;
import br.com.ges.api.service.RelatorioFinalService;
import br.com.ges.api.wrapper.RelatoriosAlunoWrapper;

@RestController
@RequestMapping("/relatorio-final")
public class RelatorioFinalController {

	@Autowired
	RelatorioFinalService relatorioFinalService;
	
	
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
