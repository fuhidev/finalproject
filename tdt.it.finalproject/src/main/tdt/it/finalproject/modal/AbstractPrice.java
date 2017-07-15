package main.tdt.it.finalproject.modal;

public abstract class  AbstractPrice {
	protected long id;
	
	public AbstractPrice() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AbstractPrice(long id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "AbstractPrice [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		AbstractPrice other = (AbstractPrice) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}