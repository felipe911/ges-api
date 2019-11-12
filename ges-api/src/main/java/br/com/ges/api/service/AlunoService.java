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

	private static final String ANE = "Aluno não encontrado.";
	private static final String ALUNO_JA_REGISTRADO = "Já existe um aluno registrado com este RA.";

	@Autowired
	private AlunoRepository alunoRepository;

	
	public Aluno exibir(Long id) {
		return alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ANE));
	}
	

	public List<Aluno> listar() {
		return (List<Aluno>) alunoRepository.findAll();
	}
	

	public String deletar(Long id) throws BusinessException {
		
		alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ANE));
		
		try {
			alunoRepository.deleteById(id);
			return "Aluno deletado com sucesso.";
			
		} catch (Exception e) {
			throw new BusinessException("Erro ao deletar Aluno.");
		}
	}
	
	
	public String salvar(Aluno aluno) throws BusinessException {

		try {
			
			verificaDuplicidadeRa(aluno, aluno.getId());
			alunoRepository.save(aluno);
			return "Aluno registrado com sucesso.";
			
		} catch (Exception e) {
			throw new BusinessException(ALUNO_JA_REGISTRADO);
		}
		
	}

	
	public String atualizar(Long id, Aluno aluno) throws BusinessException {

		alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ANE));

		try {
			
			verificaDuplicidadeRa(aluno, id);
			aluno.setId(id);
			alunoRepository.save(aluno);
			return "Aluno atualizado com sucesso.";
			
		} catch (Exception e) {
			throw new BusinessException(ALUNO_JA_REGISTRADO);
		}
	}


	public void verificaDuplicidadeRa(Aluno aluno, Long id) throws BusinessException {

		List<Aluno> alunosRa = alunoRepository.findByRa(aluno.getRa());

		if (!alunosRa.isEmpty()) {

			if (id != null) {

				if (alunosRa.get(0).getId() != id)
					throw new BusinessException(ALUNO_JA_REGISTRADO);

			} else {
				throw new BusinessException(ALUNO_JA_REGISTRADO);
			}

		}
	}
}
