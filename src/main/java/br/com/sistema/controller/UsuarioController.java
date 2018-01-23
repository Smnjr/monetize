package br.com.sistema.controller;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.sistema.exception.ApplicationException;
import br.com.sistema.exception.BusinessException;
import br.com.sistema.model.Usuario;
import br.com.sistema.seguranca.CustomUserDetailsService;
import br.com.sistema.service.UsuarioService;

@Controller
@RequestMapping("/")
public class UsuarioController extends BaseController {

	@Autowired
	RequestCache requestCache;

	@Autowired
	protected CustomUserDetailsService customUserDetailsService;

	static final Logger logger = Logger.getLogger(UsuarioController.class);

	@Autowired(required = true)
	private UsuarioService service;

	Locale ptBR = new Locale("pt", "BR");

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT})
	public ModelAndView index(Model model) {
		try {
			service.verificarPerfisExistentes();
		} catch (ApplicationException e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView("/credenciais.jsp");
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public  ResponseEntity<?> login(@RequestBody Usuario usuario, HttpServletRequest request, Model model) throws ApplicationException  {
		try {
			efetuarLogin(usuario, request, model);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (UsernameNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public ModelAndView home(Model model) throws ApplicationException {
		if (isAuthenticated()) {
			Usuario usuarioLogado = getUsuarioLogado();
			model.addAttribute("usuario", usuarioLogado);
			usuarioLogado.setUltimoLogin(new Date());
			usuarioService.update(usuarioLogado);
		}
		return new ModelAndView("home");
	}


	@RequestMapping(value = { "/principal" }, method = RequestMethod.GET)
	public ModelAndView principal(Model model) {
		if (isAuthenticated()) {
			model.addAttribute("usuario", getUsuarioLogado());
		}
		return new ModelAndView("home");
	}

	@RequestMapping(value = { "user" }, method = RequestMethod.GET)
	public ModelAndView user(Model model)  {
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
	 * @throws ApplicationException
	 * @throws BusinessException
	 */
	@ResponseBody
	@RequestMapping(value = "/salvarUsuario", method = RequestMethod.POST)
	public ResponseEntity<?> executarRegistro(@RequestBody Usuario usuario, Model model, HttpServletRequest request)
	{
		try {
			service.create(usuario);
			efetuarLogin(usuario, request, model);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (BusinessException ex) {
			return new ResponseEntity<String>(messageSource.getMessage("create.error", null, ptBR) + ex.getCause().getMessage(), HttpStatus.BAD_REQUEST);
		} catch (UsernameNotFoundException e) {
			return new ResponseEntity<String>(messageSource.getMessage("create.error", null, ptBR) +  e.getCause().getMessage(), HttpStatus.BAD_REQUEST);
		} catch (ApplicationException e) {
			return new ResponseEntity<String>(
					messageSource.getMessage("create.error", null, ptBR) + e.getCause().getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	private void efetuarLogin(Usuario usuario, HttpServletRequest request, Model model)
			throws UsernameNotFoundException {
		try {
			UserDetails userDetails = customUserDetailsService.loadUserByUsername(usuario.getUsername());
			Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,
					userDetails.getPassword(), userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (UsernameNotFoundException e) {
			SecurityContextHolder.getContext().setAuthentication(null);
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logoutPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return index(model);
	}


	@ResponseBody
	@RequestMapping(value = "/isUsernameValido")
	public ResponseEntity<?> isUsernameValido(String username) {
		logger.warn("Validando o login do usuario " + username);
		Boolean isValido = false;
		String nomeUsuarioLogado = null;
		try {
			if (isAuthenticated()) {
				if (getUsuarioLogado() != null) {
					nomeUsuarioLogado = getUsuarioLogado().getUsername();
				}
			}
			isValido = service.isUsernameValido(username.trim(), nomeUsuarioLogado);
			return new ResponseEntity<Boolean>(isValido, HttpStatus.OK);
		} catch (ApplicationException e) {
			logger.error(e + e.getCause().getMessage());
			return new ResponseEntity<String>(messageSource.getMessage("user.validate.error", null, ptBR),
					HttpStatus.BAD_REQUEST);
		}
	}



}
