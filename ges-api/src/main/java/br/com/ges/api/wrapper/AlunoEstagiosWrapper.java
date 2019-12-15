package br.com.ges.api.wrapper;

import java.util.List;

import br.com.ges.api.model.Aluno;
import br.com.ges.api.model.Estagio;

public class AlunoEstagiosWrapper {
	
	private Aluno aluno;
	private List<Estagio> estagios;
	
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public List<Estagio> getEstagios() {
		return estagios;
	}
	public void setEstagios(List<Estagio> estagios) {
		this.estagios = estagios;
	}
}
