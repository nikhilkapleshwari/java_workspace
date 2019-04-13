package com.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.model.Response;
import com.model.User;
import com.service.UserService;
import com.sun.jersey.multipart.FormDataParam;
import com.utilities.JsonConverter;
import com.utilities.JwtTokenCreator;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {
	
	@Autowired
	UserService UserService;
	
	@RequestMapping(path = "/signup", method = RequestMethod.POST)
	public ResponseEntity<User> signUpAction(@RequestBody String json) {
		User user = UserService.performSignup(json);
		if (Objects.isNull(user))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "/signin", method = RequestMethod.POST)
	public ResponseEntity<Response> signInAction(@RequestBody String json) {
		User user = UserService.performSignIn(json);
		if (Objects.isNull(user))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		String token = JwtTokenCreator.generateToken(JsonConverter.objToJsonConverter(user));
		Response response = new Response();
		response.setToken(token);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "/active", method = RequestMethod.POST)
	public Boolean isActive(@RequestBody String json) throws JsonParseException, JsonMappingException, IOException {
		
		return UserService.isUserActive(json);
		
	}
	
}
