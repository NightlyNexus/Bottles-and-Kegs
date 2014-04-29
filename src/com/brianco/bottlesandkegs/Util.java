package com.brianco.bottlesandkegs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Util {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Beginner? (y/n)");
		boolean beginner = false;
		if (scan.nextLine().equals("y")) {
			beginner = true;
		}

		System.out.println("Enter number to start.");
		int num = scan.nextInt();
		
		while (true) {
			scan.nextLine();
			System.out.println(kegs(beginner, num++));
		}
	}
	
	static String kegs(boolean beginner, int num) {
		if (num == 0) {
			return "0";
		}
		int ten = 1;
		boolean hasKegs = false;
		boolean hasBottles = false;
		List<Integer> listDigits = new ArrayList<Integer>();
		while (num / ten != 0) {
			int digit = (num / ten) % 10;
			if (digit == 5) {
				hasKegs = true;
			} else if (digit == 7) {
				hasBottles = true;
			}
			ten *= 10;
			listDigits.add(digit);
		}
		
		if (!beginner) {
			int sum;
			for (int i = 0; i < listDigits.size() - 1; i++) {
				sum = listDigits.get(i) + listDigits.get(i + 1);
				if (sum == 5) {
					hasKegs = true;
				} else if (sum == 7) {
					hasBottles = true;
				}
			}
		}
		
		
		if (num % 5 == 0) {
			hasKegs = true;
		}
		if (num % 7 == 0) {
			hasBottles = true;
		}
		if (hasKegs && hasBottles) {
			return "bottles and kegs";
		} else if (hasKegs) {
			return "kegs";
		} else if (hasBottles) {
			return "bottles";
		}
		return String.valueOf(num);
	}
}
