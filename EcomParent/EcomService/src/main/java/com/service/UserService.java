package com.service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.constant.QueryConstant;
import com.dao.GenericDao;
import com.ecomenum.UserRoleEnum;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.model.User;
import com.utilities.JsonConverter;
import com.utilities.JwtTokenCreator;

@Service
public class UserService {
	
	@Autowired
	GenericDao genericDao;
	
	public User performSignup(final String json) {
		JsonNode jsonNode = JsonConverter.getJsonNode(json);
		String userName = jsonNode.get("userName").textValue();
		String password = jsonNode.get("password").textValue();
		
		if (this.isUserNameExist(userName)) {
			return null;
		}
		
		User user = new User(userName, password, UserRoleEnum.USER.toString());
		genericDao.insertEntity(user);
		return user;
	}
	
	public User performSignIn(final String json) {
		JsonNode jsonNode = JsonConverter.getJsonNode(json);
		String userName = jsonNode.get("userName").textValue();
		String password = jsonNode.get("password").textValue();
		List<User> userList = genericDao.executeQuery(QueryConstant.FIND_BY_USERNAME,
				ImmutableMap.of("userName", userName));
		if (CollectionUtils.isEmpty(userList))
			return null;
		if (userList.get(0).getPassword().equals(password))
			return userList.get(0);
		return null;
	}
	
	public Boolean isUserNameExist(final String userName) {
		List<User> userList = genericDao.executeQuery(QueryConstant.FIND_BY_USERNAME,
				ImmutableMap.of("userName", userName));
		return (!CollectionUtils.isEmpty(userList));
	}
	
	public Boolean isUserActive(final String token) throws JsonParseException, JsonMappingException, IOException {
		Object obj = JwtTokenCreator.validateToken(token);
		return (Objects.nonNull(obj));
	}
}
