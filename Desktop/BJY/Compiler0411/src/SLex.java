import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SLex {
    private Scanner scanner;
    public SLex() {
    	
    }
    	 public void initialize(String fileName) {
    	    	
    	 
    	try {
			scanner = new Scanner(new File(fileName));
//			scanner.useDelimiter("[\\s]+");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   
    public void finalize() {
    	
    }
	public String getToken() {
       if(scanner.hasNext()) {
    	   return scanner.next();
       }
		return null;
	}


	public String[] getTokens() {
		if(scanner.hasNext()) {
	    	   String line= scanner.nextLine();
	    	   line = line.trim();
	    	   String [] tokens = line.split("[\t]+");
	    	  
	    	   return tokens;
	       }
			return null;
	}
}
