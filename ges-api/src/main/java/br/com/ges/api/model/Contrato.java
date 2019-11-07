package br.com.ges.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contrato")
public class Contrato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "service_sequence", sequenceName = "service_sequence", allocationSize=1)
	private Long id;

	@NotNull
	@Column(name = "data_inicio")
	private LocalDate dataInicio;

	@NotNull
	@Column(name = "data_fim")
	private LocalDate dataFim;

	@Column(name = "prorrogado_ate")
	private LocalDate prorrogadoAte;

	@NotNull
	@Column(name = "valor_bolsa")
	private BigDecimal valorBolsa;

	@NotNull
	@Column(name = "agente_integracao")
	private String agenteIntegracao;

	@NotNull
	@Column(name = "supervisor_estagio")
	private String supervisorEstagio;

	@NotNull
	@Column(name = "email_supervisor")
	private String emailSupervisor;

	@NotNull
	@Column(name = "observacao")
	private String observacao;

	@ManyToOne
	@JoinColumn(name = "id_empresa", referencedColumnName = "id", nullable = true)
	private Empresa empresa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalDate getProrrogadoAte() {
		return prorrogadoAte;
	}

	public void setProrrogadoAte(LocalDate prorrogadoAte) {
		this.prorrogadoAte = prorrogadoAte;
	}

	public BigDecimal getValorBolsa() {
		return valorBolsa;
	}

	public void setValorBolsa(BigDecimal valorBolsa) {
		this.valorBolsa = valorBolsa;
	}

	public String getAgenteIntegracao() {
		return agenteIntegracao;
	}

	public void setAgenteIntegracao(String agenteIntegracao) {
		this.agenteIntegracao = agenteIntegracao;
	}

	public String getSupervisorEstagio() {
		return supervisorEstagio;
	}

	public void setSupervisorEstagio(String supervisorEstagio) {
		this.supervisorEstagio = supervisorEstagio;
	}

	public String getEmailSupervisor() {
		return emailSupervisor;
	}

	public void setEmailSupervisor(String emailSupervisor) {
		this.emailSupervisor = emailSupervisor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contrato other = (Contrato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
