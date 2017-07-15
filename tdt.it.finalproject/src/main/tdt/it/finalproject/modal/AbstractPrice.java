package main.tdt.it.finalproject.modal;

import java.util.Date;

public abstract class  AbstractPrice {
	protected long id;
	protected Date date;

	public AbstractPrice(Date date) {
		super();
		this.date = date;
	}


	public AbstractPrice(long id, Date date) {
		super();
		this.id = id;
		this.date = date;
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
	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractPrice [id=" + id + ", date=" + date + "]";
	}
	
}
