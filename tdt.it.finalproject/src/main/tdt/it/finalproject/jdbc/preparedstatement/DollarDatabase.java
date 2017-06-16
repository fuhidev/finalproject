package main.tdt.it.finalproject.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import main.tdt.it.finalproject.jdbc.AbstractDB;
import main.tdt.it.finalproject.modal.DollarPrice;
import main.tdt.it.finalproject.util.DateTimeUtil;

public class DollarDatabase extends AbstractDB<DollarPrice, Boolean, Integer> {

	private final String SQL_INSERT ="Insert into dollar(price,date) values(?,?)"; 
	@Override
	public Boolean add(DollarPrice dollar) {
		PreparedStatement pstm = null;
		try {
			Connection connection = this.condb.getConnection();
			
			if (connection != null)
				pstm = connection.prepareStatement(this.SQL_INSERT);
				pstm.setDouble(0, dollar.getPrice());
				pstm.setDate(1, DateTimeUtil.convertUtilToSQL(dollar.getDateTime()));
				pstm.executeQuery();

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
		return null;
	}

	@Override
	public Boolean adds(Iterator<DollarPrice> iterator) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}


}
