package main.tdt.it.finalproject.jsondata;

import org.json.simple.JSONAware;

public class DollarPrice extends AssetPrice implements JSONAware {

	public String buyCash;
	public String buyTransfer;
	public String sellPrice;
	public String date;

	public DollarPrice(int id, String name, String buyCash, String buyTransfer, String sellPrice, String date) {
		super(id, name);
		this.buyCash = buyCash;
		this.buyTransfer = buyTransfer;
		this.sellPrice = sellPrice;
		this.date = date;
	}

	public String getBuyCash() {
		return buyCash;
	}
	public Float getFloatBuyCash() {
		return Float.parseFloat(buyCash.replace(",", ""));
	}
	public void setBuyCash(String buyCash) {
		this.buyCash = buyCash;
	}

	public String getBuyTransfer() {
		return buyTransfer;
	}
	public Float getFloatBuyTransfer() {
		return Float.parseFloat(buyTransfer.replace(",", ""));
	}
	public void setBuyTransfer(String buyTransfer) {
		this.buyTransfer = buyTransfer;
	}

	public String getSellPrice() {
		return sellPrice;
	}
	public Float getFloatSellPrice() {
		return Float.parseFloat(sellPrice.replace(",", ""));
	}
	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
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
		return super.equals(obj) && this.buyCash.equals(price.getBuyCash())
				&& this.sellPrice.equals(price.getSellPrice()) && this.buyTransfer.equals(price.getBuyTransfer())
				&& this.date.equals(price.getDate());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.toJSONString();
	}

}
