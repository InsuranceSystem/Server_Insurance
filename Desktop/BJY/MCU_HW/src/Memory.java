import java.util.Vector;

public class Memory {
	private Vector<Integer> memory;
	private CPU.Register MAR;
	private CPU.Register MBR;

	public Memory() {
		this.memory = new Vector<Integer>();
		
	}
	public void associate (CPU.Register MAR,CPU.Register MBR) {
	   this.MAR = MAR;
	   this.MBR = MBR;
	}
	public void load () {
		int address = MAR.getValue();
		MBR.setValue(this.memory.get(address));
	}
    public void store () {
    	int address = MAR.getValue();
    	int value = MBR.getValue();
		this.memory.set(address,value);
	}
}
