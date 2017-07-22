package main.tdt.it.finalproject.modal;

import java.util.Date;

public class GoldPrice extends ForeignCurrencyPrice {
	private double buyPrice;
	
	public GoldPrice(long id, String name, double price,Date date) {
		super(id, name, price,date);
		// TODO Auto-generated constructor stub
	}
	public GoldPrice(String name, double buyPrice, double sellPrice, Date date) {
		super(name,sellPrice,date);
		this.buyPrice = buyPrice;
	}
	public GoldPrice(long id,String name, double buyPrice, double sellPrice, Date date) {
		super(id,name,sellPrice,date);
		this.buyPrice = buyPrice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		return super.getPrice();
	}

	public void setSellPrice(String sellPrice) {
		super.setPrice(Double.parseDouble(sellPrice.replace(",", "")));
	}
	

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public void setSellPrice(double sellPrice) {
		super.setPrice(sellPrice);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(buyPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		GoldPrice other = (GoldPrice) obj;
		if (Double.doubleToLongBits(buyPrice) != Double.doubleToLongBits(other.buyPrice))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}
//
//	@Override
//	public String toString() {
//		return "GoldPrice [buyPrice=" + buyPrice + ", date=" + date + ", price=" + price + ", name=" + name + ", id="
//				+ id + "]\r\n";
//	}
	@Override
	public String toString() {
		return "Vàng [giá mua=" + buyPrice + ", giá bán=" + price + ", ngày=" + date + "]\r\n";
	}



}