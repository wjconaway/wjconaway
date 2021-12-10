package com.revature.dto;

import java.io.InputStream;
import java.util.Objects;

public class ReceiptDto {
	
	private int id;
	private String amount;
	private String type;
	private String description;
	private int author;
	private int resolver;
	
	public ReceiptDto() {
		super();
	}

	public ReceiptDto(int id, String amount, String type, String description, int author, int resolver) {
		super();
		this.id = id;
		this.amount = amount;
		this.type = type;
		this.description = description;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return "ReceiptDto [id=" + id + ", amount=" + amount + ", type=" + type + ", description=" + description
				+ ", author=" + author + ", resolver=" + resolver + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, author, description, id, resolver, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReceiptDto other = (ReceiptDto) obj;
		return Objects.equals(amount, other.amount) && author == other.author
				&& Objects.equals(description, other.description) && id == other.id && resolver == other.resolver
				&& Objects.equals(type, other.type);
	}

}	