package com.learn.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.learn.service.UserService;
import com.learn.util.UserUtil;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
	
	private UserService userService = new UserService();
	
//	@RequestMapping("/login")
//	public String showlogin() {
//		return "login";
//	}
	
	
	@RequestMapping("/loginsubmit")
	public ModelAndView Login(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String error = UserUtil.validateLogin(request);
		System.out.printf(request.getParameter("username"));
		System.out.print(request.getParameter("password"));
		System.out.print(error);
		if(error == null) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			error = userService.AuthUser(username, password);
			System.out.print(error);	
			if(error == null) {
				mv.addObject("username", request.getParameter("username"));
				mv.setViewName("success");
			}
		}
		else {
			mv.setViewName("failure");
			mv.addObject("message", "Login Failed");
		}

		
		return mv;
	}
	
}
