package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(path = "/save")
	public void save() {
		User user=new User(1,"Nikhil",1000);
		userRepository.save(user);
	}
}
