package calculations;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EvalLogic {
	private ArrayList<String> userInput;
	private LogicArray test;
		
	public EvalLogic(String strIn) {
		// Parse The input string into elements
		inputParse(strIn);
		//add brackets at start and end of input
		userInput.add(0, "(");
		userInput.add(userInput.size(), ")");

		// Get unique inputs
		ArrayList<String> uniqueInputs = getUniqueInputs();
		
		// create logic array with unique inputs
		test = new LogicArray(uniqueInputs);
		
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
		ArrayList<Integer> terms;
		do {
			// Look for inner brackets
			set = innerBrackets();
			System.out.println("First eval brackets at: " + set[0] + " " + set[1]);
			
			// Look for separators
			//ArrayList<Integer> terms;
			//terms = getSeparators(set);
			System.out.println(getTermSplits(set, getSeparators(set)));
			
			// Make indices to separate terms (incl brackets)
			terms = getTermSplits(set, getSeparators(set));
			
			//evaluate terms greater than 1 col wide (eval right to left term wise)
			for (int i = terms.size()-1; i > 0; i--) {
				if (terms.get(i) - terms.get(i -1) > 2) {
					evalTerm(terms.get(0)+1, terms.get(1));
					test.printResults();
				}
				System.out.println(userInput);	
			}
			
			//evaluate separators
			set = innerBrackets();
			terms = getTermSplits(set, getSeparators(set));
						
//			for (int i = terms.size()-1; i > 1; i--) {
//				evalSep(terms.get(0), terms.get(2));
//				test.printResults();
//
//				System.out.println(userInput);	
//			}
			for (int i = terms.size()-1; i > 1; i--) {
				evalSep(terms.get(0) + 1, terms.get(2) - 1, terms.get(1));
				test.printResults();

				System.out.println(userInput);	
			}
			
			//remove brackets
			set = innerBrackets();
			if (set[1] - set[0] == 2) {
				userInput.remove(set[1]);
				userInput.remove(set[0]);
			}
			System.out.println(userInput);
		}while(userInput.size()> 1);


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
	
	private void evalTerm(int start, int op) {
		String name;
		int colNum;
		//Get first operator
		switch(userInput.get(op)) {
		case "!":
			System.out.println("!" + userInput.get(op));
			name = makeName(start, op);
			colNum = test.notFun( Integer.parseInt(userInput.get(start)), name);
			updateInput(start, op, colNum);
			break;
		case "*":
			name = makeName(start, start + 2);
			colNum = test.andFun( Integer.parseInt(userInput.get(start)), 
					Integer.parseInt(userInput.get(start+2)), 
					name);
			updateInput(start ,start + 2,colNum);
			break;
		}
	}		
	
	/**
	 * Evaluate the separators with the term before and after the separator. 
	 * Calls the update input function and the appropriate function of Logic Array (+, O)
	 * 
	 * @param start - 
	 * @param end
	 */
	private void evalSep(int start, int end, int sep) {
		String name;
		int colNum;
		//Get first operator
		switch(userInput.get(sep)) {
		case "+":
			name = makeName(start, end);
			colNum = test.orFun( Integer.parseInt(userInput.get(start)), 
					Integer.parseInt(userInput.get(end)), 
					name);
			updateInput(start ,end,colNum);
			break;
		case "O":
			name = makeName(start , end);
			colNum = test.xorFun( Integer.parseInt(userInput.get(start)), 
					Integer.parseInt(userInput.get(end)), 
					name);
			updateInput(start -1 ,end +1,colNum);
			break;
		}
	}

	/**
	 * Makes the column name (before the input is removed from the userInput string).
	 * 
	 * @param start - starting index of userInput
	 * @param end - end index of userInput
	 * @return - name to be used as column header in truth table
	 */
	private String makeName(int start, int end) {
		String name = "";
		for (int i = start; i <= end; i++) {
			switch(userInput.get(i)) {
			case("*"):
				name += "*";
				break;
			case("!"):
				name += "!";
				break;
			case("+"):
				name += "+";
				break;
			case("O"):
				name += "O";
				break;
			case("("):
				break;
			case(")"):
				break;
			// this should be the column numbers only
			default:
				name += test.getName(Integer.parseInt(userInput.get(i)));
				break;
			}
		}
		return name;
	}
	
	/**
	 * Removes input between specified numbers and 
	 * replaces with the column number where the answer is located.
	 * 
	 * @param start - starting index of userInput
	 * @param end - end index of userInput
	 * @param colNum - index of column that represents this operation
	 */
	private void updateInput(int start, int end, int colNum) {
		userInput.set(start, Integer.toString(colNum));
		for (int i = end; i > start; i--) {
			userInput.remove(i);
		}
	}
	
	/**
	 * Prints the current status of the input string
	 */
	public void printInput() {
		System.out.println(userInput);
	}
}
