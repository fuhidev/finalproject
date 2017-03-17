package main.tdt.it.finalproject.jsondata;

public abstract class  AssetPrice {
	protected int id;
	protected String name;
	public AssetPrice(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public boolean equals(Object obj) {
		AssetPrice price = (AssetPrice)obj;
		return this.id == price.getId() && this.name.equals(price.getName());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name + " " + this.id;
	}
	
}
