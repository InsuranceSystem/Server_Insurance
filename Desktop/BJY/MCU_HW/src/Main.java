
public class Main {

	public static void main(String[] args) {
		CPU cpu = new CPU();
		Memory memory = new Memory();
		cpu.assoicate(memory);
		memory.associate(cpu.mar, cpu.mbr);
		cpu.start();
	}

}
