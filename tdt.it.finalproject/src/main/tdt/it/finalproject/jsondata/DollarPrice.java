package main.tdt.it.finalproject.jsondata;

import java.util.Date;

import org.json.simple.JSONAware;

import main.tdt.it.finalproject.modal.ForeignCurrencyPrice;

public class DollarPrice extends ForeignCurrencyPrice implements JSONAware{
	
	public DollarPrice(String name, double price, Date date) {
		super(name, price,date);
	}

	public DollarPrice(long id, double price,Date date) {
		super(id,"USD", price,date);
	}

	@Override
	public void setName(String name) {
	}


	@Override
	public String toJSONString() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
