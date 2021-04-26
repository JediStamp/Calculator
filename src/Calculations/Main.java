package Calculations;

public class Main {
	public static void main(String[] args) {
		int start_base = 2;
		int end_base = 16;
		String numIn = "10110000.011";
//		String numIn = "176.375";
		String numOut = NumConverter.baseToBase(numIn, start_base, end_base);
		
		System.out.printf("%s base %d is %s in base %d%n%n", 
				numIn, start_base, numOut, end_base);
		
	}
}
