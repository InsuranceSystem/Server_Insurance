

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import Interface.GuaranteeList;
import Interface.InsuranceApplicationList;
import Interface.InsuranceList;
import Interface.TermsList;
import ListImpl.GuaranteeListImpl;
import ListImpl.InsuranceApplicationListImpl;
import ListImpl.InsuranceListImpl;
import ListImpl.TermsListImpl;




public class InsuranceServer extends UnicastRemoteObject{
	private static final long serialVersionUID = 1L;
	
	
	protected InsuranceServer() throws RemoteException {
		super();
	}
	
	public static void main(String[] args) throws Exception {
		try {
			System.setProperty("java.security.policy", "policy.txt");
			System.setSecurityManager(null);	
			InsuranceServer server = new InsuranceServer();
       
			TermsList TermsList = new TermsListImpl();
			TermsList stub1 = (TermsList) UnicastRemoteObject.exportObject(TermsList, 0);
	        Registry registry1 = LocateRegistry.createRegistry(1309);
	        registry1.rebind("TermsList", stub1);
	        
	        // SurveyList 객체 등록
	        GuaranteeList GuaranteeList = new GuaranteeListImpl();
	        GuaranteeList stub2 = (GuaranteeList) UnicastRemoteObject.exportObject(GuaranteeList, 0);
	        Registry registry2 = LocateRegistry.createRegistry(1310);
	        registry2.rebind("GuaranteeList", stub2);
		
	        InsuranceList InsuranceList = new InsuranceListImpl();
	        InsuranceList stub3 = (InsuranceList) UnicastRemoteObject.exportObject(InsuranceList, 0);
	        Registry registry3 = LocateRegistry.createRegistry(1311);
	        registry3.rebind("InsuranceList", stub3);
	        
	        // SurveyList 객체 등록
	        InsuranceApplicationList InsuranceApplicationList = new InsuranceApplicationListImpl();
	        InsuranceApplicationList stub4 = (InsuranceApplicationList) UnicastRemoteObject.exportObject(InsuranceApplicationList, 0);
	        Registry registry4 = LocateRegistry.createRegistry(1312);
	        registry4.rebind("InsuranceApplicationList", stub4);
		

			
			System.out.println("Insurance Server is ready !!!");
	
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();	
		}
	}
	
	
}
