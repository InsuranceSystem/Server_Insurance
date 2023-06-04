package ListImpl;

import Dao.InsuranceApplicationDao;
import Interface.InsuranceApplication;
import Interface.InsuranceApplicationList;

import java.io.*;
import java.rmi.Remote;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;

public class InsuranceApplicationListImpl implements InsuranceApplicationList, Remote, Serializable {

	private ArrayList<InsuranceApplication> insuranceApplicationList;
	private InsuranceApplicationDao insuranceApplicationDao;
	public InsuranceApplicationListImpl() throws Exception {
		this.insuranceApplicationDao = new InsuranceApplicationDao();
		this.insuranceApplicationList = insuranceApplicationDao.retrieveAll();
	}
	public void finalize() throws Throwable {
	}
	public boolean add(){
		return false;
	}
	public boolean delete(String applicationId) throws Exception {
		for (int i = 0; i < this.insuranceApplicationList.size(); i++) {
			InsuranceApplication insuranceApplication = (InsuranceApplication) this.insuranceApplicationList.get(i);
			if (insuranceApplication.matchId(applicationId))
				if (this.insuranceApplicationList.remove(insuranceApplication)) {
					insuranceApplicationDao.deleteById(applicationId);
					return true;
				} else
					return false;
		}
		return false;
	}
	public ArrayList<InsuranceApplication> retrieve(){
		return insuranceApplicationList;
	}
	public boolean update(){
		return false;
	}
	public boolean updateInsuranceApplication(InsuranceApplication updatedInsuranceApplication) throws Exception {
		for (int i = 0; i < this.insuranceApplicationList.size(); i++) {
			InsuranceApplication insuranceApplication = (InsuranceApplication) this.insuranceApplicationList.get(i);
			if (insuranceApplication.matchId(updatedInsuranceApplication.getInsuranceID()))
				this.insuranceApplicationList.set(i, updatedInsuranceApplication);
			insuranceApplicationDao.update(updatedInsuranceApplication);
			return true;
		}
		return false;
	}
	public boolean createInsuranceApplication(InsuranceApplication insuranceApplication) throws Exception {
		if (this.insuranceApplicationList.add(insuranceApplication)) {
			insuranceApplicationDao.create(insuranceApplication);
			return true;
		}
		else return false;
	}
	public InsuranceApplication getApplicationbyId(String applicationID) {
		for(int i=0;i<this.insuranceApplicationList.size();i++) {
			InsuranceApplication insuranceApplication = (InsuranceApplication) this.insuranceApplicationList.get(i);
			if(insuranceApplication.matchId(applicationID))
				return insuranceApplication;
		}
		return null;
	}
	
}//end InsuranceApplicationListImpl