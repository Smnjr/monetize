package br.com.sistema.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PESSOA")
public class Pessoa extends BaseEntity<Integer> {

	/**
	 *
	 */
	private static final long serialVersionUID = 7737393571268898794L;

	@OneToOne
	@JoinColumn(name = "USUARIO_ID")
	private Usuario usuario;

	@Column(length = 60, nullable = false, name = "NOME")
	private String nome;

	@Column(length = 12, nullable = false, name = "CPF")
	private String CPF;

	@Column(length = 60, nullable = false, name = "RG")
	private String RG;

	@Temporal(TemporalType.DATE)
	@Column(length = 60, nullable = false, name = "DATA_NASCIMENTO")
	private Date dataNascimento;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rG) {
		RG = rG;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}




}
