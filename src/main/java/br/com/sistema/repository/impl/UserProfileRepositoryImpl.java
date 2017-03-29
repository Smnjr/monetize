package br.com.sistema.repository.impl;

import org.springframework.stereotype.Repository;

import br.com.sistema.model.PerfilUsuario;
import br.com.sistema.repository.UserProfileRepository;
@Repository
public class UserProfileRepositoryImpl extends GenericRepositoryImpl<PerfilUsuario> implements UserProfileRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6438123737738832219L;

}
