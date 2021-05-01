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
	
/**
 * Create a Logic array based on how many initial inputs there are.
 * 
 * @param names - arrayList of the input names
 */
	public LogicArray(ArrayList<String> names) {
		// Define the names for variables
		this.names = names;
		this.numCols = names.size();
		
		// Fill in the inputs
		//numRows is how many lines will be in the truth table (2^numCols)
		numRows = (int) Math.pow(2, numCols);
		truth = new ArrayList<List<Boolean>>(numCols);

		// For each input
		for (int col = 0; col < numCols; col++) {
			ArrayList<Boolean> boolList = new ArrayList<Boolean>(numRows);
			//calculate size of true & false groups
			int gpSize = (int) Math.pow(2,  numCols - col - 1);
			// calculate how many times the groups repeat
			int repeats = numRows / gpSize / 2;
			//pattern of 0's and 1's (false and true)
			for ( int i = 0; i < repeats; i ++) {
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
	
	/**
	 * Prints out the matrix with headers
	 */
	public void printResults() {

		System.out.println();
		//Print Headers
		for (String i:names) {
			System.out.printf("\t%s",i);
		}
		
		System.out.println();
		System.out.println();

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
	
	/**
	 * Adds a column to the matrix 
	 * May be an intermediary term or the output
	 * 
	 * @param term - the name, shows the operation performed
	 * @param newCol - an ArrayList of the results of the operation described in the name
	 */
	public void addTerm(String term, List<Boolean> newCol) {
		names.add(term);
		this.numCols++;
		truth.add(newCol);
	}
	
	/**
	 * Checks if a term is already in the matrix
	 * 
	 * @param input - the term to be checked
	 * @return - true if present / otherwise false
	 */
	public boolean checkTerm(String input) {
		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).equals(input)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets the index of a column from the name
	 * 
	 * @param input - column name
	 * @return - index of column, -1 if not present.
	 */
	public int getIndex(String input) {
		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).equals(input)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Returns the number of rows in the matrix (the number of true / false values per term
	 * 
	 * @return - the number of rows.
	 */
	public int getLen() {
		return this.numRows;
	}
	
	/**
	 * AND function for two boolean lists
	 * 
	 * @param in1 - column 1
	 * @param in2 - column 2
	 * @param name - header to give output column
	 * @return - index of new column
	 */
	public int andFun(int in1, int in2, String name) {
		List<Boolean> test = new ArrayList<Boolean>(numRows);
		for (int i = 0; i < numRows; i++) {
			boolean val = truth.get(in1).get(i) && truth.get(in2).get(i);
			test.add(i, val);
		}
		addTerm(name, test);
		return truth.size() - 1;
	}
	
	/**
	 * XOR function for two boolean lists
	 * 
	 * @param in1 - column 1
	 * @param in2 - column 2
	 * @param name - header to give output column
	 * @return - index of new column
	 */
	public int notFun(int in1, String name) {
		List<Boolean> test = new ArrayList<Boolean>(numRows);
		for (int i = 0; i < numRows; i++) {
			boolean val = !(truth.get(in1).get(i));
			test.add(i, val);
		}
		addTerm(name, test);
		return truth.size() - 1;
	}
	
	/**
	 * OR function for two boolean lists
	 * 
	 * @param in1 - column 1
	 * @param in2 - column 2
	 * @param name - header to give output column
	 * @return - index of new column
	 */
	public int orFun(int in1, int in2, String name) {
		List<Boolean> test = new ArrayList<Boolean>(numRows);
		for (int i = 0; i < numRows; i++) {
			boolean val = truth.get(in1).get(i) || truth.get(in2).get(i);
			test.add(i, val);
		}
		addTerm(name, test);
		return truth.size() - 1;
	}
	
	/**
	 * XOR function for two boolean lists
	 * 
	 * @param in1 - column 1
	 * @param in2 - column 2
	 * @param name - header to give output column
	 * @return - index of new column
	 */
	public int xorFun(int in1, int in2, String name) {
		List<Boolean> test = new ArrayList<Boolean>(numRows);
		for (int i = 0; i < numRows; i++) {
			boolean val = truth.get(in1).get(i) ^ truth.get(in2).get(i);
			test.add(i, val);
		}
		addTerm(name, test);
		return truth.size() - 1;
	}
	
	/**
	 * Return the column Name
	 * 
	 * @param index - index to fetch the column name
	 * @return - name (String)
	 */
	public String getName(int index) {
		return names.get(index);
	}
}
