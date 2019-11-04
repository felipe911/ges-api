package br.com.ges.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
//	@ApiOperation(value = "Exibe um registro de Aluno")
//	@ApiResponses(value = @ApiResponse(code = 200, message = "Retorna um registro da entidade buscada", response = Aluno.class))
	@GetMapping("/{id}")
	public Aluno exibir(@PathVariable Long id) throws BusinessException {
		return alunoService.exibir(id);
	}
}
