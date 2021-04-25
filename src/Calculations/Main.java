package Calculations;

public class Main {
	public static void main(String[] args) {
		int start_base = 16;
		int end_base = 2;
//		String numIn = "01111000111011110001";
		String numIn = "78EF1";
		String numOut = NumConverter.baseToBase(numIn, start_base, end_base);
		
		System.out.printf("%s base %d is %s in base %d%n%n", 
				numIn, start_base, numOut, end_base);
		
//		System.out.println("1237 oct in dec is: " + NumConverter.BaseToDec("1237", 2));
//		System.out.println("16 dec in binary is: " + NumConverter.decToBase(16, 2));
		
//		System.out.println( "a is: " + NumConverter.ConvAlphaToNum('a'));
//		System.out.println( "A is: " + NumConverter.ConvAlphaToNum('A'));
//		
//		System.out.println( "9 is: " + NumConverter.ConvNumToAlpha(9));
//		System.out.println( "10 is: " + NumConverter.ConvNumToAlpha(10));
//		System.out.println( "35 is: " + NumConverter.ConvNumToAlpha(35));
//		System.out.println( "36 is: " + NumConverter.ConvNumToAlpha(36));
	}
}
