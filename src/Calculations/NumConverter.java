package Calculations;

public class NumConverter {
	
	/**
	 * Convert a number from one base to another.
	 * 
	 * @param numIn - The number to be converted
	 * @param start_base - The base of the input number
	 * @param end_base - The base of the output number
	 * @return - The number in the output base
	 *  return of -1 means something was invalid.
	 *  
	 *  Restrictions: base 2-36 (Z is the highest letter that can be evaluated)
	 *  			  number cannot have any characters which evaluate to larger than the base
	 */
	public static String baseToBase (String numIn, int start_base, int end_base ) {
		String decIn;
		String decOut = "";
		String numOut;
		
		// Make sure the bases are within bounds
		if (start_base < 2 || start_base > 36) {
			return "-1";
		} else if (end_base < 2 || end_base > 36) {
			return "-1";
		}
		
		// Check for a decimal
		if (numIn.contains(".")) {
			int decimal = numIn.indexOf(".");
			// Split the string into two
			decIn = numIn.substring(decimal + 1);
//			System.out.println(decIn);
			numIn = numIn.substring(0, decimal);
			decOut = ".";
			decOut += decToBaseDecimal(baseToDecDecimal(decIn, start_base), end_base);
//			System.out.println(numIn);
//			System.out.println(decIn);
		}
//		System.out.println(baseToDec(numIn, start_base));
		numOut = decToBase(baseToDec(numIn, start_base), end_base);
		return numOut + decOut;
	}
	
	/**
	 * Converts from any whole number base to base 10 - Integer portion
	 *  
	 * @param num - The input number as a String (to account for bases higher than 10)
	 * @param start_base - The number system base of the original number (2-37)
	 * @return - Returns the base 10 value, or -1 if the input contains 
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
	 * Converts from any whole number base to base 10 - Integer portion
	 *  
	 * @param num - The input number as a String (to account for bases higher than 10)
	 * @param start_base - The number system base of the original number (2-37)
	 * @return - Returns the base 10 value, or -1 if the input contains 
	 * a character other than alphanumeric
	 */
	private static double baseToDecDecimal( String numIn, int start_base ) {
		double numOut = 0;
		int valInt = 0;
		String valStr;

			// for each symbol
			for ( int i = 1 ; i <= numIn.length(); i++ ) {
				valStr = (numIn.substring(i - 1, i ));
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
				numOut += valInt * Math.pow(start_base, -i);
			}
			System.out.println(numOut);
			return numOut;
	}
	
	/**
	 * Convert from base 10 to another base - Integer portion
	 * 
	 * @param numIn - integer number to be converted from decimal
	 * @param end_base - integer from 2-37
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

	/**
	 * Convert from base 10 to another base - Decimal portion of number
	 * 
	 * @param numIn - double number (to the right of decimal place to be converted from base 10
	 * @param end_base - integer from 2-37
	 * @return - String decimal portion of number in new base
	 */
	private static String decToBaseDecimal( double numIn, int end_base ) {
		String numOut = "";
		double rem = numIn;
		int digitOut;
		
		// figure out how many loops to do - max 16 or when no remainder
			while (rem > 0 && numOut.length() < 16) {
			rem = rem * end_base;
//			System.out.println("rem is: " + rem);
			// get value (whatever is before decimal place)
			if (rem >= 1) {
				digitOut = (int) rem;
				rem = rem - digitOut;
			}else {
				digitOut = 0;
			}
//			System.out.println("rem digit is: " + digitOut);
			if (digitOut > 9) {
				numOut += convNumToAlpha(digitOut) ;
			} else {
				numOut += digitOut;
			}
//			System.out.println("numOut: " + numOut);
		}
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
			//System.out.println("j is: " + j + " i is " + i);
		}
		return i - 1;
 	}
}
