package calculations;

public class NumConverter {
	
	/**
	 * Converts from any whole number base to base 10 - only for whole numbers
	 * 
	 * @param num - The input number as a String (to account for bases higher than 10)
	 * @param base - The number system base of the original number
	 * @return - Returns the decimal value, or -1 if the input contains 
	 * a character other than alphanumeric
	 */
	public static int ConvToDec( String numIn, int base ) {
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
						valInt = ConvAlphaToNum(numIn.charAt(numIn.length() - i - 1));
					// Otherwise throw an exception	
					} else {
						System.out.println(e);
						return -1;
					}
				}
				//If number is equal to or larger than base, return -1
				if ((valInt >= base)) {
					return -1;
				}
				// Multiply by base to the power of place
				numOut += valInt * (int) Math.pow(base, i);
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
	public static String DecToBase( int numIn, int base ) {
		String numOut = "";
		int rem = 0;
		int x = numIn;
		
		do {
			rem = x % base;
			if (rem > 9) {
				numOut = ConvNumToAlpha(rem) + numOut;
			} else {
				numOut = rem + numOut;
			}
			x = x / base;
		}while (x != 0 && rem != 0);
		return numOut;
	}

	
	/**
	 * Converts a char to it's value (i.e. A = 10, B = 11)
	 * Case insensitive
	 * 
	 * @param a - the char (letter) that needs to be converted to a number
	 * @return - the number value of the char
	 */
 	public static int ConvAlphaToNum(char a) {
		//System.out.println("Numeric value is: " + Character.getNumericValue(a));
		return Character.getNumericValue(a);
	}

 	public static char ConvNumToAlpha(int x) {
 		char a = (char) (x + 55);
 		//System.out.println("Char for num is: " + a);
 		return a;
 	}
}
