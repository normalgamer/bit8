package bit8;


public class bit8 {
	String name;
	
	short X;	// General purpose registers
	short Y;
	
	// Unused opcode translations
	short NOP = 0x00;
	short LDX_ABSOLUTE = 0xA0;
	short LDX_ADDRESS = 0xAA;
	short STX = 0xA1;

	short LDY_ABSOLUTE = 0xB0;
	short LDY_ADDRESS = 0xBA;
	short STY = 0xB1;
	
	short JMP = 0xD0;
	short JSR = 0xE0;
	short RTS = 0xEA;
	
	
	
	int pc;	// Program Counter
	int pcStack;
	
	short opcode;
	
	public short memory [] = new short[4096];
	
	
	int addr;
	
	

	public bit8(String name) {
		this.name = name;
	}
	

	
	public void reset() {
		X = 0;
		Y = 0;
		
		pc = 0;
		pcStack = 0;
		addr = 0;
		
		opcode = 0;
		for(int i=0; i<=4095;i++) {
			memory[i] = 0;
		}
		
		
	}
	
	public void load() {
		memory[0]=0xE0;
		memory[1]=0x00;
		memory[2]=0x05;
		memory[3]=0x66;
		memory[4]=0x00;
		memory[5]=0xA0;
		memory[6]=0x05;
		memory[7]=0xEA;
		memory[8]=0x00;
		memory[9]=0x00;
		memory[10]=0x66;
	}
	
	
	
	
	public void run() {
		opcode = memory[pc];
		
		switch(opcode) {
		case 0x00:{
			pc++;	// Increase program counter
			break;
		}
		case 0x66:{			// Execute order 66 >:D
			pc=4096;
			System.exit(0);
			break;
		}
		case 0xA0:{
			pc++;
			X = memory[pc];
			pc++;	// Increase program counter
			break;
		}
		case 0xA1:{
			pc++;
			addr = memory[pc] << 8 | memory[pc+1];
			
			memory[addr] = X;
			pc++;
			pc++;	// Increase program counter twice
			break;
		}
		case 0xAA:{
			pc++;
			addr = memory[pc] << 8 | memory[pc+1];
			X = memory[addr];
			pc++;
			pc++;	// Increase program counter twice
			break;
		}
		case 0xB0:{
			pc++;
			Y = memory[pc];
			pc++;	// Increase program counter
			break;
		}
		case 0xB1:{
			pc++;
			addr = memory[pc] << 8 | memory[pc+1];
			
			memory[addr] = Y;
			pc++;
			pc++;	// Increase program counter twice
			break;
		}
		case 0xBB:{
			pc++;
			addr = memory[pc] << 8 | memory[pc+1];
			Y = memory[addr];
			pc++;
			pc++;	// Increase program counter twice
			break;
		}
		case 0xD0:{
			pc++;
			addr = memory[pc] << 8 | memory[pc+1];
			pc = addr;	// No need to increase pc here
			break;
		}
		case 0xE0:{
			pc++;
			addr = memory[pc] << 8 | memory[pc+1];
			pcStack = pc+2;	// Push pc to stack to return later, add 2 to skip lo byte of addr
			pc = addr;	// No need to increase pc here
			break;
		}
		case 0xEA:{
			pc = pcStack;	// Return pc from stack
			break;
		}
		
		default:{
			pc++;	// Increase program counter
		}
		
		
		}
		

	}
	
	
	public void debug() {
		System.out.print("X value:\t");
		System.out.println(X);
		System.out.print("Y value:\t");
		System.out.println(Y);
		System.out.print("Program Counter value:\t");
		System.out.println(pc);
		System.out.print("Program Counter Stack value:\t");
		System.out.println(pcStack);
		//System.out.println(String.format("0x%08X", addr));
		//System.out.println(memory[addr]);
	}
	
	
}




