package main.tdt.it.finalproject.portfolio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import main.tdt.it.finalproject.jdbc.preparedstatement.DollarDatabase;
import main.tdt.it.finalproject.jdbc.preparedstatement.GoldDatabase;
import main.tdt.it.finalproject.modal.DollarPrice;
import main.tdt.it.finalproject.modal.ForeignCurrencyPrice;
import main.tdt.it.finalproject.modal.GoldPrice;
import main.tdt.it.finalproject.portfolio.modal.DanhMucDauTu;
import main.tdt.it.finalproject.portfolio.modal.Period;
import main.tdt.it.finalproject.portfolio.modal.Portfolio;
import main.tdt.it.finalproject.portfolio.modal.Return;
import main.tdt.it.finalproject.util.DateTimeUtil;

public class test {
	public static List<Return> suatsinhloi(List<? extends ForeignCurrencyPrice> prices) {
		List<ForeignCurrencyPrice> collect = prices.stream().filter(price -> {
			return DateTimeUtil.getDate(price.getDate(), Calendar.DATE) == 1
//					|| DateTimeUtil.getDate(price.getDate(), Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
			 || DateTimeUtil.getDate(DateTimeUtil.addDays(price.getDate(), 1),
			 Calendar.DATE) == 1;
		}).sorted(Comparator.comparing(ForeignCurrencyPrice::getDate)).collect(Collectors.toList());
		System.out.println(collect);
		List<Return> suatsinhlois = new ArrayList<>();
		for (int i = 0; i < collect.size(); i += 2) {
			if (i + 1 < collect.size()) {
				ForeignCurrencyPrice current = collect.get(i + 1);
				ForeignCurrencyPrice after = collect.get(i);
				float price = (float) ((current.getPrice() - after.getPrice()) / after.getPrice());
				Return suatSinhLoi = new Return(Period.MONTH, price);
				if (current instanceof DollarPrice)
					suatSinhLoi.setId("DOLLAR");
				if (current instanceof GoldPrice)
					suatSinhLoi.setId("GOLD");
				suatsinhlois.add(suatSinhLoi);
			}
		}
		return suatsinhlois;
	}

	public static Portfolio porfolio(List<Return> suatsinhlois) {
		Portfolio portfolio = new Portfolio();
		if (suatsinhlois.size() > 0) {
			// suat sinh loi ky vong

			// theo thang
			float total = 0;
			for (Return suatSinhLoi : suatsinhlois) {
				total += suatSinhLoi.getPercent();
			}
			total /= suatsinhlois.size();
			portfolio.setReturnMonth(total);
			// theo nam
			float totalNam = (float) (Math.pow((1 + total), 12) - 1);
			portfolio.setReturnYear(totalNam);

			// tinh do lech chuan suat sinh loi
			double var = 0;

			for (Return suatSinhLoi : suatsinhlois) {
				var += Math.pow(suatSinhLoi.getPercent() - portfolio.getReturnMonth().getPercent(), 2);
			}
			var /= suatsinhlois.size() - 1;
			portfolio.setVarianceMonth(var);
			double sigma = Math.sqrt(var);
			portfolio.setVarianceYear(sigma);
			portfolio.setId(suatsinhlois.get(0).getId());
		}
		return portfolio;
	}

	public static void main(String[] args) {
		GoldDatabase goldDatabase = new GoldDatabase();
		List<GoldPrice> goldPrices = goldDatabase.getAll();
		DollarDatabase dollarDatabase = new DollarDatabase();
		List<DollarPrice> dollarPrices = dollarDatabase.getAll();
		// lấy danh sách giá vàng theo kỳ

		// suat sinh loi cua dola
		List<Return> sslDolas = suatsinhloi(dollarPrices);
		Portfolio pflDola = porfolio(sslDolas);
		System.out.println(pflDola);
		// suat sinh loi cua vang
		List<Return> sslVangs = suatsinhloi(goldPrices);
		Portfolio pflVang = porfolio(sslVangs);
		System.out.println(pflVang);

		int length = sslDolas.size();
		float sslDolaThang = pflDola.getReturnMonth().getPercent();
		float sslVangThang = pflVang.getReturnMonth().getPercent();
		float hesotuongquan = 0;
		// tinh he so tuong quan giua vang va dola
		for (int i = 0; i < length; i++) {
			Return sslVang = sslVangs.get(i);
			Return sslDola = sslDolas.get(i);

			hesotuongquan += (sslVang.getPercent() - sslVangThang) * (sslDola.getPercent() - sslDolaThang);
		}
		hesotuongquan /= (length - 1) * pflDola.getVarianceYear() * pflVang.getVarianceYear();
		System.out.println(hesotuongquan);
		// tinh he so tuong quan giua vang va nh
		// tinh he so tuong quan giua dola va nh
//		DanhMucDauTu danhMucDauTu = new DanhMucDauTu();
//		//gia su dau tu dola 0% va vang 100%
//		danhMucDauTu.add("DOLLAR", 0f);
//		danhMucDauTu.add("GOLD", 1f);
//		float dolechchuansinhloikyvongcuamotdanhmucdautu = cov(danhMucDauTu, pflVang, pflDola, hesotuongquan);
//		System.out.println(dolechchuansinhloikyvongcuamotdanhmucdautu);
		//ve duong CAL va IOS
		
	}

	public static float cov(DanhMucDauTu danhMucDauTu, Portfolio vang, Portfolio dola, float hesotuongquan) {
		int size = danhMucDauTu.size();
		double dolechchuan = 0;
		for (int i = 0; i < size; i++) {
			String vangId = vang.getId();
			String dolaId = dola.getId();
			float percentVang = danhMucDauTu.get(vangId);
			float percentDola = danhMucDauTu.get(dolaId);
			dolechchuan = percentDola * percentDola * vang.getVarianceYear()
					+ percentVang * percentVang * dola.getVarianceYear()
					+ 2 * percentDola * percentVang * hesotuongquan * vang.getVarianceYear() * dola.getVarianceYear();

		}
		dolechchuan = Math.sqrt(dolechchuan);
		return (float) dolechchuan;
	}
}
