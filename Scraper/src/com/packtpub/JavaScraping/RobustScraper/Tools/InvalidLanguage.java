package com.packtpub.JavaScraping.RobustScraper.Tools;

/* InvalidLanguage.java is the exception method that is thrown
 when the language given to the Wikipedia class does not exist:
 */
/**
 * Is thrown if a two letter language code (e.g. "en," "fr," "de") does not
 * actually exist
 */
public class InvalidLanguage extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidLanguage(String msg) {
		System.out.println(msg);
	}
}
