package br.com.sistema.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sistema.dao.GenericDao;
import br.com.sistema.exception.ApplicationException;

@Repository
public abstract class GenericDaoImpl<E, K extends Serializable> implements GenericDao<E, K> {

	@Autowired
	private SessionFactory sessionFactory;

	Locale ptBR = new Locale("pt", "BR");
	ResourceBundle messages = ResourceBundle.getBundle("sistema", ptBR);

	static final Logger logger = Logger.getLogger(GenericDaoImpl.class);

	protected Class<? extends E> daoType;

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		java.lang.reflect.Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		daoType = (Class) pt.getActualTypeArguments()[0];
	}

	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(E entity) throws ApplicationException {
		try {
			currentSession().save(entity);

		} catch (Exception e) {
			logger.error("Erro ao salvar registro " + e.getMessage());
			throw new ApplicationException(messages.getString("create.error"), e);
		}
	}

	@Override
	public void saveOrUpdate(E entity) throws ApplicationException {
		try {
			currentSession().saveOrUpdate(entity);
		} catch (Exception e) {
			logger.error("Erro ao atualizar registro " + e.getMessage());
			throw new ApplicationException(messages.getString("update.error"), e);
		}
	}

	@Override
	public void update(E entity) throws ApplicationException {
		try {
			currentSession().merge(entity);
		} catch (Exception e) {
			logger.error("Erro ao atualizar registro " + e.getMessage());
			throw new ApplicationException(messages.getString("update.error"), e);
		}
	}

	@Override
	public void delete(E entity) throws ApplicationException {
		try {
			currentSession().delete(entity);
		} catch (Exception e) {
			logger.error("Erro ao excluir registro " + e.getMessage());
			throw new ApplicationException(messages.getString("delete.error"), e);
		}
	}


	@Override
	@SuppressWarnings("unchecked")
	public E find(K key) throws ApplicationException {
		try {
			return (E) currentSession().get(daoType, key);
		} catch (Exception e) {
			logger.error("Erro ao obter registro " + e.getMessage());
			throw new ApplicationException(messages.getString("query.error"), e);
		}
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<E> findAll() throws ApplicationException {
		try {
			return currentSession().createCriteria(daoType).list();
		} catch (Exception e) {
			logger.error("Erro ao obter registro " + e.getMessage());
			throw new ApplicationException(messages.getString("query.error"), e);
		}
	}
}