package com;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.constant.UrlConstant;
import com.fasterxml.jackson.databind.JsonNode;
import com.model.Response;
import com.model.User;
import com.service.AccountService;
import com.service.AuthService;
import com.service.RestService;
import com.util.JsonConverter;
import com.util.JwtTokenCreator;

@RestController
@CrossOrigin
public class UserController {

	@RequestMapping(path = "/signin", method = RequestMethod.POST)
	public ResponseEntity<Response> signInAction(@RequestBody String json) {

		User user = AuthService.performValidation(json);
		if (user == null)
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

		String token = JwtTokenCreator.generateToken(JsonConverter.objToJsonConverter(user));
		Response response = new Response();
		response.setToken(token);
		response.setIsAccountVerified(user.isAccountVerified());
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	@RequestMapping(path = "/signup", method = RequestMethod.POST)
	public ResponseEntity<Response> signUpAction(@RequestBody String json) {
		JsonNode jsonNode = JsonConverter.getJsonNode(json);
		String userName = jsonNode.get("userName").textValue();
		String password = jsonNode.get("password").textValue();
		
		User user=AuthService.addUser(userName, password);
		if (Objects.isNull(user)) {
			Response response = new Response();
			response.setMessage("User already present");
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
		String token = JwtTokenCreator.generateToken(JsonConverter.objToJsonConverter(user));
		Response response = new Response();
		response.setToken(token);
		//RestService.hitRestUrl(userName,user.getvCode(),"http://localhost:3001/verifyAccount");
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "/sendvCode", method = RequestMethod.POST)
	public ResponseEntity<Response> sendvCode(@RequestBody String json) {
		System.out.println("in sendVCode");
		JsonNode jsonNode = JsonConverter.getJsonNode(json);
		String userName = jsonNode.get("userName").textValue();
		
		String vCode=AccountService.generatevCode();
		System.out.println("newly generated vCode:"+vCode);
		Response response = new Response();
		response.setMessage("verification code sent");
		//RestService.hitRestUrl(userName,vCode,UrlConstant.VERIFY_ACCOUNT);
		AccountService.updatevCode(userName, vCode);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@RequestMapping(path = "/hello")
	public String sayHello() {
		return "Hello Nikhil!";
	}

	@RequestMapping(path = "/deleteUser/{userName}")
	public String deleteUser(@PathVariable String userName) {
		if (AuthService.deleteUser(userName))
			return String.format("User: %s deleted successfully.", userName);
		return String.format("No such user: %s found", userName);
	}
	
	@RequestMapping(path="/changePassword",method=RequestMethod.POST)
	public ResponseEntity<Response> updateUserPassword(@RequestBody String json) {
		JsonNode jsonNode = JsonConverter.getJsonNode(json);
		String userName = jsonNode.get("userName").textValue();
		String password = jsonNode.get("password").textValue();
		String currentPassword = jsonNode.get("currentPassword").textValue();
		Response response=new Response();
		if(AuthService.changePassword(userName, currentPassword,password)) {
			response.setMessage("Password updated successfully.");
			return new ResponseEntity<>(response,HttpStatus.CREATED);
		}
		response.setMessage("Invalid current password!");
		return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(path="/verifyAccount",method=RequestMethod.POST)
	public Boolean verifyAccount(@RequestBody String json) {
		JsonNode jsonNode = JsonConverter.getJsonNode(json);
		String userName = jsonNode.get("userName").textValue();
		String vCode = jsonNode.get("vCode").textValue();
		if(AccountService.verifyAccount(userName, vCode))
			return Boolean.TRUE;
		
		return Boolean.FALSE;
	}
}