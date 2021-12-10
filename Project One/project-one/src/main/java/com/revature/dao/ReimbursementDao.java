package com.revature.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.dto.EmpsAndReimbDto;
import com.revature.dto.ReceiptDto;
import com.revature.dto.UpdateDto;
import com.revature.model.Reimbursement;
import com.reveture.util.JDBCUtility;

public class ReimbursementDao {

	public List<Reimbursement> getAllReimbs() throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {
			List<Reimbursement> reimbursements = new ArrayList<>();

			String sql = "select id, amount, submitted, resolved, status, type,"
					+ "author_id, resolver_id from httpsession_demo.reimb";

			PreparedStatement pst = con.prepareStatement(sql);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String amount = rs.getString("amount");
				String submitted = rs.getString("submitted");
				String resolved = rs.getString("resolved");
				String status = rs.getString("status");
				String type = rs.getString("type");
				int author = rs.getInt("author_id");
				int resolver = rs.getInt("resolver_id");

				Reimbursement reimbursement = new Reimbursement(id, amount, submitted, resolved, status, type,
						author, resolver);
				reimbursements.add(reimbursement);
			}
			return reimbursements;

		}
	}

	public List<Reimbursement> getReimbByEmployee(int reimbursementid) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {

			List<Reimbursement> reimburs = new ArrayList<>();

			String sql = "select id, amount, submitted, resolved, status, type,"
					+ "author_id, resolver_id from httpsession_demo.reimb where author_id = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, reimbursementid);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String amount = rs.getString("amount");
				String submitted = rs.getString("submitted");
				String resolved = rs.getString("resolved");
				String status = rs.getString("status");
				String type = rs.getString("type");
				int author = rs.getInt("author_id");
				int resolver = rs.getInt("resolver_id");

				Reimbursement reimburse = new Reimbursement(id, amount, submitted, resolved, status, type, 
						author, resolver);
				reimburs.add(reimburse);
			}
			return reimburs;

		}
	}

	public UpdateDto getReimbursementbyId(int reimbursementId) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {

			String sql = "select * from httpsession_demo.reimb where id = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, reimbursementId);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {

				int id = rs.getInt("id");
				String status = rs.getString("status");
				int author = rs.getInt("author_id");
				int resolver = rs.getInt("resolver_id");

				return new UpdateDto(id, status, author, resolver);
			} else {
				return null;

			}
		}
	}

	public void changeStatus(int id, String status, int resolver) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {

			String sql = "update httpsession_demo.reimb " 
								+ "set " 
								+ "status = ?, " 
								+ "resolver_id = ? " 
								+ "where id = ?;";
			
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, status);
			pst.setInt(2, resolver);
			pst.setInt(3, id);

			int updatedPend = pst.executeUpdate();

			if (updatedPend != 1) {
				throw new SQLException("Something Went Wrong");
			}

		}

	}
	
	public List<EmpsAndReimbDto> getAllEmpsAndReimbDto() throws SQLException{
		try (Connection con = JDBCUtility.getConnection()) {
			List<EmpsAndReimbDto> reimbById = new ArrayList<>();
		String sql = "select emps.id, emps.first_name, emps.last_name, emps.user_role, "
				+ "reimb.amount, reimb.status, reimb.type, reimb.author_id, reimb.resolver_id "
				+ "from httpsession_demo.reimb"
				+ " inner join httpsession_demo.emps"
				+ " on reimb.author_id = emps.id "
				+ "left join httpsession_demo.emps m "
				+ "on reimb.resolver_id= emps.id;";
		PreparedStatement pst = con.prepareStatement(sql);
		
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
					int id = rs.getInt("id"); 
					String firstname = rs.getString("first_name");
					String lastname = rs.getString("last_name");
					String userrole = rs.getString("user_role");
					String amount =	rs.getString("amount");
					String status = rs.getString("status");
					String type = rs.getString("type"); 
					int author = rs.getInt("author_id");
					int resolver = rs.getInt("resolver_id");
					
					EmpsAndReimbDto EandRDto = new EmpsAndReimbDto(id, firstname, lastname, userrole, amount, status, type,
							author, resolver);
					reimbById.add(EandRDto);		
		}
		return reimbById;
		}
	}
	
	public ReceiptDto addReimbursement(String reimbursementName, String amount, InputStream image, int authorId) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()){
			String sql = "insert into httpsession_demo.reimb (type, amount, receipt_image, author_id)"
					+ " values (?, ?, ?, ?);";
			PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, reimbursementName);
			pst.setString(2, amount);
			pst.setBinaryStream(3, image);
			pst.setInt(4, authorId);
			
			int numberOfRecords = pst.executeUpdate();
			
			if (numberOfRecords != 1) {
				throw new SQLException("Something went wrong");			
			}
			
			ResultSet rs = pst.getGeneratedKeys();
			
			rs.next();
			int generatedId = rs.getInt(1);
			
			return new ReceiptDto();
		}
		
	}

	public InputStream getImageFromId(int id) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()){
			String sql = "select receipt_image from httpsession_demo.reimb where id = ?";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setInt(1, id);
			
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				InputStream image = rs.getBinaryStream("receipt_image");
				
				return image;
			}
				return null;
		}

	}
	public List<EmpsAndReimbDto> getReimbByEmp(int reimbursementid) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {

			List<EmpsAndReimbDto> reimburs = new ArrayList<>();

			String sql = "select id, amount, submitted, resolved, status, type,"
					+ "author_id, resolver_id from httpsession_demo.reimb where author_id = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, reimbursementid);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String amount = rs.getString("amount");
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				String username = rs.getString("username");
				String status = rs.getString("status");
				String type = rs.getString("type");
				int author = rs.getInt("author_id");
				int resolver = rs.getInt("resolver_id");

				EmpsAndReimbDto reimburse = new EmpsAndReimbDto(id, amount, firstname, lastname, username, status, type, 
						author, resolver);
				reimburs.add(reimburse);
			}
			return reimburs;
		}
	}
}

