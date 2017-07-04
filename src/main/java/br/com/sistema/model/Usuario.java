package br.com.sistema.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "USUARIO")
public class Usuario extends BaseEntity<Integer> {
	private static final long serialVersionUID = -7590317347612436291L;

	@Column(name = "username",  unique = true, nullable = false, length = 128)
	private String username;

	@Column(name = "password",  unique = true, nullable = false, length = 100)
	private String password;

	@Transient
	private String confirmacaoSenha;

	@Column(name = "email", nullable = false, length = 128)
	private String email;

	@Column(name="data_cadastro", nullable=false) @Temporal(TemporalType.TIMESTAMP)

	private Date dataCadastro;

	@Column(name = "data_ultimo_login", nullable = true, length = 128)
	private Date ultimoLogin;

	@JsonIgnore
	@OneToOne(mappedBy = "usuario" )
	private Pessoa pessoa;

	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "USUARIO_ID")
	private PerfilUsuario perfilUsuario;


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getUltimoLogin() {
		return ultimoLogin;
	}

	public void setUltimoLogin(Date ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PerfilUsuario getPerfilUsuario() {
		return perfilUsuario;
	}

	public void setPerfilUsuario(PerfilUsuario perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}


}