package main.tdt.it.finalproject.jdbc;

import java.util.List;

public abstract class BaseDB<Model,ReturnType,IdType> {
	protected IConnection condb;
	
	public BaseDB() {
		super();
		this.condb = new MySQLConnUtils();
	}
	public BaseDB(IConnection condb) {
		super();
		this.condb = condb;
	}
	public abstract ReturnType add(Model e);
	public abstract ReturnType adds(Model e);	
	public abstract ReturnType delete (IdType k);
	public abstract ReturnType update (Model e);
	public abstract Model find(IdType k);
	public abstract List<Model> getAll();
}