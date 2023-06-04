package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Exception.DaoException;
import Insurance.Insurance;

public class InsuranceDao extends Dao{
	public InsuranceDao() {
		try {
			super.connect();
		} catch (Exception e) {
			System.out.println("데이터베이스 연결에 실패했습니다." + e.getMessage());
			System.out.println("DAO Exception 발생한 메서드: " + ((DaoException) e).getDaoMethodName());
		}
	}
	  public void create(Insurance insurance) throws DaoException {
	        String query = "INSERT INTO Insurance (insuranceID, insuranceName, type, maxCompensation, periodOfInsurance, paymentCycle, paymentPeriod, ageOfTarget, basicPremium, rate, distributionStatus, termsIDList, insuranceClausePeriod, precaution, authorization) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        try (PreparedStatement statement = connect.prepareStatement(query)) {
	            statement.setString(1, insurance.getInsuranceID());
	            statement.setString(2, insurance.getInsuranceName());
	            statement.setString(3, insurance.getType());
	            statement.setInt(4, insurance.getMaxCompensation());
	            statement.setString(5, insurance.getPeriodOfInsurance());
	            statement.setString(6, insurance.getPaymentCycle());
	            statement.setString(7, insurance.getPaymentPeriod());
	            statement.setString(8, insurance.getAgeOfTarget());
	            statement.setInt(9, insurance.getBasicPremium());
	            statement.setString(10, insurance.getRate());
	            statement.setBoolean(11, insurance.isDistributionStatus());
	            statement.setString(12, insurance.getTermsIDList());
	            statement.setString(13, insurance.getInsuranceClausePeriod());
	            statement.setString(14, insurance.getPrecaution());
	            statement.setBoolean(15, insurance.isAuthorization());
	            statement.executeUpdate();
	        } catch (SQLException e) {
	            throw new DaoException("Insurance 생성에 실패했습니다.",  "create");
	        }
	    }

	    public ArrayList<Insurance> retrieveAll() throws DaoException {
	        String query = "SELECT * FROM Insurance";
	        try (PreparedStatement statement = connect.prepareStatement(query)) {
	            ResultSet results = statement.executeQuery();
	            ArrayList<Insurance> insuranceList = new ArrayList<Insurance>();
	            while (results.next()) {
	                Insurance insurance = new Insurance();
	                insurance.setInsuranceID(results.getString("insuranceID"));
	                insurance.setInsuranceName(results.getString("insuranceName"));
	                insurance.setType(results.getString("type"));
	                insurance.setMaxCompensation(results.getInt("maxCompensation"));
	                insurance.setPeriodOfInsurance(results.getString("periodOfInsurance"));
	                insurance.setPaymentCycle(results.getString("paymentCycle"));
	                insurance.setPaymentPeriod(results.getString("paymentPeriod"));
	                insurance.setAgeOfTarget(results.getString("ageOfTarget"));
	                insurance.setBasicPremium(results.getInt("basicPremium"));
	                insurance.setRate(results.getString("rate"));
	                insurance.setTermsIDListFromDB(results.getString("termsIDList"));
	                insurance.setDistributionStatus(results.getBoolean("distributionStatus"));
	                insurance.setInsuranceClausePeriod(results.getString("insuranceClausePeriod"));
	                insurance.setPrecaution(results.getString("precaution"));
	                insurance.setAuthorization(results.getBoolean("authorization"));
	                insuranceList.add(insurance);
	            }
	            return insuranceList;
	        } catch (SQLException e) {
	            throw new DaoException("Insurance 전체 조회에 실패했습니다.", "retrieveAll");
	        }
	    }

	    public void update(Insurance insurance) throws DaoException {
	        String query = "UPDATE Insurance SET insuranceName = ?, type = ?, maxCompensation = ?, periodOfInsurance = ?, paymentCycle = ?, paymentPeriod = ?, ageOfTarget = ?, basicPremium = ?, rate = ?, distributionStatus = ?, termsIDList = ?, insuranceClausePeriod = ?, precaution = ?, authorization = ? WHERE insuranceID = ?";
	        try (PreparedStatement statement = connect.prepareStatement(query)) {
	            statement.setString(1, insurance.getInsuranceName());
	            statement.setString(2, insurance.getType());
	            statement.setInt(3, insurance.getMaxCompensation());
	            statement.setString(4, insurance.getPeriodOfInsurance());
	            statement.setString(5, insurance.getPaymentCycle());
	            statement.setString(6, insurance.getPaymentPeriod());
	            statement.setString(7, insurance.getAgeOfTarget());
	            statement.setInt(8, insurance.getBasicPremium());
	            statement.setString(9, insurance.getRate());
	            statement.setBoolean(10, insurance.isDistributionStatus());
	            statement.setString(11, insurance.getTermsIDList());
	            statement.setString(12, insurance.getInsuranceClausePeriod());
	            statement.setString(13, insurance.getPrecaution());
	            statement.setBoolean(14, insurance.isAuthorization());
	            statement.setString(15, insurance.getInsuranceID());
	            statement.executeUpdate();
	        } catch (SQLException e) {
	            throw new DaoException("Insurance 업데이트에 실패했습니다.",  "update");
	        }
	    }

	    public void deleteById(String insuranceId) throws DaoException {
	        String query = "DELETE FROM Insurance WHERE insuranceID = ?";
	        try (PreparedStatement statement = connect.prepareStatement(query)) {
	            statement.setString(1, insuranceId);
	            statement.executeUpdate();
	        } catch (SQLException e) {
	            throw new DaoException("Insurance 삭제에 실패했습니다.", "deleteById");
	        }
	    }
	}