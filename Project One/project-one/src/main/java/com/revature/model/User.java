package com.revature.model;

import java.util.Objects;

public class User {
	
	private int id;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String userroll;

	public User () {}

	public User(int id, String firstname, String lastname, String username, String password, String userroll) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.userroll = userroll;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getUserroll() {
		return userroll;
	}

	public void setUserroll(String userroll) {
		this.userroll = userroll;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname= " + firstname + ", lastname= " + lastname + ", username= " + username
				+ ", password= " + password + ", userroll= " + userroll + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstname, id, lastname, password, username, userroll);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(firstname, other.firstname) && id == other.id && Objects.equals(lastname, other.lastname)
				&& Objects.equals(password, other.password) && Objects.equals(username, other.username)
				&& Objects.equals(userroll, other.userroll);
	}
	
	
}
