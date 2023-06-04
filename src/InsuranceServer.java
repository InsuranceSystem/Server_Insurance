
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import Exception.CustomClassNotFoundException;
import Exception.CustomConnectException;
import Exception.CustomNotBoundException;
import Insurance.GuaranteeListImpl;
import Insurance.InsuranceApplicationListImpl;
import Insurance.InsuranceListImpl;
import Insurance.TermsListImpl;
import Interface.Insurance_ServerIF;
import Insurance.GuaranteeList;
import Insurance.InsuranceApplicationList;
import Insurance.InsuranceList;
import Insurance.TermsList;

public class InsuranceServer extends UnicastRemoteObject implements Insurance_ServerIF {
	private static final long serialVersionUID = 1L;
	private static InsuranceList InsuranceList;
	private static GuaranteeList GuaranteeList;
	private static InsuranceApplicationList InsuranceApplicationList;
	private static TermsList TermsList;

	protected InsuranceServer() throws RemoteException {
		super();
	}

	public static void main(String[] args) throws Exception {
		try {

			Registry registry = LocateRegistry.createRegistry(1400);
			InsuranceServer server = new InsuranceServer();
			registry.rebind("InsuranceServer", server);
			InsuranceList = new InsuranceListImpl();
			GuaranteeList = new GuaranteeListImpl();
			InsuranceApplicationList = new InsuranceApplicationListImpl();
			TermsList = new TermsListImpl();

			System.out.println("Insurance Server is ready !!!");

		} catch (CustomNotBoundException e) {
			System.out.println("Not bound exception occurred: " + e.getMessage());
		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException occurred: " + e.getMessage());
		} catch (CustomConnectException e) {
			System.out.println("Connection exception occurred:  " + e.getMessage());
		} catch (IllegalAccessException e) {
			System.out.println("Illegal access exception occurred: " + e.getMessage());
		} catch (CustomClassNotFoundException | NoClassDefFoundError e) {
			System.out.println("Class Found Error: " + e.getMessage());
		}
	}

	@Override
	public InsuranceList getInsuranceList() throws RemoteException {
		return InsuranceList;
	}

	@Override
	public GuaranteeList getGuaranteeList() throws RemoteException {
		return GuaranteeList;
	}

	@Override
	public InsuranceApplicationList getInsuranceApplicationList() throws RemoteException {
		return InsuranceApplicationList;
	}

	@Override
	public TermsList getTermsList() throws RemoteException {
		return TermsList;
	}

}
