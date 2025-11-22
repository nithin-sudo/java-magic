package com.learn.service;

import com.learn.bean.UserBean;
import com.learn.dao.UserDAO;

public class UserService {
	
	UserDAO userdao = new UserDAO();
	
	public String registerUser(UserBean userBean) {
		boolean inserted = userdao.insertUser(userBean);
		
		if (!inserted) {
            return "Registration failed.";
        }

        return null;
	}
	
	public String AuthUser(String username, String password) {
	    boolean isValid = userdao.validateUser(username, password);

	    if (!isValid) {
	        return "Invalid username or password";
	    }

	    return null; // no error
	}
}
