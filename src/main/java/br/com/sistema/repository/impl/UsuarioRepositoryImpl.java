package br.com.sistema.repository.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.sistema.exception.ApplicationException;
import br.com.sistema.model.Usuario;
import br.com.sistema.repository.UsuarioRepository;

@Repository
public class UsuarioRepositoryImpl extends GenericRepositoryImpl<Usuario> implements UsuarioRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7430764372399065553L;
	
	public Usuario findByNameAndEmail(String username, String email) throws ApplicationException {
			Query q = getSession().createQuery(("FROM Usuario u where u.username = ? and u.email=?"));
			q.setParameter(0, username);
			q.setParameter(1, email);
			return (Usuario) q.uniqueResult();
	}

    public Usuario findByLogin(String username)  {
		Query q = getSession().createQuery(("FROM Usuario u where u.username = ? "));
		q.setParameter(0, username);
		return (Usuario) q.uniqueResult();
	}
}
