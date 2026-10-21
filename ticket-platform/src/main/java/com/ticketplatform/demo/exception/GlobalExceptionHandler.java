package com.ticketplatform.demo.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.core.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EventNotFoundException.class)
	public ResponseEntity<ApiError> handleNotFound(EventNotFoundException ex){
		ApiError error = new ApiError(404, ex.getMessage(), null, LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex){
		Map<String, String > fields = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
		.forEach(f -> fields.put(f.getField(), f.getDefaultMessage()));
		ApiError error = new ApiError(400, "Validation Failed", fields, LocalDateTime.now());
		return ResponseEntity.badRequest().body(error);
	}
	
	@ExceptionHandler(PropertyReferenceException.class)
	public ResponseEntity<ApiError> handleInvalidSort(PropertyReferenceException ex){
		
		Map<String, String > fields = new HashMap<>();
		fields.put(ex.getPropertyName(), ex.getPropertyName());
		ApiError error = new ApiError(400, "Invalid sort parameter", fields, LocalDateTime.now());
		return ResponseEntity.badRequest().body(error);
	}
}
