package br.com.sistema.model;

import com.fasterxml.jackson.annotation.JsonView;

public class UsuarioVO {

	@JsonView(Views.Public.class)
	private Long id;

	@JsonView(Views.Public.class)
	private String username;

	@JsonView(Views.Public.class)
	private String name;

	@JsonView(Views.Public.class)
	private String email;

	@JsonView(Views.Public.class)
	private String password;

	@JsonView(Views.Public.class)
	private String confirmacaoSenha;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

}
