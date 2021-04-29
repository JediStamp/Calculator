package calculations;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogicFunctions {
	
	
//	private static boolean inputA, inputB, inputC;
	
	
//	//Constructors 1-3 inputs
//	
//	public LogicFunctions(boolean inputA) {
//		this.inputA = inputA;
//	}
//	
//	public LogicFunctions(boolean inputA, boolean inputB) {
//		this.inputA = inputA;
//		this.inputB = inputB;
//	}
//	
//	public LogicFunctions(boolean inputA, boolean inputB, boolean inputC) {
//		this.inputA = inputA;
//		this.inputB = inputB;
//		this.inputC = inputC;
//	}
	
	//NOT functions
	
	//AND functions

	public static boolean andFun(boolean input1, boolean input2) {
		return input1 && input2;
	}
	
	public static boolean andFun(boolean input1, boolean input2, boolean input3) {
		return input1 && input2 && input3;
	}

	//OR functions
	
	//XOR functions
	
	// Input Parser
	// * AND
	// + OR
	// ! NOT
	// O XOR
	public static void inputParse(String input) {
		// Find how many inputs there are
		int cnt = 0;
		String[] inputs = {"A", "B", "C"};
		for (int i = 0; i < inputs.length; i++) {
			if (input.indexOf(inputs[i])> -1) {
				cnt++;
			}
		}
		System.out.println("Number of inputs: " + cnt);
		
		//Create an Array for the correct number of inputs
		LogicArray test = new LogicArray(cnt);
		test.printResults();
		
		// Break the string into terms
		// 1. Check for brackets
		int brackets = countMatches(input,"[(]");
		System.out.println("number of brackets is: " + brackets);
		
		for (int i = 0; i < brackets; i++) {
			// 2. evaluate the part in the brackets
			System.out.println(innerBrackets(input));
			// 2.a. How many terms in the brackets (is there a + or O?)
			ArrayList<String> terms = separateTerms(innerBrackets(input));
			System.out.println(terms);
			
			// Evaluate Terms //longer than 1
			// for each term
			for (int j = 0; j < terms.size(); j++) {
				
				// is it already in the truth table?
				if (test.getIndex(terms.get(j)) >= 0) {
					//if so, get the column number
					terms.set(j, "col" + test.getIndex(terms.get(j)));
				}
				
				//if not
				else {
					//if it is not a separator, add it
					if (!isSeparator(terms.get(j))) {
						System.out.println("Add term to test and evaluate it");
						ArrayList<Boolean> boolList = evalTerm(terms.get(j),test.getLen());
						test.addTerm(terms.get(j),boolList);
						test.printResults();
					}
					//if it is a separator, skip it for now
				}
					
				
			//once all non separator terms are evaluated
			//combine with separator
			//evaluate	
			//move outside of brackets and repeat	
				
			}
			//evaluate rest

		}
		
		//Once all brackets are complete 
		//Check for NOT
	}
	
	private static int countMatches(String input, String term) {
		int terms = 0;
		for (int i = 0; i < input.length(); i++) {
			if (input.substring(i, i + term.length()-2).matches(term)) {
				terms++;
			}
		}
		return terms;
	}
	
	
	private static String innerBrackets(String input) {
		int start = 0;
		int end = 0;
		
		for (int i = 0; i <input.length(); i++) {
//			System.out.println(input.substring(i,i+1));
			if(input.substring(i,i+1).equals("(")) {
				start = i;
//				System.out.println("Start: " + start);
			}
			//returns when it hits the first end bracket (with latest start bracket)
			if(input.substring(i, i+1).equals(")")) {
				end = i;
//				System.out.println("end: " + end);
				//Doing this removes the brackets
				return input.substring(start+1,end);
			}
		}
		return input;
	}
	
	
	private static ArrayList<String> separateTerms(String input){
		ArrayList<String> out = new ArrayList<String>();
		String[] separators = {"+", "O"};
		String sub = "";
		int term = 0;
		for (int i = 0; i < input.length(); i++) {
			if (input.substring(i, i+1).equals(separators[0]) || 
				input.substring(i, i+1).equals(separators[1])	) {
				out.add(term, sub);
				sub = "";
				term++;
				//Add operator as a term
				out.add(term, input.substring(i, i+1));
				term++;
			} else {
				sub += input.substring(i, i+1);
			}
		}
		out.add(term, sub);
		return out;
	}
	
	private static boolean isSeparator(String input) {
		String[] separators = {"+", "O"};
		for (int i = 0; i < separators.length; i++) {
			if (input.equals(separators[i])) {
				return true;
			}
		}
		return false;
	}
	
	private static ArrayList<Boolean> evalTerm(String term, int len){
		ArrayList<Boolean> boolList = new ArrayList<Boolean>(len);
		System.out.println("Eval Term returns all false atm");
		//get letter column
		// get operator (AND, NOT)
		// evaluate NOT
		//AND get next input...
		
		for( int j =0; j < len; j++) {
			boolList.add(j,false);
		}
		return boolList;
	}
}
