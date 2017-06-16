package main.tdt.it.finalproject.scraper;

public class TypeScraper {
	private int id;
	private int name;
	public TypeScraper(int id, int name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getName() {
		return name;
	}
	public void setName(int name) {
		this.name = name;
	}
	
}
