package br.com.sistema.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

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
