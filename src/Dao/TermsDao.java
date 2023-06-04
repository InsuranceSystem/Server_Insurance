package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Exception.DaoException;
import Insurance.Terms;

public class TermsDao extends Dao {
	public TermsDao() {
		try {
			super.connect();
		} catch (Exception e) {
			System.out.println("데이터베이스 연결에 실패했습니다." + e.getMessage());
			System.out.println("DAO Exception 발생한 메서드: " + ((DaoException) e).getDaoMethodName());
		}
	}

	public void create(Terms terms) throws DaoException {
		String query = "INSERT INTO Terms (termsID, termsName, calculatedMoneyMethod, termsContent) VALUES (?, ?, ?, ?)";
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			statement.setString(1, terms.getTermsID());
			statement.setString(2, terms.getTermsName());
			statement.setString(3, terms.getCalculatedMoneyMethod());
			statement.setString(4, terms.getTermsContent());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Terms 생성에 실패했습니다.", "create");
		}
	}

	public ArrayList<Terms> retrieveAll() throws DaoException {
		String query = "SELECT * FROM Terms";
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			ResultSet results = statement.executeQuery();
			ArrayList<Terms> termsList = new ArrayList<Terms>();
			while (results.next()) {
				Terms terms = new Terms();
				terms.setTermsID(results.getString("termsID"));
				terms.setTermsName(results.getString("termsName"));
				terms.setTermsContent(results.getString("termsContent"));
				terms.setCalculatedMoneyMethod(results.getString("calculatedMoneyMethod"));
				termsList.add(terms);
			}
			return termsList;
		} catch (SQLException e) {
			throw new DaoException("Terms 전체 조회에 실패했습니다.", "retrieveAll");
		}
	}

	public ResultSet retrieveById(String type) throws DaoException {
		String query = "SELECT * FROM Terms WHERE type = ?";
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			statement.setString(1, type);
			return statement.executeQuery();
		} catch (SQLException e) {
			throw new DaoException("Terms 조회에 실패했습니다.", "retrieveById");
		}
	}
}