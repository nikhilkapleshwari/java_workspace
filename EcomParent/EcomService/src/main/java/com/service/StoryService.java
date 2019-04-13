package com.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.constant.CommonConstant;
import com.model.User;
import com.utilities.JsonConverter;
import com.utilities.JwtTokenCreator;

@Service
public class StoryService {
	
	@Autowired
	ResourceService resourceService;
	
	public Boolean writeToFile(final String token, MultipartFile file, String fileName) throws Exception {
		
		User user = getUserFromToken(token);
		if (file.isEmpty() || Objects.isNull(user))
			return Boolean.FALSE;
		
		String dir = this.createDir(user);
		byte[] bytes = file.getBytes();
		BufferedOutputStream stream = new BufferedOutputStream(
				new FileOutputStream(new File(CommonConstant.STORY_DEST + dir + fileName)));
		stream.write(bytes);
		stream.close();
		return Boolean.TRUE;
	}
	
	private User getUserFromToken(final String token) {
		String userJson = JwtTokenCreator.validateToken(token);
		
		User user = (User) JsonConverter.jsonToObjConverter(userJson, User.class);
		return user;
	}
	
	private String createDir(User user) {
		File directory = new File(CommonConstant.STORY_DEST + String.valueOf(user.getId()));
		if (!directory.exists())
			directory.mkdir();
		return String.valueOf(user.getId()) + "/";
	}
	
	public List<String> getAllFiles(final String token) {
		User user = getUserFromToken(token);
		List<String> fileNameList = resourceService.getAllFileList(user.getId());
		return fileNameList;
	}
	
	public List<Resource> getAllResources(final String token) {
		User user = getUserFromToken(token);
		List<Resource> list = resourceService.getAllResources(user.getId());
		return list;
	}
	
}
