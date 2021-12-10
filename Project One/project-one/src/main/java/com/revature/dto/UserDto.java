package com.revature.dto;

import java.util.Objects;

public class UserDto {
	
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String userole;
	public UserDto() {
		super();
	}
	public UserDto(String firstname, String lastname, String username, String password, String userole) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.userole = userole;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserole() {
		return userole;
	}
	public void setUserole(String userole) {
		this.userole = userole;
	}
	@Override
	public String toString() {
		return "UserDto [firstname=" + firstname + ", lastname=" + lastname + ", username=" + username + ", password="
				+ password + ", userole=" + userole + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(firstname, lastname, password, username, userole);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return Objects.equals(firstname, other.firstname) && Objects.equals(lastname, other.lastname)
				&& Objects.equals(password, other.password) && Objects.equals(username, other.username)
				&& Objects.equals(userole, other.userole);
	}
	
	

}
