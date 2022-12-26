package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@ControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler
	String handleException(MaxUploadSizeExceededException exc,RedirectAttributes attributes) {
		String errorMessage = "Couldn't upload File. File Larger than 10 MB.";
		attributes.addFlashAttribute("errorMessage",errorMessage);
		return "redirect:/result";
	}
	
	@ExceptionHandler
	String handleException(Exception ex,RedirectAttributes attributes) {
		attributes.addFlashAttribute("errorMessage",ex.getMessage());
		return "redirect:/result";
	}
	
	
}
