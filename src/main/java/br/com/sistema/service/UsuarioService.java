package br.com.sistema.service;

import br.com.sistema.exception.ApplicationException;
import br.com.sistema.exception.BusinessException;
import br.com.sistema.model.Usuario;

public interface UsuarioService extends GenericService<Usuario, Integer> {

	void create(Usuario usuario) throws BusinessException, ApplicationException;

	Boolean isUsernameValido(String userName, String usernameUsuarioLogado) throws ApplicationException;

	Usuario findByLogin(String username);

	void verificarPerfisExistentes() throws ApplicationException;

}