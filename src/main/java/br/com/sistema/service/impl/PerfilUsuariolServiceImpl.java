package br.com.sistema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.sistema.dao.GenericDao;
import br.com.sistema.dao.PerfilUsuarioDao;
import br.com.sistema.model.PerfilUsuario;
import br.com.sistema.service.PerfilUsuarioService;

@Service
public class PerfilUsuariolServiceImpl extends GenericServiceImpl<PerfilUsuario, Integer>
implements PerfilUsuarioService {

	private PerfilUsuarioDao perfilUsuarioDao;

	public PerfilUsuariolServiceImpl() {
	}

	@Autowired
	public PerfilUsuariolServiceImpl(@Qualifier("perfilUsuarioDaoImpl") GenericDao<PerfilUsuario, Integer> genericDao) {
		super(genericDao);
		this.perfilUsuarioDao = (PerfilUsuarioDao) genericDao;
	}

}
