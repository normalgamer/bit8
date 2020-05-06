package bit8;

import java.util.concurrent.TimeUnit;

public class bit8 {
	String name;
	
	short X;	// General purpose registers
	short Y;
	
	
	short NOP = 0x00;
	short LDX_IMMEDIATE = 0xA0;
	short LDX_ABSOLUTE = 0xAA;
	short STX = 0xA1;

	short LDY_IMMEDIATE = 0xB0;
	short LDY_ABSOLUTE = 0xBA;
	short STY = 0xB1;
	
	short JMP = 0xD0;	// Jumping opcodes are always absolute
	short JSR = 0xE0;
	short RTS = 0xEA;
	
	short HLT = 0x66;
	
	short INC = 0x20;	// INC and DEC are always absolute
	short DEC = 0x21;
	short INX = 0x25;
	short DEX = 0x26;
	short INY = 0x2A;
	short DEY = 0x2B;
	short TXY = 0x2E;
	short TYX = 0x2F;
	
	short CPX_IMMEDIATE = 0xC0;
	short CPX_ABSOLUTE = 0xC1;
	short CPY_IMMEDIATE = 0xC2;
	short CPY_ABSOLUTE = 0xC3;
	
	short BEQ = 0xC4;
	short BNE = 0xC5;
	short BPL = 0xC6;	// Not used
	short BMI = 0xC7;	// Not used
	
	
	int pc;	// Program Counter
	int pcStack;
	
	short opcode;
	
	public short memory [] = new short[4096];
	
	
	int addr;
	
	short comparison;
	// 0=not equal, 1=equal, 2=higher,3=lower
	
	

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
		
		comparison = 0;
		
		
	}
	
	public void load() {
		// Test code to test newly added opcodes
		memory[0]=LDX_IMMEDIATE;
		memory[1]=0x00;
		memory[2]=INX;
		memory[60]=HLT;
	}
	
	
	
	
	public void run() throws InterruptedException {
		opcode = memory[pc];
		
		if(opcode == NOP) {
			pc++;
		}
		else if(opcode == INC) {
			pc++;
			addr = memory[pc] << 8 | memory[pc+1];
			
			memory[addr]++;
			pc++;
			pc++;	// Increase program counter twice
		}
		else if(opcode == DEC) {
			pc++;
			addr = memory[pc] << 8 | memory[pc+1];
			
			memory[addr]--;
			pc++;
			pc++;	// Increase program counter twice
		}
		else if(opcode == INX) {
			X++;
			pc++;	// Increase program counter
		}
		else if(opcode == DEX) {
			X--;
			pc++;	// Increase program counter
		}
		else if(opcode == INY) {
			Y++;
			pc++;	// Increase program counter
		}
		else if(opcode == DEY) {
			Y--;
			pc++;	// Increase program counter
		}
		else if(opcode == TXY) {
			Y = X;
			pc++;
		}
		else if(opcode == TYX) {
			X = Y;
			pc++;
		}
		else if(opcode == CPX_IMMEDIATE) {
			if(X == memory[pc+1]) {
				comparison = 1;
			}
			else {
				comparison = 0;
			}
			pc++;
			pc++;
		}
		else if(opcode == CPX_ABSOLUTE) {
			pc++;
			addr = memory[pc] << 8 | memory[pc+1];
			if(X == memory[addr]) {
				comparison = 1;
			}
			else {
				comparison = 0;
			}
			pc++;
			pc++;
		}
		else if(opcode == CPY_IMMEDIATE) {
			if(Y == memory[pc+1]) {
				comparison = 1;
			}
			else {
				comparison = 0;
			}
			pc++;
			pc++;
		}
		else if(opcode == CPY_ABSOLUTE) {
			pc++;
			addr = memory[pc] << 8 | memory[pc+1];
			if(Y == memory[addr]) {
				comparison = 1;
			}
			else {
				comparison = 0;
			}
			pc++;
			pc++;
		}
		else if(opcode == HLT) {
			pc=4096;
			System.exit(0);
		}
		else if(opcode == LDX_IMMEDIATE) {
			pc++;
			X = memory[pc];
			pc++;	// Increase program counter
		}
		else if(opcode == STX) {
			pc++;
			addr = memory[pc] << 8 | memory[pc+1];
			
			memory[addr] = X;
			pc++;
			pc++;	// Increase program counter twice
		}
		else if(opcode == LDX_ABSOLUTE) {
			pc++;
			addr = memory[pc] << 8 | memory[pc+1];
			X = memory[addr];
			pc++;
			pc++;	// Increase program counter twice
		}
		else if(opcode == LDY_IMMEDIATE) {
			pc++;
			Y = memory[pc];
			pc++;	// Increase program counter
		}
		else if(opcode == STY) {
			pc++;
			addr = memory[pc] << 8 | memory[pc+1];
			
			memory[addr] = Y;
			pc++;
			pc++;	// Increase program counter twice
		}
		else if(opcode == LDY_ABSOLUTE) {
			pc++;
			addr = memory[pc] << 8 | memory[pc+1];
			Y = memory[addr];
			pc++;
			pc++;	// Increase program counter twice
		}
		else if(opcode == JMP) {
			pc++;
			addr = memory[pc] << 8 | memory[pc+1];
			pc = addr;	// No need to increase pc here
		}
		else if(opcode == JSR) {
			pc++;
			addr = memory[pc] << 8 | memory[pc+1];
			pcStack = pc+2;	// Push pc to stack to return later, add 2 to skip lo byte of addr
			pc = addr;	// No need to increase pc here
		}
		else if(opcode == RTS) {
			pc = pcStack;	// Return pc from stack
		}
		
		TimeUnit.MILLISECONDS.sleep(16);	// 1/60s, 60Hz = 60 instructions/second
		
		/* Old opcode switch didn't support shorts as comparison
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
		
		
		}*/
		

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
		System.out.print("Comparison:\t");
		System.out.println(comparison);
		//System.out.println(String.format("0x%08X", addr));
		//System.out.println(memory[addr]);
	}
	
	
}




