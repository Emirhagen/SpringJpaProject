package se.jjek.security;


public final class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5013360934942943714L;

	public ServiceException(String message, Throwable e) {
		super(message, e);
	}
	
}
