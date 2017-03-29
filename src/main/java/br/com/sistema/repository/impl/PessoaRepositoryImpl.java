package br.com.sistema.repository.impl;

import org.springframework.stereotype.Repository;

import br.com.sistema.model.Pessoa;
import br.com.sistema.repository.PessoaRepository;
@Repository
public class PessoaRepositoryImpl extends GenericRepositoryImpl<Pessoa> implements PessoaRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6438123737738832219L;

}
