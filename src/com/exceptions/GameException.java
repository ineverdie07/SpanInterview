package com.exceptions;

/*Exception when interact with the Game controller*/
public class GameException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public GameException(String message) {
		super(message);
	}
	
	public GameException(String message, Throwable cause) {
		super(message, cause);
	}

}
