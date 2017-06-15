package main.tdt.it.finalproject.jdbc.preparedstatement;

import java.sql.PreparedStatement;
import java.util.List;

public abstract class BaseDAO<T> {
	protected PreparedStatement pstm;
	public abstract void insert(T t);
	public abstract void inserts(List<T> ts);
}
