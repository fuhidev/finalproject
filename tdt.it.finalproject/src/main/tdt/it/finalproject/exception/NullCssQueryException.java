package main.tdt.it.finalproject.exception;

public class NullCssQueryException extends ScraperException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3510062655466840810L;

	public NullCssQueryException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NullCssQueryException() {
		super("Không xác định tham số css");
	}
	

}
