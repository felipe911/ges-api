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
@Table(name = "empresa")
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(name = "razao_social")
	private String razaoSocial;

	@NotNull
	@Column(name = "contato_responsavel")
	private String contatoResponsavel;

	@NotNull
	@Column(name = "endereco")
	private String endereco;

	@NotNull
	@Column(name = "cep")
	private String cep;

	@NotNull
	@Column(name = "bairro")
	private String bairro;

	@NotNull
	@Column(name = "cidade")
	private String cidade;

	@NotNull
	@Column(name = "uf")
	private char uf;

	@NotNull
	@Column(name = "numero")
	private int numero;

	@NotNull
	@Column(name = "telefone")
	private String telefone;

	@NotNull
	@Column(name = "email")
	private String email;

	@NotNull
	@Column(name = "prazo_convenio")
	private LocalDate prazoConvenio;

	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
	private Set<Contrato> contrato;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getContatoResponsavel() {
		return contatoResponsavel;
	}

	public void setContatoResponsavel(String contatoResponsavel) {
		this.contatoResponsavel = contatoResponsavel;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public char getUf() {
		return uf;
	}

	public void setUf(char uf) {
		this.uf = uf;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getPrazoConvenio() {
		return prazoConvenio;
	}

	public void setPrazoConvenio(LocalDate prazoConvenio) {
		this.prazoConvenio = prazoConvenio;
	}

	public Set<Contrato> getContrato() {
		return contrato;
	}

	public void setContrato(Set<Contrato> contrato) {
		this.contrato = contrato;
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
		Empresa other = (Empresa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
