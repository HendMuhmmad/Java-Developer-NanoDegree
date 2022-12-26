package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping("note")
public class NoteController {
	private final NoteService noteService;
	private final UserService userService;
	
	public NoteController(NoteService noteService, UserService userService) {
		this.noteService = noteService;
		this.userService = userService;
	}
	@PostMapping
	String addOrEditNote(Authentication authentication,@ModelAttribute Note note, RedirectAttributes attributes) {
		String errorMessage = null;
		if(note.getNoteid() != null) {
			try {
				this.noteService.updateNote(note);
			}
			catch(Exception e) {
				errorMessage = "Couldn't edit note. Please try again .";
			}
		}
		else {
			try {
				note.setUserid(userService.getUser(authentication.getName()).getUserId());
				if(this.noteService.addNote(note) < 0) {
					errorMessage = "Couldn't create note. Please try again .";
				}
			}
			catch(Exception e) {
				errorMessage = "Couldn't create note. Please try again .";
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
	
	@PostMapping("deleteNote")
	String deleteNote(Authentication authentication,@RequestParam Integer noteid,RedirectAttributes attributes) {
		String errorMessage = null;
		try {
			this.noteService.deleteNote(noteid);
		}
		catch(Exception e) {
			errorMessage = "Couldn't delete note. Please try again .";
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
