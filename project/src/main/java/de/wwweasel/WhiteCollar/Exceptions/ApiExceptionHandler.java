package de.wwweasel.WhiteCollar.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(value=ApiRequestException.class)
	public ResponseEntity<Object> handleException(ApiRequestException e){
		
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		
		// Copy payload of ApiRequestException into ApiException
		ExceptionDTO apiException = new ExceptionDTO( e.message, httpStatus, LocalDateTime.now() );
		
		return new ResponseEntity<Object>( apiException, httpStatus );
		
	}
}
