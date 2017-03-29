package br.com.sistema.repository;

import br.com.sistema.exception.ApplicationException;
import br.com.sistema.model.Usuario;

public interface UsuarioRepository extends GenericRepository<Usuario>{

	Usuario findByNameAndEmail(String username, String email)  throws ApplicationException;
	
	Usuario findByLogin(String username) ;

	
}