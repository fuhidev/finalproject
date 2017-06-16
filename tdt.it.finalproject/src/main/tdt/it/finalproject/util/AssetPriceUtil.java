package main.tdt.it.finalproject.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import main.tdt.it.finalproject.jsondata.DollarPrice1;
import main.tdt.it.finalproject.modal.AbstractPrice;
import main.tdt.it.finalproject.modal.GoldPrice;

public class AssetPriceUtil {
	public static Collection<AbstractPrice> getGolds(List<AbstractPrice> golds,boolean isSort) {
		HashMap<Integer, AbstractPrice> map = new HashMap<>();
		for (AbstractPrice p : golds) {
			GoldPrice g = (GoldPrice) p;
			int year;
			try {
				year = DateTimeUtil.getYear(g.getDateTime());
				if (map.containsKey(year)) {
					GoldPrice x = (GoldPrice) map.get(year);
					x.setBuyPrice(x.getBuyPrice()+g.getBuyPrice());
					x.setSellPrice(x.getSellPrice()+g.getSellPrice());
				} else {
					map.put(year, new GoldPrice(0, "", g.getBuyPrice(), g.getSellPrice(), year+""));
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(isSort){
			SortedSet<Integer> keys = new TreeSet<Integer>(map.keySet());
			List<AbstractPrice> lst = new ArrayList<>();
			for (Integer key : keys) { 
			   AbstractPrice value = map.get(key);
lst.add(value);
			}
//			return map.values().stream().sorted((g1,g2)->((GoldPrice)g2).getDateTime().compareTo(((GoldPrice)g1).getDateTime())).collect(Collectors.toCollection(ArrayList::new));
			return lst;
		}
		return map.values();
	}
	public static Collection<AbstractPrice> getDollars(List<AbstractPrice> dollars, boolean isSort) {
		HashMap<Integer, AbstractPrice> map = new HashMap<>();
		for (AbstractPrice p : dollars) {
			DollarPrice1 g = (DollarPrice1) p;
			int year;
			try {
				year = DateTimeUtil.getYear(g.getDate());
				if (map.containsKey(year)) {
					DollarPrice1 x = (DollarPrice1) map.get(year);
					x.setBuyCash(x.getBuyCash()+g.getBuyCash());
					x.setSellPrice(x.getSellPrice()+g.getSellPrice());
					x.setSellPrice(x.getSellPrice()+g.getSellPrice());
				} else {
					map.put(year, new DollarPrice1(0, "", g.getBuyCash(),g.getBuyTransfer(), g.getSellPrice(), year+""));
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(isSort){
			return map.values().stream().sorted((g1,g2)->((DollarPrice1)g1).getDate().compareTo(((DollarPrice1)g2).getDate())).collect(Collectors.toCollection(ArrayList::new));
		}
		return map.values();
	}
}
