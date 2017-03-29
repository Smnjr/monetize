package br.com.sistema.service;

import java.util.List;

import br.com.sistema.exception.ApplicationException;
import br.com.sistema.exception.BusinessException;
import br.com.sistema.model.Usuario;

public interface UsuarioService {

	public void create(Usuario usuario) throws BusinessException, ApplicationException;

	public void update(Usuario u) throws BusinessException, ApplicationException;

	public void delete(Usuario u) throws BusinessException, ApplicationException;

	public List<Usuario> findAll() throws BusinessException, ApplicationException;
	
	public Usuario findByLogin(String username) ;
	
}