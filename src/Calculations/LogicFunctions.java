package Calculations;

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
			// evaluate the part in the brackets
			System.out.println(innerBrackets(input));
			// How many terms in the brackets
			//TODO: How many terms are in the brackets
			// Evaluate Term
			//TODO: EVALUATE TERM
			ArrayList<Boolean> boolList = new ArrayList<Boolean>(8);
			for( int j =0; j <8; j++) {
				boolList.add(j,false);
			}
			// Add term to array 
			test.addTerm(innerBrackets(input),boolList);
			test.printResults();
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
				return input.substring(start,end+1);
			}
		}
		
		return input;
	}
}
