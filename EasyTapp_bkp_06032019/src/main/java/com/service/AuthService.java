package com.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.model.User;
import com.util.JsonConverter;

public class AuthService {

	private static Map<String,User> userMap=new HashMap<>();
	
	public static Map<String,User> getUserMap(){
		return userMap;
	}
	
	public static User getUser(final String userName) {
		return userMap.get(userName);
	}
	public static User addUser(final String userName,final String password) {
		if(userMap.containsKey(userName))
			return null;
		String vCode=AccountService.generatevCode();
		System.out.println("generated vCode:"+vCode);
		User user=new User(userName,password,null,Boolean.FALSE,vCode,LocalDateTime.now());
		userMap.put(userName,user);
		return user;
	}
	
	public static boolean validateUser(final String userName,final String password) {
		User user=userMap.get(userName);
		String dbPassword=user.getPassword();
		if(dbPassword==null || password==null)
			return Boolean.FALSE;
		return password.equals(dbPassword);
	}
	
	
	public static boolean deleteUser(final String userName) {
		if(userMap.containsKey(userName)) {
			userMap.remove(userName);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public static boolean changePassword(final String userName,final String currentPassword,final String password) {
	
		if(AuthService.validateUser(userName, currentPassword)) {
			User user=userMap.get(userName);
			user.setPassword(password);
			userMap.put(userName,user);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public static User performValidation(String json) {
		JsonNode jsonNode = JsonConverter.getJsonNode(json);
		String userName = jsonNode.get("userName").textValue();
		String password = jsonNode.get("password").textValue();
		if(AuthService.validateUser(userName, password)) {
			User user=userMap.get(userName);
			userMap.put(userName,user);
			return user;
		}
			
		return null;
	}
	
}
