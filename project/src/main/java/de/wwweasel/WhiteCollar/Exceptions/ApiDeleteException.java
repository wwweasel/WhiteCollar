package de.wwweasel.WhiteCollar.Exceptions;

public class ApiDeleteException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	Throwable throwable;
	
	public ApiDeleteException(String message) {
		this.message = message;
	}
	
	public ApiDeleteException(String message, Throwable throwable) {
		this.message = message;
		this.throwable = throwable;
	}

}
