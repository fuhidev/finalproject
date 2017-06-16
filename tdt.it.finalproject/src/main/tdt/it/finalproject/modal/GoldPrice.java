package main.tdt.it.finalproject.modal;

import java.util.Date;

public class GoldPrice extends AbstractPrice {
	private String name;
	private double buyPrice;
	private double sellPrice;
	private String dateTime;
	private Date date;
	public GoldPrice(int id, String name, double buyPrice, double sellPrice, String dateTime) {
		super(id);
		this.setName(name);
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
		this.dateTime = dateTime;
	}
	public GoldPrice(int id, String name, String buyPrice, String sellPrice, String dateTime) {
		super(id);
		this.setName(name);
		this.setBuyPrice(buyPrice);
		this.setSellPrice(sellPrice);
		this.dateTime = dateTime;
	}
	public GoldPrice(int id, String name, String buyPrice, String sellPrice, Date dateTime) {
		super(id);
		this.setName(name);
		this.setBuyPrice(buyPrice);
		this.setSellPrice(sellPrice);
		this.date = dateTime;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(String buyPrice) {
		this.buyPrice = Double.parseDouble(buyPrice.replace(",", ""));
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(String sellPrice) {
		this.sellPrice = Double.parseDouble(sellPrice.replace(",", ""));
	}
	

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GoldPrice [buyPrice=" + buyPrice + ", sellPrice=" + sellPrice + ", dateTime=" + dateTime + ", id=" + id
				+ ", name=" + name + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(buyPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		temp = Double.doubleToLongBits(sellPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof GoldPrice))
			return false;
		GoldPrice other = (GoldPrice) obj;
		if (Double.doubleToLongBits(buyPrice) != Double.doubleToLongBits(other.buyPrice))
			return false;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (Double.doubleToLongBits(sellPrice) != Double.doubleToLongBits(other.sellPrice))
			return false;
		return true;
	}



}