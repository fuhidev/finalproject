package main.tdt.it.finalproject.modal;

import java.util.Date;

public class DollarPrice extends ForeignCurrencyPrice {


	public DollarPrice(String name, double price, Date dateTime) {
		super(name, price,dateTime);
	}

	public DollarPrice(long id, double price,Date date) {
		super(id, "USD", price,date);
	}

	@Override
	public void setName(String name) {
	}

	@Override
	public String toString() {
		return "DollarPrice [price=" + price + ", name=" + name + ", id=" + id + ", date=" + date + "]";
	}
	

	

}
