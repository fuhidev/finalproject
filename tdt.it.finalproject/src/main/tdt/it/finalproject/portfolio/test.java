package main.tdt.it.finalproject.portfolio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import main.tdt.it.finalproject.jdbc.preparedstatement.DollarDatabase;
import main.tdt.it.finalproject.jdbc.preparedstatement.GoldDatabase;
import main.tdt.it.finalproject.modal.DollarPrice;
import main.tdt.it.finalproject.modal.ForeignCurrencyPrice;
import main.tdt.it.finalproject.modal.GoldPrice;
import main.tdt.it.finalproject.portfolio.modal.InvestmentPorfolio;
import main.tdt.it.finalproject.portfolio.modal.Period;
import main.tdt.it.finalproject.portfolio.modal.Portfolio;
import main.tdt.it.finalproject.portfolio.modal.Return;
import main.tdt.it.finalproject.util.DateTimeUtil;

public class test {
	public static List<Return> suatsinhloi(List<? extends ForeignCurrencyPrice> prices) {
		List<ForeignCurrencyPrice> collect = prices.stream().filter(price -> {
			return DateTimeUtil.getDate(price.getDate(), Calendar.DATE) == 1
					|| DateTimeUtil.getDate(DateTimeUtil.addDays(price.getDate(), 1), Calendar.DATE) == 1;
		}).sorted(Comparator.comparing(ForeignCurrencyPrice::getDate)).collect(Collectors.toList());
		System.out.println(collect);
		List<Return> suatsinhlois = new ArrayList<>();
		int step = 1;
		for (int i = 0; i < collect.size(); i += step) {
			if (i + 1 < collect.size()) {
				ForeignCurrencyPrice current = collect.get(i + 1);
				ForeignCurrencyPrice after = collect.get(i);
				//kiểm tra xem có phải cùng tháng hay không
				if(DateTimeUtil.getDate(current.getDate(),Calendar.MONTH) == DateTimeUtil.getDate(after.getDate(),Calendar.MONTH)){
				float price = (float) ((current.getPrice() - after.getPrice()) / after.getPrice());
				Return suatSinhLoi = new Return(Period.MONTH, price);
				if (current instanceof DollarPrice)
					suatSinhLoi.setId("DOLLAR");
				if (current instanceof GoldPrice)
					suatSinhLoi.setId("GOLD");
				suatsinhlois.add(suatSinhLoi);
				step = 2;
				}else{
					step = 1;
				}
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
				var += Math.pow(suatSinhLoi.getPercent() - portfolio.getReturnMonth(), 2);
			}
			var /= suatsinhlois.size() - 1;
			portfolio.setVarianceMonth((float) var);
			double sigma = Math.sqrt(var);
			portfolio.setVarianceYear((float) sigma);
			portfolio.setId(suatsinhlois.get(0).getId());
		}
		return portfolio;
	}

	public static void main(String[] args) {
		GoldDatabase goldDatabase = new GoldDatabase();
		List<GoldPrice> goldPrices = goldDatabase.getAll().stream().sorted(Comparator.comparing(ForeignCurrencyPrice::getDate).reversed()).collect(Collectors.toList());
		DollarDatabase dollarDatabase = new DollarDatabase();
		List<DollarPrice> dollarPrices = dollarDatabase.getAll().stream().sorted(Comparator.comparing(ForeignCurrencyPrice::getDate).reversed()).collect(Collectors.toList());
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
		float sslDolaThang = pflDola.getReturnMonth();
		float sslVangThang = pflVang.getReturnMonth();
		float hesotuongquan = 0;
		// tinh he so tuong quan giua vang va dola
		for (int i = 0; i < length; i++) {
			Return sslVang = sslVangs.get(i);
			Return sslDola = sslDolas.get(i);

			hesotuongquan += (sslVang.getPercent() - sslVangThang) * (sslDola.getPercent() - sslDolaThang);
		}
		hesotuongquan /= (length - 1) * pflDola.getVariance() * pflVang.getVariance();
		// System.out.println(hesotuongquan);
		// // tinh he so tuong quan giua vang va nh
		// // tinh he so tuong quan giua dola va nh
		// InvestmentPorfolio danhMucDauTu = new InvestmentPorfolio();
		// //gia su dau tu dola 0% va vang 100%
		// danhMucDauTu.add("DOLLAR", 0f);
		// danhMucDauTu.add("GOLD", 1f);
		// float dolechchuansinhloikyvongcuamotdanhmucdautu = cov(danhMucDauTu,
		// pflVang, pflDola, hesotuongquan);
		// System.out.println(dolechchuansinhloikyvongcuamotdanhmucdautu);
		// ve duong CAL va IOS
		float[] ios = IOS(pflDola.getVariance(), pflVang.getVariance(),
				hesotuongquan * pflDola.getVariance() * pflVang.getVariance(), pflDola.getReturn(),
				pflVang.getReturn());
		float maxy = Math.max(pflDola.getReturn(), pflVang.getReturn());
		ArrayList<float[]> dothi = dothi(ios[0], ios[1], ios[2], maxy);
		for (float[] fs : dothi) {

			System.out.println(Arrays.toString(fs));
		}
	}

	public static float cov(InvestmentPorfolio danhMucDauTu, Portfolio vang, Portfolio dola, float hesotuongquan) {
		int size = danhMucDauTu.size();
		double dolechchuan = 0;
		for (int i = 0; i < size; i++) {
			String vangId = vang.getId();
			String dolaId = dola.getId();
			float percentVang = danhMucDauTu.get(vangId);
			float percentDola = danhMucDauTu.get(dolaId);
			dolechchuan = percentDola * percentDola * vang.getVariance()
					+ percentVang * percentVang * dola.getVariance()
					+ 2 * percentDola * percentVang * hesotuongquan * vang.getVariance() * dola.getVariance();

		}
		dolechchuan = Math.sqrt(dolechchuan);
		return (float) dolechchuan;
	}

	public static float[] IOS(float sigma1, float sigma2, float sigma12, float r1, float r2) {
		float alpha, beta, gamma;
		alpha = (float) ((Math.pow(sigma1, 2) + Math.pow(sigma2, 2) - 2 * sigma12) / Math.pow((r1 - r2), 2));
		beta = (float) ((r1 * Math.pow(sigma2, 2) + r2 * Math.pow(sigma1, 2) - (r1 + r2) * sigma12)
				/ Math.pow(r1 - r2, 2));
		gamma = (float) ((Math.pow(r1, 2) * Math.pow(sigma2, 2) + Math.pow(r2, 2) * Math.pow(sigma1, 2)
				- 2 * sigma1 * sigma2 * sigma12) / Math.pow(r1 - r2, 2));
		System.out.println(alpha);
		System.out.println(beta);
		System.out.println(gamma);
		return new float[] { alpha, beta, gamma };

	}

	public static ArrayList<float[]> dothi(float alphha, float beta, float gamma, float minx) {
		ArrayList<float[]> arrayList = new ArrayList<>();
		for (int i = 0; i < minx; i += 0.1f) {
			float[] kq = new float[2];
			kq[0] = i;
			kq[1] = (float) Math.sqrt(alphha * i * i - 2 * beta * i + gamma);
			arrayList.add(kq);
		}
		return arrayList;
	}

}
