package main.tdt.it.finalproject.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.mysql.cj.api.jdbc.Statement;

import main.tdt.it.finalproject.jdbc.ConnectionUtils;
import main.tdt.it.finalproject.modal.DollarPrice;
import main.tdt.it.finalproject.modal.GoldPrice;
import main.tdt.it.finalproject.modal.WorldGold;
import main.tdt.it.finalproject.util.DateTimeUtil;

@Deprecated
public class Gold_DollarPreparedStatement {
	public PreparedStatement pstm = null;

	public void addVNGold(List<GoldPrice> golds) {
		String sql = "Insert into VNGold(name,buyprice,sellprice,datetime) values(?,?,?,?)";
		try {
			Connection connection = ConnectionUtils.getMyConnection();
			if (connection != null)
				pstm = connection.prepareStatement(sql);
			for (GoldPrice goldPrice : golds) {
				pstm.setString(1, goldPrice.getName());
				pstm.setDouble(2, goldPrice.getBuyPrice());
				pstm.setDouble(3, goldPrice.getSellPrice());
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				Date parsed;
				try {
					parsed = format.parse(goldPrice.getDateTime());
					java.sql.Date date = new java.sql.Date(parsed.getTime());
					pstm.setDate(4, date);
					System.out.println(date + "-" + goldPrice.getName());
					pstm.executeUpdate();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (pstm != null)
					pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void addDollar(List<DollarPrice> dollars) {
		String sql = "Insert into dollar(name,price,date) values(?,?,?)";
		try {
			Connection connection = ConnectionUtils.getMyConnection();
			if (connection != null)
				pstm = connection.prepareStatement(sql);
			for (DollarPrice dollar : dollars) {
				pstm.setString(1, dollar.getName());
				pstm.setDouble(2, dollar.getPrice());

				pstm.setDate(3, DateTimeUtil.convertUtilToSQL(dollar.getDateTime()));

				pstm.executeUpdate();

			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (pstm != null)
					pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void addWorldGold(List<WorldGold> worldGolds) {
		String sql = "INSERT INTO goldworld(name,vnprice,usprice,datetime) VALUES(?,?,?,?)";

		try {
			Connection connection = ConnectionUtils.getMyConnection();
			if (connection != null)
				pstm = connection.prepareStatement(sql);
			for (WorldGold wgold : worldGolds) {
				String sqlDollarPrice = "SELECT price FROM dollar WHERE" + wgold.getDateTime();
				Statement statement = (Statement) connection.createStatement();
				ResultSet rs = statement.executeQuery(sqlDollarPrice);
				pstm.setString(1, wgold.getName());
				if (rs.next()) {
					pstm.setDouble(2, wgold.getUsPrice() * rs.getDouble("price"));
					System.out.println(wgold.getUsPrice() * rs.getDouble("price"));
				}
				pstm.setDouble(2, wgold.getVnPrice());
				pstm.setDouble(3, wgold.getUsPrice());
				java.sql.Date sqlDate = new java.sql.Date(wgold.getDateTime().getTime());
				pstm.setDate(4, sqlDate);

				// pstm.executeUpdate();
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (pstm != null)
					pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void addDollarFormWorldGold(List<WorldGold> dollarPrices) {
		String sql = "INSERT INTO dollar(name,price,date) VALUES(?,?,?)";

		try {
			Connection connection = ConnectionUtils.getMyConnection();
			if (connection != null)
				pstm = connection.prepareStatement(sql);

			for (WorldGold doPrice : dollarPrices) {
				pstm.setString(1, doPrice.getName());
				DecimalFormat df = new DecimalFormat("###.###");
				pstm.setDouble(2, Double.parseDouble(df.format(doPrice.getVnPrice() / doPrice.getUsPrice())));
				java.sql.Date sqlDate = new java.sql.Date(doPrice.getDateTime().getTime());
				pstm.setDate(3, sqlDate);
				pstm.executeUpdate();
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (pstm != null)
					pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
