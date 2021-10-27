package com.revature.controller;

import com.revature.service.ArithmeticService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ArithmeticController {
	
	public ArithmeticService arithmeticService;
	
		
	public ArithmeticController() {
		this.arithmeticService = new ArithmeticService();
		
	}
	public Handler add = (ctx) -> {
		ctx.result("add lambda invoked");
		
		String number1String = ctx.formParam("number1");
		String number2String = ctx.formParam("number2");
		
		
		
		ctx.result(arithmeticService.doAddition(number1String,number2String));
		
	};
	
	
		
	
	public Handler sub = (ctx) -> {
		ctx.result("add lambda invoked");
		
		String number1String = ctx.formParam("number1");
		String number2String = ctx.formParam("number2");
		
		
		
		ctx.result(arithmeticService.doSubtraction(number1String,number2String));
		
	};
	
	public Handler mul = (ctx) -> {
		ctx.result("add lambda invoked");
		
		String number1String = ctx.formParam("number1");
		String number2String = ctx.formParam("number2");
		
		
		
		ctx.result(arithmeticService.doMultiplication(number1String,number2String));
		
	};
	
	public Handler div = (ctx) -> {
		ctx.result("add lambda invoked");
		
		String number1String = ctx.formParam("number1");
		String number2String = ctx.formParam("number2");
		
		
		
		ctx.result(arithmeticService.doDivision(number1String,number2String));
		
	};
	public void registerEndpoint(Javalin app) {
		app.post("/add", add);
		app.post("/sub", sub);
		app.post("/mul", mul);
		app.post("/div", div);
	
	
	}	
	

}
