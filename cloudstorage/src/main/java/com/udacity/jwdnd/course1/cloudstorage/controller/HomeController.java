package com.udacity.jwdnd.course1.cloudstorage.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	private final NoteService noteService;
	private final CredentialService credentialService;
	private final EncryptionService encryptionService;
	private final FileService fileService;
	
	public HomeController(NoteService noteService, CredentialService credentialService, EncryptionService encryptionService, FileService fileService) {
		this.noteService = noteService;
		this.credentialService = credentialService;
		this.encryptionService = encryptionService;
		this.fileService = fileService;
	}

	@GetMapping
	String getHomePage(Authentication authentication,Model model) {
		model.addAttribute("userNotes",noteService.getUserNotes(authentication.getName()));
		model.addAttribute("userCredentials", credentialService.getUserCredentials(authentication.getName()));
		model.addAttribute("encryptionService",this.encryptionService);
		model.addAttribute("userFiles", fileService.getUserFiles(authentication.getName()));
		return "home";
	}
	


}
