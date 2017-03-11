package com.packtpub.JavaScraping.RobustScraper.Tools;

/**
 * Error is thrown if the webpage does not exist
 */
public class PageDoesNotExist extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PageDoesNotExist(String msg) {
		System.out.println(msg);
	}
}