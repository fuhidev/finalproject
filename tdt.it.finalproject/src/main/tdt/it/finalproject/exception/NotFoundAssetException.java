package main.tdt.it.finalproject.exception;

public class NotFoundAssetException extends ScraperException{
	private final static String MESS ="Cannot found asset "; 
	/**
	 * 
	 */
	private static final long serialVersionUID = -8429898682449212941L;

	public NotFoundAssetException(String message) {
		super(MESS + message);
	}
	
	
}
