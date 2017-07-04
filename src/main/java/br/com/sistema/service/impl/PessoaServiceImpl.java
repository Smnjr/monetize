package br.com.sistema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.sistema.dao.GenericDao;
import br.com.sistema.dao.PessoaDao;
import br.com.sistema.model.Pessoa;
import br.com.sistema.service.PessoaService;

@Service
public class PessoaServiceImpl extends GenericServiceImpl<Pessoa, Integer> implements PessoaService {

	private PessoaDao pessoaDao;

	public PessoaServiceImpl() {
	}

	@Autowired
	public PessoaServiceImpl(@Qualifier("pessoaDaoImpl") GenericDao<Pessoa, Integer> genericDao) {
		super(genericDao);
		this.pessoaDao = (PessoaDao) genericDao;
	}
}
