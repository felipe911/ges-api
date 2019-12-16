package br.com.ges.api.wrapper;

import java.util.List;

public class EmpresasContratacaoWrapper {
	
	private List<String> empresas;
	private List<Integer> qtdPorEmpresa;
	
	public List<String> getEmpresas() {
		return empresas;
	}
	public void setEmpresas(List<String> empresas) {
		this.empresas = empresas;
	}
	public List<Integer> getQtdPorEmpresa() {
		return qtdPorEmpresa;
	}
	public void setQtdPorEmpresa(List<Integer> qtdPorEmpresa) {
		this.qtdPorEmpresa = qtdPorEmpresa;
	}
}
