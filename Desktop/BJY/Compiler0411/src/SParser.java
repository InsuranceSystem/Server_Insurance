public class SParser {
	private SLex lex;
	
	
	public void parse(SLex lex) {
		SProgram program = new SProgram();
		program.parse(lex);
	}
	

	
	
	

}