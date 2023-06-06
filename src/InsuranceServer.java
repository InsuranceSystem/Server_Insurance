
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import Exception.CustomClassNotFoundException;
import Exception.CustomConnectException;
import Exception.CustomIllegalAccessException;
import Exception.CustomNotBoundException;
import Interface.GuaranteeList;
import Interface.InsuranceApplicationList;
import Interface.InsuranceList;
import Interface.TermsList;
import ListImpl.GuaranteeListImpl;
import ListImpl.InsuranceApplicationListImpl;
import ListImpl.InsuranceListImpl;
import ListImpl.TermsListImpl;

public class InsuranceServer extends UnicastRemoteObject {
	private static final long serialVersionUID = 1L;

	protected InsuranceServer() throws RemoteException {
		super();
	}
	
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception, CustomNotBoundException, CustomConnectException,
			CustomIllegalAccessException, CustomClassNotFoundException, NoClassDefFoundError {
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
			InsuranceApplicationList stub4 = (InsuranceApplicationList) UnicastRemoteObject
					.exportObject(InsuranceApplicationList, 0);
			Registry registry4 = LocateRegistry.createRegistry(1312);
			registry4.rebind("InsuranceApplicationList", stub4);

			System.out.println("Insurance Server is ready !!!");

		} catch (RemoteException e) {
			System.out.println("서버와의 원격 통신 중 오류가 발생했습니다: " + e.getMessage());
			System.out.println("잠시 후에 다시 시도해 주세요.");
		} catch (CustomNotBoundException e) {
			System.out.println("요청한 서비스를 찾을 수 없습니다: " + e.getMessage());
			System.out.println("관리자에게 문의하여 문제를 해결해 주세요.");
		} catch (MalformedURLException e) {
			System.out.println("잘못된 URL 형식으로 연결을 시도했습니다: " + e.getMessage());
			System.out.println("URL 주소를 다시 확인하고 재시도해 주세요.");
		} catch (CustomConnectException e) {
			System.out.println("서버에 연결할 수 없습니다: " + e.getMessage());
			System.out.println("인터넷 연결을 확인하고, 서버가 정상적으로 실행 중인지 확인해 주세요.");
		} catch (IllegalAccessException e) {
			System.out.println("잘못된 접근으로 인해 오류가 발생했습니다: " + e.getMessage());
			System.out.println("권한이 필요한 작업에 접근하지 않도록 주의해 주세요.");
		} catch (CustomClassNotFoundException | NoClassDefFoundError e) {
			System.out.println("필요한 클래스를 찾을 수 없습니다: " + e.getMessage());
			System.out.println("프로그램이 올바르게 설치되었는지 확인하고, 필요한 파일이 제대로 위치해 있는지 확인해 주세요.");
		} catch (IOException e) {
			// IOException 예외 처리
			throw new CustomConnectException("입출력 오류가 발생했습니다", e);
		} catch (Exception e) {
			System.out.println("오류가 발생했습니다: " + e.getMessage());
			System.out.println("문제가 지속되면 관리자에게 문의하여 도움을 받으세요.");
		}
	}
}
