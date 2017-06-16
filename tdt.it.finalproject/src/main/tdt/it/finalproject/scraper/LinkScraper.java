package main.tdt.it.finalproject.scraper;

public class LinkScraper {
	private int id;
	private TypeScraper type;
	private String link;
	private String css;
	public LinkScraper(int id, TypeScraper type, String link, String css) {
		super();
		this.id = id;
		this.type = type;
		this.link = link;
		this.css = css;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TypeScraper getType() {
		return type;
	}
	public void setType(TypeScraper type) {
		this.type = type;
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
	@Override
	public String toString() {
		return "LinkScraper [id=" + id + ", type=" + type + ", link=" + link + ", css=" + css + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((css == null) ? 0 : css.hashCode());
		result = prime * result + id;
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LinkScraper other = (LinkScraper) obj;
		if (css == null) {
			if (other.css != null)
				return false;
		} else if (!css.equals(other.css))
			return false;
		if (id != other.id)
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
}
