package com.revature.service;

import java.security.InvalidParameterException;
import java.sql.SQLException;

import javax.security.auth.login.FailedLoginException;

import com.revature.dao.UserDao;
import com.revature.dto.UserDto;
import com.revature.model.User;

public class UserService {
	
	public UserDao userDao;
	
	public UserService() {
		this.userDao = new UserDao();
	}
	
	public User getUserByUandP(String username, String password) throws FailedLoginException, SQLException {
		
		User user = this.userDao.getUserByUandP(username, password);
		
		if (user == null) {
			throw new FailedLoginException("Incorrect username and/or password");
		}
		return user;
		
	}
	
	
}
