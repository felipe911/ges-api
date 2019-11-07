package br.com.ges.api.wrapper;

import java.io.Serializable;

import br.com.ges.api.model.Aluno;
import br.com.ges.api.model.RelatorioAtividade;
import br.com.ges.api.model.RelatorioFinal;
import br.com.ges.api.model.RelatorioParcial;

public class RelatoriosAlunoWrapper implements Serializable {

	private static final long serialVersionUID = 1L;

	private Aluno aluno;

	private RelatorioFinal relatorioFinal;

	private RelatorioParcial relatorioParcial;

	private RelatorioAtividade relatorioAtividade;
	
	public RelatoriosAlunoWrapper(Aluno aluno, RelatorioFinal relatorioFinal, RelatorioParcial relatorioParcial,
			RelatorioAtividade relatorioAtividade) {
		this.aluno = aluno;
		this.relatorioFinal = relatorioFinal;
		this.relatorioParcial = relatorioParcial;
		this.relatorioAtividade = relatorioAtividade;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public RelatorioFinal getRelatorioFinal() {
		return relatorioFinal;
	}

	public void setRelatorioFinal(RelatorioFinal relatorioFinal) {
		this.relatorioFinal = relatorioFinal;
	}

	public RelatorioParcial getRelatorioParcial() {
		return relatorioParcial;
	}

	public void setRelatorioParcial(RelatorioParcial relatorioParcial) {
		this.relatorioParcial = relatorioParcial;
	}

	public RelatorioAtividade getRelatorioAtividade() {
		return relatorioAtividade;
	}

	public void setRelatorioAtividade(RelatorioAtividade relatorioAtividade) {
		this.relatorioAtividade = relatorioAtividade;
	}

}
