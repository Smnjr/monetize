package br.com.sistema.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.sistema.model.Usuario;
import br.com.sistema.model.UsuarioVO;
import br.com.sistema.service.UsuarioService;

@Controller
public class HomeController extends BaseController  {

	static final Logger logger = Logger.getLogger(HomeController.class);

	@Autowired(required = true)
	private UsuarioService service;

	@ResponseBody
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public void atualizar(@RequestBody UsuarioVO usuarioVo) {
		//@RequestBody UsuarioVO usuarioVo
		//System.out.println(usuarioVo.getName());
		Usuario usuario = new Usuario();
		System.out.println(usuario.getNome());
		// service.update(usuario);
		//		} catch (ApplicationException ex) {
		//			new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		//		}
	}




}
