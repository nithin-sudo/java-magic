package com.learn.controller;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;

import com.learn.service.UserService;
import com.learn.util.UserUtil;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
	private UserService userService = new UserService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = UserUtil.validateLogin(request);
		HttpSession session = request.getSession(); // need to check whether useful or not
		
		if(error == null) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			error = userService.AuthUser(username, password);
			request.setAttribute("username", username);
			if(error == null) {
				request.getRequestDispatcher("success.jsp").forward(request, response);
			}
		}
		response.sendRedirect("failure.html");
	}

}
