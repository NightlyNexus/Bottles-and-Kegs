package com.brianco.bottlesandkegs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BottlesAndKegs {
	public static void main(String[] args) {
		BottlesAndKegs bottlesAndKegs = new BottlesAndKegs();
		Scanner scan = new Scanner(System.in);
		System.out.println("Beginner? (y/n) (or type \"edge\" to get test cases)");
		boolean beginner = false;
		String entry = scan.nextLine();
		if (entry.equals("y")) {
			beginner = true;
		} else if (entry.equals("edge")) {
			List<Integer> list = bottlesAndKegs.getEdgeCases();
			for (Integer number : list) {
				System.out.println(number);
			}
			return;
		}

		System.out.println("Enter number to start.");
		int num = scan.nextInt();
		
		
		while (true) {
			scan.nextLine();
			System.out.println(bottlesAndKegs.bottlesAndKegs(beginner, num++));
		}
	}
	
	public String bottlesAndKegs(boolean beginner, int num) {
		if (num == 0) {
			return "0";
		}
		boolean[] hasArrDigits = checkDigits(num);
		boolean[] hasArrAdjacent = {false, false};
		if (!beginner) {
			hasArrAdjacent = checkAdjacent(num);
		}
		boolean[] hasArrDivisible = checkDivisible(num);
		boolean hasKegs = hasArrDigits[0] | hasArrAdjacent[0] | hasArrDivisible[0];
		boolean hasBottles = hasArrDigits[1] | hasArrAdjacent[1] | hasArrDivisible[1];
		return statement(hasKegs, hasBottles, num);
	}

	private String statement(boolean hasKegs, boolean hasBottles, int num) {
		if (hasKegs && hasBottles) {
			return "bottles and kegs";
		} else if (hasKegs) {
			return "kegs";
		} else if (hasBottles) {
			return "bottles";
		}
		return String.valueOf(num);
	}

	private boolean[] checkDigits(int num) {
		boolean[] hasArr = new boolean[2];
		List<Integer> listDigits = getListOfDigits(num);
		hasArr[0] = listDigits.contains(5);
		hasArr[1] = listDigits.contains(7);
		return hasArr;
	}

	private boolean[] checkAdjacent(int num) {
		boolean[] hasArr = new boolean[2];
		List<Integer> listDigits = getListOfDigits(num);
			int sum;
			for (int i = 0; i < listDigits.size() - 1; i++) {
				sum = listDigits.get(i) + listDigits.get(i + 1);
				if (sum == 5) {
					hasArr[0] = true;
				} else if (sum == 7) {
					hasArr[1] = true;
				}
			}
			return hasArr;
	}

	private boolean[] checkDivisible(int num) {
		boolean[] hasArr = new boolean[2];
		if (num % 5 == 0) {
			hasArr[0] = true;
		}
		if (num % 7 == 0) {
			hasArr[1] = true;
		}
		return hasArr;
	}
	
	private List<Integer> getListOfDigits(int num) {
		List<Integer> listDigits = new ArrayList<Integer>();
		int ten = 1;
		while (num / ten != 0) {
			int digit = (num / ten) % 10;
			ten *= 10;
			listDigits.add(digit);
		}
		return listDigits;
	}

	/*
	 * only works for non-expert/beginner mode now
	 * note that the list will be ordered
	 */
	public List<Integer> getEdgeCases() {
		List<Integer> list = initEdgeList();
		boolean[] boolArr = initEdgeBool();
		int num = 1;
		while (!checkIfEdgesFilled(boolArr)) {
			boolean[] isDigits = checkDigits(num);
			boolean[] isDivisible = checkDivisible(num);
			if (!boolArr[0] && isDigits[0] && isDigits[1] && isDivisible[0] && isDivisible[1]) {
				boolArr[0] = true;
				list.set(0, num);
			} else if (!boolArr[1] && isDigits[0] && isDigits[1] && isDivisible[0] && !isDivisible[1]) {
				boolArr[1] = true;
				list.set(1, num);
			} else if (!boolArr[2] && isDigits[0] && isDigits[1] && !isDivisible[0] && isDivisible[1]) {
				boolArr[2] = true;
				list.set(2, num);
			} else if (!boolArr[3] && isDigits[0] && isDigits[1] && !isDivisible[0] && !isDivisible[1]) {
				boolArr[3] = true;
				list.set(3, num);
			} else if (!boolArr[4] && isDigits[0] && !isDigits[1] && isDivisible[0] && isDivisible[1]) {
				boolArr[4] = true;
				list.set(4, num);
			} else if (!boolArr[5] && isDigits[0] && !isDigits[1] && isDivisible[0] && !isDivisible[1]) {
				boolArr[5] = true;
				list.set(5, num);
			} else if (!boolArr[6] && isDigits[0] && !isDigits[1] && !isDivisible[0] && isDivisible[1]) {
				boolArr[6] = true;
				list.set(6, num);
			} else if (!boolArr[7] && isDigits[0] && !isDigits[1] && !isDivisible[0] && !isDivisible[1]) {
				boolArr[7] = true;
				list.set(7, num);
			} else if (!boolArr[8] && !isDigits[0] && isDigits[1] && isDivisible[0] && isDivisible[1]) {
				boolArr[8] = true;
				list.set(8, num);
			} else if (!boolArr[9] && !isDigits[0] && isDigits[1] && isDivisible[0] && !isDivisible[1]) {
				boolArr[9] = true;
				list.set(9, num);
			} else if (!boolArr[10] && !isDigits[0] && isDigits[1] && !isDivisible[0] && isDivisible[1]) {
				boolArr[10] = true;
				list.set(10, num);
			} else if (!boolArr[11] && !isDigits[0] && isDigits[1] && !isDivisible[0] && !isDivisible[1]) {
				boolArr[11] = true;
				list.set(11, num);
			} else if (!boolArr[12] && !isDigits[0] && !isDigits[1] && isDivisible[0] && isDivisible[1]) {
				boolArr[12] = true;
				list.set(12, num);
			} else if (!boolArr[13] && !isDigits[0] && !isDigits[1] && isDivisible[0] && !isDivisible[1]) {
				boolArr[13] = true;
				list.set(13, num);
			} else if (!boolArr[14] && !isDigits[0] && !isDigits[1] && !isDivisible[0] && isDivisible[1]) {
				boolArr[14] = true;
				list.set(14, num);
			} else if (!boolArr[15] && !isDigits[0] && !isDigits[1] && !isDivisible[0] && !isDivisible[1]) {
				boolArr[15] = true;
				list.set(15, num);
			}
			num++;
		}
		return list;
	}

	private boolean checkIfEdgesFilled(boolean[] boolArr) {
		for (boolean bool : boolArr) {
			if (!bool) {
				return false;
			}
		}
		return true;
	}

	private List<Integer> initEdgeList() {
		List<Integer> list = new ArrayList<Integer>(16);
		for (int i = 0; i < 16; i++) {
			list.add(0);
		}
		return list;
	}

	private boolean[] initEdgeBool() {
		boolean[] boolArr = new boolean[16];
		for (boolean bool : boolArr) {
			bool = false;
		}
		return boolArr;
	}
}
