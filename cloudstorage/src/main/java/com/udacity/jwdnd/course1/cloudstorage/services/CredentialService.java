package com.udacity.jwdnd.course1.cloudstorage.services;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.User;

@Service
public class CredentialService {
	private final CredentialMapper credentialMapper;
	private final EncryptionService encryptionService;
	private final UserService userService;

	public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService, UserService userService) {
		this.credentialMapper = credentialMapper;
		this.encryptionService = encryptionService;
		this.userService = userService;
	}
	
	List<Credential> getUserCredentials(Integer userid){
		return this.credentialMapper.getUserCredentials(userid);
	}
	
	public List<Credential> getUserCredentials(String username){
		User user = userService.getUser(username);
		if(user != null) {
			return getUserCredentials(user.getUserId());
		}
		return null;
	}
	
	public int addCredential(Credential credential) throws MalformedURLException, URISyntaxException {
		new URL(credential.getUrl()).toURI();
		SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        credential.setKey(encodedKey);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), encodedKey));
		return this.credentialMapper.insertCredential(credential);
        
	}
	public void updateCredential(Credential updatedCredential) throws MalformedURLException, URISyntaxException {
		Credential credential = credentialMapper.getCredential(updatedCredential.getCredentialid());
		new URL(updatedCredential.getUrl()).toURI();
		credential.setUrl(updatedCredential.getUrl());
		credential.setUsername(updatedCredential.getUsername());
		credential.setPassword(encryptionService.encryptValue(updatedCredential.getPassword(), credential.getKey()));
		credentialMapper.updateCredential(credential);
	}
	public void deleteCredentil(Integer credentialId) {
		this.credentialMapper.deleteCredential(credentialId);
	}
	
	

}
