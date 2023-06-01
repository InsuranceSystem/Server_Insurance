package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import Insurance.GuaranteeListImpl;
import Insurance.InsuranceApplicationListImpl;
import Insurance.InsuranceListImpl;
import Insurance.TermsListImpl;
import Insurance.GuaranteeList;
import Insurance.InsuranceApplicationList;
import Insurance.InsuranceList;
import Insurance.TermsList;




public class InsuranceServer extends UnicastRemoteObject implements Insurance_ServerIF{
	private static final long serialVersionUID = 1L;
	private static InsuranceList InsuranceList;
	private static  GuaranteeList GuaranteeList;
	private static  InsuranceApplicationList InsuranceApplicationList;
	private static  TermsList TermsList;
	
	protected InsuranceServer() throws RemoteException {
		super();
	}
	
	public static void main(String[] args) throws Exception {
		try {
			InsuranceServer server = new InsuranceServer();
			Naming.rebind("InsuranceServer", server);		

			InsuranceList = new InsuranceListImpl();
			GuaranteeList = new GuaranteeListImpl();
			InsuranceApplicationList = new InsuranceApplicationListImpl();
			TermsList = new TermsListImpl();
			
			System.out.println("Insurance Server is ready !!!");
	
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();	
		}
	}
	@Override
	public InsuranceList getInsuranceList() {
		return InsuranceList;
	}

	@Override
	public GuaranteeList getGuaranteeList() {
		return GuaranteeList;
	}
	@Override
	public InsuranceApplicationList getInsuranceApplicationList() {
		return InsuranceApplicationList;
	}

	@Override
	public TermsList getTermsList() {
		return TermsList;
	}
	
}