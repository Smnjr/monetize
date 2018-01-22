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
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.sistema.exception.ApplicationException;
import br.com.sistema.exception.BusinessException;
import br.com.sistema.model.Usuario;
import br.com.sistema.seguranca.CustomUserDetailsService;
import br.com.sistema.service.UsuarioService;
import br.com.sistema.util.Mensagem;
import br.com.sistema.util.TipoMensagem;

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
	public String login(ModelMap model) {
		try {
			service.verificarPerfisExistentes();
		} catch (ApplicationException e) {
			logger.error(e.getMessage());
		}
		return "/credenciais.jsp";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) throws ApplicationException  {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("mensagem",
					new Mensagem(getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"), TipoMensagem.ERRO));
		}
		model.setViewName("/credenciais.jsp");
		return model;
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
		} catch (ApplicationException e) {
			return new ResponseEntity<String>(messageSource.getMessage("create.error", null, ptBR) +  e.getCause().getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	private void efetuarLogin(Usuario usuario, HttpServletRequest request, Model model)
			throws ApplicationException {
		try {
			UserDetails userDetails = customUserDetailsService.loadUserByUsername(usuario.getUsername());
			Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,
					userDetails.getPassword(), userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception e) {
			SecurityContextHolder.getContext().setAuthentication(null);
			throw new ApplicationException(getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}
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
