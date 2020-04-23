package de.wwweasel.WhiteCollar.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ExceptionDTO{
	private final String message;
	private final HttpStatus httpStatus;
	private final LocalDateTime dateTime;
	
	public ExceptionDTO(String message, HttpStatus httpStatus, LocalDateTime dateTime) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
		this.dateTime = dateTime;
	}

	public String getMessage() {
		return message;
	}


	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}
	
	
	
	
}
