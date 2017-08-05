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
import main.tdt.it.finalproject.portfolio.modal.InvestmentPorfolio;
import main.tdt.it.finalproject.util.DateTimeUtil;

public class PorfolioDatabase extends AbstractDB<InvestmentPorfolio, Boolean, Long> {

	private final String SQL_INSERT = "insert into `porfolio`(`gold`,`dollar`,`bank`,`from`,`to`) values(?,?,?,?,?)";
	private final String SQL_SELECT = "SELECT * FROM porfolio";

	@Override
	public Boolean adds(Iterator<InvestmentPorfolio> iterator) {
		PreparedStatement pstm = null;
		try {
			Connection connection = this.condb.getConnection();
			if (connection != null)
				pstm = connection.prepareStatement(this.SQL_INSERT);
			while (iterator.hasNext()) {
				InvestmentPorfolio model = iterator.next();
				pstm.setFloat(1, model.getGold());
				pstm.setFloat(2, model.getDollar());
				pstm.setFloat(3, model.getBank());
				pstm.setDate(4, DateTimeUtil.convertUtilToSQL(model.getFrom()));
				pstm.setDate(5, DateTimeUtil.convertUtilToSQL(model.getTo()));
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
	public Boolean delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(InvestmentPorfolio model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InvestmentPorfolio find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InvestmentPorfolio> getAll() {
		List<InvestmentPorfolio> rs = new ArrayList<InvestmentPorfolio>();
		Connection connection = this.condb.getConnection();
		try {
			Statement statement =  (Statement) connection.createStatement();
			ResultSet rsSet = statement.executeQuery(this.SQL_SELECT);
			while (rsSet.next()) {
				float gold = rsSet.getFloat(1);
				float dollar =rsSet.getFloat(2);
				float bank=rsSet.getFloat(3);
				Date from = rsSet.getDate(4);
				Date to = rsSet.getDate(5);
				rs.add(new InvestmentPorfolio(gold, dollar, bank,from,to));
			}
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public List<InvestmentPorfolio> getByTime(String startDay, String endDay) {
		return null;
	}

	@Override
	public Boolean add(InvestmentPorfolio model) {
		PreparedStatement pstm = null;
		try {
			Connection connection = this.condb.getConnection();

			if (connection != null) {
				pstm = connection.prepareStatement(this.SQL_INSERT);
				pstm.setFloat(1, model.getGold());
				pstm.setFloat(2, model.getDollar());
				pstm.setFloat(3, model.getBank());
				pstm.setDate(4, DateTimeUtil.convertUtilToSQL(model.getFrom()));
				pstm.setDate(5, DateTimeUtil.convertUtilToSQL(model.getTo()));
				pstm.executeUpdate();
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
		return true;
	}
	public static void main(String[] args) {
		PorfolioDatabase database = new PorfolioDatabase();
		database.add(new InvestmentPorfolio(0, 0, 1, new Date(2342342), new Date(123123)));
	}


}
