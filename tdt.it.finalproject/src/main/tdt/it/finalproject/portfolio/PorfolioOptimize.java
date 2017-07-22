package main.tdt.it.finalproject.portfolio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.jfree.ui.RefineryUtilities;

import main.tdt.it.finalproject.FastScatterPlotDemo;
import main.tdt.it.finalproject.jdbc.preparedstatement.DollarDatabase;
import main.tdt.it.finalproject.jdbc.preparedstatement.GoldDatabase;
import main.tdt.it.finalproject.modal.DollarPrice;
import main.tdt.it.finalproject.modal.ForeignCurrencyPrice;
import main.tdt.it.finalproject.modal.GoldPrice;
import main.tdt.it.finalproject.portfolio.modal.Graph;
import main.tdt.it.finalproject.portfolio.modal.InvestmentPorfolio;
import main.tdt.it.finalproject.portfolio.modal.Period;
import main.tdt.it.finalproject.portfolio.modal.Point;
import main.tdt.it.finalproject.portfolio.modal.Portfolio;
import main.tdt.it.finalproject.portfolio.modal.Return;
import main.tdt.it.finalproject.portfolio.modal.TestModal;
import main.tdt.it.finalproject.util.DateTimeUtil;

public class PorfolioOptimize {
	private float alpha;
	private float beta;
	private float gamma;
	private final float A = 1.2f;
	private float bankPercent = 0;

	public float getBankPercent() {
		return bankPercent;
	}

	public void setBankPercent(float bankPercent) {
		this.bankPercent = bankPercent;
	}

	public List<Return> suatsinhloi(List<? extends ForeignCurrencyPrice> prices) {
		prices.stream().sorted(Comparator.comparing(ForeignCurrencyPrice::getDate)).collect(Collectors.toList());
		int month = 1;
		List<ForeignCurrencyPrice> collect = new LinkedList<>();
		for (ForeignCurrencyPrice price : prices) {
			int monthPrice = DateTimeUtil.getDate(price.getDate(), Calendar.MONTH) + 1;
			if (monthPrice == month) {
				collect.add(price);
				month++;
				if (month == 13)
					month = 1;
			}
		}
		List<Return> suatsinhlois = new LinkedList<Return>();
		for (int i = 1; i < collect.size(); i++) {
			ForeignCurrencyPrice current = collect.get(i);
			ForeignCurrencyPrice before = collect.get(i - 1);
			// kiểm tra xem có phải cùng tháng hay không
			double percent = (current.getPrice() - before.getPrice()) / before.getPrice();
			Return suatSinhLoi = new Return(Period.MONTH, (float) percent);
			suatsinhlois.add(suatSinhLoi);
		}
		return suatsinhlois;
	}

	public Point bestPoint() {
		float rp = (1 / this.alpha) * (this.beta + (1 / A));
		float standardDeviation = this.fy(rp);
		return new Point(standardDeviation, rp);
	}

	public Portfolio porfolio(List<Return> suatsinhlois) {
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
			float totalNam = (float) Math.pow((1 + total), 12) - 1;
			portfolio.setReturnYear(totalNam);

			// tinh do lech chuan suat sinh loi
			double sdn = 0;

			for (Return suatSinhLoi : suatsinhlois) {
				sdn += Math.pow(suatSinhLoi.getPercent() - portfolio.getReturnMonth(), 2);
			}
			sdn /= suatsinhlois.size() - 1;
			sdn = Math.sqrt(sdn);
			portfolio.setStandardDeviationMonth((float) sdn);
			portfolio.setStandardDeviationYear((float) (sdn * Math.sqrt(12)));
		}
		return portfolio;
	}

	public float getRt() {
		float rf = this.getBankPercent();
		return (gamma - beta * rf) / (beta - alpha * rf);
	}

	public float getSharp() {
		float rt = this.getRt();
		float rf = this.getBankPercent();
		return (float) ((rt - rf) / Math.sqrt(alpha * rt * rt - 2 * beta * rt + gamma));
	}

	public void porfolioT(float r1, float r2) {
		float rt = this.getRt();
		System.out.println(rt);
		float w1 = (rt - r2) / (r1 - r2), w2 = (r1 - rt) / (r1 - r2);
		System.out.println(w1);
		System.out.println(w2);
		// for (float i = 1; i <= 2; i += 0.001) {
		// float rp = (1 / alpha) * (beta + (1 / i)),
		// sigmap = (float) Math.sqrt(alpha * rp * rp - 2 * beta * rp + gamma);
		// System.out.println(i + " " + rp + " " + sigmap);
		// }
	}

	public float cov(InvestmentPorfolio danhMucDauTu, Portfolio vang, Portfolio dola, float hesotuongquan) {
		int size = danhMucDauTu.size();
		double dolechchuan = 0;
		for (int i = 0; i < size; i++) {
			String vangId = vang.getId();
			String dolaId = dola.getId();
			float percentVang = danhMucDauTu.get(vangId);
			float percentDola = danhMucDauTu.get(dolaId);
			dolechchuan = percentDola * percentDola * vang.getStandardDeviation()
					+ percentVang * percentVang * dola.getStandardDeviation() + 2 * percentDola * percentVang
							* hesotuongquan * vang.getStandardDeviation() * dola.getStandardDeviation();

		}
		dolechchuan = Math.sqrt(dolechchuan);
		return (float) dolechchuan;
	}

	public void IOS(float sigma1, float sigma2, float sigma12, float r1, float r2) {
		this.alpha = (float) ((Math.pow(sigma1, 2) + Math.pow(sigma2, 2) - 2 * sigma12) / Math.pow((r1 - r2), 2));
		this.beta = (float) ((r1 * Math.pow(sigma2, 2) + r2 * Math.pow(sigma1, 2) - (r1 + r2) * sigma12)
				/ Math.pow(r1 - r2, 2));
		this.gamma = (float) ((Math.pow(r1, 2) * Math.pow(sigma2, 2) + Math.pow(r2, 2) * Math.pow(sigma1, 2)
				- 2 * r1 * r2 * sigma12) / Math.pow(r1 - r2, 2));

	}

	public float fy(float y) {
		return (float) Math.sqrt(alpha * y * y - 2 * beta * y + gamma);
	}

	public Graph dothi(float maxY, float minY) {
		Graph graph = new Graph();
		for (float y = minY; y < maxY; y += 0.001f) {
			Point p = new Point();
			p.setExpectedReturn(y);
			float x = this.fy(y);
			float standardDeviation = (x);
			p.setStandardDeviation(standardDeviation);
			graph.add(p);
		}
		return graph;
	}

	public static Portfolio[] inputData() {
		ArrayList<TestModal> vnm = new ArrayList<>();
		ArrayList<TestModal> kdc = new ArrayList<>();
		vnm.add(new TestModal(68.5f, 0, 0, 0, DateTimeUtil.dateTime(2006, 2, 28)));
		kdc.add(new TestModal(55.5f, 0, 0, 0, DateTimeUtil.dateTime(2006, 2, 28)));
		vnm.add(new TestModal(81, 0, 0, 0, DateTimeUtil.dateTime(2006, 3, 31)));
		kdc.add(new TestModal(82, 0.8f, 0, 0, DateTimeUtil.dateTime(2006, 3, 31)));
		vnm.add(new TestModal(93.5f, 0, 0, 0, DateTimeUtil.dateTime(2006, 4, 28)));
		kdc.add(new TestModal(97, 0, 0, 0, DateTimeUtil.dateTime(2006, 4, 28)));
		vnm.add(new TestModal(89, 0, 0, 0, DateTimeUtil.dateTime(2006, 5, 31)));
		kdc.add(new TestModal(84, 0, 1 / 5f, 0, DateTimeUtil.dateTime(2006, 5, 31)));
		vnm.add(new TestModal(81, 0.9f, 0, 0, DateTimeUtil.dateTime(2006, 6, 30)));
		kdc.add(new TestModal(88, 0, 0, 0, DateTimeUtil.dateTime(2006, 6, 30)));
		vnm.add(new TestModal(67.5f, 0, 0, 0, DateTimeUtil.dateTime(2006, 7, 31)));
		kdc.add(new TestModal(69, 0, 0, 0, DateTimeUtil.dateTime(2006, 7, 31)));
		vnm.add(new TestModal(82.5f, 0, 0, 0, DateTimeUtil.dateTime(2006, 8, 31)));
		kdc.add(new TestModal(82.5f, 0, 0, 0, DateTimeUtil.dateTime(2006, 8, 31)));
		vnm.add(new TestModal(83, 0, 0, 0, DateTimeUtil.dateTime(2006, 9, 29)));
		kdc.add(new TestModal(83, 0, 0, 0, DateTimeUtil.dateTime(2006, 9, 29)));
		vnm.add(new TestModal(80, 0, 0, 0, DateTimeUtil.dateTime(2006, 10, 31)));
		kdc.add(new TestModal(88, 0, 0, 0, DateTimeUtil.dateTime(2006, 10, 31)));
		vnm.add(new TestModal(106, 0, 0, 0, DateTimeUtil.dateTime(2006, 11, 30)));
		kdc.add(new TestModal(102, 0, 0, 0, DateTimeUtil.dateTime(2006, 11, 30)));
		vnm.add(new TestModal(125, 0, 0, 0, DateTimeUtil.dateTime(2006, 12, 29)));
		kdc.add(new TestModal(142, 0, 0, 0, DateTimeUtil.dateTime(2006, 12, 29)));
		vnm.add(new TestModal(175, 1, 1 / 20f, 10, DateTimeUtil.dateTime(2007, 1, 31)));
		kdc.add(new TestModal(220, 0, 0, 0, DateTimeUtil.dateTime(2007, 1, 31)));
		vnm.add(new TestModal(202, 0, 0, 0, DateTimeUtil.dateTime(2007, 2, 28)));
		kdc.add(new TestModal(227, 0, 0, 0, DateTimeUtil.dateTime(2007, 2, 28)));
		vnm.add(new TestModal(184, 0, 0, 0, DateTimeUtil.dateTime(2007, 3, 30)));
		kdc.add(new TestModal(196, 0.9f, 0, 0, DateTimeUtil.dateTime(2007, 3, 30)));
		vnm.add(new TestModal(169, 0, 0, 0, DateTimeUtil.dateTime(2007, 4, 25)));
		kdc.add(new TestModal(173, 0, 0, 0, DateTimeUtil.dateTime(2007, 4, 25)));
		vnm.add(new TestModal(185, 0, 0, 0, DateTimeUtil.dateTime(2007, 5, 31)));
		kdc.add(new TestModal(230, 0, 1 / 5f, 0, DateTimeUtil.dateTime(2007, 5, 31)));
		vnm.add(new TestModal(180, 1.9f, 0, 0, DateTimeUtil.dateTime(2007, 6, 29)));
		kdc.add(new TestModal(236, 0, 0, 0, DateTimeUtil.dateTime(2007, 6, 29)));
		vnm.add(new TestModal(174, 0, 0, 0, DateTimeUtil.dateTime(2007, 7, 31)));
		kdc.add(new TestModal(226, 0, 0, 0, DateTimeUtil.dateTime(2007, 7, 31)));
		vnm.add(new TestModal(160, 0, 0, 0, DateTimeUtil.dateTime(2007, 8, 31)));
		kdc.add(new TestModal(246, 0, 0, 0, DateTimeUtil.dateTime(2007, 8, 31)));
		vnm.add(new TestModal(182, 0, 0, 0, DateTimeUtil.dateTime(2007, 9, 28)));
		kdc.add(new TestModal(255, 0, 0, 0, DateTimeUtil.dateTime(2007, 9, 28)));
		vnm.add(new TestModal(184, 0, 0, 0, DateTimeUtil.dateTime(2007, 10, 31)));
		kdc.add(new TestModal(203, 0, 0, 0, DateTimeUtil.dateTime(2007, 10, 31)));
		vnm.add(new TestModal(166, 0, 0, 0, DateTimeUtil.dateTime(2007, 11, 30)));
		kdc.add(new TestModal(200, 0, 0, 0, DateTimeUtil.dateTime(2007, 11, 30)));
		vnm.add(new TestModal(166, 0, 0, 0, DateTimeUtil.dateTime(2007, 12, 28)));
		kdc.add(new TestModal(194, 0, 0, 0, DateTimeUtil.dateTime(2007, 12, 28)));
		vnm.add(new TestModal(142, 0, 0, 0, DateTimeUtil.dateTime(2008, 1, 31)));
		kdc.add(new TestModal(174, 0, 0, 0, DateTimeUtil.dateTime(2008, 1, 31)));
		vnm.add(new TestModal(117, 0, 0, 0, DateTimeUtil.dateTime(2008, 2, 29)));
		kdc.add(new TestModal(145, 0, 0, 0, DateTimeUtil.dateTime(2008, 2, 29)));
		vnm.add(new TestModal(109, 0, 0, 0, DateTimeUtil.dateTime(2008, 3, 31)));
		kdc.add(new TestModal(109, 0, 0, 0, DateTimeUtil.dateTime(2008, 3, 31)));
		vnm.add(new TestModal(134, 1, 0, 0, DateTimeUtil.dateTime(2008, 4, 29)));
		kdc.add(new TestModal(120, 0, 0, 0, DateTimeUtil.dateTime(2008, 4, 29)));
		vnm.add(new TestModal(108, 0, 0, 0, DateTimeUtil.dateTime(2008, 5, 30)));
		kdc.add(new TestModal(92, 0, 0, 0, DateTimeUtil.dateTime(2008, 5, 30)));
		vnm.add(new TestModal(106, 0, 0, 0, DateTimeUtil.dateTime(2008, 6, 30)));
		kdc.add(new TestModal(78, 0, 0, 0, DateTimeUtil.dateTime(2008, 6, 30)));
		vnm.add(new TestModal(109, 0, 0, 0, DateTimeUtil.dateTime(2008, 7, 31)));
		kdc.add(new TestModal(59.5f, 0.9f, 11f / 50f, 0, DateTimeUtil.dateTime(2008, 7, 31)));
		vnm.add(new TestModal(106, 1, 0, 0, DateTimeUtil.dateTime(2008, 8, 29)));
		kdc.add(new TestModal(72.5f, 0, 0, 0, DateTimeUtil.dateTime(2008, 8, 29)));
		vnm.add(new TestModal(90.5f, 0, 0, 0, DateTimeUtil.dateTime(2008, 9, 30)));
		kdc.add(new TestModal(53.5f, 0, 0, 0, DateTimeUtil.dateTime(2008, 9, 30)));
		vnm.add(new TestModal(78, 0, 0, 0, DateTimeUtil.dateTime(2008, 10, 31)));
		kdc.add(new TestModal(34, 0, 0, 0, DateTimeUtil.dateTime(2008, 10, 31)));
		vnm.add(new TestModal(76.5f, 0, 0, 0, DateTimeUtil.dateTime(2008, 11, 28)));
		kdc.add(new TestModal(31.5f, 0, 0, 0, DateTimeUtil.dateTime(2008, 11, 28)));
		vnm.add(new TestModal(83, 0, 0, 0, DateTimeUtil.dateTime(2008, 12, 31)));
		kdc.add(new TestModal(30.1f, 1.8f, 0, 0, DateTimeUtil.dateTime(2008, 12, 31)));
		vnm.add(new TestModal(82, 0, 0, 0, DateTimeUtil.dateTime(2009, 1, 23)));
		kdc.add(new TestModal(27.5f, 0, 0, 0, DateTimeUtil.dateTime(2009, 1, 23)));
		vnm.add(new TestModal(74.5f, 0, 0, 0, DateTimeUtil.dateTime(2009, 2, 27)));
		kdc.add(new TestModal(20, 0, 0, 0, DateTimeUtil.dateTime(2009, 2, 27)));
		vnm.add(new TestModal(78.5f, 0, 0, 0, DateTimeUtil.dateTime(2009, 3, 31)));
		kdc.add(new TestModal(23.9f, 0, 0, 0, DateTimeUtil.dateTime(2009, 3, 31)));
		vnm.add(new TestModal(85, 0, 0, 0, DateTimeUtil.dateTime(2009, 4, 29)));
		kdc.add(new TestModal(30.1f, 0, 0, 0, DateTimeUtil.dateTime(2009, 4, 29)));
		vnm.add(new TestModal(88, 0, 0, 0, DateTimeUtil.dateTime(2009, 5, 29)));
		kdc.add(new TestModal(44.5f, 0, 0, 0, DateTimeUtil.dateTime(2009, 5, 29)));
		vnm.add(new TestModal(92, 0, 0, 0, DateTimeUtil.dateTime(2009, 6, 30)));
		kdc.add(new TestModal(43.7f, 0, 0, 0, DateTimeUtil.dateTime(2009, 6, 30)));
		vnm.add(new TestModal(126, 2, 0, 0, DateTimeUtil.dateTime(2009, 7, 31)));
		kdc.add(new TestModal(47, 0, 0, 0, DateTimeUtil.dateTime(2009, 7, 31)));
		vnm.add(new TestModal(158, 2, 0, 0, DateTimeUtil.dateTime(2009, 8, 31)));
		kdc.add(new TestModal(70, 0, 0, 0, DateTimeUtil.dateTime(2009, 8, 31)));
		vnm.add(new TestModal(90, 2, 1 / 1f, 0, DateTimeUtil.dateTime(2009, 9, 30)));
		kdc.add(new TestModal(89, 0, 0, 0, DateTimeUtil.dateTime(2009, 9, 30)));
		vnm.add(new TestModal(87.5f, 0, 0, 0, DateTimeUtil.dateTime(2009, 10, 30)));
		kdc.add(new TestModal(90.5f, 0, 0, 0, DateTimeUtil.dateTime(2009, 10, 30)));
		vnm.add(new TestModal(78.5f, 0, 0, 0, DateTimeUtil.dateTime(2009, 11, 30)));
		kdc.add(new TestModal(75.5f, 2.4f, 0, 0, DateTimeUtil.dateTime(2009, 11, 30)));
		vnm.add(new TestModal(75, 0, 0, 0, DateTimeUtil.dateTime(2009, 12, 31)));
		kdc.add(new TestModal(60.5f, 0, 2 / 5f, 0, DateTimeUtil.dateTime(2009, 12, 31)));
		vnm.add(new TestModal(77.5f, 0, 0, 0, DateTimeUtil.dateTime(2010, 1, 29)));
		kdc.add(new TestModal(61, 0, 0, 0, DateTimeUtil.dateTime(2010, 1, 29)));
		vnm.add(new TestModal(85, 1, 0, 0, DateTimeUtil.dateTime(2010, 2, 26)));
		kdc.add(new TestModal(62, 0, 0, 0, DateTimeUtil.dateTime(2010, 2, 26)));
		vnm.add(new TestModal(85, 0, 0, 0, DateTimeUtil.dateTime(2010, 3, 31)));
		kdc.add(new TestModal(63, 0, 0, 0, DateTimeUtil.dateTime(2010, 3, 31)));
		vnm.add(new TestModal(94, 1, 0, 0, DateTimeUtil.dateTime(2010, 4, 29)));
		kdc.add(new TestModal(66.5f, 0, 0, 0, DateTimeUtil.dateTime(2010, 4, 29)));
		vnm.add(new TestModal(88.5f, 0, 0, 0, DateTimeUtil.dateTime(2010, 5, 31)));
		kdc.add(new TestModal(53.5f, 0, 1 / 4f, 0, DateTimeUtil.dateTime(2010, 5, 31)));
		vnm.add(new TestModal(90, 0, 0, 0, DateTimeUtil.dateTime(2010, 6, 30)));
		kdc.add(new TestModal(52.5f, 0, 0, 0, DateTimeUtil.dateTime(2010, 6, 30)));
		vnm.add(new TestModal(91.5f, 0, 0, 0, DateTimeUtil.dateTime(2010, 7, 30)));
		kdc.add(new TestModal(52, 0, 0, 0, DateTimeUtil.dateTime(2010, 7, 30)));
		vnm.add(new TestModal(89, 3, 0, 0, DateTimeUtil.dateTime(2010, 8, 31)));
		kdc.add(new TestModal(53, 0, 0, 0, DateTimeUtil.dateTime(2010, 8, 31)));
		vnm.add(new TestModal(88.5f, 0, 0, 0, DateTimeUtil.dateTime(2010, 9, 30)));
		kdc.add(new TestModal(55.5f, 0, 0, 0, DateTimeUtil.dateTime(2010, 9, 30)));
		vnm.add(new TestModal(87, 0, 0, 0, DateTimeUtil.dateTime(2010, 10, 29)));
		kdc.add(new TestModal(53, 1.2f, 0, 0, DateTimeUtil.dateTime(2010, 10, 29)));
		vnm.add(new TestModal(84.5f, 0, 0, 0, DateTimeUtil.dateTime(2010, 11, 30)));
		kdc.add(new TestModal(51.5f, 0, 0, 0, DateTimeUtil.dateTime(2010, 11, 30)));
		vnm.add(new TestModal(86, 0, 0, 0, DateTimeUtil.dateTime(2010, 12, 31)));
		kdc.add(new TestModal(51.5f, 0, 0, 0, DateTimeUtil.dateTime(2010, 12, 31)));
		vnm.add(new TestModal(93.5f, 0, 0, 0, DateTimeUtil.dateTime(2011, 1, 28)));
		kdc.add(new TestModal(55, 0, 0, 0, DateTimeUtil.dateTime(2011, 1, 28)));
		List<Return> suatsinhloisVNM = new ArrayList<>();
		for (int i = 0; i < vnm.size() - 1; i++) {
			TestModal current = vnm.get(i + 1);
			TestModal after = vnm.get(i);
			double percent = (current.getPrice()
					+ current.getTylechiacp() * (current.getPrice() - current.getGiacpchia()) - after.getPrice()
					+ current.getCotuctm()) / after.getPrice();
			suatsinhloisVNM.add(new Return(Period.MONTH, (float) percent));
		}
		List<Return> suatsinhloisKDC = new ArrayList<>();
		for (int i = 0; i < kdc.size() - 1; i++) {
			TestModal current = kdc.get(i + 1);
			TestModal after = kdc.get(i);
			double percent = (current.getPrice()
					+ current.getTylechiacp() * (current.getPrice() - current.getGiacpchia()) - after.getPrice()
					+ current.getCotuctm()) / after.getPrice();
			suatsinhloisKDC.add(new Return(Period.MONTH, (float) percent));
		}
		PorfolioOptimize optimize = new PorfolioOptimize();
		Portfolio porfolioVNM = optimize.porfolio(suatsinhloisVNM);
		Portfolio porfolioKDC = optimize.porfolio(suatsinhloisKDC);
		return new Portfolio[] { porfolioVNM, porfolioKDC };
	}

	public float heSoTuongQuan(List<Return> sslVangs, List<Return> sslDolas, Portfolio pflVang, Portfolio pflDola) {
		float p12 = 0;
		int length = Math.min(sslVangs.size(), sslDolas.size());
		float sslDolaThang = pflDola.getReturnMonth();
		float sslVangThang = pflVang.getReturnMonth();
		// tinh he so tuong quan giua vang va dola
		for (int i = 0; i < length; i++) {
			Return sslVang = sslVangs.get(i);
			Return sslDola = sslDolas.get(i);

			p12 += (sslVang.getPercent() - sslVangThang) * (sslDola.getPercent() - sslDolaThang);
		}
		p12 /= ((length + 1) * pflDola.getStandardDeviationMonth() * pflVang.getStandardDeviationMonth());
		return p12;
	}

	public static void main(String[] args) {
		// inputData();
		PorfolioOptimize optimize = new PorfolioOptimize();
		optimize.setBankPercent(0.1f);
		GoldDatabase goldDatabase = new GoldDatabase();
		List<GoldPrice> goldPrices = goldDatabase.getByTime("2010-01-01", "2017-05-31");
		DollarDatabase dollarDatabase = new DollarDatabase();
		List<DollarPrice> dollarPrices = dollarDatabase.getByTime("2010-01-01", "2017-05-31");
		// lấy danh sách giá vàng theo kỳ

		// suat sinh loi cua dola
		List<Return> sslDolas = optimize.suatsinhloi(dollarPrices);
		Portfolio pflDola = optimize.porfolio(sslDolas);
		System.out.println("dola" + pflDola);
		// suat sinh loi cua vang
		List<Return> sslVangs = optimize.suatsinhloi(goldPrices);
		Portfolio pflVang = optimize.porfolio(sslVangs);
		System.out.println("vang" + pflVang);

		float p12 = optimize.heSoTuongQuan(sslVangs, sslDolas, pflVang, pflDola);
		float r1 = pflVang.getReturn(), r2 = pflDola.getReturn(), sigma1 = pflVang.getStandardDeviation(),
				sigma2 = pflDola.getStandardDeviation(), sigma12 = p12 * sigma1 * sigma2;
		// float p12 = 0.2f;
		// float r1 = 0.2f, r2 = 0.12f, sigma1 = 0.4f,
		// sigma2 = 0.25f, sigma12 = p12 * sigma1 * sigma2;
		optimize.IOS(sigma1, sigma2, sigma12, r1, r2);

		float r0 = optimize.getBankPercent();
		float rf = optimize.getBankPercent();
		float minReturn = Math.min(r1, r2);
		float maxReturn = Math.max(r1, r2);
		if (rf > maxReturn) {
System.out.println("Nên đầu tư vào vàng");
		} else {
			float sharp = optimize.getSharp();
			float sigmap = sharp / optimize.A;
			float rt = optimize.getRt();
			float sigmat = optimize.fy(rt);
			float w0 = 1 - (sharp / (optimize.A * sigmat));
			float rp = (((rt - rf) * sigmap) / sigmat) + rf;
			float w2 = (r1 - w0 * r1 + w0 * r0 - rp) / (r1 - r2);
			System.out.println(optimize.bestPoint());
			float w1 = 1 - w0 - w2;
			System.out.println(w0);
			System.out.println(w1);
			System.out.println(w2);

			FastScatterPlotDemo demo = new FastScatterPlotDemo("Fast Scatter Plot Demo");
			float[][] data = new float[2][10000000];
			int i = 0;

			for (float y = minReturn; y <= maxReturn; y += 0.000001f, i++) {
				float x = (float) Math.sqrt(optimize.alpha * y * y - 2 * optimize.beta * y + optimize.gamma);
				data[0][i] = x;
				data[1][i] = y;
			}
			float b = optimize.getBankPercent();
			float a = (rt - b) / sigmat;
			for (float x = 0; x <= sigmat; x += 0.000001f, i++) {
				float y = a * x + b;
				data[0][i] = x;
				data[1][i] = y;
			}
			demo.open(data);
			demo.pack();
			RefineryUtilities.centerFrameOnScreen(demo);
			demo.setVisible(true);
		}
	}

}
