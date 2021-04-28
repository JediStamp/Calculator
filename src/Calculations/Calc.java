import java.util.Scanner;

public class Calc {

	public static void main(String[] args) {
		
		Calc calc = new Calc();
		
		calc.mainMenu();
		
	}
	
	private static void mainMenu() {
		
		int yes = 0;
		
		Scanner scans = new Scanner(System.in);
		
		System.out.println("MAIN MENU Press 1 for the angle calculator, 2 for the side calculator and 3 to exit: ");
		
		int option = scans.nextInt();
		
		switch(option) {
		case 1:
			angleCalc();
			break;
		case 2:
			sideInput();
			break;
		case 3: 
			System.exit(yes);
			
		default:
			System.out.println("invalid input, please try again");
			mainMenu();
		}
	}
	
	private static void angleCalc () {
		
		boolean again = true;
		double preCalc;
		double theta;
		
		Scanner scans = new Scanner(System.in);
		
		System.out.println("To determin angle theta, we need two sides of a triangle OR 2 angles" + "\n" 
		+ "Press 1 if you have two sides and 2 if you have two angles and 3 for the main menu: ");
		
		int option = scans.nextInt();
		
		switch(option) {
		
		case 1: 
			//if we have 2 sides, using soh cah toh to solve the triangle
			System.out.println("Which two sides do you have? Where C is the hypotenues" + "\n" + "Enter AB AC or BC: ");
			
			String sides = scans.next();
			sides.toUpperCase();
			
			switch(sides) {
			
			case "AB":
			System.out.println("If you have sides A and B we need to use TAN or TOH." + "\n"+
			"where toh represents tan(theta) = opposite/adjacent." + "\n" + 
			"remeber the angle calculated (theta) will be the one opposite of the opposite side and adjacent to the adjacent side."+
			"\n" + "Enter side A (adjacent): ");
				double sideAab = scans.nextDouble();
				System.out.println("Enter side B (opposite): ");
				double sideBab = scans.nextDouble();
				
				preCalc = Math.atan(sideBab/sideAab);
				theta = (preCalc * 180) / Math.PI;
								
				System.out.println("this is angle theta: " + theta);
				
				break;
				
			case "AC":
			System.out.println("If you have sides A and C we need to use COS(cah) or SIN(soh) ." + "\n"+
			"where CAH represents cos(theta) = adjacent/hypotentues and SOH represents opposite/hypotenues." + "\n" +
			"\n" + "Enter side A (adjacent): ");
				double sideAac = scans.nextDouble();
				System.out.println("Enter side C (hypotenues): ");
				double sideCac = scans.nextDouble();
							
				preCalc = Math.acos(sideAac/sideCac);
				theta = (preCalc * 180) / Math.PI;
									
				System.out.println("this is angle theta: " + theta);
							
				break;
				
			case "BC":
			System.out.println("If you have sides B and C we need to use COS(cah or) SIN(soh)." + "\n"+
				"where CAH represents tan(theta) = adjacent/hypotentues and SOH represents opposite/hypotenues." + "\n" +
				"\n" + "Enter side B (opposite): ");
				double sideBbc = scans.nextDouble();
				System.out.println("Enter side C (hypotenues): ");
				double sideCbc = scans.nextDouble();
							
				preCalc = Math.asin(sideBbc/sideCbc);
				theta = (preCalc * 180) / Math.PI;
										
				System.out.println("this is angle theta: " + theta);
							
				break;
			
			default:
				angleCalc();
			}
			
			angleCalc();
			break;
			
		case 2: 
			//if we have 2 angles already using basic math for the angle to add up to 180
			System.out.println("the one angle we always know is 90 degrees." + "\n" +
			"please enter the other angle you know: ");
			
			//enter known angle + 90 degrees
			double angle = scans.nextDouble();
			
			if (angle >= 90 || angle <= 0) {
				System.out.println("invalid input please try again");
				angleCalc();
			}
			
			else {
				double unknownAngle = 180 - (angle + 90);
				System.out.println(unknownAngle + " is your 3rd angle in the 90 degree triangle");
			}
			
			angleCalc();
			break;
			
		case 3: 
			mainMenu();
			break;
		
		default:
			System.out.println("invalid input, please try again");
			angleCalc();
		}
	}
	
	private static void sideInput () {
		
		float preCalc;
				
		System.out.println("Enter 2 sides of the right anlgle triangle and we will use Pythagoris'" +
				"\n" + "theory to calculate the thrid side, where side C is the hypotenues" +
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
		System.out.println("press 0 to exit and 1 for another calculation and 2 for the main menu: ");
		int yes = scans.nextInt();
		
		switch(yes) {
		
		case 0:
			System.exit(yes);
			
		case 1:
			sideInput();
			break;
			
		case 2:
			mainMenu();
			break;
			
		default: 
			System.out.println("invalid input, please try again");
			sideInput();
		}
	}

}