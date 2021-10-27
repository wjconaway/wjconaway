package com.revature.app;

import com.revature.controller.ArithmeticController;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Application {

	public static void main(String[] args) {
		Javalin app = Javalin.create(config -> {
			config.addStaticFiles("/",Location.CLASSPATH);
		});
		
		ArithmeticController controller1 = new ArithmeticController();
		controller1.registerEndpoint(app);
		
		
		app.start(8080);

	}

}
