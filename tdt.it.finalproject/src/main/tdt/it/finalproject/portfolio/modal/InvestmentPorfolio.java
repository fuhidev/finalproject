package main.tdt.it.finalproject.portfolio.modal;

import java.util.HashMap;

public class InvestmentPorfolio {
	private HashMap<String, Float> hashmap;

	public InvestmentPorfolio() {
		super();
		hashmap = new HashMap<>();
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

}
