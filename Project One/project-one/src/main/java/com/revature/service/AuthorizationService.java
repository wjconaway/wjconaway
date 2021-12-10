package com.revature.service;

import com.revature.exception.UnauthorizedException;
import com.revature.model.User;

public class AuthorizationService {
	
	public void authorizeAllUsers(User user) throws UnauthorizedException {
		if (user == null || !(user.getUserroll().equals("worker") || user.getUserroll().equals("manager"))) {
			throw new UnauthorizedException("You must be an Authorize User");
		}
	}
	
	public void authorizeManager(User user ) throws UnauthorizedException {
		if (user == null || !user.getUserroll().equals("manager")) {
			throw new UnauthorizedException("You must be a manager!!");
		}
	}

	public void authorizeEmployee(User user) throws UnauthorizedException {
		if (user == null || !user.getUserroll().equals("worker")) {
			throw new UnauthorizedException("You must be a Authorize User!!");
		}
		
	}

}
