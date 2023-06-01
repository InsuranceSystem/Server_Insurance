package server;
import Insurance.GuaranteeList;
import Insurance.InsuranceApplicationList;
import Insurance.InsuranceList;
import Insurance.TermsList;

public interface Insurance_ServerIF {

	public InsuranceList getInsuranceList();

	public GuaranteeList getGuaranteeList();

	public InsuranceApplicationList getInsuranceApplicationList();

	public TermsList getTermsList();
}
