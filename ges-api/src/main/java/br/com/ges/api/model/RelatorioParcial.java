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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "relatorio_parcial")
public class RelatorioParcial {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "atividades_auxiliadas")
	private String atividadesAuxiliadas;

	@Column(name = "acordo_plano_atividades")
	private boolean acordoPlanoAtividades;

	@Column(name = "compativel_curso")
	private boolean compativelCurso;

	@Column(name = "compativel_semestre")
	private boolean compativelSemestre;

	@Column(name = "conhecimento_obtido")
	private boolean conhecimentoObtido;

	@Column(name = "instalacoes_adequadas")
	private boolean instalacoesAdequadas;

	@Column(name = "motivos_nao")
	private String motivosNao;

	@Column(name = "dificuldades")
	private String dificuldades;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "id_estagio", referencedColumnName = "id")
	private Estagio estagioRelatorioParcial;
	
	@Column(name = "relatorio_entregue")
	private boolean relatorioEntregue;
	
	@Column(name = "periodo_de")
	private LocalDate periodoDe;
	
	@Column(name = "periodo_ate")
	private LocalDate periodoAte;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAtividadesAuxiliadas() {
		return atividadesAuxiliadas;
	}

	public void setAtividadesAuxiliadas(String atividadesAuxiliadas) {
		this.atividadesAuxiliadas = atividadesAuxiliadas;
	}

	public boolean isAcordoPlanoAtividades() {
		return acordoPlanoAtividades;
	}

	public void setAcordoPlanoAtividades(boolean acordoPlanoAtividades) {
		this.acordoPlanoAtividades = acordoPlanoAtividades;
	}

	public boolean isCompativelCurso() {
		return compativelCurso;
	}

	public void setCompativelCurso(boolean compativelCurso) {
		this.compativelCurso = compativelCurso;
	}

	public boolean isCompativelSemestre() {
		return compativelSemestre;
	}

	public void setCompativelSemestre(boolean compativelSemestre) {
		this.compativelSemestre = compativelSemestre;
	}

	public boolean isConhecimentoObtido() {
		return conhecimentoObtido;
	}

	public void setConhecimentoObtido(boolean conhecimentoObtido) {
		this.conhecimentoObtido = conhecimentoObtido;
	}

	public boolean isInstalacoesAdequadas() {
		return instalacoesAdequadas;
	}

	public void setInstalacoesAdequadas(boolean instalacoesAdequadas) {
		this.instalacoesAdequadas = instalacoesAdequadas;
	}

	public String getMotivosNao() {
		return motivosNao;
	}

	public void setMotivosNao(String motivosNao) {
		this.motivosNao = motivosNao;
	}

	public String getDificuldades() {
		return dificuldades;
	}

	public void setDificuldades(String dificuldades) {
		this.dificuldades = dificuldades;
	}

	public Estagio getEstagioRelatorioParcial() {
		return estagioRelatorioParcial;
	}

	public void setEstagioRelatorioParcial(Estagio estagioRelatorioParcial) {
		this.estagioRelatorioParcial = estagioRelatorioParcial;
	}

	public boolean isRelatorioEntregue() {
		return relatorioEntregue;
	}

	public void setRelatorioEntregue(boolean relatorioEntregue) {
		this.relatorioEntregue = relatorioEntregue;
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
		RelatorioParcial other = (RelatorioParcial) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
