package br.com.sistema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.sistema.dao.GenericDao;
import br.com.sistema.dao.PerfilDao;
import br.com.sistema.model.Perfil;
import br.com.sistema.service.PerfilService;

@Service
public class PerfilServiceImpl extends GenericServiceImpl<Perfil, Integer>
implements PerfilService {

	private PerfilDao perfilDao;

	public PerfilServiceImpl() {
	}

	@Autowired
	public PerfilServiceImpl(@Qualifier("perfilDaoImpl") GenericDao<Perfil, Integer> genericDao) {
		super(genericDao);
		this.perfilDao = (PerfilDao) genericDao;
	}


}
