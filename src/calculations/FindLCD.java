package calculations;

public class FindLCD {

    /**
     * @author gecodes
     *
     * Calculates the lowest common denominator given any number of denominators stored in an array.
     *
     * @param denoms    An array of one or more denominators.
     * @return          The lowest common denominator of array elements.
     *
     * Does not include numerators, but code could be updated to calculate equivalent fractions.
     * We'll see how this calculator evolves.
     * Feel free to uncomment main() and throw some values into the testArr.
     */

    // Finds the LCD of first two elements, then finds the LCD between that value and the next element in the array.
    // Progresses through the array and returns the final element which becomes the ultimate LCD.
    public long findLCD(long[] denoms) {

        int counter = 0;

        for (int i = 0; i < denoms.length - 1; i++) {

            long factorUp = denoms[counter + 1];

            while (denoms[i+1] % denoms[i] != 0) {
                denoms[i+1] = denoms[i+1] + factorUp;
            }
            counter++;
        }
        return denoms[counter];
    }

//    public static void main(String[] args) {
//        long[] testArr = { };
//        FindLCD test = new FindLCD();
//        System.out.println(test.findLCD(testArr));
//    }

}
