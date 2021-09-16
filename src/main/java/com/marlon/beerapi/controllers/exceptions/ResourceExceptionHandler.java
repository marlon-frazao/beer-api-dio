package com.marlon.beerapi.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.marlon.beerapi.services.exceptions.BeerAlreadyRegisteredException;
import com.marlon.beerapi.services.exceptions.BeerNotFoundException;
import com.marlon.beerapi.services.exceptions.BeerStockExceededException;
import com.marlon.beerapi.services.exceptions.BeerStockNegativeException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(BeerNotFoundException.class)
	public ResponseEntity<StandardError> beerNotFound(BeerNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;

		return ResponseEntity.status(status).body(getStandardError(e, request, status, "Beer not found!"));
	}
	
	@ExceptionHandler(BeerAlreadyRegisteredException.class)
	public ResponseEntity<StandardError> beerAlreadyRegistered(BeerAlreadyRegisteredException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;

		return ResponseEntity.status(status).body(getStandardError(e, request, status, "Beer already exists!"));
	}
	
	@ExceptionHandler(BeerStockExceededException.class)
	public ResponseEntity<StandardError> beerAlreadyRegistered(BeerStockExceededException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;

		return ResponseEntity.status(status).body(getStandardError(e, request, status, "Beer stock limit exceeded!"));
	}
	
	@ExceptionHandler(BeerStockNegativeException.class)
	public ResponseEntity<StandardError> hasNoBeerEnough(BeerStockNegativeException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;

		return ResponseEntity.status(status).body(getStandardError(e, request, status, "Has no beer enough!"));
	}
	
	private StandardError getStandardError(Exception e, HttpServletRequest request, HttpStatus status, String error) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError(error);
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return err;
	}
}
