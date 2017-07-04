package br.com.sistema.service;

import java.util.List;

import br.com.sistema.exception.ApplicationException;

public interface GenericService<E, K> {

	void save(E entity) throws ApplicationException;

	void saveOrUpdate(E entity) throws ApplicationException;

	void update(E entity) throws ApplicationException;

	void delete(E entity) throws ApplicationException;

	E find(K key) throws ApplicationException;

	List<E> findAll() throws ApplicationException;

}
