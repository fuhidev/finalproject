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
	public DollarPrice(int id, String name, String buyCash, String buyTransfer, String sellPrice, String date) {
		super(id, name);
		this.setBuyCash(buyCash);
		this.setBuyTransfer(buyTransfer);
		this.setSellPrice(sellPrice);
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
	

	public void setBuyCash(float buyCash) {
		this.buyCash = buyCash;
	}

	public void setBuyTransfer(float buyTransfer) {
		this.buyTransfer = buyTransfer;
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
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

		sb.append("\"buyCash\":" + getBuyCash() + "");
		sb.append(",");

		sb.append("\"buyTransfer\":" + getBuyTransfer() + "");
		sb.append(",");

		sb.append("\"sellPrice\":" + getSellPrice() + "");
		sb.append(",");

		sb.append("\"date\":\"" + getDate() + "\"");

		sb.append("}"); // Káº¿t thÃºc má»™t Ä‘á»‘i tÆ°á»£ng JSON lÃ  dáº¥u Ä‘Ã³ng ngoáº·c nhá»�n

		return sb.toString();
	}


	@Override
	public String toString() {
		return "DollarPrice [buyCash=" + buyCash + ", buyTransfer=" + buyTransfer + ", sellPrice=" + sellPrice
				+ ", date=" + date + ", id=" + id + ", name=" + name + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Float.floatToIntBits(buyCash);
		result = prime * result + Float.floatToIntBits(buyTransfer);
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + Float.floatToIntBits(sellPrice);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof DollarPrice))
			return false;
		DollarPrice other = (DollarPrice) obj;
		if (Float.floatToIntBits(buyCash) != Float.floatToIntBits(other.buyCash))
			return false;
		if (Float.floatToIntBits(buyTransfer) != Float.floatToIntBits(other.buyTransfer))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (Float.floatToIntBits(sellPrice) != Float.floatToIntBits(other.sellPrice))
			return false;
		return true;
	}


}
