package com.learn.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

public class UserUtil {
	
	public static List<String> validateRegistration(HttpServletRequest request) {
		Map<String,String> fields = Map.of(
				"Username", request.getParameter("username"),
	            "Password", request.getParameter("password"),
	            "First Name", request.getParameter("firstname"),
	            "Last Name", request.getParameter("lastname")
				);
		
		List<String> errors = new ArrayList<>();
		
		for(Map.Entry<String, String> entry : fields.entrySet()) {
			if(entry.getValue() == null || entry.getValue().isBlank()) {
				errors.add(entry.getKey() + "cannot be blank");
			}
		}
		
		return errors;
	}
	
	public static String validateLogin(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username.isBlank() || password.isBlank()) {
			return "Username and password cannot be blank";
		}
		
		return null;
	}
}
