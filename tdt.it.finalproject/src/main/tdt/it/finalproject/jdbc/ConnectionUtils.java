package main.tdt.it.finalproject.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
@Deprecated
public class ConnectionUtils {
	public static Connection getMyConnection() throws ClassNotFoundException,
			SQLException {
		return MySQLConnUtils.getMySQLConnection();
	}

	public static void main(String[] args) throws SQLException,
			ClassNotFoundException {

		System.out.println("Get connection ... ");

		// Lấy ra đối tượng Connection kết nối vào database.
		Connection conn = ConnectionUtils.getMyConnection();

		System.out.println("Get connection " + conn);

		System.out.println("Done!");
	}
}
