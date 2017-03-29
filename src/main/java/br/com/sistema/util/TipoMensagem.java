package br.com.sistema.util;

public enum TipoMensagem {
	ERRO("alert alert-danger"),
	AVISO("alert alert-warning"),
	SUCESSO("alert alert-success");

	private String classeCss;

	private TipoMensagem(String classeCss) {
		this.classeCss = classeCss;
	}

	public String getClasseCss() {
		return classeCss;
	}

	public void setClasseCss(String classeCss) {
		this.classeCss = classeCss;
	}
}
