package br.com.sistema.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

public class BaseController {
	
	
	protected String getPrincipal(){
		String userName = null;
		Object principal = null;
		if(SecurityContextHolder.getContext().getAuthentication()!=null){
			principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (principal instanceof UserDetails) {
				userName = ((UserDetails)principal).getUsername();
			} else {
				userName = principal.toString();
			}
		}
		return userName;
	}
	

}
