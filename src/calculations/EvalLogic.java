package calculations;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EvalLogic {
	private ArrayList<String> userInput;
	
		
	public EvalLogic(String strIn) {
		// Parse The input string into elements
		inputParse(strIn);

		// Get unique inputs
		ArrayList<String> uniqueInputs = getUniqueInputs();
		
		// create logic array with unique inputs
		LogicArray test = new LogicArray(uniqueInputs);
		
		//Print out what we have so far
		System.out.println(uniqueInputs);
		test.printResults();
		
		// Replace inputs with the column numbers
		for (int i = 0; i < userInput.size(); i++) {
			if (test.getIndex(userInput.get(i)) >= 0) {
				//if so, get the column number
				userInput.set(i, Integer.toString(test.getIndex(userInput.get(i))));
			}
		}
		
		// Print out what we have now
		System.out.println(userInput);
		
		// Repeat until no more brackets
		int[] set = new int[2];
//		do {
			// Look for inner brackets
			set = innerBrackets();
			System.out.println("First eval brackets at: " + set[0] + " " + set[1]);
			
			// Look for separators
			ArrayList<Integer> terms;
			terms = getSeparators(set);
			System.out.println(getTermSplits(set, terms));
			
			// Make indices to separate terms (incl brackets)
			terms = getTermSplits(set, terms);
			
			for (int i = 0; i < terms.size() - 1; i++) {
				if (terms.get(i+1) - terms.get(i) > 1) {
					//eval
					evalTerm(terms.get(i), terms.get(i+1));
				}				
			}

			//once all terms between separators are evaluated, 
			// evaluate separators
			
			//return from inner loop (and remove brackets)

//		} while(set[1] != 0);

		//remove brackets when only one entry and not followed or preceded by a !

	}
	
	/**
	 * Creates an arrayList of the input values excluding spaces
	 * @param strIn - the user provided string for evaluation
	 */
	private void inputParse(String strIn){
		userInput = new ArrayList<String>();
		int cnt = 0;
		for (int i = 0; i < strIn.length(); i++) {
			if (!strIn.substring(i, i+1).contains(" ")) {
				userInput.add(cnt, strIn.substring(i, i+1));
				cnt++;
			}
		}
	}
	
	/**
	 * Get the unique inputs to the equation
	 * 
	 * @return - an arrayList of the unique inputs
	 */
	private ArrayList<String> getUniqueInputs(){
		ArrayList<String> uniqueInputs = new ArrayList<String>();
		int cnt = 0;
		boolean flag = false;
		
		for (int i = 0; i < userInput.size(); i++) {
			// discard operators
			if (!(isOperator(userInput.get(i)))){
				//make sure it hasn't already been added
				for (int j = 0; j < uniqueInputs.size(); j++) {
					if (userInput.get(i).matches(uniqueInputs.get(j))) {
						flag = true;
					}
				}
				if (!flag) {
				uniqueInputs.add(cnt, userInput.get(i));
				cnt++;
				}
			}
			flag = false;
		}
		
		return uniqueInputs;
	}
	
	/**
	 * Evaluates the input and determines if it is one of the set of operators
	 * 
	 * @param in - input string to be evaluated
	 * @return - boolean true if the input matches an operator, else false
	 */
	private boolean isOperator(String in) {
		//String[] operators = {"(", "*", "+", "O", ")", "!"};
		String op = "[(,*,+,O,),!]";
		if (in.matches(op)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Finds the leftmost set of inner brackets. 
	 * 
	 * @return - the start and end indices of the first set of inner brackets
	 */
	private int[] innerBrackets() {
		int[] inner = {0, 0};
		
		for (int i = 0; i <userInput.size(); i++) {
			if(userInput.get(i).equals("(")) {
				inner[0] = i;
			}
			//returns when it hits the first end bracket (with latest start bracket)
			if(userInput.get(i).equals(")")) {
				inner[1] = i;
				return inner;
			}
		}
		return inner;
	}
	
/**
 * Compares the input to the separators (+ O)
 * 
 * @param input - the input string
 * @return - boolean true if it matches a separator.
 */
	private boolean isSeparator(String input) {
		String[] separators = {"+", "O"};
		for (int i = 0; i < separators.length; i++) {
			if (input.equals(separators[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Get the indices of the separators in the input string
	 * 
	 * @param - indices of the section of input you want to evaluate
	 * @return - indices of separators
	 */
	private ArrayList<Integer> getSeparators(int[] indices){
		ArrayList<Integer> separators = new ArrayList<Integer>();
		int cnt = 0;
		for (int i = indices[0]; i < indices[1]; i++ ) {
			if (isSeparator(userInput.get(i))) {
				separators.add(cnt,  i);
				cnt++;
			}
		}
		return separators;
	}

	/**
	 * Splices brackets and separators together to get all terms separations
	 * 
	 * @param set - indices of the start and end bracket numbers. Checks for (0,0) case as well.
	 * @param separators - indices of the separators (+, O)
	 * @return - an arrayList of integers corresponding to the indices of brackets and separators.
	 */
	private  ArrayList<Integer> getTermSplits(int[] set, ArrayList<Integer> separators) {
		//ArrayList<Integer> termSplits = new ArrayList<Integer>();
		if (set[1] != set[0]) {
			separators.add(0, set[0]);
			separators.add(set[1]);
		}
		return separators;
	}
	
	private void evalTerm(int start, int end) {
		//Get first operator
		switch(userInput.get(start+2)) {
			case "!":
				System.out.println("!" + userInput.get(start+2));
				break;
			case "*":
				System.out.println("*" + userInput.get(start+2));
//				andFun(userInput.get(start)+1,userInput.get(start)+3 );
				break;
			case "+":
				System.out.println("+" + userInput.get(start+2));
				break;
			case "O":
				System.out.println("O" + userInput.get(start+2));
				break;
		}

		
//		if !, then take first and not it
//		else take 1st and 3rd and perform the 2 operation on it
//		then replace terms with new term

		//cases
		//A*B
		//A!
		//AOB
		//A+B
	}
	
	
	//AND function
	public boolean andFun( int in1, int in2 ) {
		return false;
	}

	
	//	public static void startHere(String input) {
//		
//		
//		
////		// Find how many inputs there are
////		int cnt = 0;
////		String[] inputs = {"A", "B", "C"};
////		for (int i = 0; i < inputs.length; i++) {
////			if (input.indexOf(inputs[i])> -1) {
////				cnt++;
////			}
////		}
////		System.out.println("Number of inputs: " + cnt);
//		
//		//Create an Array for the correct number of inputs
////		LogicArray test = new LogicArray(cnt);
////		test.printResults();
//		
//		// Break the string into terms
//		// 1. Check for brackets
//		int brackets = countMatches(input,"[(]");
//		System.out.println("number of brackets is: " + brackets);
//		
//		for (int i = 0; i < brackets; i++) {
//			// 2. evaluate the part in the brackets
//			System.out.println(innerBrackets(input));
//			// 2.a. How many terms in the brackets (is there a + or O?)
//			ArrayList<String> terms = separateTerms(innerBrackets(input));
//			System.out.println(terms);
	

	
//			// Evaluate Terms //longer than 1
//			// for each term
//			for (int j = 0; j < terms.size(); j++) {
//				
//				// is it already in the truth table?
//				if (test.getIndex(terms.get(j)) >= 0) {
//					//if so, get the column number
//					terms.set(j, "col" + test.getIndex(terms.get(j)));
//				}
//				
//				//if not
//				else {
//					//if it is not a separator, add it
//					if (!isSeparator(terms.get(j))) {
//						System.out.println("Add term to test and evaluate it");
//						ArrayList<Boolean> boolList = evalTerm(terms.get(j),test.getLen());
//						test.addTerm(terms.get(j),boolList);
//						test.printResults();
//					}
//					//if it is a separator, skip it for now
//				}
//					
//				
//			//once all non separator terms are evaluated
//			//combine with separator
//			//evaluate	
//			//move outside of brackets and repeat	
//				
//			}
//			//evaluate rest
//
//		}
//		
//		//Once all brackets are complete 
//		//Check for NOT
//	}
	
	private static int countMatches(String input, String term) {
		int terms = 0;
		for (int i = 0; i < input.length(); i++) {
			if (input.substring(i, i + term.length()-2).matches(term)) {
				terms++;
			}
		}
		return terms;
	}
	
	

	
	
//	private static ArrayList<String> separateTerms(String input){
//		ArrayList<String> out = new ArrayList<String>();
//		String[] separators = {"+", "O"};
//		String sub = "";
//		int term = 0;
//		for (int i = 0; i < input.length(); i++) {
//			if (input.substring(i, i+1).equals(separators[0]) || 
//				input.substring(i, i+1).equals(separators[1])	) {
//				out.add(term, sub);
//				sub = "";
//				term++;
//				//Add operator as a term
//				out.add(term, input.substring(i, i+1));
//				term++;
//			} else {
//				sub += input.substring(i, i+1);
//			}
//		}
//		out.add(term, sub);
//		return out;
//	}
	

	
//	private static ArrayList<Boolean> evalTerm(String term, int len){
//		ArrayList<Boolean> boolList = new ArrayList<Boolean>(len);
//		System.out.println("Eval Term returns all false atm");
//		//get letter column
//		// get operator (AND, NOT)
//		// evaluate NOT
//		//AND get next input...
//		
//		for( int j =0; j < len; j++) {
//			boolList.add(j,false);
//		}
//		return boolList;
//	}
	
	/**
	 * Prints the current status of the input string
	 */
	public void printInput() {
		System.out.println(userInput);
	}

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
	


	//OR functions
	
	//XOR functions
	
	// Input Parser
	// * AND
	// + OR
	// ! NOT
	// O XOR
	
}
