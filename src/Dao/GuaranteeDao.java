package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Exception.DaoException;
import Insurance.Guarantee;

public class GuaranteeDao extends Dao {
	public GuaranteeDao() {
		try {
			super.connect();
		} catch (Exception e) {
			System.out.println("데이터베이스 연결에 실패했습니다." + e.getMessage());
			System.out.println("DAO Exception 발생한 메서드: " + ((DaoException) e).getDaoMethodName());
		}
	}

	public void create(Guarantee guarantee) throws DaoException {
		String query = "INSERT INTO Guarantee (insuranceID, termsID) VALUES (?, ?)";
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			statement.setString(1, guarantee.getInsuranceID());
			statement.setString(2, guarantee.getTermsID());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Guarantee 생성에 실패했습니다.", "create");
		}
	}

	public ArrayList<Guarantee> retrieveAll() throws DaoException {
		String query = "SELECT * FROM Guarantee";
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			ArrayList<Guarantee> guaranteeList = new ArrayList<>();
			while (resultSet.next()) {
				Guarantee guarantee = new Guarantee();
				guarantee.setInsuranceID(resultSet.getString("insuranceID"));
				guarantee.setTermsID(resultSet.getString("termsID"));
				guaranteeList.add(guarantee);
			}
			return guaranteeList;
		} catch (SQLException e) {
			throw new DaoException("Guarantee 전체 조회에 실패했습니다.", "retrieveAll");
		}
	}

	public void deleteByInsuranceId(String insuranceID) throws DaoException {
		String query = "DELETE FROM Guarantee WHERE insuranceID = ?";
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			statement.setString(1, insuranceID);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("insuranceID로 Guarantee 삭제에 실패했습니다.", "deleteByInsuranceId");
		}
	}
}