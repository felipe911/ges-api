package br.com.ges.api.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "aluno")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(name = "nome")
	private String nome;

	@NotNull
	@Column(name = "ra")
	private String ra;

	@NotNull
	@Column(name = "curso")
	private String curso;

	@NotNull
	@Column(name = "semestre")
	private int semestre;

	@NotNull
	@Column(name = "periodo")
	private int periodo;

	@NotNull
	@Column(name = "email")
	private String email;

	@NotNull
	@Column(name = "telefone")
	private String telefone;

	@NotNull
	@Column(name = "sexo")
	private char sexo;

	@NotNull
	@Column(name = "data_vestibular")
	private LocalDate dataVestibular;

	@OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
	private Set<Estagio> estagio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDataVestibular() {
		return dataVestibular;
	}

	public void setDataVestibular(LocalDate dataVestibular) {
		this.dataVestibular = dataVestibular;
	}

	public Set<Estagio> getEstagio() {
		return estagio;
	}

	public void setEstagio(Set<Estagio> estagio) {
		this.estagio = estagio;
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
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}