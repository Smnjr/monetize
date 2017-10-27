package br.com.sistema.dao.impl;

import org.springframework.stereotype.Repository;


import br.com.sistema.dao.PessoaDao;
import br.com.sistema.model.Pessoa;
@Repository
public class PessoaDaoImpl extends GenericDaoImpl<Pessoa, Integer> implements PessoaDao {


}
