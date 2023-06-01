package Insurance;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;
import Dao.InsuranceDao;

public class InsuranceListImpl implements InsuranceList, Remote{

	private ArrayList<Insurance> insuranceList;
	private InsuranceDao insuranceDao;
	public GuaranteeListImpl guaranteeList;

	public InsuranceListImpl() throws Exception {
		this.insuranceDao = new InsuranceDao();
		this.insuranceList = insuranceDao.retrieveAll();
		guaranteeList = new GuaranteeListImpl();
	}

	public String requestAuthorization(String insuranceID) {
		for (int i = 0; i < this.insuranceList.size(); i++) {
			Insurance insurance = (Insurance) this.insuranceList.get(i);
			if (insurance.matchId(insuranceID))
				return insurance.getInsuranceName();
			// 보험 인가 요청 (금감원)
			// if(금감원.requestAuthorization(insurance))return true;
		}
		return "";
	}

	public boolean updateAuthorization(String insuranceID, boolean authorization) throws Exception {
		for (int i = 0; i < this.insuranceList.size(); i++) {
			if (this.insuranceList.get(i).matchId(insuranceID)) {
				this.insuranceList.get(i).setAuthorization(authorization);
				insuranceDao.update(this.insuranceList.get(i));
				return true;
			}
		}
		return false;
	} 
	
	public boolean createInsurance(Insurance insurance) throws Exception {
		if (this.insuranceList.add(insurance)) {
			insuranceDao.create(insurance);
			String[] termsIDListSplit = insurance.getTermsIDList().split(",");
			Guarantee guarantee = new Guarantee();
			for (int i = 0; i < termsIDListSplit.length; i++) {
				guarantee.setInsuranceID(insurance.getInsuranceID());
				guarantee.setTermsID(termsIDListSplit[i]);
				guaranteeList.create(guarantee);
			}
			return true;
		}
		else return false;
	}

	public ArrayList<Insurance> retrieveInsurance(String type) {
		ArrayList<Insurance> correctinsuranceList = new ArrayList<Insurance>();
		for (int i = 0; i < this.insuranceList.size(); i++) {
			Insurance insurance = (Insurance) this.insuranceList.get(i);
			if (insurance.matchType(type) && insurance.isAuthorization() == true)
				correctinsuranceList.add(insurance);
		}
		return correctinsuranceList;
	}

	public Insurance retrieveInsuranceDetail(String insuranceID) {
		for (int i = 0; i < this.insuranceList.size(); i++) {
			if (this.insuranceList.get(i).matchId(insuranceID))
				return this.insuranceList.get(i);
		}
		return null;
	}

	public boolean deleteInsurance(String insuranceId) throws Exception {
		for (int i = 0; i < this.insuranceList.size(); i++) {
			Insurance insurance = (Insurance) this.insuranceList.get(i);
			if (insurance.matchId(insuranceId))
				if (this.insuranceList.remove(insurance)) {
					guaranteeList.deleteGuranteeById(insuranceId);
					insuranceDao.deleteById(insuranceId); 				
					return true;} 
				else return false;}
		return false;
	}
	public ArrayList<Insurance> getOnSaleInsuranceList() {
		ArrayList<Insurance> onSaleInsurance = new ArrayList<Insurance>();
		for (int i = 0; i < this.insuranceList.size(); i++) {
			Insurance insurance = (Insurance) this.insuranceList.get(i);
			if (insurance.isAuthorization()) onSaleInsurance.add(insurance);
		}
		return onSaleInsurance;
	}

	public ArrayList<Insurance> getUnregisteredInsuranceList() {
		ArrayList<Insurance> unregisteredInsurance = new ArrayList<Insurance>();
		for (int i = 0; i < this.insuranceList.size(); i++) {
			Insurance insurance = (Insurance) this.insuranceList.get(i);
			if (!insurance.isAuthorization())
				unregisteredInsurance.add(insurance);
		}
		return unregisteredInsurance;
	}

	public boolean updateinsurance(Insurance updateInsurance) throws Exception {
		for (int i = 0; i < this.insuranceList.size(); i++) {
			Insurance insurance = (Insurance) this.insuranceList.get(i);
			if (insurance.matchId(updateInsurance.getInsuranceID())) {
				this.insuranceList.set(i, updateInsurance);
				insuranceDao.update(updateInsurance);		
				return true;
			}
		}
		return false;
	}

	public String getInsuranceTypebyId(String insuranceID) {
		for (int i = 0; i < this.insuranceList.size(); i++) {
			Insurance insurance = (Insurance) this.insuranceList.get(i);
			if (insurance.matchId(insuranceID))
				return insurance.getType();
		}
		return null;
	}

	public ArrayList<String> getInsuranceNameById(String insuranceId) {
		ArrayList<String> insuranceName = new ArrayList<String>();
		for (Insurance insurance : insuranceList) {
			if (insurance.getInsuranceID().equals(insuranceId)) {
				insuranceName.add(insurance.getInsuranceName());

			}
		}
		return insuranceName;
	}

	public ArrayList<String> getInsuranceNameTypeInfoById(String insuranceId) {
		ArrayList<String> insuranceNameType = new ArrayList<String>();
		for (Insurance insurance : insuranceList) {
			if (insurance.getInsuranceID().equals(insuranceId)) {
				insuranceNameType.add(insurance.getInsuranceName());
				insuranceNameType.add(insurance.getType());
			}
		}
		return insuranceNameType;
	}

	public Insurance getInsurancebyId(String insuranceID) {
		for (int i = 0; i < this.insuranceList.size(); i++) {
			Insurance insurance = (Insurance) this.insuranceList.get(i);
			if (insurance.matchId(insuranceID))
				return insurance;
		}
		return null;
	}


	public ArrayList<Insurance> retrieve() {
		return insuranceList;
	}

	public boolean isExistInsuranceDesign(String insuranceID) {
		for (int i = 0; i < this.insuranceList.size(); i++) {
			Insurance insurance = (Insurance) this.insuranceList.get(i);
			if (insurance.matchId(insuranceID)&&insurance.isAuthorization()==false) return true;
		}
		return false;
	}

	@Override
	public List<Insurance> getInsuranceFromId(List<Contract> contracts, InsuranceList insuranceList) {
		// TODO Auto-generated method stub
		return null;
	}

}
