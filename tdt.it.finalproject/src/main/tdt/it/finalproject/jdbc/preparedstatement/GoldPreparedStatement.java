package main.tdt.it.finalproject.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import main.tdt.it.finalproject.jdbc.ConnectionUtils;
import main.tdt.it.finalproject.jdbc.IConnection;
import main.tdt.it.finalproject.jsondata.GoldPrice;

public class GoldPreparedStatement extends BaseDAO<GoldPrice> {

	@Override
	public void insert(GoldPrice goldPrice) {
		String sql = "Insert into VNGold values(?,?,?)";
		try {
			Connection connection = ConnectionUtils.getMyConnection();
			if (connection != null)
				pstm = connection.prepareStatement(sql);
				pstm.setString(1, goldPrice.getName());
				pstm.setDouble(2, goldPrice.getBuyPrice());
				pstm.setDouble(3, goldPrice.getSellPrice());
				pstm.setString(4, goldPrice.getDateTime());
				pstm.executeQuery();

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

	@Override
	public void inserts(List<GoldPrice> ts) {
		// TODO Auto-generated method stub
		
	}

}
