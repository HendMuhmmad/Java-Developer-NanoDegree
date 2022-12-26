package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping("credential")
public class CredentialController {
	private final CredentialService credentilService;
	private final UserService userService;
	

	public CredentialController(CredentialService credentilService, UserService userService) {
		this.credentilService = credentilService;
		this.userService = userService;
	}
	
	@PostMapping()
	String addOrEditCredential(Authentication authentication,@ModelAttribute Credential credential, RedirectAttributes attributes) {
		String errorMessage = null;
		if(credential.getCredentialid() != null) {
			try {
				this.credentilService.updateCredential(credential);
			}
			catch(MalformedURLException e) {
				errorMessage = "Url is invalid. Please try again .";
			}
			catch(URISyntaxException e) {
				errorMessage = "Url is invalid. Please try again .";
			}
			catch(Exception e) {
				errorMessage = "Couldn't edit Credential. Please try again .";
			}
		}
		else {
			try {
				credential.setUserid(userService.getUser(authentication.getName()).getUserId());
				if(this.credentilService.addCredential(credential) < 0) {
					errorMessage = "Couldn't create Credential. Please try again .";
				}
			}
			catch(MalformedURLException e) {
				errorMessage = "Url is invalid. Please try again .";
			}
			catch(URISyntaxException e) {
				errorMessage = "Url is invalid. Please try again .";
			}
			catch(Exception e) {
				errorMessage = "Couldn't create Credential. Please try again .";
			}
			
		}
		if(errorMessage == null) {
			attributes.addFlashAttribute("success", true);
		}
		else {
			attributes.addFlashAttribute("errorMessage",errorMessage);
		}
		return "redirect:/result";
	}
	
	
	@PostMapping("deleteCredential")
	String deleteNote(Authentication authentication,@RequestParam Integer credentialid,RedirectAttributes attributes) {
		String errorMessage = null;
		try {
			this.credentilService.deleteCredentil(credentialid);
		}
		catch(Exception e) {
			errorMessage = "Couldn't delete Credential. Please try again .";
		}
		if(errorMessage == null) {
			attributes.addFlashAttribute("success", true);
		}
		else {
			attributes.addFlashAttribute("errorMessage",errorMessage);
		}
		return "redirect:/result";
	}
	


}
