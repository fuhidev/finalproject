package main.tdt.it.finalproject.portfolio;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import main.tdt.it.finalproject.modal.AbstractPrice;
import main.tdt.it.finalproject.modal.DollarPrice;
import main.tdt.it.finalproject.modal.ForeignCurrencyPrice;
import main.tdt.it.finalproject.portfolio.modal.ExpectedReturns;
import main.tdt.it.finalproject.portfolio.modal.Period;
import main.tdt.it.finalproject.portfolio.modal.Portfolio;
import main.tdt.it.finalproject.portfolio.modal.SuatSinhLois;
import main.tdt.it.finalproject.util.DateTimeUtil;

public class MonthPorfolio<T extends ForeignCurrencyPrice> {

	public MonthPorfolio(List<T> prices) {
		super();
		List<T> collect = prices.stream().filter(price -> {
			return DateTimeUtil.getDate(price.getDate(), Calendar.DATE) == 1
					|| DateTimeUtil.getDate(DateTimeUtil.addDays(price.getDate(), 1), Calendar.DATE) == 1;
		}).sorted(Comparator.comparing(T::getDate)).collect(Collectors.toList());
		System.out.println(collect);
		
		
		
		SuatSinhLois suatsinhlois = new SuatSinhLois();
		for (int i = 0; i < collect.size(); i += 2) {
			T current = collect.get(i + 1);
			T after = collect.get(i);
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
	}

}
