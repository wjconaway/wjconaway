package com.revature.controller;

import java.io.InputStream;
import java.util.List;

import org.apache.tika.Tika;

import com.revature.dto.ChangeStatusDto;
import com.revature.dto.EmpsAndReimbDto;
import com.revature.dto.ReceiptDto;
import com.revature.dto.UpdateDto;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.AuthorizationService;
import com.revature.service.ReimbursementService;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;

public class ReimbursementController implements Controller{
	
	private AuthorizationService authService;
	private ReimbursementService reimbService;
	
	public ReimbursementController() {
		this.authService = new AuthorizationService();
		this.reimbService = new ReimbursementService();
	}
	
	private Handler getreimbursements = (ctx) -> {
		User loggedinUser = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authService.authorizeAllUsers(loggedinUser);
		
		List<Reimbursement> reimbursements = this.reimbService.getReimbursements(loggedinUser);
		ctx.json(reimbursements);
	};
	
	private Handler changeStatus = (ctx) -> {
		User loggedinUser = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authService.authorizeManager(loggedinUser);
		
		String reimbursementId = ctx.pathParam("id");
		ChangeStatusDto dto = ctx.bodyAsClass(ChangeStatusDto.class);
		
		UpdateDto updateReimb = this.reimbService.changeStatus(loggedinUser, reimbursementId, dto.getStatus());
		ctx.json(updateReimb);
	};
	private Handler getEmpsWithReimb = (ctx) -> {
		User loggedinUser = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authService.authorizeAllUsers(loggedinUser);
		
		this.reimbService.getReimbursements(loggedinUser);
		//ctx.json(eandr);
		
	};
	private Handler addReimbursements = (ctx) -> {
		User loggedinUser = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authService.authorizeEmployee(loggedinUser);
		
		String reimbursementName = ctx.formParam("reimbursement_name");
		UploadedFile file = ctx.uploadedFile("receipt_image");
		InputStream content = file.getContent();
		String amount = ctx.formParam("amount");
		
		
		Tika tika = new Tika();
		
		String mimeType = tika.detect(content);
		
		ReceiptDto addReimbursement = this.reimbService.addReimbursement(loggedinUser, mimeType, reimbursementName, content, amount);
		//ctx.json(addReimbursements);
		ctx.status(201);
		
	};
	private Handler getImageFromReimbursementId = (ctx) -> {
		User loggedinUser = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authService.authorizeEmployee(loggedinUser);
		
		String reimburementId = ctx.pathParam("id");
		
		InputStream image = this.reimbService.getImageFromReimbursementId(loggedinUser, reimburementId);
		
		Tika tika = new Tika();
		
		String mimeType = tika.detect(image);
		
		ctx.contentType(mimeType);
		ctx.result(image);
	
	};
	private Handler getreimbursement = (ctx) -> {
		User loggedinUser = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authService.authorizeAllUsers(loggedinUser);
		
		List<EmpsAndReimbDto> reimbursements = this.reimbService.getReimbursement(loggedinUser);
		ctx.json(reimbursements);
	};

	@Override
	public void mapEndpoints(Javalin app) {
		
		app.get("/reimbursements", getreimbursements);
		app.patch("/reimbursements/{id}/status", changeStatus);
		app.get("/reimbursement", getreimbursement);
		app.post("/reimbursements", addReimbursements);
		app.get("/reimbursements/{id}/image", getImageFromReimbursementId);
		
	}

}
