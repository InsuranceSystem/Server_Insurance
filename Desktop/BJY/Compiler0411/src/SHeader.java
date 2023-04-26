public class SHeader implements INode {
	  private SSymbolTable declarations;
		public SHeader() {
			this.declarations = new SSymbolTable();
		}
		@Override
		public String parse(SLex lex) {
			String token = lex.getToken();
			while (token.compareTo(".code") != 0) {
				SSymbolEntity declaration = new SSymbolEntity();
				declaration.setVariableName(token);
				declaration.setValue(Integer.parseInt(lex.getToken()));
				this.declarations.add(declaration);
				token = lex.getToken();
			}
			return token;
		}
	}
