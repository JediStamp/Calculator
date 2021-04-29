package calculations;

public class Main {
	public static void main(String[] args) {
//		Hyp();
//		baseConv();
//		LogicArray test = new LogicArray(4);
//		test.printResults();
		logicFun();
	}
	
	
	public static void Hyp() { 
		Calc calc = new Calc();
	}
	
	public static void baseConv() {
		int start_base = 16;
		int end_base = 11;
		String numIn = "89ABf.4";
//		String numIn = "176.375";
		String numOut = NumConverter.baseToBase(numIn, start_base, end_base);
		
		System.out.printf("%s base %d is %s in base %d%n%n", 
				numIn, start_base, numOut, end_base);
	}
	
	public static void logicFun() {
		String input = "(A*B+B)!*C + (AOB)";
		System.out.println("Input: " + input);
		LogicFunctions.inputParse(input);
		
	}
	
}
