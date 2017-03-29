package br.com.sistema.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistema.exception.ApplicationException;
import br.com.sistema.exception.BusinessException;
import br.com.sistema.model.Pessoa;
import br.com.sistema.repository.PessoaRepository;
import br.com.sistema.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2808929077091503397L;
	
	@Autowired
	private PessoaRepository repository;

	Locale ptBR = new Locale("pt", "BR");
	ResourceBundle messages = ResourceBundle.getBundle("sistema", ptBR);

	public void create(Pessoa pessoa) throws ApplicationException {
		repository.create(pessoa);
	}

	public void delete(Pessoa pessoa) throws ApplicationException {
		repository.delete(pessoa);
	}

	public void update(Pessoa p) throws ApplicationException {
		repository.update(p);
	}

	public List<Pessoa> findAll() throws ApplicationException {
		return repository.findAll();
	}

}
