package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;



@Controller
@RequestMapping("/signup")
public class SignupController {
private final UserService userSerivce;
	
	public SignupController(UserService userSerivce) {
		this.userSerivce = userSerivce;
	}

	@GetMapping
	public String getSignupPage() {
		return "signup";
	}
	
	@PostMapping
	public String signupUser(@ModelAttribute User user, RedirectAttributes attributes) {
        String errorMessage = null;
        if (!userSerivce.isUsernameAvailable(user.getUsername())) {
        	int rowsAdded = userSerivce.createUser(user);
            if (rowsAdded < 0) {
            	errorMessage = "There was an error signing you up. Please try again.";
            }
        }
        else {
        	errorMessage = "The username already exists.";
        }
        if (errorMessage == null) {
        	attributes.addFlashAttribute("success", true);
        	return "redirect:/login";
        } else {
            attributes.addFlashAttribute("errorMessage", errorMessage);
        }
        return "redirect:/signup";
    }

}
