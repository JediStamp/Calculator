package Calculations;

public class Quadratic {
	
	/*
	 * @param - three doubles, a, b, c
	 * @return - array of two roots
	 * Cases: 1 - two real roots
	 * 		  2 - one real root, returned as two equal roots
	 * 		  3 - no real roots, returned as NaN in both spots
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
