package calculations;

import java.util.ArrayList;
import java.util.List;

public class LogicArray {
	private ArrayList<String> names = new ArrayList<String>(); //A,B, C, .. O for out
	//Row 2^n - number of T/F per input
	// Col n + out (number of inputs + int terms + output -> variable)
	
	// inner array is the truth table for a given input or output
	// outer array is the inputs and outputs
	private List<List<Boolean>> truth;
	private int numCols, numRows;
	
	
	public LogicArray(int numCols) {
		this.numCols = numCols;
		// Define the names for variables
		for (int i = 0; i < numCols; i++) {
			names.add(Character.toString((char)(i + 65)));
		}
		
		// Fill in the inputs
		//numRows is how many lines will be in the truth table
		//2^n where n is the number of unique inputs
		numRows = (int) Math.pow(2, numCols);
		truth = new ArrayList<List<Boolean>>(numCols);

		// For each input
		for (int col = 0; col < numCols; col++) {
			ArrayList<Boolean> boolList = new ArrayList<Boolean>(numRows);
			//calculate size of true & false groups
			int gpSize = (int) Math.pow(2,  numCols - col - 1);
			// calculate how many times the groups repeat
			int repeats = numRows / gpSize / 2;
			
			for ( int i = 0; i < repeats; i ++) {
				//pattern of 0's and 1's
				// add true
				for (int t = 0; t < gpSize; t++) {
					boolList.add(t + i*(gpSize)*2,true);
				}
				//add false
				for (int t = 0; t < gpSize; t++) {
					boolList.add(false);
				}
			}
			//add truth table for this input
			truth.add(col, boolList);
		}
	}
	
	public void printResults() {
		//Print Headers
		for (String i:names) {
			System.out.printf("\t%s",i);
		}
		
		System.out.println();
		System.out.println("-----------------------------");

		// one row at a time
		for (int row = 0; row < truth.get(0).size(); row++) {
			// all column values
			String msg = "";
			for (int col = 0; col < truth.size() ; col++) {
				msg += "\t" + truth.get(col).get(row);
			}
			System.out.println(msg);
		}
	}
	
	public void addTerm(String term, ArrayList<Boolean> newCol) {
		names.add(term);
		this.numCols++;
		truth.add(newCol);
	}
	
	public boolean checkTerm(String input) {
		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).equals(input)) {
				return true;
			}
		}
		return false;
	}
	
	public int getIndex(String input) {
		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).equals(input)) {
				return i;
			}
		}
		return -1;
	}
	
	public int getLen() {
		return this.numRows;
	}
}
