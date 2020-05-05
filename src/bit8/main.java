package bit8;


public class main {
	
	
	public static void main(String[] args) {
		bit8 bit8 = new bit8("bit8");

		
		bit8.reset();
		
		
		bit8.load();
		
		
		while(bit8.pc < 4096) {
			bit8.run();
			bit8.debug();
		}
		
		

	}

}
