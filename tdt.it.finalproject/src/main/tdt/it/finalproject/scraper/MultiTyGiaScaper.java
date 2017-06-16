package main.tdt.it.finalproject.scraper;

import java.util.ArrayList;
import java.util.List;

import main.tdt.it.finalproject.exception.NotFoundAssetException;
import main.tdt.it.finalproject.modal.AbstractPrice;
@Deprecated
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
	public List<AbstractPrice> getDollarData() {
		List<AbstractPrice> result = new ArrayList<>();
		for (String date : dates) {

			scaper.setDate(date);
			try {
				result.addAll(scaper.getDollarData());
			} catch (NotFoundAssetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Lay danh sach doi tuong Gold nam trong khoang thoi gian dates
	 * 
	 * @return danh sach chua cac doi tuong
	 */
	public List<AbstractPrice> getGoldData() {
		List<AbstractPrice> result = new ArrayList<>();
		for (String date : dates) {

			scaper.setDate(date);
			try {
				result.addAll(scaper.getGoldData());
			} catch (NotFoundAssetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public void getData(List<AbstractPrice> golds, List<AbstractPrice> dollars) {
		for (String date : dates) {
			scaper = new TyGiaScaper();
			scaper.setDate(date);
			try {
				golds.addAll(scaper.getGoldData());
			} catch (NotFoundAssetException e1) {
				System.err.println(e1.getMessage());
			}
			try {
				dollars.addAll(scaper.getDollarData());
			} catch (NotFoundAssetException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		}
	}

	public void setDates(List<String> dates) {
		this.dates = dates;
	}

	public void setScaper(TyGiaScaper scaper) {
		this.scaper = scaper;
	}

}
