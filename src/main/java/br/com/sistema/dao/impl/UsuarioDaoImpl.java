package br.com.sistema.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.sistema.dao.UsuarioDao;
import br.com.sistema.exception.ApplicationException;
import br.com.sistema.model.Usuario;

@Repository
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario, Integer> implements UsuarioDao {

	static final Logger logger = Logger.getLogger(GenericDaoImpl.class);


	@Override
	public Usuario findByLogin(String username) {
		Query q = currentSession().createQuery("FROM Usuario u where u.username =?");
		q.setParameter(0, username);
		return (Usuario) q.uniqueResult();
	}

	@Override
	public Boolean isUsernameValido(String username) throws ApplicationException {
		try {
			Query q = currentSession().createQuery("FROM Usuario u where u.username =?");
			q.setParameter(0, username);
		} catch (Exception e) {
			return q.list().size() == 0;
			throw new ApplicationException(e);
	}
}