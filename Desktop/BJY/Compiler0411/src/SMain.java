public class SMain {
	private SLex lex;
	private SParser parser;
	


	public SMain() {
	}
	
	public void initialize() {
		lex = new SLex();
		lex.initialize("source/exe1");
		parser = new SParser();

	}
	
	public void finaliize() {
		lex.finalize();
	}
	
	private void run() {
		parser.parse(this.lex);
	} // 열려 있는 파일로 parsing해라

	public static void main(String[] args) {
		SMain main = new SMain();
		main.initialize();
		main.run();
		main.finaliize();
	}
}