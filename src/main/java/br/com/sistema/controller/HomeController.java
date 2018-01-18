package br.com.sistema.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.sistema.exception.ApplicationException;
import br.com.sistema.model.Usuario;
import br.com.sistema.model.UsuarioVO;
import br.com.sistema.service.UsuarioService;
import br.com.sistema.util.GenerateHashPasswordUtil;

@Controller
public class HomeController extends BaseController  {

	static final Logger logger = Logger.getLogger(HomeController.class);

	@Autowired(required = true)
	private UsuarioService service;
	
	@RequestMapping(value = { "/home/user" }, method = RequestMethod.GET)
	public ModelAndView user(Model model) {
		if (isAuthenticated()) {
			Usuario user = getUsuarioLogado();
			user.setPassword(null);
			model.addAttribute("usuario", user);
		}
		return new ModelAndView("user");
	}

	@ResponseBody
	@RequestMapping(value = "/editarPerfilUsuario", method = RequestMethod.POST)
	public void atualizar(@RequestBody UsuarioVO usuarioVo) {
		try {
			Usuario usuario = getUsuarioLogado();
			usuario.setEmail(usuarioVo.getEmail());
			usuario.setNome(usuarioVo.getName());
			String passwordHash = GenerateHashPasswordUtil.generateHash(usuarioVo.getPassword());
			usuario.setPassword(passwordHash);
			service.update(usuario);
		} catch (ApplicationException ex) {
			new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
