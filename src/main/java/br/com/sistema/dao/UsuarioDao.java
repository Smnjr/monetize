package br.com.sistema.dao;

import br.com.sistema.exception.ApplicationException;
import br.com.sistema.model.Usuario;

public interface UsuarioDao extends GenericDao<Usuario, Integer> {

	Usuario findByLogin(String username) ;

	Boolean isUsernameValido(String username) throws ApplicationException;

}