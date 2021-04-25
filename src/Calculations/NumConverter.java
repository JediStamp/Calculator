package Calculations;

public class NumConverter {
	
	/**
	 * Converts from any whole number base to base 10 - only for whole numbers
	 * 
	 * @param num - The input number as a String (to account for bases higher than 10)
	 * @param base - The number system base of the original number
	 * @return - Returns the decimal value, or -1 if the input contains 
	 * a character other than alphanumeric
	 */
	private static int baseToDec( String numIn, int start_base ) {
		int numOut = 0;
		int valInt = 0;
		String valStr;

			// for each symbol
			for ( int i = 0 ; i < numIn.length(); i++ ) {
				valStr = (numIn.substring(numIn.length() - i - 1, numIn.length() - i ));
				try {
					// Get the number if it is a number
					valInt = Integer.parseInt(valStr);
				}
				catch(NumberFormatException e) {
					// If a letter, return the value of the letter					
					if (valStr.matches("[A-Za-z]")) {
						valInt = convAlphaToNum(numIn.charAt(numIn.length() - i - 1));
					// Otherwise throw an exception	
					} else {
						System.out.println(e);
						return -1;
					}
				}
				//If number is equal to or larger than base, return -1
				if ((valInt >= start_base)) {
					return -1;
				}
				// Multiply by base to the power of place
				numOut += valInt * (int) Math.pow(start_base, i);
			}
		return numOut;
	}
	
	/**
	 * Convert from base 10 to another base
	 * 
	 * @param numIn - integer number to be converted from decimal
	 * @param base - any whole number
	 * @return - String number in new base
	 */
	private static String decToBase( int numIn, int end_base ) {
		String numOut = "";
		int rem = 0;
		// figure out how many loops to do
		int len = getSize(numIn, end_base);

		for (int i = 0; i < len; i++) {
			rem = numIn % end_base;
			// get next value
			if (rem > 9) {
				numOut = convNumToAlpha(rem) + numOut;
			} else {
				numOut = rem + numOut;
			}
			numIn = numIn / end_base;
		}
		return numOut;
	}

	public static String baseToBase (String numIn, int start_base, int end_base ) {
		String numOut;
		//System.out.println(baseToDec(numIn, start_base));
		numOut = decToBase(baseToDec(numIn, start_base), end_base);
		return numOut;
	}
	
	/**
	 * Converts a char to it's value (i.e. A = 10, B = 11)
	 * Case insensitive
	 * 
	 * @param a - the char (letter) that needs to be converted to a number
	 * @return - the number value of the char
	 */
 	private static int convAlphaToNum(char a) {
		//System.out.println("Numeric value is: " + Character.getNumericValue(a));
		return Character.getNumericValue(a);
	}

 	/**
 	 * Converts a number to it's equivalent alphabetic character
 	 * 
 	 * @param x - the number that needs to be converted to a letter
 	 * @return - char letter equivalent of number sent in (upper case)
 	 */
 	private static char convNumToAlpha(int x) {
 		if (x >= 10 && x <= 35) {
 	 		char a = (char) (x + 55);
 	 		//System.out.println("Char for num is: " + a);
 	 		return a;
 		} else {
 			return ' ';
 		}
 	}

 	/**
 	 * This calculates how long the output number will be
 	 * 
 	 * @param numIn - the number in decimal that needs to be converted
 	 * @param end_base - the base you are converting to
 	 * @return - the size of the new number (int)
 	 */
 	private static int getSize(int numIn, int end_base) {
		int i = 0;
		int j = numIn;
		// figure out how many loops to do
		while (j > 0) {
			j = numIn / ((int) Math.pow(end_base, i));
			i++;
			System.out.println("j is: " + j + " i is " + i);
		}
		return i - 1;
 	}
}
