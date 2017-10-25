package br.com.sistema.exception;

/**
 * Classe responsavel pelo tratamento das exceções originadas pelos erros de
 * sistema.
 * 
 * @author francisco.menezes
 * 
 */
public class ApplicationException extends Exception {

	private static final long serialVersionUID = -6476707710829821325L;

	/**
	 * Cria um objeto {@link ApplicationException}.
	 *
	 * @param message
	 */
	public ApplicationException(String message) {
		super(message);
	}

	/**
	 * Cria um objeto {@link ApplicationException}.
	 *
	 * @param message
	 * @param cause
	 */
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * @param cause
	 */
	public ApplicationException(Throwable cause) {
		super(cause);
	}

}