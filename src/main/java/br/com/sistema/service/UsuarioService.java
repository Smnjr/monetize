package br.com.sistema.service;

import br.com.sistema.exception.ApplicationException;
import br.com.sistema.exception.BusinessException;
import br.com.sistema.model.Usuario;

public interface UsuarioService extends GenericService<Usuario, Integer> {

	public void create(Usuario usuario) throws BusinessException, ApplicationException;

	public void validarUsername(String userName) throws BusinessException, ApplicationException;

	public Usuario findByLogin(String username) ;

}