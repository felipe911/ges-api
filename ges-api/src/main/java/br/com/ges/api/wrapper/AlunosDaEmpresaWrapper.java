package br.com.ges.api.wrapper;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import br.com.ges.api.model.Aluno;
import br.com.ges.api.model.Empresa;

public class AlunosDaEmpresaWrapper implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private Empresa empresa;
	private List<Aluno> alunos;
	
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public List<Aluno> getAlunos() {
		return alunos;
	}
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
}
