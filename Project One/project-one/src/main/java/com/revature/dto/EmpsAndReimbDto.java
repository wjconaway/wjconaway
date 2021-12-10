package com.revature.dto;

import java.util.Objects;

public class EmpsAndReimbDto {
	
	private int id;
	private String firstname;
	private String lastname;
	private String userrole;
	private String amount;
	private String status;
	private String type;
	private int author;
	private int resolver;
	
	public EmpsAndReimbDto() {
		super();
	}
	public EmpsAndReimbDto(int id, String firstname, String lastname, String userrole, String amount, String status,
			String type, int author, int resolver) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.userrole = userrole;
		this.amount = amount;
		this.status = status;
		this.type = type;
		this.author = author;
		this.resolver = resolver;
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
	public String getUserroll() {
		return userrole;
	}
	public void setUserroll(String userroll) {
		this.userrole = userroll;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAuthor() {
		return author;
	}
	public void setAuthor(int author) {
		this.author = author;
	}
	public int getResolver() {
		return resolver;
	}
	public void setResolver(int resolver) {
		this.resolver = resolver;
	}
	@Override
	public String toString() {
		return "EmpsAndReimbDto [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", userrole="
				+ userrole + ", amount=" + amount + ", status=" + status + ", type=" + type + ", author=" + author
				+ ", resolver=" + resolver + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(amount, author, firstname, id, lastname, resolver, status, type, userrole);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpsAndReimbDto other = (EmpsAndReimbDto) obj;
		return Objects.equals(amount, other.amount) && author == other.author
				&& Objects.equals(firstname, other.firstname) && id == other.id
				&& Objects.equals(lastname, other.lastname) && resolver == other.resolver
				&& Objects.equals(status, other.status) && Objects.equals(type, other.type)
				&& Objects.equals(userrole, other.userrole);
	}
	
	
	

}
