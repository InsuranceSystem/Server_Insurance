
public class CPU {
	private enum EState {
		eStopped,
		eRunning
	
	}
	public enum EOperator {
		eHalt,
		eAdd
	}
	private Memory memory;
	public void assoicate (Memory memory) {
		this.memory= memory;
	}
	private EState eState;
    private IR ir;
    public Register mar, mbr;
    public Register cs,pc,ac;
	public CPU() {
		ir = new IR();
		mar = new Register();
		mbr = new Register();
		cs = new Register();
		pc = new Register();
	}
	
	private void fetch() {
		System.out.println("fetch");
		//ir.setOperator(EOperator.eHalt);
		//MAR <- CS + PC
		//MBR = memory.load();
		//IR = MBR
		mar.setValue(cs.getValue()+pc.getValue());
		memory.load();
	    ir.setValue(mbr.getValue());
	}
	private void decode() {
		System.out.println("decode");
		//load operand
		
	}
	private void execute() {
		pc.setValue(pc.getValue() +1 );
		System.out.println("execute");
		switch (ir.getOperator()) {
		case eHalt:
			this.halt();
			break;
		case eAdd:
			this.add();
			break;
		default:
			break;
		}
	}
	private void add() {
		mar.setValue(ir.getOperand());
		memory.load();
		ac.setValue(ac.getValue() + mbr.getValue());
		
	}
	private void halt() {
		this.eState = EState.eStopped;
	
		
	}

	public void start() {
		this.eState = EState.eRunning;
		this.run();
	}
	public void stop() {
		this.eState = EState.eStopped;
		
	}
	public void run() {
		while(this.eState == EState.eRunning) {
			this.fetch();
			this.decode();
			this.execute();
			//checkInterrupt();
		}
	}
	public class Register {
		protected int value;

		public Register() {
			value = 0;
		}

		public int getValue () {
			return value;
		}
		public void setValue (int value) {
			this.value = value;
		}
	}
	private class IR extends Register {
		//private EOperator eOperator;
	
		public EOperator getOperator() {
			
			//8bit¾¿ 3¹ø
			int operator = value >> 24;
			EOperator eOperator = EOperator.values()[operator];
			return eOperator;
		}
		
		public int getOperand() {
			int operand = value & 0x00FFFFFF;
			return operand;
		}

		
		
	}

	
}
