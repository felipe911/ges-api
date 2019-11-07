package br.com.ges.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.ges.api.enums.StatusEstagio;

@Entity
@Table(name = "estagio")
public class Estagio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "service_sequence", sequenceName = "service_sequence", allocationSize = 1)
	private Long id;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "id_contrato")
	private Contrato contrato;
	
	@ManyToOne
	@JoinColumn(name = "id_aluno", referencedColumnName = "id", nullable = true)
	private Aluno aluno;

	private StatusEstagio status;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "id_rel_estagio")
	private Relatorio_Estagio relatorioEstagio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Relatorio_Estagio getRelatorioEstagio() {
		return relatorioEstagio;
	}

	public void setRelatorioEstagio(Relatorio_Estagio relatorioEstagio) {
		this.relatorioEstagio = relatorioEstagio;
	}


	public StatusEstagio getStatus() {
		return status;
	}

	public void setStatus(StatusEstagio status) {
		this.status = status;
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
		Estagio other = (Estagio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
