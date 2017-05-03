package br.com.sistema.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sistema.exception.ApplicationException;
import br.com.sistema.exception.BusinessException;
import br.com.sistema.model.Usuario;
import br.com.sistema.util.Mensagem;
import br.com.sistema.util.TipoMensagem;

@Controller
public class UsuarioController extends BaseController {
	
	@Autowired
	  @Qualifier("authenticationManager")
	  AuthenticationManager authenticationManager;
	
	@Autowired
	private br.com.sistema.service.UsuarioService service;
	
	@RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT})
	public String login(ModelMap model) {
		model.addAttribute("usuario", getPrincipal());
		return "/credenciais.jsp";
	}
	
	  
	  @RequestMapping(method = RequestMethod.GET, value="/login")
	  public ModelAndView login(
		  @RequestParam(value = "error", required = false) String error,
		  @RequestParam(value = "logout", required = false) String logout,
		  HttpServletRequest request) {
			  ModelAndView model = new ModelAndView();
			  model.addObject("usuario", new Usuario());
			  if(error!=null){
				  model.addObject("mensagem", new Mensagem( getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"), TipoMensagem.ERRO));
			  }
			  model.setViewName("/credenciais.jsp");
			  return model;
	  }
	  
	  @RequestMapping(value = { "/registrarUsuario" }, method = RequestMethod.GET)
	  public ModelAndView carregarPaginaCadastro(Model model) {
		  Usuario usuario = new Usuario();
		  model.addAttribute("usuario", usuario);
		  return new ModelAndView("/registro.jsp");
	  }
	
	
	@RequestMapping(value= "/salvarUsuario", method = RequestMethod.POST)
	public String executarRegistro(Usuario usuario, Model model, HttpServletRequest req){
		try {
			service.create(usuario);
			model.addAttribute("usuario", usuario);
			model.addAttribute("mensagem", new Mensagem("Sucesso ao cadastrar o usuário.", TipoMensagem.SUCESSO));

		} catch (BusinessException e) {
			model.addAttribute("mensagem", new Mensagem(e.getMessage(), TipoMensagem.ERRO));
		} catch (ApplicationException ex) {
			model.addAttribute("mensagem", new Mensagem(ex.getMessage(), TipoMensagem.ERRO));
		}
		return "S/registro.jsp";
	}

	
	@RequestMapping(value = "/usuario/{id}",  method = RequestMethod.PUT)
	public  ResponseEntity<Void> atualizar(@PathVariable("id")  @RequestBody Usuario usuario, Model model,   UriComponentsBuilder ucBuilder){
		HttpHeaders headers = new HttpHeaders();
		try {
			service.update(usuario);
			model.addAttribute("usuario", usuario);
			model.addAttribute("mensagem", new Mensagem("Sucesso ao alterar o usuário.", TipoMensagem.SUCESSO));
		} catch (BusinessException e) {
			model.addAttribute("mensagem", new Mensagem(e.getMessage(), e.getTipoMensagem()));
		} catch (ApplicationException ex) {
			model.addAttribute("mensagem", new Mensagem(ex.getMessage() + ex.getCause().getMessage(), TipoMensagem.ERRO));
		}
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);

		}
		return "/credenciais.jsp";
	}
	
		private String getErrorMessage(HttpServletRequest request, String key) {
			Exception exception = (Exception) request.getSession().getAttribute(key);
			String error = "";
			if(exception!=null && !exception.equals("")){
				error = exception.getMessage();
			}

			return error;
		}
		
		 
}
