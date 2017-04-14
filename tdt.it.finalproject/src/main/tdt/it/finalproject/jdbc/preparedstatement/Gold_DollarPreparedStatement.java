package main.tdt.it.finalproject.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import main.tdt.it.finalproject.jdbc.ConnectionUtils;
import main.tdt.it.finalproject.jsondata.DollarPrice;
import main.tdt.it.finalproject.jsondata.GoldPrice;

import java.sql.PreparedStatement;

public class Gold_DollarPreparedStatement {
	public PreparedStatement pstm = null;

	public void addVNGold(List<GoldPrice> golds) {
		String sql = "Insert into VNGold values(?,?,?)";
		try {
			Connection connection = ConnectionUtils.getMyConnection();
			if (connection != null)
				pstm = connection.prepareStatement(sql);
			for (GoldPrice goldPrice : golds) {
				pstm.setString(1, goldPrice.getName());
				pstm.setDouble(2, goldPrice.getBuyPrice());
				pstm.setDouble(3, goldPrice.getSellPrice());
				pstm.setString(4, goldPrice.getDateTime());
				pstm.executeQuery();
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
		String sql = "Insert into VNGold values(?,?,?)";
		try {
			Connection connection = ConnectionUtils.getMyConnection();
			if (connection != null)
				pstm = connection.prepareStatement(sql);
			for (DollarPrice dollar : dollars) {
				pstm.setString(1, dollar.getName());
				pstm.setDouble(2, dollar.getBuyCash());
				pstm.setDouble(3, dollar.getBuyTransfer());
				pstm.setDouble(4, dollar.getSellPrice());
				pstm.setString(5, dollar.getDate());
				pstm.executeQuery();
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
	public void addWorldGold() {
		// TODO Auto-generated method stub

	}
}
