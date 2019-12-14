package br.com.ges.api.service;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ges.api.enums.StatusEstagio;
import br.com.ges.api.exception.BusinessException;
import br.com.ges.api.model.Aluno;
import br.com.ges.api.model.Estagio;
import br.com.ges.api.repository.AlunoRepository;
import br.com.ges.api.repository.EstagioRepository;

@Service
public class AlunoService {

	private static final String ANE = "Aluno não encontrado.";
	private static final String ALUNO_JA_REGISTRADO = "Já existe um aluno registrado com este RA.";

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private EstagioRepository estagioRepository;

	public Aluno exibir(Long id) {
		return alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ANE));
	}

	public List<Aluno> listar() {
		return alunoRepository.findAll();
	}

	public void deletar(Long id) throws BusinessException {

		alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ANE));

		Estagio estagioAluno = estagioRepository.findByAlunoId(id);
		
		if(estagioAluno != null && estagioAluno.getStatus() == StatusEstagio.ATIVO) {
			throw new BusinessException("Não é possível deletar um aluno com Estágio Ativo.");
			
		} else {
		
			try {
				alunoRepository.deleteById(id);
	
			} catch (Exception e) {
				throw new BusinessException("Erro ao deletar Aluno.");
			}
		}
	}

	public ResponseEntity<Aluno> salvar(Aluno aluno, HttpServletResponse response) throws BusinessException {

		try {

			verificaDuplicidadeRa(aluno, aluno.getId());
			Aluno alunoSalvo = alunoRepository.save(aluno);

			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
					.buildAndExpand(alunoSalvo.getId()).toUri();
			response.setHeader("Location", uri.toASCIIString());
			return ResponseEntity.created(uri).body(alunoSalvo);

		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

	}

	public ResponseEntity<Aluno> atualizar(Long id, Aluno aluno, HttpServletResponse response) throws BusinessException {

		alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ANE));

		try {

			verificaDuplicidadeRa(aluno, id);
			aluno.setId(id);
			Aluno alunoAtualizado = alunoRepository.save(aluno);
			
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
					.buildAndExpand(alunoAtualizado.getId()).toUri();
			response.setHeader("Location", uri.toASCIIString());
			return ResponseEntity.created(uri).body(alunoAtualizado);

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

	public Aluno buscaAlunoPorRa(Aluno alunoRa) throws BusinessException {

		List<Aluno> alunoEncontrado = alunoRepository.findByRa(alunoRa.getRa());

		if (!alunoEncontrado.isEmpty()) {

			if (validaAlunoComEstagio(alunoEncontrado.iterator().next())) {
				return alunoEncontrado.iterator().next();
				
			} else {
				throw new BusinessException("Este aluno já possui estágio vinculado.");
			}

		} else {
			throw new BusinessException(ANE);
		}
	}

	private boolean validaAlunoComEstagio(Aluno aluno) {

		Estagio estagioAluno = estagioRepository.findByAluno(aluno);

		if (estagioAluno == null)
			return true;

		return false;
	}

}
