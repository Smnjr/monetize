package br.com.sistema.controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.sistema.exception.ApplicationException;
import br.com.sistema.exception.BusinessException;
import br.com.sistema.model.Usuario;
import br.com.sistema.service.UsuarioService;
import br.com.sistema.util.Mensagem;
import br.com.sistema.util.TipoMensagem;

@Controller
public class UsuarioController extends BaseController {

	@Autowired
	RequestCache requestCache;

	@Autowired
	protected AuthenticationManager authenticationManager;

	static final Logger logger = Logger.getLogger(UsuarioController.class);

	@Autowired(required = true)
	private UsuarioService service;

	@RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT})
	public String login(ModelMap model) {
		try {
			service.verificarPerfisExistentes();
		} catch (ApplicationException e) {
			logger.error(e.getMessage());
		}
		return "/credenciais.jsp";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.addObject("usuario", new Usuario());
		if (error != null) {
			model.addObject("mensagem",
					new Mensagem(getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"), TipoMensagem.ERRO));
		}
		model.setViewName("/credenciais.jsp");
		return model;
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public ModelAndView home(Model model) {
		if (isAuthenticated()) {
			model.addAttribute("usuario", getUsuarioLogado());
		}
		return new ModelAndView("home");
	}

	@RequestMapping(value = { "user" }, method = RequestMethod.GET)
	public ModelAndView user(Model model) {
		if (isAuthenticated()) {
			Usuario user = getUsuarioLogado();
			user.setPassword(null);
			model.addAttribute("usuario", user);
		}
		return new ModelAndView("user");
	}


	/**
	 * Efetua registro, loga em seguida.
	 *
	 * @param usuario
	 * @param model
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value= "/salvarUsuario", method = RequestMethod.POST)
	public ModelAndView executarRegistro(Usuario usuario, HttpServletRequest request, Model model) {
		try {
			logger.debug("Salvando o usuario " + usuario.getUsername());
			service.create(usuario);
			efetuarLogin(usuario, request);

		} catch (BusinessException e) {
			new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (ApplicationException ex) {
			new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return home(model);
	}

	private void efetuarLogin(Usuario usuario, HttpServletRequest request) throws ApplicationException {
		try {
			Usuario principal = service.findByLogin(usuario.getUsername());
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(principal.getUsername(),
					getGrantedAuthorities(principal));
			request.getSession();
			token.setDetails(new WebAuthenticationDetails(request));
			Authentication authenticatedUser = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
		} catch (Exception e) {
			throw new ApplicationException(getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}
	}

	private List<GrantedAuthority> getGrantedAuthorities(Usuario user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getPerfilUsuario()));
		return authorities;
	}


	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	private String getErrorMessage(HttpServletRequest request, String key) {
		Exception exception = (Exception) request.getSession().getAttribute(key);
		String error = "";
		if(exception!=null && !exception.equals("")){
			error = exception.getMessage();
		}
		return error;
	}


	@ResponseBody
	@RequestMapping(value = "/isUsernameValido")
	public Boolean isUsernameValido(String username) {
		logger.warn("Validando o login do usuario " + username);
		Boolean isValido = false;
		String nomeUsuarioLogado = null;
		try {
			if (isAuthenticated()) {
				nomeUsuarioLogado = getUsuarioLogado().getUsername();

			}
			isValido = service.isUsernameValido(username.trim(), nomeUsuarioLogado);
		} catch (ApplicationException e) {
			logger.error(e + e.getCause().getMessage());
		}
		return isValido;
	}

	@ResponseBody
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) {
		try {
			service.update(usuario);
		} catch (ApplicationException ex) {
			new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Sucesso", HttpStatus.OK);
	}
}
