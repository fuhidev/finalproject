package main.tdt.it.finalproject.jdbc;

import java.sql.Connection;

public interface IConnection {
	void open();
	void close();
	Connection getConnection();
	String getConnectString();
}
