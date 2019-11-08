package br.com.ges.api.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "relatorio_final")
public class RelatorioFinal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "total_horas_cumpridas")
	private int totalHorasCumpridas;

	@Column(name = "tipo_atividade")
	private int tipoAtividade;

	@Column(name = "local")
	private String local;

	@Column(name = "periodo_de")
	private LocalDate periodoDe;

	@Column(name = "periodo_ate")
	private LocalDate periodoAte;

	@Column(name = "area_atividade")
	private String areaAtividade;
	
	@OneToOne
	@JoinColumn(name = "id_estagio_relatorio", nullable = false)
	private EstagioRelatorio estagioRelatorio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTotalHorasCumpridas() {
		return totalHorasCumpridas;
	}

	public void setTotalHorasCumpridas(int totalHorasCumpridas) {
		this.totalHorasCumpridas = totalHorasCumpridas;
	}

	public int getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(int tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public LocalDate getPeriodoDe() {
		return periodoDe;
	}

	public void setPeriodoDe(LocalDate periodoDe) {
		this.periodoDe = periodoDe;
	}

	public LocalDate getPeriodoAte() {
		return periodoAte;
	}

	public void setPeriodoAte(LocalDate periodoAte) {
		this.periodoAte = periodoAte;
	}

	public String getAreaAtividade() {
		return areaAtividade;
	}

	public void setAreaAtividade(String areaAtividade) {
		this.areaAtividade = areaAtividade;
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
		RelatorioFinal other = (RelatorioFinal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
