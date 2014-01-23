/* All copyrights reserved, by Yavor Lilyanov */

package com.skrill.interns.DealershipExample;

import java.util.List;
import java.util.Scanner;

public class Insertions {
	
	private static Scanner scan = new Scanner(System.in);
	
	public static String string() {
		String result = "";
		do {
			try {
				result = scan.nextLine();
			}
			catch(Exception ex) {
				System.out.println("Sorry, but you didn't insert any correct option.Please try again.");
			}
		}while("".equals(result));
		
		return result;
	}
	
	
	public static int number(int minValue, int maxValue) {
		String input;
		int option = -1;
		boolean showErrMessage = false;
		do {
			if(showErrMessage) {
				System.out.println("Sorry, but you didn't insert any correct option.Please try again.");
			}
			showErrMessage = true;
			input = scan.nextLine();
			try {
				option = Integer.parseInt(input);
			}
			catch(NumberFormatException ex) {
				System.out.println("Sorry, but you didn't insert any correct option.Please try again.");
				showErrMessage = false;
			}
		}while(option < minValue || option > maxValue);
		
		return option;
	}
	
	public static int number(List<Integer> possibleOptions) {
		String input;
		int option = -1;
		boolean showErrMessage = false;
		do {
			if(showErrMessage) {
				System.out.println("Sorry, but you didn't insert any correct option.Please try again.");
			}
			showErrMessage = true;
			input = scan.nextLine();
			try {
				option = Integer.parseInt(input);
			}
			catch(NumberFormatException ex) {
				System.out.println("Sorry, but you didn't insert any correct option.Please try again.");
				showErrMessage = false;
			}
		}while(!(possibleOptions.contains(option)));
		
		return option;
	}
	
	public static void closeScanner() {
		scan.close();
	}
}
