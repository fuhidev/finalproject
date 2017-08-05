package main.tdt.it.finalproject.scraper.modal;

import java.util.LinkedList;

public class LinkScrapers extends LinkedList<LinkScraper> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4615627978489290461L;

	public LinkScrapers() {
		super();
		this.add(new LinkScraper(LinkScraper.DOLLAR_TYPE, "https://www.tygia.com/?nganhang=VIETCOM", "#ratetb tr:first-child td:first-child b,#ratetb tr:first-child td:last-child span.c4", null));
		this.add(new LinkScraper(LinkScraper.DOLLAR_TYPE, "https://vietcombank.com.vn/", "#exchangerate .tbl-exch tr:last-child td:first-child,#exchangerate .tbl-exch tr:last-child td:last-child",null));
	}

}
