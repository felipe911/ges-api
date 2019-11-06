package br.com.ges.api.wrapper;

import java.io.Serializable;

import br.com.ges.api.model.Aluno;
import br.com.ges.api.model.Contrato;
import br.com.ges.api.model.Empresa;

public class AssociarContratoWrapper implements Serializable {

	private static final long serialVersionUID = 1L;

	private Contrato contrato;
	private Aluno aluno;
	private Empresa empresa;

	public AssociarContratoWrapper(Contrato contrato, Aluno aluno, Empresa empresa) {
		this.contrato = contrato;
		this.aluno = aluno;
		this.empresa = empresa;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
