package main.tdt.it.finalproject.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import main.tdt.it.finalproject.jsondata.AssetPrice;
import main.tdt.it.finalproject.jsondata.DollarPrice;
import main.tdt.it.finalproject.jsondata.GoldPrice;

public class AssetPriceUtil {
	public static Collection<AssetPrice> getGolds(List<AssetPrice> golds,boolean isSort) {
		HashMap<Integer, AssetPrice> map = new HashMap<>();
		for (AssetPrice p : golds) {
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
			List<AssetPrice> lst = new ArrayList<>();
			for (Integer key : keys) { 
			   AssetPrice value = map.get(key);
lst.add(value);
			}
//			return map.values().stream().sorted((g1,g2)->((GoldPrice)g2).getDateTime().compareTo(((GoldPrice)g1).getDateTime())).collect(Collectors.toCollection(ArrayList::new));
			return lst;
		}
		return map.values();
	}
	public static Collection<AssetPrice> getDollars(List<AssetPrice> dollars, boolean isSort) {
		HashMap<Integer, AssetPrice> map = new HashMap<>();
		for (AssetPrice p : dollars) {
			DollarPrice g = (DollarPrice) p;
			int year;
			try {
				year = DateTimeUtil.getYear(g.getDate());
				if (map.containsKey(year)) {
					DollarPrice x = (DollarPrice) map.get(year);
					x.setBuyCash(x.getBuyCash()+g.getBuyCash());
					x.setSellPrice(x.getSellPrice()+g.getSellPrice());
					x.setSellPrice(x.getSellPrice()+g.getSellPrice());
				} else {
					map.put(year, new DollarPrice(0, "", g.getBuyCash(),g.getBuyTransfer(), g.getSellPrice(), year+""));
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(isSort){
			return map.values().stream().sorted((g1,g2)->((DollarPrice)g1).getDate().compareTo(((DollarPrice)g2).getDate())).collect(Collectors.toCollection(ArrayList::new));
		}
		return map.values();
	}
}
