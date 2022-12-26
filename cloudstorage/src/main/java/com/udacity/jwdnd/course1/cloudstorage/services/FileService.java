package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.User;

@Service
public class FileService {
	private final FileMapper fileMapper;
	private final UserService userService;

	public FileService(FileMapper fileMapper, UserService userService) {
		this.fileMapper = fileMapper;
		this.userService = userService;
	}
	
	public boolean isFileNameAvailable(String fileName,Integer userid) {
		File file = this.fileMapper.getFile(fileName);
		if(file != null && file.getUserid() == userid) {
			return true;
		}		
		return false;
	}
	public int insertFile(File file) {
		return this.fileMapper.insertFile(file);
	}
	public File getFile(Integer fileid) {
		 return this.fileMapper.getFileById(fileid);
	}
	public void deleteFile(Integer fileid) {
		this.fileMapper.deleteFile(fileid);
	}
	
	public List<File> getUserFiles(Integer userid){
		return this.fileMapper.getUserFiles(userid);
	}
	
	public List<File> getUserFiles(String username){
		User user = userService.getUser(username);
		if(user != null) {
			return getUserFiles(user.getUserId());
		}
		return null;
	}

}
