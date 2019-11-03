package br.com.ges.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "relatorio_parcial")
public class RelatorioParcial {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name="atividades_auxiliadas")
	private String atividadesAuxiliadas;

	@Column(name="acordo_plano_atividades")
	private boolean acordoPlanoAtividades;

	@Column(name="compativel_curso")
	private boolean compativelCurso;

	@Column(name="compativel_semestre")
	private boolean compativelSemestre;

	@Column(name="conhecimento_obtido")
	private boolean conhecimentoObtido;

	@Column(name="instalacoes_adequadas")
	private boolean instalacoesAdequadas;

	@Column(name="motivos_nao")
	private String motivosNao;

	@Column(name="dificuldades")
	private String dificuldades;

	@Column(name="consideracoes_supervisor")
	private String consideracoesSupervisor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getConsideracoesSupervisor() {
		return consideracoesSupervisor;
	}

	public void setConsideracoesSupervisor(String consideracoesSupervisor) {
		this.consideracoesSupervisor = consideracoesSupervisor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		if (id != other.id)
			return false;
		return true;
	}

}
