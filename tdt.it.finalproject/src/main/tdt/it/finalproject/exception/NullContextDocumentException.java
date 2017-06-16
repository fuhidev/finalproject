package main.tdt.it.finalproject.exception;

public class NullContextDocumentException extends ScraperException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8167456384670034264L;

	public NullContextDocumentException() {
		super("Not found context document");
	}

	public NullContextDocumentException(String message) {
		super("Not found context document with "+message);
		// TODO Auto-generated constructor stub
	}
	
	

}
