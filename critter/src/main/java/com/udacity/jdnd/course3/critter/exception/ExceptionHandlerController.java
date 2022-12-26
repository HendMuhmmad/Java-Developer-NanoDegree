package com.udacity.jdnd.course3.critter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
	@ExceptionHandler
	public ResponseEntity<String> handleException(NotFoundException exc) {
		 return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler
	public ResponseEntity<String> handleException(Exception exc) {		
		return new ResponseEntity<>(exc.getMessage(),  HttpStatus.BAD_REQUEST); 
	}

}
