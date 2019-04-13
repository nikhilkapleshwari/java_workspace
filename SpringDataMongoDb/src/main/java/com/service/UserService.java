package com.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.dao.User;

import com.repository.CustomUserRepoImpl;
import com.repository.UserRepository;

@Component
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CounterService counterService;

	@Autowired
	CustomUserRepoImpl customUserRepoImpl;


	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUser(String userName) {
		return customUserRepoImpl.getUser(userName);
	}
	public User insertUser(User user) {
		User alreadyUser = userRepository.findByUserName(user.getUserName());
		if (Objects.nonNull(alreadyUser)) {
			System.out.println("User already present: " + alreadyUser.getUserName());
			return null;
		}
		user.setUserId(String.valueOf(counterService.getCounter()));
		return userRepository.insert(user);
	}

	public void updateUser(String userName, Map<String, Object> inputMap) {
		customUserRepoImpl.update(userName,inputMap);
		System.out.println("User has been updated.");
	}

	public Boolean deleterUser(User user) {
		if(Objects.isNull(user))
			return Boolean.FALSE;
		userRepository.delete(user);
		return Boolean.TRUE;
	}

}
