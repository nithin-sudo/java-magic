package com.learn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginpageServlet")
public class LoginpageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try(Connection conn = DBConnect.getConnection()){
			String sql = "SELECT * FROM users WHERE user_name = ? AND password = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			
			ResultSet executeQuery = statement.executeQuery();
			
			if(executeQuery.next()) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("success.jsp");
				System.out.println("successfully logged in");
				request.setAttribute("username", username);
				requestDispatcher.forward(request, response);
			} else {
				response.sendRedirect("Login.html");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
