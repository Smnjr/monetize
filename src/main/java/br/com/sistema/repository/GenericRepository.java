package br.com.sistema.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sistema.exception.ApplicationException;

public interface GenericRepository<T> extends Serializable{

	void create(T t) throws ApplicationException ;

	void delete(Object id) throws ApplicationException ;

	T find(Object id) throws ApplicationException ;

	void update(T t) throws ApplicationException ;
	
	List<T> findAll() throws ApplicationException ;
	
}