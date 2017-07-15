package main.tdt.it.finalproject.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mysql.cj.api.jdbc.Statement;

import main.tdt.it.finalproject.jdbc.AbstractDB;
import main.tdt.it.finalproject.modal.InterestRate;
import main.tdt.it.finalproject.modal.WorldGold;

public class InterestRateDatabase extends AbstractDB<InterestRate, Boolean, Long> {
	private final String SQL_INSERT = "INSERT INTO interestrate(kyhan,namebank,percentinterestrate,datetime) VALUES(?,?,?,?)";
	private final String SQL_SELECT = "SELECT * FROM interestrate";
	@Override
	public Boolean add(InterestRate model) {
		PreparedStatement pstm = null;
		try {
			Connection connection = this.condb.getConnection();
			if (connection != null)
				pstm = connection.prepareStatement(SQL_INSERT);
			pstm.setString(1, model.getKyHan());

			pstm.setString(2, model.getNameBank());
			pstm.setDouble(3, model.getPercentInterestRate());
			long millis = System.currentTimeMillis();
			Date date = new java.sql.Date(millis);
			pstm.setDate(4, date);
			// pstm.executeUpdate();
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
	public Boolean adds(Iterator<InterestRate> iterator) {
		PreparedStatement pstm = null;
		try {
			Connection connection = this.condb.getConnection();
			if (connection != null)
				pstm = connection.prepareStatement(SQL_INSERT);
			while (iterator.hasNext()) {
				InterestRate iRate = iterator.next();
				pstm.setString(1, iRate.getKyHan());
				pstm.setString(2, iRate.getNameBank());
				pstm.setDouble(3, iRate.getPercentInterestRate());
				long millis = System.currentTimeMillis();
				Date date = new java.sql.Date(millis);
				pstm.setDate(4, date);
				System.out.println("Kỳ hạn: " + iRate.getKyHan() + "- Tên ngân hàng: " + iRate.getNameBank() + "- Phần trăm: " + iRate.getPercentInterestRate() + "- Ngày: " + date);
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
	public Boolean update(InterestRate model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InterestRate> getAll() {
		List<InterestRate> rs = new ArrayList<InterestRate>();
		Connection connection = this.condb.getConnection();
		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet rsSet = statement.executeQuery(this.SQL_SELECT);
			while (rsSet.next()) {
				rs.add(new InterestRate(rsSet.getString("name"), rsSet.getString("vnprice"), rsSet.getDouble("usprice"), rsSet.getDate("datetime")));
			}
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public Boolean delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InterestRate find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
