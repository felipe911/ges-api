package br.com.ges.api.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "estagio_relatorio")
public class EstagioRelatorio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "service_sequence", sequenceName = "service_sequence", allocationSize = 1)
	private Long id;

	@OneToMany(mappedBy = "estagioRelatorio_relAtiv")
//	@JoinColumn(name = "rel_estagio_id")
	private List<RelatorioAtividade> relatoriosAtividades;

	@OneToMany
	@JoinColumn(name = "rel_estagio_id")
	private Set<RelatorioParcial> relatoriosParciais;
	
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "rel_estagio_final")
	private RelatorioFinal relatorioFinal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RelatorioFinal getRelatorioFinal() {
		return relatorioFinal;
	}

	public void setRelatorioFinal(RelatorioFinal relatorioFinal) {
		this.relatorioFinal = relatorioFinal;
	}
	

	public List<RelatorioAtividade> getRelatoriosAtividades() {
		return relatoriosAtividades;
	}

	public void setRelatoriosAtividades(List<RelatorioAtividade> relatoriosAtividades) {
		this.relatoriosAtividades = relatoriosAtividades;
	}

	public Set<RelatorioParcial> getRelatoriosParciais() {
		return relatoriosParciais;
	}

	public void setRelatoriosParciais(Set<RelatorioParcial> relatoriosParciais) {
		this.relatoriosParciais = relatoriosParciais;
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
		EstagioRelatorio other = (EstagioRelatorio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
