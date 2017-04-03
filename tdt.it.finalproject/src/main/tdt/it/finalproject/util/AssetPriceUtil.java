package main.tdt.it.finalproject.util;

import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import main.tdt.it.finalproject.jsondata.AssetPrice;
import main.tdt.it.finalproject.jsondata.DollarPrice;
import main.tdt.it.finalproject.jsondata.GoldPrice;

public class AssetPriceUtil {
	public static Collection<AssetPrice> getGolds(List<AssetPrice> golds) {
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
		return map.values();
	}
	public static Collection<AssetPrice> getDollars(List<AssetPrice> dollars) {
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
		return map.values();
	}
}
