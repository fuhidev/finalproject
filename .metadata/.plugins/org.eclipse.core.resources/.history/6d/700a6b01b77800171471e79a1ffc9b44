package main.tdt.it.finalproject.portfolio.modal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

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
