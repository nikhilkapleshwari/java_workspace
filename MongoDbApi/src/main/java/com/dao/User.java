/*package com.dao;


import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Document
public class User {
	
	public User(String name, String password, String role,boolean isAccountVerified,String vCode,LocalDateTime vCodeValidTs) {
		this.name = name;
		this.password = password;
		this.role = role;
		this.isAccountVerified=isAccountVerified;
		this.vCode=vCode;
		this.vCodeValidTs=vCodeValidTs;
	}
	
	public User() {
	}
	
	@Id
	private int userId;
	private String name;
	private String password;
	private String role;
	private boolean isAccountVerified;
	private String vCode;
	private LocalDateTime vCodeValidTs;


	public int getId() {
		return userId;
	}
	
	public void setId(int id) {
		this.userId = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	public boolean isAccountVerified() {
		return isAccountVerified;
	}

	public void setAccountVerified(boolean isAccountVerified) {
		this.isAccountVerified = isAccountVerified;
	}

	public String getvCode() {
		return vCode;
	}

	public void setvCode(String vCode) {
		this.vCode = vCode;
	}
	
	public LocalDateTime getvCodeValidTs() {
		return vCodeValidTs;
	}

	public void setvCodeValidTs(LocalDateTime vCodeValidTs) {
		this.vCodeValidTs = vCodeValidTs;
	}
}
*/