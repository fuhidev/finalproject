package main.tdt.it.finalproject.scraper.modal;

public class LinkScraper {
	public static final String DOLLAR_TYPE = "dollar";
	public static final String GOLD_TYPE = "gold";
	public static final String BANK_TYPE = "bank";
	private int id;
	private String type;
	private String link;
	private String css;
	private String parameters;


	public LinkScraper(String type, String link, String css, String parameters) {
		super();
		this.type = type;
		this.link = link;
		this.css = css;
		this.parameters = parameters;
	}

	public String getparameters() {
		return parameters;
	}

	public void setparameters(String parameters) {
		this.parameters = parameters;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "LinkScraper [id=" + id + ", type=" + type + ", link=" + link + ", css=" + css + ", parameters="
				+ parameters + "]";
	}
	
}
