package Dao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Exception.DaoException;
import Interface.InsuranceApplication;

public class InsuranceApplicationDao extends Dao {

	public InsuranceApplicationDao() {
		try {
			super.connect();
		} catch (Exception e) {
			System.out.println("데이터베이스 연결에 실패했습니다." + e.getMessage());
			System.out.println("DAO Exception 발생한 메서드: " + ((DaoException) e).getDaoMethodName());
		}
	}

	public void create(InsuranceApplication insuranceApplication) throws DaoException {
		String query = "INSERT INTO InsuranceApplication (applicationID, insuranceID, customerID, createdAt, insurancePeriod, paymentCycle, subscriptionFilePath, premium, maxCompensation, approval, reasonOfApproval) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			statement.setString(1, insuranceApplication.getApplicationID());
			statement.setString(2, insuranceApplication.getInsuranceID());
			statement.setString(3, insuranceApplication.getCustomerID());
			statement.setDate(4, java.sql.Date.valueOf(insuranceApplication.getCreatedAt()));
			statement.setString(5, insuranceApplication.getInsurancePeriod());
			statement.setString(6, insuranceApplication.getPaymentCycle());
			statement.setString(7, insuranceApplication.getSubscriptionFilePath());
			statement.setInt(8, insuranceApplication.getPremium());
			statement.setInt(9, insuranceApplication.getMaxCompensation());
			statement.setBoolean(10, insuranceApplication.isApproval());
			statement.setString(11, insuranceApplication.getReasonOfApproval());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("InsuranceApplication 생성에 실패했습니다.", "create");
		}
	}

	public void createAll(ArrayList<InsuranceApplication> insuranceApplicationList) throws DaoException {
		String query = "INSERT INTO InsuranceApplication (applicationID, insuranceID, customerID, createdAt, insurancePeriod, paymentCycle, subscriptionFilePath, premium, maxCompensation, approval, reasonOfApproval) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			for (InsuranceApplication insuranceApplication : insuranceApplicationList) {
				statement.setString(1, insuranceApplication.getApplicationID());
				statement.setString(2, insuranceApplication.getInsuranceID());
				statement.setString(3, insuranceApplication.getCustomerID());
				statement.setDate(4, java.sql.Date.valueOf(insuranceApplication.getCreatedAt()));
				statement.setString(5, insuranceApplication.getInsurancePeriod());
				statement.setString(6, insuranceApplication.getPaymentCycle());
				statement.setString(7, insuranceApplication.getSubscriptionFilePath());
				statement.setInt(8, insuranceApplication.getPremium());
				statement.setInt(9, insuranceApplication.getMaxCompensation());
				statement.setBoolean(10, insuranceApplication.isApproval());
				statement.setString(11, insuranceApplication.getReasonOfApproval());
				statement.addBatch();
			}
			statement.executeBatch();
		} catch (SQLException e) {
			throw new DaoException("InsuranceApplication 다중 생성에 실패했습니다.", "createAll");
		}
	}

	public ArrayList<InsuranceApplication> retrieveAll() throws DaoException {
		String query = "SELECT * FROM InsuranceApplication";
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			ArrayList<InsuranceApplication> insuranceApplicationList = new ArrayList<>();
			while (resultSet.next()) {
				InsuranceApplication insuranceApplication = new InsuranceApplication();
				insuranceApplication.setApplicationID(resultSet.getString("applicationID"));
				insuranceApplication.setInsuranceID(resultSet.getString("insuranceID"));
				insuranceApplication.setCustomerID(resultSet.getString("customerID"));
				insuranceApplication.setCreatedAt(resultSet.getDate("createdAt").toLocalDate());
				insuranceApplication.setInsurancePeriod(resultSet.getString("insurancePeriod"));
				insuranceApplication.setPaymentCycle(resultSet.getString("paymentCycle"));
				insuranceApplication.setSubscriptionFilePath(resultSet.getString("subscriptionFilePath"));
				insuranceApplication.setPremium(resultSet.getInt("premium"));
				insuranceApplication.setMaxCompensation(resultSet.getInt("maxCompensation"));
				insuranceApplication.setApproval(resultSet.getBoolean("approval"));
				insuranceApplication.setReasonOfApproval(resultSet.getString("reasonOfApproval"));
				insuranceApplicationList.add(insuranceApplication);
			}
			return insuranceApplicationList;
		} catch (SQLException e) {
			throw new DaoException("InsuranceApplication 전체 조회에 실패했습니다.", "retrieveAll");
		}
	}

	public void update(InsuranceApplication insuranceApplication) throws DaoException {
		String query = "UPDATE InsuranceApplication SET insuranceID = ?, customerID = ?, createdAt = ?, insurancePeriod = ?, paymentCycle = ?, subscriptionFilePath = ?, premium = ?, maxCompensation = ?, approval = ?, reasonOfApproval = ? WHERE applicationID = ?";
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			statement.setString(1, insuranceApplication.getInsuranceID());
			statement.setString(2, insuranceApplication.getCustomerID());
			statement.setDate(3, java.sql.Date.valueOf(insuranceApplication.getCreatedAt()));
			statement.setString(4, insuranceApplication.getInsurancePeriod());
			statement.setString(5, insuranceApplication.getPaymentCycle());
			statement.setString(6, insuranceApplication.getSubscriptionFilePath());
			statement.setInt(7, insuranceApplication.getPremium());
			statement.setInt(8, insuranceApplication.getMaxCompensation());
			statement.setBoolean(9, insuranceApplication.isApproval());
			statement.setString(10, insuranceApplication.getReasonOfApproval());
			statement.setString(11, insuranceApplication.getApplicationID());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("InsuranceApplication 업데이트에 실패했습니다.", "update");
		}
	}

	public void updateById(String applicationID, String column, String content) throws DaoException {
		String query = "UPDATE InsuranceApplication SET " + column + " = ? WHERE applicationID = ?";
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			statement.setString(1, content);
			statement.setString(2, applicationID);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("ID로 InsuranceApplication 업데이트에 실패했습니다.", "updateById");
		}
	}

	public void deleteById(String applicationID) throws DaoException {
		String query = "DELETE FROM InsuranceApplication WHERE applicationID = ?";
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			statement.setString(1, applicationID);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("ID로 InsuranceApplication 삭제에 실패했습니다.", "deleteById");
		}
	}

	public void deleteAll() throws DaoException {
		String query = "DELETE FROM InsuranceApplication";
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("InsuranceApplication 전체 삭제에 실패했습니다.", "deleteAll");
		}
	}
}