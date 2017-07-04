package br.com.sistema.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.sistema.dao.UsuarioDao;
import br.com.sistema.exception.ApplicationException;
import br.com.sistema.model.Usuario;

@Repository
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario, Integer> implements UsuarioDao {

	/**
	 *
	 */
	private static final long serialVersionUID = 7430764372399065553L;

	@Override
	public Usuario findByNameAndEmail(String username, String email) throws ApplicationException {
		Query q = currentSession().createQuery("FROM Usuario u where u.username = ? and u.email=?");
		q.setParameter(0, username);
		q.setParameter(1, email);
		return (Usuario) q.uniqueResult();
	}

	@Override
	public Usuario findByLogin(String username)  {
		Query q = currentSession().createQuery("FROM Usuario u where u.username =?");
		q.setParameter(0, username);
		return (Usuario) q.uniqueResult();
	}

}
