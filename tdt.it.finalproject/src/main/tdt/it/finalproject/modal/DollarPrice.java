package main.tdt.it.finalproject.modal;

import java.util.Date;

public class DollarPrice extends ForeignCurrencyPrice{
	private Date dateTime;
	
	public DollarPrice(String name, double price, Date dateTime) {
		super(name, price);
		this.dateTime = dateTime;
	}

	public DollarPrice(long id, double price) {
		super(id,"USD", price);
		this.dateTime = new Date();
	}

	@Override
	public void setName(String name) {
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

}
