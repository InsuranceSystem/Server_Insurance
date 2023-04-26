import java.util.Vector;


public class SCodeSegment implements INode {
	    // 이걸 찍음.
		private Vector<SStatement> statements;
		public SCodeSegment() {
			this.statements = new Vector<SStatement>();
		}
		@Override
		public String parse(SLex lex) {
			String[] tokens = lex.getTokens();			
			String operator = tokens[0];
			while (operator.compareTo(".end") != 0) {
				if (!(operator.startsWith("//"))||(operator.length()==0)) {
					//skip
				}else if(operator.contains(":")) {
					//symbol table -> 코드 세그먼트 주소 -> 벡터의 인덱스
					SSymbolEntity entity = new SSymbolEntity();
					entity.setVariableName(operator.replace(":",""));
					//문장의 위치
					entity.setValue(this.statements.size()-1);
					this.symbolTable.add(entity);
					
				}else {
					// parse tree
					SStatement statement = null;
					switch (tokens.length) {
					case 1:
						statement = new SStatement(tokens[0]);
						break;
					case 2:
						statement = new SStatement(tokens[0], tokens[1]);
						break;
					case 3:
						statement = new SStatement(tokens[0], tokens[1], tokens[2]);
						break;
					default:
						break;
					}
					this.statements.add(statement);
				}				
				tokens = lex.getTokens();
				operator = tokens[0];
			}
			return operator;
		}		
	}