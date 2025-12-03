package com.learn.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.learn.bean.UserBean;
import com.learn.service.UserService;
import com.learn.util.UserUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class RegistrationController {

	private UserService userService = new UserService();


	@RequestMapping("/")
	public String home() {
		return "registration";
	}
	
	@RequestMapping("/register")
	public ModelAndView welcome(HttpServletRequest request, HttpSession session) {
		 ModelAndView mv = new ModelAndView();
		 

		List<String> errors = UserUtil.validateRegistration(request);
		if (!errors.isEmpty()) {
	        mv.setViewName("registration"); 
	        mv.addObject("error", errors);
	        return mv;
	    }

	    UserBean userBean = new UserBean();
	    userBean.setUsername(request.getParameter("username"));
	    userBean.setPassword(request.getParameter("password"));
	    userBean.setFirstname(request.getParameter("firstname"));
	    userBean.setLastname(request.getParameter("lastname"));

	    String serviceError = userService.registerUser(userBean);

	    if (serviceError != null) {
	        mv.setViewName("registration");
	        mv.addObject("error", serviceError);
	        return mv;
	    }

	    mv.setViewName("login");   
	    return mv;
	}
}
