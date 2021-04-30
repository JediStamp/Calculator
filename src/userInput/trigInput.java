package userInput;

import java.util.Scanner;


public class trigInput {
	
public static void mainMenu() {
		
		//int yes = 0;
		
		Scanner scans = new Scanner(System.in);
		
		System.out.println("MAIN MENU Press 1 for the angle calculator, 2 for the side calculator and 3 to exit: ");
		
		int option = scans.nextInt();
		
		switch(option) {
		case 1:
			calculations.Calc.angleCalc();
			break;
		case 2:
			calculations.Calc.sideInput();
			break;
		case 3: 
			System.exit(0);
			
		default:
			System.out.println("invalid input, please try again");
			mainMenu();
		}
	}

public static void getUserInput() {
	Scanner scans = new Scanner(System.in);
	//ask for sides and angles
	//pass it to triangle math
}
}
