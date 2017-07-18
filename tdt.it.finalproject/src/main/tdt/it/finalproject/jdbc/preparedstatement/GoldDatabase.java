package main.tdt.it.finalproject.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.mysql.cj.api.jdbc.Statement;

import main.tdt.it.finalproject.jdbc.AbstractDB;
import main.tdt.it.finalproject.modal.DollarPrice;
import main.tdt.it.finalproject.modal.GoldPrice;
import main.tdt.it.finalproject.util.DateTimeUtil;

public class GoldDatabase extends AbstractDB<GoldPrice, Boolean, Integer> {

	private final String SQL_INSERT = "Insert into vngold(name,buyprice,sellprice,datetime) values(?,?,?,?)";
	private final String SQL_SELECT = "SELECT * FROM vngold";
	@Override
	public Boolean add(GoldPrice goldPrice) {
		PreparedStatement pstm = null;
		try {
			if (this.condb.getConnection() != null)
				pstm = this.condb.getConnection().prepareStatement(SQL_INSERT);
			pstm.setString(1, goldPrice.getName());
			pstm.setDouble(2, goldPrice.getBuyPrice());
			pstm.setDouble(3, goldPrice.getSellPrice());
			pstm.setDate(4, DateTimeUtil.convertUtilToSQL(goldPrice.getDate()));
			pstm.executeUpdate();

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
		return true;
	}

	@Override
	public Boolean adds(Iterator<GoldPrice> iterator) {
		PreparedStatement pstm = null;
		try {
			Connection connection = this.condb.getConnection();
			pstm = connection.prepareStatement(SQL_INSERT);
			if (connection != null) {
				while (iterator.hasNext()) {
					GoldPrice goldPrice = iterator.next();
					pstm.setString(1, goldPrice.getName());
					pstm.setDouble(2, goldPrice.getBuyPrice());
					pstm.setDouble(3, goldPrice.getSellPrice());
					pstm.setDate(4, DateTimeUtil.convertUtilToSQL(goldPrice.getDate()));
					System.out.println(goldPrice.getName() + "-" + goldPrice.getBuyPrice());
					pstm.addBatch();
				}
				pstm.executeBatch();
				return true;
			}

		} catch (SQLException e) {
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
		List<GoldPrice> rs = new ArrayList<GoldPrice>();
		Connection connection = this.condb.getConnection();
		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet rsSet = statement.executeQuery(this.SQL_SELECT);
			while (rsSet.next()) {
				rs.add(new GoldPrice(rsSet.getString("name"), rsSet.getDouble("buyprice"), rsSet.getDouble("sellprice"), rsSet.getDate("datetime")));
			}
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public List<GoldPrice> getByTime(String startDay, String endDay) {
		String sql = "SELECT * FROM VNGold WHERE datetime >= '"+ startDay + "' AND datetime <= '" + endDay + "'";
		List<GoldPrice> rs = new ArrayList<GoldPrice>();
		Connection connection = this.condb.getConnection();
		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet rsSet = statement.executeQuery(sql);
			while (rsSet.next()) {
				rs.add(new GoldPrice(rsSet.getString("name"), rsSet.getDouble("buyprice"), rsSet.getDouble("sellprice"), rsSet.getDate("datetime")));
			}
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	

}
