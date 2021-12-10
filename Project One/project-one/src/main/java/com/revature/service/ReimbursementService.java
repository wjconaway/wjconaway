package com.revature.service;

import java.io.InputStream;
import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.revature.dao.ReimbursementDao;
import com.revature.dto.EmpsAndReimbDto;
import com.revature.dto.ReceiptDto;
import com.revature.dto.UpdateDto;
import com.revature.exception.ReimbursementNotFoundException;
import com.revature.exception.ReimbursementResolvedException;
import com.revature.exception.UnauthorizedException;
import com.revature.model.Reimbursement;
import com.revature.model.User;

public class ReimbursementService {

	private ReimbursementDao reimbDao;

	public ReimbursementService() {
		this.reimbDao = new ReimbursementDao();
	}

	public ReimbursementService(ReimbursementDao reimbDao) {
		this.reimbDao = reimbDao;
	}

	public List<Reimbursement> getReimbursements(User loggedinUser) throws SQLException {
		List<Reimbursement> reimbs = null;

		if (loggedinUser.getUserroll().equals("manager")) {
			reimbs = this.reimbDao.getAllReimbs();
		} else if (loggedinUser.getUserroll().equals("worker")) {
			reimbs = this.reimbDao.getReimbByEmployee(loggedinUser.getId());
		}
		return reimbs;
	}

	public UpdateDto changeStatus(User loggedinUser, String reimbursementId, String status)
			throws SQLException, ReimbursementNotFoundException, ReimbursementResolvedException {
		try {
			int id = Integer.parseInt(reimbursementId);

			UpdateDto dto = this.reimbDao.getReimbursementbyId(id);

			if (dto == null) {
				throw new ReimbursementNotFoundException("Reimbursement with ID " + reimbursementId + " not found");
			}
			
			if (dto.getResolver() == 0) {
				this.reimbDao.changeStatus(id, status, loggedinUser.getId());
			}else {
				throw new ReimbursementResolvedException("Reimbursement with ID "+ reimbursementId + " has already been resolved!!!");
				
			}
			return this.reimbDao.getReimbursementbyId(id);
		} catch (NumberFormatException e) {
			throw new InvalidParameterException("Reimbursement ID must be a Number");
		}
		
	}

	public ReceiptDto addReimbursement(User loggedinUser, String mimeType, String type, InputStream content,String amount) throws SQLException {
		Set<String> allowedFileTypes = new HashSet<>();
		allowedFileTypes.add("image/jpeg");
		allowedFileTypes.add("image/png");
		allowedFileTypes.add("image/gif");
		
		if (!allowedFileTypes.contains(mimeType)) {
			throw new InvalidParameterException("Image can only be JPEG, PNG, or GIF");
		}
		int authorId = loggedinUser.getId(); 
		ReceiptDto addReimbursement = this.reimbDao.addReimbursement(type, amount, content, authorId);
		return addReimbursement;
		
		
	}

	public InputStream getImageFromReimbursementId(User loggedinUser, String reimbursementId) throws SQLException, UnauthorizedException, ReimbursementNotFoundException {
		try {
			int id = Integer.parseInt(reimbursementId);
			
			if (loggedinUser.getUserroll().equals("worker")) {
				int employeeId = loggedinUser.getId();
				List<Reimbursement> employeeReceipts = this.reimbDao.getReimbByEmployee(employeeId);
				
				Set<Integer> employeeIdsEncounter = new HashSet<>();
				for (Reimbursement r : employeeReceipts) {
					employeeIdsEncounter.add(r.getId());
				}
				
				if (!employeeIdsEncounter.contains(id)) {
					throw new UnauthorizedException("You are not authorize to view receipt!");
				}
			}
			
			InputStream image = this.reimbDao.getImageFromId(id);
			
			if(image == null) {
				throw new ReimbursementNotFoundException("Image of receipt is not found or does not exist");
			}
			
			return image;
			
		}catch(NumberFormatException e) {
			throw new InvalidParameterException("Reimbursement ID must be a Number");
		}
	
	}
	
	public List<EmpsAndReimbDto> getReimbursement(User loggedinUser) throws SQLException {
		List<EmpsAndReimbDto> reimb = null;

		if (loggedinUser.getUserroll().equals("manager")) {
			reimb = this.reimbDao.getAllEmpsAndReimbDto();
		} else if (loggedinUser.getUserroll().equals("worker")) {
			reimb = this.reimbDao.getReimbByEmp(loggedinUser.getId());
		}
		return reimb;
	}

	
}
