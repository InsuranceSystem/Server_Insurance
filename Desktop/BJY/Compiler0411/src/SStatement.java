
public class SStatement implements INode {
		private String operator;
		private String operand1;
		private String operand2;
		
		public SStatement(String operator) {
			this.operator = operator;
		}
		public SStatement(String operator, String operand1) {
			this.operator = operator;
			this.operator = operand1;
		}
		public SStatement(String operator, String operand1, String operand2) {
			this.operator = operator;
			this.operand1 = operand1;
			this.operand2 = operand2;
		}
		@Override
		public String parse(SLex lex) {
			return operator;
		}		
	}