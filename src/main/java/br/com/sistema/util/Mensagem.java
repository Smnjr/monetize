package br.com.sistema.util;

public class Mensagem {
	private String texto;
	private TipoMensagem tipoMensagem;
	
	public Mensagem(String texto, TipoMensagem tipoMensagem) {
	     this.texto = texto;
	     this.tipoMensagem = tipoMensagem;
	   }
	
	public Mensagem(String texto) {
	     this.texto = texto;
	   }

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public TipoMensagem getTipoMensagem() {
		return tipoMensagem;
	}

	public void setTipoMensagem(TipoMensagem tipoMensagem) {
		this.tipoMensagem = tipoMensagem;
	}
}
