package com.revature.controller;

import java.security.InvalidParameterException;

import javax.security.auth.login.FailedLoginException;

import com.revature.dto.MessageDto;
import com.revature.exception.ReimbursementNotFoundException;
import com.revature.exception.ReimbursementResolvedException;
import com.revature.exception.UnauthorizedException;

import io.javalin.Javalin;

public class ExceptionMapper {
	
	public void mapException(Javalin app) {
		app.exception(FailedLoginException.class, (e, ctx) -> {
			ctx.status(400);
			ctx.json(new MessageDto(e.getMessage()));
		});
		
		app.exception(UnauthorizedException.class, (e, ctx) -> {
			ctx.status(401);
			ctx.json(new MessageDto(e.getMessage()));
		});
		
		app.exception(ReimbursementNotFoundException.class, (e, ctx) -> {
			ctx.status(404);
			ctx.json(new MessageDto(e.getMessage()));
			
		});
		
		app.exception(InvalidParameterException.class, (e, ctx) -> {
			ctx.status(400);
			ctx.json(new MessageDto(e.getMessage()));
		});
		
		app.exception(ReimbursementResolvedException.class, (e, ctx) -> {
			ctx.status(400);
			ctx.json(new MessageDto(e.getMessage()));
		});
		
	}

}
