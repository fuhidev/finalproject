package main.tdt.it.finalproject.jdbc;

import java.sql.Connection;

public interface IConnection {
	Connection getConnection();
	String getConnectString();
}
