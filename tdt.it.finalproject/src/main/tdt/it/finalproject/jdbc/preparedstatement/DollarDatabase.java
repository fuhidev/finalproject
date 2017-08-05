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
import main.tdt.it.finalproject.util.DateTimeUtil;

public class DollarDatabase extends AbstractDB<DollarPrice, Boolean, Integer> {

	private final String SQL_INSERT = "Insert into dollar(name,price,date) values(?,?,?)";
	private final String SQL_SELECT = "SELECT * FROM dollar";

	@Override
	public Boolean add(DollarPrice dollar) {
		if(dollar == null)
			return false;
		PreparedStatement pstm = null;
		try {
			Connection connection = this.condb.getConnection();

			if (connection != null)
				pstm = connection.prepareStatement(this.SQL_INSERT);
			pstm.setString(1, dollar.getName());
			pstm.setDouble(2, dollar.getPrice());
			pstm.setDate(3, DateTimeUtil.convertUtilToSQL(dollar.getDate()));
			pstm.executeUpdate();

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
		return true;
	}

	@Override
	public Boolean adds(Iterator<DollarPrice> iterator) {
		PreparedStatement pstm = null;
		try {
			Connection connection = this.condb.getConnection();
			if (connection != null)
				pstm = connection.prepareStatement(this.SQL_INSERT);
			while (iterator.hasNext()) {
				DollarPrice price = iterator.next();
				if(price == null)
					continue;
				pstm.setString(1, price.getName());
				pstm.setDouble(2, price.getPrice());
				pstm.setDate(3, DateTimeUtil.convertUtilToSQL(price.getDate()));
				pstm.addBatch();
			}
			pstm.executeBatch();
			return true;

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
		return true;
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(DollarPrice model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DollarPrice find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DollarPrice> getAll() {
		List<DollarPrice> rs = new ArrayList<DollarPrice>();
		Connection connection = this.condb.getConnection();
		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet rsSet = statement.executeQuery(this.SQL_SELECT);
			while (rsSet.next()) {
				String name = rsSet.getString(2);
				double price =  rsSet.getDouble(3);
				Date date = rsSet.getDate(4);
				rs.add(new DollarPrice(name,price,DateTimeUtil.convertUtilToSQL(date) ));
			}
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public List<DollarPrice> getByTime(String startDay, String endDay) {
		String sql = "SELECT * FROM dollar WHERE date >= '"+ startDay + "' AND date <= '" + endDay + "'";
		List<DollarPrice> rs = new ArrayList<DollarPrice>();
		Connection connection = this.condb.getConnection();
		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet rsSet = statement.executeQuery(sql);
			while (rsSet.next()) {
				String name = rsSet.getString(2);
				double price =  rsSet.getDouble(3);
				Date date = rsSet.getDate(4);
				rs.add(new DollarPrice(name,price,DateTimeUtil.convertUtilToSQL(date) ));
			}
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	

}
