package main.tdt.it.finalproject.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.mysql.cj.api.jdbc.Statement;

import main.tdt.it.finalproject.jdbc.AbstractDB;
import main.tdt.it.finalproject.modal.WorldGold;
import main.tdt.it.finalproject.util.DateTimeUtil;

public class WorldGoldDatabase extends AbstractDB<WorldGold, Boolean, Long> {
	private final String SQL_INSERT = "INSERT INTO goldworld(name,vnprice,usprice,datetime) VALUES(?,?,?,?)";

	@Override
	public Boolean add(WorldGold wgold) {

		PreparedStatement pstm = null;
		try {
			Connection connection = this.condb.getConnection();
			if (connection != null)
				pstm = connection.prepareStatement(SQL_INSERT);
			String sqlDollarPrice = String.format("SELECT price FROM dollar WHERE date = '%s'",
					DateTimeUtil.formatDateToString(wgold.getDateTime()));
			Statement statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sqlDollarPrice);
			pstm.setString(1, wgold.getName());
			if (rs.next()) {
				pstm.setDouble(2, wgold.getUsPrice() * rs.getDouble("price"));
				System.out.println(wgold.getUsPrice() * rs.getDouble("price"));
			}
			pstm.setDouble(2, wgold.getVnPrice());
			pstm.setDouble(3, wgold.getUsPrice());
			pstm.setDate(4, DateTimeUtil.convertUtilToSQL(wgold.getDateTime()));
			 pstm.executeUpdate();
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
		return false;
	}

	@Override
	public Boolean adds(Iterator<WorldGold> iterator) {

		PreparedStatement pstm = null;
		try {
			Connection connection = this.condb.getConnection();
			if (connection != null)
				pstm = connection.prepareStatement(SQL_INSERT);
			while (iterator.hasNext()) {
				WorldGold wgold = iterator.next();
				String sqlDollarPrice = String.format("SELECT price FROM dollar WHERE date = '%s'",
						DateTimeUtil.formatDateToString(wgold.getDateTime()));
				Statement statement = (Statement) connection.createStatement();
				ResultSet rs = statement.executeQuery(sqlDollarPrice);
				pstm.setString(1, wgold.getName());
				if (rs.next()) {
					pstm.setDouble(2, wgold.getUsPrice() * rs.getDouble("price"));
					System.out.println(wgold.getUsPrice() * rs.getDouble("price"));
				}
				pstm.setDouble(3, wgold.getUsPrice());
				pstm.setDate(4, DateTimeUtil.convertUtilToSQL(wgold.getDateTime()));
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
		return false;
	}

	@Override
	public Boolean delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(WorldGold model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WorldGold find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorldGold> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
