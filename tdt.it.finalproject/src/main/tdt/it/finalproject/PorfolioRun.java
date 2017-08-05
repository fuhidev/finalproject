package main.tdt.it.finalproject;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import main.tdt.it.finalproject.jdbc.preparedstatement.DollarDatabase;
import main.tdt.it.finalproject.jdbc.preparedstatement.GoldDatabase;
import main.tdt.it.finalproject.jdbc.preparedstatement.PorfolioDatabase;
import main.tdt.it.finalproject.modal.DollarPrice;
import main.tdt.it.finalproject.modal.GoldPrice;
import main.tdt.it.finalproject.portfolio.PorfolioOptimize;
import main.tdt.it.finalproject.portfolio.modal.InvestmentPorfolio;
import main.tdt.it.finalproject.portfolio.modal.Point;
import main.tdt.it.finalproject.portfolio.modal.Portfolio;
import main.tdt.it.finalproject.portfolio.modal.Return;
import main.tdt.it.finalproject.util.DateTimeUtil;

public class PorfolioRun {
	private static final float BANK_PERCENT = 0.07f;
	private static final float A = 1.2f;

	public static void main(String[] args) {
		// lay ngay hien tai
		Date date = Calendar.getInstance().getTime();
		// lay khoang thoi gian truoc
		int year = DateTimeUtil.getDate(date, Calendar.YEAR);
		int month = DateTimeUtil.getDate(date, Calendar.MONTH)+1;
		int day = DateTimeUtil.getDate(date, Calendar.DATE);
		String beginTime = year - 5 + "-" + month + "-" + day;
		String endTime = year + "-" + month + "-" + day;
		PorfolioOptimize optimize = new PorfolioOptimize();
		optimize.setBankPercent(BANK_PERCENT);
		GoldDatabase goldDatabase = new GoldDatabase();
		List<GoldPrice> goldPrices = goldDatabase.getByTime(beginTime, endTime);
		DollarDatabase dollarDatabase = new DollarDatabase();
		List<DollarPrice> dollarPrices = dollarDatabase.getByTime(beginTime, endTime);
		// suat sinh loi cua dola
		List<Return> sslDolas = optimize.returns(dollarPrices);
		
		// suat sinh loi cua vang
		List<Return> sslVangs = optimize.returns(goldPrices);
		for (int i = 0; i < sslDolas.size(); i++) {
			Return r1 = sslDolas.get(i);
			Return r2 = sslVangs.get(i);
			System.out.println(i + "\t" + String.format("%.10f", r1.getPercent()) + "\t"
					+ String.format("%.10f", r2.getPercent()));
		}
		Portfolio pflVang = optimize.porfolio(sslVangs);
		System.out.println("vang" + pflVang);
		Portfolio pflDola = optimize.porfolio(sslDolas);
		System.out.println("dola" + pflDola);
		

		float r_gold = pflVang.getReturn(), r_dola = pflDola.getReturn(), std_vang = pflVang.getStandardDeviation(),
				std_dollar = pflDola.getStandardDeviation(),
				cov_vangdola = optimize.cov(sslVangs, sslDolas, pflVang, pflDola);

		optimize.IOS(std_vang, std_dollar, cov_vangdola, r_gold, r_dola);

		float r0 = optimize.getBankPercent();
		float rf = optimize.getBankPercent();
		float minReturn = Math.min(r_gold, r_dola);
		float maxReturn = Math.max(r_gold, r_dola);

		float w_tknh, w_gold, w_dollar;

		if (rf > maxReturn) {
			w_tknh = 1;
			w_gold = 0;
			w_dollar = 0;
		} else {
			float maxSharp = 0;
			Point pointT = new Point();
			for (float y = minReturn; y <= maxReturn; y += 0.000001f) {
				float x = optimize.fy(y);
				float tmpSharp = (y - rf) / x;
				if (tmpSharp > maxSharp) {
					maxSharp = tmpSharp;
					pointT.setExpectedReturn(y);
					pointT.setStandardDeviation(x);
				}
			}
			float sharp = maxSharp;
			float rt = pointT.getExpectedReturn();
			float stdt = pointT.getStandardDeviation();
			System.out.println("PointT" + pointT);
//			float[][] data = new float[2][10000000];
//			int i = 0;
			w_tknh = 1 - (sharp / (A * stdt));
			if (w_tknh < 0) {
				w_tknh = 0;
				w_gold = (rt - r_dola) / (r_gold - r_dola);
				w_dollar = (r_gold - rt) / (r_gold - r_dola);

			} else {
				float b = optimize.getBankPercent();
				float a = (rt - b) / stdt;
				float rp = ((rf + ((rt - rf) / stdt)) * a + rf) / (a + 1);
				float stdp = (rp - rf) / a;
				System.out.println(new Point(stdp, rp));
				w_dollar = (r_gold - w_tknh * r_gold + w_tknh * r0 - rp) / (r_gold - r_dola);

				w_gold = 1 - w_tknh - w_dollar;

				if (w_tknh < 0) {
					w_tknh = 0;
					float w = w_gold + w_dollar;
					w_gold = w_gold / w;
					w_dollar = w_dollar / w;
				}
				if (w_gold < 0) {
					w_gold = 0;
					float w = w_tknh + w_dollar;
					w_tknh = w_tknh / w;
					w_dollar = w_dollar / w;
				}
				if (w_dollar < 0) {
					w_dollar = 0;
					float w = w_tknh + w_gold;
					w_tknh = w_tknh / w;
					w_gold = w_gold / w;
				}

//				for (float x = 0; x <= stdt; x += 0.000001f, i++) {
//					float y = a * x + b;
//					data[0][i] = x;
//					data[1][i] = y;
//				}

			}
//			// ve do thi
//
//			for (float y = minReturn; y <= maxReturn; y += 0.000001f, i++) {
//				float x = (float) Math
//						.sqrt(optimize.getAlpha() * y * y - 2 * optimize.getBeta() * y + optimize.getGamma());
//				data[0][i] = x;
//				data[1][i] = y;
//			}
//			Plot.showScatter("Biểu đồ danh mục đầu tư", data);

		}

		InvestmentPorfolio investmentPorfolio = new InvestmentPorfolio(w_gold, w_dollar, w_tknh,
				DateTimeUtil.formatStringToDate(beginTime,"yyyy-MM-dd"), DateTimeUtil.formatStringToDate(endTime,"yyyy-MM-dd"));
		PorfolioDatabase porfolioDatabase = new PorfolioDatabase();
		porfolioDatabase.add(investmentPorfolio);
	}
}
