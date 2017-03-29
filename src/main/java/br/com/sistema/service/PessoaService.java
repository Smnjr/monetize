package br.com.sistema.service;

import java.util.List;

import br.com.sistema.exception.ApplicationException;
import br.com.sistema.exception.BusinessException;
import br.com.sistema.model.Pessoa;

public interface PessoaService {
	public void create(Pessoa p) throws BusinessException, ApplicationException;

	public void update(Pessoa p) throws BusinessException, ApplicationException;

	public void delete(Pessoa p) throws BusinessException, ApplicationException;

	public List<Pessoa> findAll() throws BusinessException, ApplicationException;
}
