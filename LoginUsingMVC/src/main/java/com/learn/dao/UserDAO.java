package com.learn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.learn.bean.UserBean;
import com.learn.util.DBUtil;

import jakarta.servlet.RequestDispatcher;

public class UserDAO {
	
	
	
	public boolean insertUser(UserBean userBean) {
		boolean success = false;
		try {
			
			Connection connection = DBUtil.getConnection();
			try(PreparedStatement statement = connection.prepareStatement("INSERT INTO users(user_name, password, first_name, last_name) VALUES (?, ?, ?, ?)")){
					
				statement.setString(1, userBean.getUsername());
				statement.setString(2, userBean.getPassword());
				statement.setString(3, userBean.getFirstname());
				statement.setString(4, userBean.getLastname());
				
				int row = statement.executeUpdate();
				if(row > 0) {
					success = true;
				} 
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return success;
		
	}
	
	public boolean validateUser(String username, String password) {
		Connection connection = DBUtil.getConnection();
		
		try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE user_name  = ? AND password = ?")) {
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet resultset = ps.executeQuery();
			if(resultset.next()) {
				return true;
			}
			return false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
}
