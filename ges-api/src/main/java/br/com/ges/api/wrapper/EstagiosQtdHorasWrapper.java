package br.com.ges.api.wrapper;

import java.util.List;

import br.com.ges.api.model.Estagio;

public class EstagiosQtdHorasWrapper {

	private List<Estagio> estagios;
	private List<Integer> qtdHoras;
	
	public List<Estagio> getEstagios() {
		return estagios;
	}
	public void setEstagios(List<Estagio> estagios) {
		this.estagios = estagios;
	}
	public List<Integer> getQtdHoras() {
		return qtdHoras;
	}
	public void setQtdHoras(List<Integer> qtdHoras) {
		this.qtdHoras = qtdHoras;
	}
}
