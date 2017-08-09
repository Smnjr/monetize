package br.com.sistema.dao;

import br.com.sistema.exception.ApplicationException;
import br.com.sistema.model.Usuario;

public interface UsuarioDao extends GenericDao<Usuario, Integer> {

	Usuario findByNameAndEmail(String username, String email)  throws ApplicationException;

	Usuario findByLogin(String username) ;

	boolean isUsernameExisting(String username) throws ApplicationException;

}