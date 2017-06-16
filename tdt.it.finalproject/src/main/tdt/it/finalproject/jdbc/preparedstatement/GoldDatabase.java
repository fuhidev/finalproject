package main.tdt.it.finalproject.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import main.tdt.it.finalproject.jdbc.AbstractDB;
import main.tdt.it.finalproject.jdbc.ConnectionUtils;
import main.tdt.it.finalproject.modal.GoldPrice;

public class GoldDatabase extends AbstractDB<GoldPrice, Boolean, Integer> {

	private final String SQL_INSERT = "Insert into VNGold values(?,?,?)";

	@Override
	public Boolean add(GoldPrice goldPrice) {
		PreparedStatement pstm = null;
		try {
			if (this.condb.getConnection() != null)
				pstm = this.condb.getConnection().prepareStatement(SQL_INSERT);
			pstm.setString(1, goldPrice.getName());
			pstm.setDouble(2, goldPrice.getBuyPrice());
			pstm.setDouble(3, goldPrice.getSellPrice());
			pstm.setString(4, goldPrice.getDateTime());
			return pstm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (pstm != null)
					pstm.close();
				this.condb.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public Boolean adds(Iterator<GoldPrice> iterator) {
		PreparedStatement pstm = null;
		try {
			Connection connection = ConnectionUtils.getMyConnection();
			pstm = connection.prepareStatement(SQL_INSERT);
			if (connection != null) {
				while (iterator.hasNext()) {
					GoldPrice goldPrice = iterator.next();
					pstm.setString(1, goldPrice.getName());
					pstm.setDouble(2, goldPrice.getBuyPrice());
					pstm.setDouble(3, goldPrice.getSellPrice());
					pstm.setString(4, goldPrice.getDateTime());
					pstm.addBatch();
				}
				pstm.executeBatch();
				return true;
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
		return false;
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(GoldPrice model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GoldPrice find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GoldPrice> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
