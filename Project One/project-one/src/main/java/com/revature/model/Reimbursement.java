package com.revature.model;

import java.util.Objects;

public class Reimbursement {
	
	private int id;
	private String amount;
	private String submitted;
	private String resolved;
	private String status;
	private String type;
	private int author;
	private int resolver;
	
	public Reimbursement() {
		super();
	}
	public Reimbursement(int id, String amount, String submitted, String resolved, String status, String type,
		 int author, int resolver) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getSubmitted() {
		return submitted;
	}
	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}
	public String getResolved() {
		return resolved;
	}
	public void setResolved(String resolved) {
		this.resolved = resolved;
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
		return "Reimbursement [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", status=" + status + ", type=" + type + ", author=" + author
				+ ", resolver=" + resolver + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(amount, author, id, resolved, resolver, status, submitted, type);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		return Objects.equals(amount, other.amount) && author == other.author
				&& Objects.equals(resolved, other.resolved) && resolver == other.resolver
				&& Objects.equals(status, other.status) && Objects.equals(submitted, other.submitted)
				&& Objects.equals(type, other.type);
	}
	
}
