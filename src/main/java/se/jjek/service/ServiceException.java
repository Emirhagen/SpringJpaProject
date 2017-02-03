package se.jjek.service;

public class ServiceException extends Exception {

	private static final long serialVersionUID = -7372730042003359907L;

	protected ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	protected ServiceException(String message) {
		super(message);
	}

	protected ServiceException(Throwable cause) {
		super(cause);
	}

	
	
}
