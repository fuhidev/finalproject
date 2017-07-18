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
		List<DollarPrice> collect = prices.stream().filter(price -> {
			return DateTimeUtil.getDate(price.getDateTime(), Calendar.DATE) == 1
					|| DateTimeUtil.getDate(DateTimeUtil.addDays(price.getDateTime(), 1), Calendar.DATE) == 1;
		}).sorted(Comparator.comparing(DollarPrice::getDateTime)).collect(Collectors.toList());
		System.out.println(collect);
		
		
		SuatSinhLois suatsinhlois = new SuatSinhLois();
		for (int i = 0; i < collect.size(); i += 2) {
			DollarPrice current = collect.get(i + 1);
			DollarPrice after = collect.get(i);
			double price = (current.getPrice() - after.getPrice()) / after.getPrice();
			ExpectedReturns suatSinhLoi = new ExpectedReturns(Period.MONTH, price);
			suatsinhlois.add(suatSinhLoi);
		}
		System.out.println(suatsinhlois);
		
		//porfolio
		Portfolio portfolio = new Portfolio();
		// suat sinh loi ky vong

		// theo thang
		double total = 0;
		for (ExpectedReturns suatSinhLoi : suatsinhlois) {
			total += suatSinhLoi.getPrice();
		}
		total /= suatsinhlois.size();
		portfolio.setReturnMonth(total);
		// theo nam
		double totalNam = Math.pow((1 + total), 12) - 1;
		portfolio.setReturnYear(totalNam);

		// tinh do lech chuan suat sinh loi
		double var = 0;

		for (ExpectedReturns suatSinhLoi : suatsinhlois) {
			var += Math.pow(suatSinhLoi.getPrice() -portfolio.getReturnMonth(), 2);
		}
		var /= suatsinhlois.size() - 1;
		portfolio.setVarianceMonth(var);
		double sigma = Math.sqrt(var);
		portfolio.setVarianceYear(sigma);
		System.out.println(portfolio);
		// tinh he so tuong quan giua vang va do la
		
	}
}
