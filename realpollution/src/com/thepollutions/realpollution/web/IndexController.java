package com.thepollutions.realpollution.web;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  
public class IndexController {  
	private static final long serialVersionUID = 1L;  

	
	@RequestMapping("/")
	public String index(ModelMap model, HttpServletResponse response){
		
		return "index";
	}
	
	@RequestMapping("/login/")
	public String login(ModelMap model, HttpServletResponse response){
		
		return "login";
	}
	
}  