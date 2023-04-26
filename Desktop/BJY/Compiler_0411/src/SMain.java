import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SMain {
	private SLex lex;
	private SParser parser;
	
	private Scanner scanner;

	public SMain() {
		lex = new SLex();
		parser = new SParser();
		parser.associate(lex); 
	}
	
	public void initialize() {

		try {
			File file = new File("source/exe1.txt");
			scanner = new Scanner(file);
			 while(scanner.hasNextLine()){
               System.out.println(scanner.nextLine());
	            }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void finaliize() {
		scanner.close();
	}
	
	private void run() {
		parser.parse(scanner);
	} 

	public static void main(String[] args) {
		SMain main = new SMain();
		main.initialize();
		main.run();
		main.finaliize();
	}
}