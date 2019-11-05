package br.com.ges.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ges.api.exception.BusinessException;
import br.com.ges.api.model.Aluno;
import br.com.ges.api.repository.AlunoRepository;

@Service
public class AlunoService {

	private static final String ANE = "Aluno não encontrado";
	private String mensagem = null;

	@Autowired
	private AlunoRepository alunoRepository;

	public Aluno exibir(Long id) {
		return alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ANE));
	}

	public List<Aluno> listar() {
		return (List<Aluno>) alunoRepository.findAll();
	}

	public String salvar(Aluno aluno) throws BusinessException {

		verificaDuplicidadeRa(aluno);

		// TODO: Verificar mensagem de erro retornada para o usuário.
		
		alunoRepository.save(aluno);
		this.mensagem = "Aluno registrado com sucesso.";

		return this.mensagem;
	}

	public String atualizar(Long id, Aluno aluno) {

		alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ANE));

		try {
			aluno.setId(id);
			alunoRepository.save(aluno);
			this.mensagem = "Aluno atualizado com sucesso.";

		} catch (Exception e) {
			// TODO: handle exception
		}

		return this.mensagem;
	}

	public String deletar(Long id) throws BusinessException {
		alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ANE));

		try {
			alunoRepository.deleteById(id);
			this.mensagem = "Aluno deletado com sucesso.";
		} catch (Exception e) {
			// TODO: handle exception
		}

		return this.mensagem;
	}

	public void verificaDuplicidadeRa(Aluno aluno) throws BusinessException {

		List<Aluno> alunosRa = alunoRepository.findByRa(aluno.getRa());

		if (!alunosRa.isEmpty()) {
			throw new BusinessException("Já existe um aluno registrado com este RA.");
		}
	}
}
