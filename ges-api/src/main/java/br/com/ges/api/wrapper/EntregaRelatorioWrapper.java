package br.com.ges.api.wrapper;

import java.util.List;

import br.com.ges.api.model.Estagio;
import br.com.ges.api.model.RelatorioAtividade;
import br.com.ges.api.model.RelatorioFinal;
import br.com.ges.api.model.RelatorioParcial;

public class EntregaRelatorioWrapper {

	private Estagio estagio;
	private List<RelatorioFinal> relatorioFinal;
	private List<RelatorioParcial> relatorioParcial;
	private List<RelatorioAtividade> relatorioAtividade;

	public Estagio getEstagio() {
		return estagio;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}

	public List<RelatorioFinal> getRelatorioFinal() {
		return relatorioFinal;
	}

	public void setRelatorioFinal(List<RelatorioFinal> relatorioFinal) {
		this.relatorioFinal = relatorioFinal;
	}

	public List<RelatorioParcial> getRelatorioParcial() {
		return relatorioParcial;
	}

	public void setRelatorioParcial(List<RelatorioParcial> relatorioParcial) {
		this.relatorioParcial = relatorioParcial;
	}

	public List<RelatorioAtividade> getRelatorioAtividade() {
		return relatorioAtividade;
	}

	public void setRelatorioAtividade(List<RelatorioAtividade> relatorioAtividade) {
		this.relatorioAtividade = relatorioAtividade;
	}

}
