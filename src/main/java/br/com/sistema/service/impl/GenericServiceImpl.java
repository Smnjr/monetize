package br.com.sistema.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.dao.GenericDao;
import br.com.sistema.exception.ApplicationException;
import br.com.sistema.service.GenericService;

@Service
public abstract class GenericServiceImpl<E, K> implements GenericService<E, K> {

	private GenericDao<E, K> genericDao;

	public GenericServiceImpl(GenericDao<E, K> genericDao) {
		this.genericDao = genericDao;
	}

	public GenericServiceImpl() {

	}

	@Override
	@Transactional
	public void saveOrUpdate(E entitty) throws ApplicationException {
		genericDao.saveOrUpdate(entitty);
	}

	@Override
	@Transactional(readOnly = true)
	public List<E> findAll() throws ApplicationException {
		return genericDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public E find(K id) throws ApplicationException {
		return genericDao.find(id);
	}

	@Override
	@Transactional
	public void save(E entity) throws ApplicationException {
		genericDao.save(entity);
	}

	@Override
	@Transactional
	public void update(E entity) throws ApplicationException {
		genericDao.update(entity);
	}

	@Override
	@Transactional
	public void delete(E entity) throws ApplicationException {
		genericDao.delete(entity);
	}
}
