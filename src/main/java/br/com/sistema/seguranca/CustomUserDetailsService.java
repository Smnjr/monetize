package br.com.sistema.seguranca;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.exception.ApplicationException;
import br.com.sistema.model.Usuario;
import br.com.sistema.service.PerfilService;
import br.com.sistema.service.UsuarioService;

@Qualifier("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger logger = Logger.getLogger(CustomUserDetailsService.class);

	@Autowired
	MessageSource messageSource;
	Locale ptBR = new Locale("pt", "BR");

	@Autowired
	private UsuarioService userService;

	@Autowired
	private PerfilService perfilService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = userService.findByLogin(username);
		validaUsuario(usuario);
		return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, getGrantedAuthorities(usuario));
	}

	private void validaUsuario(Usuario usuario) {
		if (usuario.getId() == null) {
			throw new UsernameNotFoundException(messageSource.getMessage("usuario.notfound", null, ptBR));
		}
	}

	private List<GrantedAuthority> getGrantedAuthorities(Usuario user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		try {
			authorities.add(new SimpleGrantedAuthority(
					perfilService.find(user.getPerfilUsuario().getId()).getTipoPerfil().getPerfil()));
		} catch (ApplicationException e) {
			logger.info("Erro ao obter o perfil :" + e.getMessage());
			e.printStackTrace();
		}
		return authorities;
	}
}