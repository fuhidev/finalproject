package main.tdt.it.finalproject.portfolio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import main.tdt.it.finalproject.modal.DollarPrice;
import main.tdt.it.finalproject.modal.GoldPrice;
import main.tdt.it.finalproject.portfolio.modal.DoLechChuan;
import main.tdt.it.finalproject.portfolio.modal.Period;
import main.tdt.it.finalproject.portfolio.modal.Portfolio;
import main.tdt.it.finalproject.portfolio.modal.ExpectedReturns;
import main.tdt.it.finalproject.portfolio.modal.SuatSinhLois;
import main.tdt.it.finalproject.util.DateTimeUtil;

public class test {
	public static void main(String[] args) {
		// lấy danh sách giá vàng theo kỳ
		ArrayList<DollarPrice> prices = new ArrayList<>();
		prices.add(new DollarPrice(null, 1312.5, DateTimeUtil.formatStringToDate("20170101")));
		prices.add(new DollarPrice(null, 2312.3, DateTimeUtil.formatStringToDate("20170131")));
		prices.add(new DollarPrice(null, 534, DateTimeUtil.formatStringToDate("20170201")));
		prices.add(new DollarPrice(null, 569, DateTimeUtil.formatStringToDate("20170228")));
		ArrayList<GoldPrice> pricesGold = new ArrayList<>();
		pricesGold.add(new GoldPrice(null, 0.0,1312.5, DateTimeUtil.formatStringToDate("20170101")));
		pricesGold.add(new GoldPrice(null,0.0, 2312.3, DateTimeUtil.formatStringToDate("20170131")));
		pricesGold.add(new GoldPrice(null,0.0, 534, DateTimeUtil.formatStringToDate("20170201")));
		pricesGold.add(new GoldPrice(null,0.0, 569, DateTimeUtil.formatStringToDate("20170228")));
		
		// tinh he so tuong quan giua vang va do la
	}
}
