package main.tdt.it.finalproject.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import main.tdt.it.finalproject.jdbc.ConnectionUtils;
import main.tdt.it.finalproject.jsondata.InterestRate;

public class InterestRateDatabase {
	public PreparedStatement pstm = null;
	public void addInterestRage(List<InterestRate> interestRate) {
		String sql = "INSERT INTO interestrate(kyhan,namebank,percentinterestrate,datetime) VALUES(?,?,?,?)";
		try {
			Connection connection = ConnectionUtils.getMyConnection();
			if (connection != null)
				pstm = connection.prepareStatement(sql);
			for (InterestRate interestRatePrice : interestRate) {
				pstm.setString(1, interestRatePrice.getKyHan());
				pstm.setString(2, interestRatePrice.getNameBank());
				pstm.setString(3, interestRatePrice.getPercentInterestRate());
				long millis=System.currentTimeMillis();  
				Date date=new java.sql.Date(millis);  
				pstm.setDate(4, date);
				pstm.executeUpdate();
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
}
