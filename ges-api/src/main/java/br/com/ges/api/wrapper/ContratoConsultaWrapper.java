package br.com.ges.api.wrapper;

import java.io.Serializable;
import java.time.LocalDate;

public class ContratoConsultaWrapper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nomeAluno;
	private String empresaAssociada;
	private String curso;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private String status;
	private String agenteIntegracao;
	private String supervisor;
	
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public String getEmpresaAssociada() {
		return empresaAssociada;
	}
	public void setEmpresaAssociada(String empresaAssociada) {
		this.empresaAssociada = empresaAssociada;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalDate getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAgenteIntegracao() {
		return agenteIntegracao;
	}
	public void setAgenteIntegracao(String agenteIntegracao) {
		this.agenteIntegracao = agenteIntegracao;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

}
