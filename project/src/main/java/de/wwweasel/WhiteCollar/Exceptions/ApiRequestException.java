package de.wwweasel.WhiteCollar.Exceptions;

public class ApiRequestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	Throwable throwable;
	
	public ApiRequestException(String message) {
		this.message = message;
	}
	
	public ApiRequestException(String message, Throwable throwable) {
		this.message = message;
		this.throwable = throwable;
	}
	
}
