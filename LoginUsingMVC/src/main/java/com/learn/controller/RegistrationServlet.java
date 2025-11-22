package com.learn.controller;

import java.io.IOException;
import java.util.List;

import com.learn.bean.UserBean;
import com.learn.service.UserService;
import com.learn.util.UserUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<String> error = UserUtil.validateRegistration(request);
		HttpSession session = request.getSession();
		
		if (error.isEmpty()) {
			UserBean userBean = new UserBean();
			
			//building bean
			userBean.setUsername(request.getParameter("username"));
			userBean.setPassword(request.getParameter("password"));
			userBean.setFirstname(request.getParameter("firstname"));
			userBean.setLastname(request.getParameter("lastname"));
			
			String serviceError = userService.registerUser(userBean);
			
			if(serviceError != null) {
				session.setAttribute("error", serviceError);
	            response.sendRedirect("registration.html");
	            return;
			}
			response.sendRedirect("login.html");
		}
		else {
			session.setAttribute("error", error);
			response.sendRedirect("registration.html");
		}
	}

}
