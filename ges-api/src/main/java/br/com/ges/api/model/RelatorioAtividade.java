package br.com.ges.api.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "relatorio_atividade")
public class RelatorioAtividade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "data_entrega")
	private LocalDate dataEntrega;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "qtd_horas")
	private int qtdHoras;
	
	@ManyToOne
	@JoinColumn(name = "id_estagio_relatorio", referencedColumnName = "id", nullable = false)
	@JsonBackReference
    private EstagioRelatorio estagioRelatorio_relAtiv;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQtdHoras() {
		return qtdHoras;
	}

	public void setQtdHoras(int qtdHoras) {
		this.qtdHoras = qtdHoras;
	}

	public EstagioRelatorio getEstagioRelatorio_relAtiv() {
		return estagioRelatorio_relAtiv;
	}

	public void setEstagioRelatorio_relAtiv(EstagioRelatorio estagioRelatorio_relAtiv) {
		this.estagioRelatorio_relAtiv = estagioRelatorio_relAtiv;
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
		RelatorioAtividade other = (RelatorioAtividade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
