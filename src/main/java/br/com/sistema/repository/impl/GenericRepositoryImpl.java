package br.com.sistema.repository.impl;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sistema.exception.ApplicationException;
import br.com.sistema.repository.GenericRepository;

@Repository
public abstract class GenericRepositoryImpl<T> implements GenericRepository<T> {
	
	Locale ptBR = new Locale("pt", "BR");
	ResourceBundle messages = ResourceBundle.getBundle("sistema", ptBR);
	Logger logger;

	/**
	 * 
	 */
	private static final long serialVersionUID = 4053899609015410292L;

	@Autowired
	private SessionFactory sessionFactory;

	private Class<T> type;

	public void create(final T t) throws ApplicationException {
		try {
			getSession().save(t);
			sessionFactory.getCurrentSession().flush();

		} catch (Exception e) {
			logger.error("Erro ao criar sessão " + e.getMessage());
			throw new ApplicationException(messages.getString("create.error"), e);
		}
	}
	public void delete(final Object id) throws ApplicationException {
		try {
			getSession().delete(id);
			getSession().flush();
		} catch (Exception e) {
			logger.error("Erro ao excluir registro " + e.getMessage());
			throw new ApplicationException(messages.getString("delete.error"), e);
		}
	}

	public void update(final T t) throws ApplicationException {
		try {
			getSession().merge(t);
			getSession().flush();
		} catch (Exception e) {
			logger.error("Erro ao atualizar registro " + e.getMessage());
			throw new ApplicationException(messages.getString("update.error"), e);
		}
	}

	@SuppressWarnings("unchecked")
	public T find(final Object id) throws ApplicationException {
		try {
			return (T) getSession().byId(type);
		} catch (Exception e) {
			logger.error("Erro ao obter registro " + e.getMessage());
			throw new ApplicationException(messages.getString("query.error"), e);
		}
	}
	@SuppressWarnings("unchecked")
	public List<T> findAll() throws ApplicationException {
		try {
			return sessionFactory.getCurrentSession().createCriteria(type).list();
		} catch (Exception e) {
			logger.error("Erro ao obter registro " + e.getMessage());
			throw new ApplicationException(messages.getString("query.error"), e);
		}
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}