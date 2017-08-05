package main.tdt.it.finalproject.portfolio.modal;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class InvestmentPorfolio {
	private HashMap<String, Float> hashmap;
	private Date id;
	private float gold;
	private float dollar;
	private float bank;
	private Date from;
	private Date to;
	
	

	public InvestmentPorfolio(float gold, float dollar, float bank, Date from, Date to) {
		super();
		this.gold = gold;
		this.dollar = dollar;
		this.bank = bank;
		this.from = from;
		this.to = to;
	}

	public InvestmentPorfolio() {
		super();
		hashmap = new HashMap<>();
	}

	public Date getId() {
		return id;
	}

	public void setId(Date id) {
		this.id = id;
	}

	public float getGold() {
		return gold;
	}

	public void setGold(float gold) {
		this.gold = gold;
	}

	public float getDollar() {
		return dollar;
	}

	public void setDollar(float dollar) {
		this.dollar = dollar;
	}

	public float getBank() {
		return bank;
	}

	public void setBank(float bank) {
		this.bank = bank;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public void add(String name, float percent) {
		if (!hashmap.containsKey(name))
			hashmap.put(name, percent);
	}
	public float get(String name){
		return hashmap.get(name);
	}
	public int size(){
		return hashmap.size();
	}
	@Override
	public String toString() {
		Set<Entry<String,Float>> entrySet = hashmap.entrySet();
		Iterator<Entry<String, Float>> iterator = entrySet.iterator();
		String s = "";
		while(iterator.hasNext()){
			Entry<String, Float> entry = iterator.next();
			s+=entry.getKey()+String.format(": %.2f", entry.getValue()*100)+"%\r\n";
		}
		return s;
	}

}
