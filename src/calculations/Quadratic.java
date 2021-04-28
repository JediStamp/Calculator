package calculations;

public class Quadratic {
	
	/**
	 * Performs Quadratic equation to get real roots (0, 1, or 2)
	 * Form of ax^2 + bx = c = 0
	 * 
	 * @param a - x^2 coefficient (double) 
	 * @param b - x coefficient (double) 
	 * @param c - constant (double)
	 * @return an array of doubles (size 2) with the roots. [Root1, Root2]
	 * If there is only one root, it will be repeated [Root, Root]
	 * If there are no real roots, values returned will be [NaN, NaN]
	 */
	public static Double[] calcQuadratic(double a, double b, double c) {
		Double[] x = new Double[2];
		double i = b*b - (4*a*c);

		i = Math.sqrt(i);

		x[0] =(-b + i)/(2.0*a);
		x[1] = (-b - i)/(2.0*a);

		return x;
	}
}
