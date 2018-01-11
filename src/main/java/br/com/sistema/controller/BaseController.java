package br.com.sistema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.sistema.model.Usuario;
import br.com.sistema.service.UsuarioService;

public class BaseController {

	@Autowired
	UsuarioService usuarioService;

	protected String getPrincipal() {
		String userName = null;
		Object principal = null;
		String nome = null;
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (principal instanceof UserDetails) {
				userName = ((UserDetails) principal).getUsername();
			} else {
				userName = principal.toString();
			}

			nome = usuarioService.findByLogin(userName).getNome();
		}
		return nome;
	}

	protected boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && !(authentication instanceof AnonymousAuthenticationToken)
				&& authentication.isAuthenticated();
	}

	protected Usuario getUsuarioLogado() {
		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario;
		if (usuarioLogado instanceof UserDetails) {
			String username = ((UserDetails) usuarioLogado).getUsername();
			usuario = usuarioService.findByLogin(username);
		} else {
			usuario = (Usuario) usuarioLogado;
		}
		return usuario;
	}

}
