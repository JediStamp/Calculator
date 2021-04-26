package Calculations;

import java.util.Scanner;

public class Calc {

	public static void main(String[] args) {
		
		Calc calc = new Calc();
		calc.sideInput();
		
	}
	
	public static void sideCalc () {
		
	}
	
	private static void sideInput () {
		
		float preCalc;
				
		System.out.println("Enter 2 sides of the right angle triangle and we will use Pythagoris'" +
				"\n" + "theory to calculate the third side, where side C is the hypotenuse" +
				"\n" + "Enter '0' for the unknown side");
		
		//scan for input
		Scanner scans = new Scanner(System.in);
		System.out.println("Side A: ");
		float sideA = scans.nextFloat();
		
		System.out.println("Side B: ");
		float sideB = scans.nextFloat();
		
		System.out.println("Side C: ");
		float sideC = scans.nextFloat();
		
		//calculate the hypotenues
		if (sideC == 0) {
			preCalc = (sideA*sideA) + (sideB*sideB); 
			System.out.println("Side C = " + Math.sqrt(preCalc));
		}
		
		//calculate the side a or b when the hypotenues is given 
		else if (sideA == 0) {
			preCalc = (sideC*sideC) - (sideB*sideB);
			System.out.println("Side A = " + Math.sqrt(preCalc));
			
			if (sideB>=sideC) {
				System.out.println("The hypotenues has to be greater than the other 2 sides" + "\n"
			+ "try again");
				sideInput();
			}
		}
		
		else if (sideB == 0) {
			preCalc = (sideC*sideC) - (sideA*sideA);
			System.out.println("Side B = " + Math.sqrt(preCalc));
			
			if (sideA>=sideC) {
				System.out.println("The hypotenues has to be greater than the other 2 sides" + "\n"
			+ "try again");
				sideInput();
			}
		}
		
		else {
			System.out.println("please enter a correct input.");
			sideInput();
		}
		
		//ask the user if they want to try again, if not terminate the program
		System.out.println("press 0 to exit and 1 for another calculation: ");
		int yes = scans.nextInt();
		
		if (yes == 1) {
			sideInput();
		}
		else {
			System.exit(yes);
		}
	}

}
