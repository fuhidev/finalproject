package main.tdt.it.finalproject.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import main.tdt.it.finalproject.jdbc.AbstractDB;
import main.tdt.it.finalproject.jdbc.ConnectionUtils;
import main.tdt.it.finalproject.jdbc.IConnection;
import main.tdt.it.finalproject.jsondata.DollarPrice;
import main.tdt.it.finalproject.jsondata.GoldPrice;

public class DollarDatabase extends AbstractDB<DollarPrice, Boolean, Integer> {

	public DollarDatabase(IConnection condb) {
		super(condb);
	}




	@Override
	public Boolean add(DollarPrice dollar) {
		String sql = "Insert into dollar values(?,?,?)";
		PreparedStatement pstm = null;
		try {
			Connection connection = ConnectionUtils.getMyConnection();
			
			if (connection != null)
				pstm = connection.prepareStatement(sql);
				pstm.setString(1, dollar.getName());
				pstm.setDouble(2, dollar.getBuyCash());
				pstm.setDouble(3, dollar.getBuyTransfer());
				pstm.setDouble(4, dollar.getSellPrice());
				pstm.setString(5, dollar.getDate());
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
		return null;
	}

	@Override
	public Boolean update(DollarPrice e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DollarPrice find(Integer k) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Boolean delete(Integer k) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<DollarPrice> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Boolean adds(DollarPrice e) {
		// TODO Auto-generated method stub
		return null;
	}

}
