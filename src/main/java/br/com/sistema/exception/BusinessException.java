package br.com.sistema.exception;

import br.com.sistema.util.TipoMensagem;

public class BusinessException extends Exception {
	private TipoMensagem tipoMensagem;
	private String errorCode;

	private static final long serialVersionUID = 1L;

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String msg, Throwable cause, TipoMensagem tipoMensagem, String errorCode) {
		super(msg, cause);
		this.tipoMensagem = tipoMensagem;
		this.errorCode = errorCode;
	}
	
	public BusinessException(String msg) {
		super(msg);
	}


	public BusinessException(String msg, TipoMensagem tipoMensagem) {
		super(msg);
		this.tipoMensagem = tipoMensagem;
	}

	public BusinessException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * Obtem o valor do atributo tipoMensagem
	 * 
	 * @return the tipoMensagem
	 */
	public TipoMensagem getTipoMensagem() {
		return tipoMensagem;
	}

	/**
	 * atribui o valor do atributo tipoMensagem
	 * 
	 * @param tipoMensagem
	 */
	public void setTipoMensagem(TipoMensagem tipoMensagem) {
		this.tipoMensagem = tipoMensagem;
	}

	/**
	 * Obtém o valor do atributo errorCode
	 * 
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * atribui o valor do atributo errorCode
	 * 
	 * @param errorCode
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
