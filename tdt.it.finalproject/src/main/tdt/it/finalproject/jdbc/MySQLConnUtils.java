package main.tdt.it.finalproject.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnUtils {
	public static Connection getMySQLConnection() throws SQLException,
			ClassNotFoundException {
		String hostName = "localhost";
		String dbName = "finalproject";
		String userName = "root";
		String password = "";
		return getMySQLConnection(hostName, dbName, userName, password);
	}

	public static Connection getMySQLConnection(String hostName,
			String dbName, String userName, String password)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?useSSL=false";
		Connection conn = DriverManager.getConnection(connectionURL, userName,
				password);
		return conn;
	}
}
