package main.tdt.it.finalproject.jsondata;

import org.json.simple.JSONAware;

public class GoldPrice extends AssetPrice implements JSONAware {
	private float buyPrice;
	private float sellPrice;
	private String dateTime;

	public GoldPrice(int id, String name, float buyPrice, float sellPrice, String dateTime) {
		super(id, name);
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
		this.dateTime = dateTime;
	}
	public GoldPrice(int id, String name, String buyPrice, String sellPrice, String dateTime) {
		super(id, name);
		this.setBuyPrice(buyPrice);
		this.setSellPrice(sellPrice);
		this.dateTime = dateTime;
	}
	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public float getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(String buyPrice) {
		this.buyPrice = Float.parseFloat(buyPrice.replace(",", ""));
	}

	public float getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(String sellPrice) {
		this.sellPrice = Float.parseFloat(sellPrice.replace(",", ""));
	}
	

	public void setBuyPrice(float buyPrice) {
		this.buyPrice = buyPrice;
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String toJSONString() {
		StringBuffer sb = new StringBuffer();

		sb.append("{"); // Bắt đầu một đối tượng JSON là dấu mở ngoặc nhọn

		sb.append("\"id\":\"" + getId() + "\""); // dòng này có nghĩa là
													// "id":"Giá_Trị"
		sb.append(","); // sau mỗi cặp key/value là một dấu phẩy

		sb.append("\"name\":\"" + getName() + "\"");
		sb.append(",");

		sb.append("\"buyPrice\":" + getBuyPrice() + "");
		sb.append(",");

		sb.append("\"sellPrice\":" + getSellPrice() + "");
		sb.append(",");

		sb.append("\"date\":\"" + getDateTime() + "\"");

		sb.append("}"); // Kết thúc một đối tượng JSON là dấu đóng ngoặc nhọn

		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GoldPrice [buyPrice=" + buyPrice + ", sellPrice=" + sellPrice + ", dateTime=" + dateTime + ", id=" + id
				+ ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Float.floatToIntBits(buyPrice);
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + Float.floatToIntBits(sellPrice);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof GoldPrice))
			return false;
		GoldPrice other = (GoldPrice) obj;
		if (Float.floatToIntBits(buyPrice) != Float.floatToIntBits(other.buyPrice))
			return false;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (Float.floatToIntBits(sellPrice) != Float.floatToIntBits(other.sellPrice))
			return false;
		return true;
	}



}