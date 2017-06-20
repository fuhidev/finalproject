package main.tdt.it.finalproject.jsondata;

import java.util.Date;

import org.json.simple.JSONAware;

import main.tdt.it.finalproject.modal.ForeignCurrencyPrice;

public class DollarPrice extends ForeignCurrencyPrice implements JSONAware{
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

	@Override
	public String toJSONString() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
