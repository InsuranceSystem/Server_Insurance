package ListImpl;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import Dao.TermsDao;
import Interface.Terms;
import Interface.TermsList;

public class TermsListImpl implements TermsList, Remote, Serializable {

	private ArrayList<Terms> termsList;
	private TermsDao termsDao;
	
	public TermsListImpl()throws Exception {
		this.termsDao = new TermsDao();
		this.termsList = termsDao.retrieveAll();
	}
	
	@Override
	public boolean createTerms(Terms terms) throws Exception{
		if(this.termsList.add(terms)) {
			termsDao.create(terms);
			return true;}
		else return false;
	}
	
	@Override
	public ArrayList<Terms> retrieveAllTerms(){
		return termsList;
	}
	
	public Terms getTermsByID(String termsID) {
		for(int i=0;i<this.termsList.size();i++) {
			Terms terms = (Terms) this.termsList.get(i);
			if(terms.matchId(termsID))
				return terms;
		}
		return null;
	}
	
	public boolean isExistTermsID(String termsID) throws Exception {	
				for (int j = 0; j < termsList.size(); j++) {
					if (termsList.get(j).matchID(termsID)) return true;
				}		
			return false;
	}
}