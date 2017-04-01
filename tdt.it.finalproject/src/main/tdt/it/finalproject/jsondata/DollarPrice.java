package main.tdt.it.finalproject.jsondata;

import org.json.simple.JSONAware;

public class DollarPrice extends AssetPrice implements JSONAware {

	public float buyCash;
	public float buyTransfer;
	public float sellPrice;
	public String date;

	public DollarPrice(int id, String name, float buyCash, float buyTransfer, float sellPrice, String date) {
		super(id, name);
		this.buyCash = buyCash;
		this.buyTransfer = buyTransfer;
		this.sellPrice = sellPrice;
		this.date = date;
	}

	public float getBuyCash() {
		return buyCash;
	}
	public void setBuyCash(String buyCash) {
		this.buyCash = Float.parseFloat(buyCash.replace(",", ""));
	}

	public float getBuyTransfer() {
		return buyTransfer;
	}
	public void setBuyTransfer(String buyTransfer) {
		this.buyTransfer = Float.parseFloat(buyTransfer.replace(",", ""));
	}

	public float getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(String sellPrice) {
		this.sellPrice = Float.parseFloat(sellPrice.replace(",", ""));
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toJSONString() {
		StringBuffer sb = new StringBuffer();

		sb.append("{"); // Báº¯t Ä‘áº§u má»™t Ä‘á»‘i tÆ°á»£ng JSON lÃ  dáº¥u má»Ÿ ngoáº·c nhá»�n

		sb.append("\"id\":\"" + getId() + "\""); // dÃ²ng nÃ y cÃ³ nghÄ©a lÃ 
													// "id":"GiÃ¡_Trá»‹"
		sb.append(","); // sau má»—i cáº·p key/value lÃ  má»™t dáº¥u pháº©y

		sb.append("\"name\":\"" + getName() + "\"");
		sb.append(",");

		sb.append("\"buyCash\":\"" + getBuyCash() + "\"");
		sb.append(",");

		sb.append("\"buyTransfer\":\"" + getBuyTransfer() + "\"");
		sb.append(",");

		sb.append("\"sellPrice\":\"" + getSellPrice() + "\"");
		sb.append(",");

		sb.append("\"date\":\"" + getDate() + "\"");

		sb.append("}"); // Káº¿t thÃºc má»™t Ä‘á»‘i tÆ°á»£ng JSON lÃ  dáº¥u Ä‘Ã³ng ngoáº·c nhá»�n

		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		DollarPrice price = (DollarPrice) obj;
		return super.equals(obj) && this.buyCash == price.getBuyCash()
				&& this.sellPrice==price.getSellPrice()
				&& this.buyTransfer==price.getBuyTransfer()
				&& this.date.equals(price.getDate());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.toJSONString();
	}

}
