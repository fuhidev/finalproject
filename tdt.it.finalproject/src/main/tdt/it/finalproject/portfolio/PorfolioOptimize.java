package main.tdt.it.finalproject.portfolio;

import java.util.Calendar;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import main.tdt.it.finalproject.modal.ForeignCurrencyPrice;
import main.tdt.it.finalproject.portfolio.modal.Graph;
import main.tdt.it.finalproject.portfolio.modal.InvestmentPorfolio;
import main.tdt.it.finalproject.portfolio.modal.Period;
import main.tdt.it.finalproject.portfolio.modal.Point;
import main.tdt.it.finalproject.portfolio.modal.Portfolio;
import main.tdt.it.finalproject.portfolio.modal.Return;
import main.tdt.it.finalproject.util.DateTimeUtil;

public class PorfolioOptimize {
	private float alpha;
	private float beta;
	private float gamma;
	private float bankPercent = 0;

	public float getBankPercent() {
		return bankPercent;
	}

	public void setBankPercent(float bankPercent) {
		this.bankPercent = bankPercent;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	public float getBeta() {
		return beta;
	}

	public void setBeta(float beta) {
		this.beta = beta;
	}

	public float getGamma() {
		return gamma;
	}

	public void setGamma(float gamma) {
		this.gamma = gamma;
	}
	public List<ForeignCurrencyPrice> filter(List<? extends ForeignCurrencyPrice> prices){
		prices.stream().sorted(Comparator.comparing(ForeignCurrencyPrice::getDate)).collect(Collectors.toList());
		int month = DateTimeUtil.getDate(prices.get(0).getDate(), Calendar.MONTH) + 1;
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
		return collect;
	}
	public List<Return> returns(List<? extends ForeignCurrencyPrice> prices) {
		List<ForeignCurrencyPrice> collect = this.filter(prices);
		List<Return> returns = new LinkedList<Return>();
		for (int i = 1; i < collect.size(); i++) {
			ForeignCurrencyPrice current = collect.get(i);
			ForeignCurrencyPrice before = collect.get(i - 1);
			// kiểm tra xem có phải cùng tháng hay không
			double percent = (current.getPrice() - before.getPrice()) / before.getPrice();
			Return rt = new Return(Period.MONTH, (float) percent);
			rt.setId(ForeignCurrencyPrice.class.getName());
			rt.setTime(current.getDate());
			returns.add(rt);
		}
		return returns;
	}

	public Portfolio porfolio(List<Return> returns) {
		Portfolio portfolio = new Portfolio();
		if (returns.size() > 0) {
			// suat sinh loi ky vong
			// theo thang
			float total = 0;
			for (Return suatSinhLoi : returns) {
				total += suatSinhLoi.getPercent();
			}
			total /= returns.size();
			portfolio.setReturnMonth(total);
			// theo nam
			float totalNam = (float) Math.pow((1 + total), 12) - 1;
			portfolio.setReturnYear(totalNam);

			// tinh do lech chuan suat sinh loi
			double sdn = 0;

			for (Return rt : returns) {
				sdn += Math.pow(rt.getPercent() - portfolio.getReturnMonth(), 2);
			}
			sdn /= returns.size() - 1;
			sdn = Math.sqrt(sdn);
			portfolio.setStandardDeviationMonth((float) sdn);
			portfolio.setStandardDeviationYear((float) (sdn * Math.sqrt(12)));
		}
		return portfolio;
	}

	public float getRt() {
		float rf = this.getBankPercent();
		float rt = (gamma - beta * rf) / (beta - alpha * rf);
		return rt;
	}

	public float getSharp() {
		float rt = this.getRt();
		float rf = this.getBankPercent();
		return (float) (rt - rf) / this.fy(rt);
	}

	public float[] porfolioT(float r1, float r2) {
		float rt = this.getRt();
		float w1 = (rt - r2) / (r1 - r2), w2 = (r1 - rt) / (r1 - r2);
		return new float[]{w1,w2};
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


	public float cov(List<Return> sslVangs, List<Return> sslDolas, Portfolio pflVang, Portfolio pflDola) {
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
		float cov = p12 * pflVang.getStandardDeviation() * pflDola.getStandardDeviation();
		return cov;
	}

	

}
