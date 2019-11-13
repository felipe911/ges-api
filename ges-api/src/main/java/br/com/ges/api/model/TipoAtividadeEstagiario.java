package br.com.ges.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_atividade_estagiario")
public class TipoAtividadeEstagiario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "setor_privado")
	private boolean setorPrivado;

	@Column(name = "organizacao_terceiro_setor")
	private boolean organizacaoTerceiroSetor;

	@Column(name = "orgao_adm_publica")
	private boolean orgaoAdmPublica;

	@Column(name = "ativ_extracurriculares_fatec")
	private boolean ativExtracurricularesFatec;
	
	@OneToOne
	@JoinColumn(name = "id_relatorio_final", referencedColumnName = "id")
	private RelatorioFinal relatorioFinal;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isSetorPrivado() {
		return setorPrivado;
	}

	public void setSetorPrivado(boolean setorPrivado) {
		this.setorPrivado = setorPrivado;
	}

	public boolean isOrganizacaoTerceiroSetor() {
		return organizacaoTerceiroSetor;
	}

	public void setOrganizacaoTerceiroSetor(boolean organizacaoTerceiroSetor) {
		this.organizacaoTerceiroSetor = organizacaoTerceiroSetor;
	}

	public boolean isOrgaoAdmPublica() {
		return orgaoAdmPublica;
	}

	public void setOrgaoAdmPublica(boolean orgaoAdmPublica) {
		this.orgaoAdmPublica = orgaoAdmPublica;
	}

	public boolean isAtivExtracurricularesFatec() {
		return ativExtracurricularesFatec;
	}

	public void setAtivExtracurricularesFatec(boolean ativExtracurricularesFatec) {
		this.ativExtracurricularesFatec = ativExtracurricularesFatec;
	}

	public RelatorioFinal getRelatorioFinal() {
		return relatorioFinal;
	}

	public void setRelatorioFinal(RelatorioFinal relatorioFinal) {
		this.relatorioFinal = relatorioFinal;
	}
}
