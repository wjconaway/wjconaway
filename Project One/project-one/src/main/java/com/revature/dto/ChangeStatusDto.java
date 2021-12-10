package com.revature.dto;

import java.util.Objects;

public class ChangeStatusDto {
	
	private String status;

	public ChangeStatusDto() {
		super();
	}

	public ChangeStatusDto(String status) {
		super();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ChangeStatusDto [status= " + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChangeStatusDto other = (ChangeStatusDto) obj;
		return status == other.status;
	}
	
	

}
