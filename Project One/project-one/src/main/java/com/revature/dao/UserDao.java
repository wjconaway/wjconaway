package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.dto.UserDto;
import com.revature.model.User;
import com.reveture.util.JDBCUtility;

public class UserDao {
	
	public User getUserByUandP(String username, String password) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()){
			String sql = "select * from httpsession_demo.emps where username = ? and password = ?";
			
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				int id = rs.getInt("id");
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				String user = rs.getString("username");
				String pass = rs.getString("password");
				String userrole = rs.getString("user_role");
				
				return new User(id, firstname, lastname, user, pass, userrole);
			} else {
				return null;
			}
		}
		
	}
	
	
	 

}
