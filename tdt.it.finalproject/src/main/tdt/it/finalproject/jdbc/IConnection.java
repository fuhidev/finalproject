package main.tdt.it.finalproject.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnection {
	Connection getConnection();
	String getConnectString();
	default void close() throws SQLException{
		this.getConnection().close();
	};
}
