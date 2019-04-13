package com.service;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {
	
	private final Path rootLocation = Paths.get("stories/1");
	private final String BASE_STORY_PATH = "stories";
	private final String FORWARD_SLASH = "/";
	
	public List<String> getAllFileList(final Integer id) {
		File folder = getFileFromURL(id);
		List<String> fileNameList = Arrays.stream(folder.listFiles()).map(File::getName).collect(Collectors.toList());
		return fileNameList;
	}
	
	public List<Resource> getAllResources(final Integer id) {
		File folder = getFileFromURL(id);
		List<Resource> resourceList = new ArrayList();
		Arrays.asList(folder.listFiles()).forEach(f -> {
			resourceList.add(getResource(id, f.getName()));
		});
		return resourceList;
	}
	
	public Resource getResource(final Integer id, final String fileName) {
		File file = getFileFromURL(id, fileName);
		Resource resource = null;
		try {
			resource = new UrlResource(file.toURI());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			return resource;
		}
	}
	
	private File getFileFromURL(final Integer id, final String fileName) {
		
		ClassLoader classLoader = getClass().getClassLoader();
		URL url = classLoader
				.getResource(BASE_STORY_PATH + FORWARD_SLASH + String.valueOf(id) + FORWARD_SLASH + fileName);
		File file = null;
		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e) {
			file = new File(url.getPath());
		} finally {
			return file;
		}
	}
	
	private File getFileFromURL(final Integer id) {
		
		ClassLoader classLoader = getClass().getClassLoader();
		URL url = classLoader.getResource(BASE_STORY_PATH + FORWARD_SLASH + String.valueOf(id));
		File file = null;
		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e) {
			file = new File(url.getPath());
		} finally {
			return file;
		}
	}
	
}
