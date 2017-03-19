package main.tdt.it.finalproject.scraper;

import java.util.ArrayList;
import java.util.List;

import main.tdt.it.finalproject.jsondata.AssetPrice;

public class MultiTyGiaScaper {
	private TyGiaScaper scaper = new TyGiaScaper();
	private List<String> dates;

	public MultiTyGiaScaper() {
	}

	public MultiTyGiaScaper(TyGiaScaper scaper, List<String> dates) {
		super();
		this.scaper = scaper;
		this.dates = dates;
	}

	public List<String> getDates() {
		return dates;
	}

	/**
	 * Lay danh sach doi tuong Dollar nam trong khoang thoi gian dates
	 * 
	 * @return danh sach chua cac doi tuong
	 */
	public List<AssetPrice> getDollarData() {
		List<AssetPrice> result = new ArrayList<>();
		for (String date : dates) {
			System.out.println(String.format("Dang duyet du lieu cua Dollar vao ngay %s", date));
			scaper.setDate(date);
			result.addAll(scaper.getDollarData());
		}
		return result;
	}

	/**
	 * Lay danh sach doi tuong Gold nam trong khoang thoi gian dates
	 * 
	 * @return danh sach chua cac doi tuong
	 */
	public List<AssetPrice> getGoldData() {
		List<AssetPrice> result = new ArrayList<>();
		for (String date : dates) {
			System.out.println(String.format("Dang duyet du lieu cua Gold vao ngay %s", date));
			scaper.setDate(date);
			result.addAll(scaper.getGoldData());
		}
		return result;
	}

	public TyGiaScaper getScaper() {
		return scaper;
	}

	public void setDates(List<String> dates) {
		this.dates = dates;
	}

	public void setScaper(TyGiaScaper scaper) {
		this.scaper = scaper;
	}

}
