package main.tdt.it.finalproject.jdbc;

import java.util.Iterator;
import java.util.List;

public abstract class AbstractDB<Model,ReturnType,IdType> {
	protected IConnection condb;
	
	public AbstractDB() {
		super();
		this.condb = new MySQLConnUtils();
	}
	public AbstractDB(IConnection condb) {
		super();
		this.condb = condb;
	}
	public abstract ReturnType add(Model model);
	public abstract ReturnType adds(Iterator<Model> iterator);	
	public abstract ReturnType delete (IdType id);
	public abstract ReturnType update (Model model);
	public abstract Model find(IdType id);
	public abstract List<Model> getAll();
	@Override
	protected void finalize() throws Throwable {
		this.condb.close();
		super.finalize();
	}
}