package Calculations;

public class LogicArray {
	private Character[] names; //A,B, C, .. O for out
	private boolean[][] truth; //Row - for each truth table value, Col for each input and for output
	public LogicArray(int size) {
		// Define the names for variables and outputs
		names = new Character[size + 1];
		for (int i = 0; i < size; i++) {
			names[i] = (char)(i + 65);
		}
		//O is out
		names[size] = 'O';
		
		// Fill in the inputs
		int rowLen = (int) Math.pow(2, size);
		int colLen = size + 1;
		truth = new boolean[rowLen][colLen];
		for (int col = 0; col < colLen -1; col++) {
			for (int row = 0; row < rowLen; row++) {
				//calculate size of true & false groups
				int gpSize = (int) Math.pow(2,  size-col-1);
				// calculate how many times the true groups repeat
				int repeats = truth.length / gpSize / 2;
				//number of groups in this column
				for ( int i = 0; i < repeats; i ++) {
					//pattern of 0's and 1's
					//true only, false is default value
					for (int t = 0; t < gpSize; t++) {
						truth[t + i*(gpSize)*2][col] = true;
					}
				}
			}
		}
	}
	
	public void printResults() {
		for (char i:names) {
			System.out.printf("\t%s",i);
		}
		
		System.out.println();
		// one row at a time
		for (int row = 0; row < truth.length; row++) {
			// all column values
			String msg = "";
			for (int col = 0; col < truth[0].length; col++) {
				msg += "\t" + truth[row][col];
			}
			System.out.println(msg);
		}
		
	}
	
}
