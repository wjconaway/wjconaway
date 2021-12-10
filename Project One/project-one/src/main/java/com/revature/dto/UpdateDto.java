package com.revature.dto;

import java.util.Objects;

public class UpdateDto {
	
	private int id;
	private String status;
	private int author;
	private int resolver;
	
	public UpdateDto() {
	}

	public UpdateDto(int id, String status, int author, int resolver) {
		this.id = id;
		this.status = status;
		this.author = author;
		this.resolver = resolver;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		return "UpdateDto [id= " + id + ", status= " + status + ", author= " + author + ", resolver= " + resolver + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, id, resolver, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdateDto other = (UpdateDto) obj;
		return author == other.author && id == other.id && resolver == other.resolver
				&& Objects.equals(status, other.status);
	}
	
	

}
