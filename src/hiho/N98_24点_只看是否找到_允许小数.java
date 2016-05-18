package hiho;

/*
 * 	URL : http://hihocoder.com/contest/hiho98/problem/1
 * 	STATUS : AC
 * 	Time    Memory
 * 	1954ms	20MB
 */

import java.util.Arrays;
import java.util.Scanner;

public class N98_24点_只看是否找到_允许小数 {
	final static int numOfNumbers = 4;
	final static int target = 24;
	final static double almostZero = 0.0000001d;
	static boolean[] used = new boolean[numOfNumbers];
	static int[] nowNumber = new int[numOfNumbers];
	static int[] number = new int[numOfNumbers];
	static int[] ops = new int[numOfNumbers - 1];
	final static char[] opType = {'+', '-', '*', '/', '!', '@'};
	static boolean isCalRight = true;
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numOfTest = scanner.nextInt();
		while (numOfTest -- > 0) {
			Arrays.fill(used, false);
			number = new int[]{scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt()};
			if (makeNumber(0))
				System.out.println("Yes");
			else
				System.out.println("No");
		}
		scanner.close();
	}
	static boolean makeNumber(int depth) {
		if (depth >= numOfNumbers) {
			return makeOperation(0);
		}
		for (int index = 0; index < numOfNumbers; index ++) {
			if (used[index])	continue;
			nowNumber[depth] = number[index];
			used[index] = true;
			if ( makeNumber(depth + 1) )
				return true;
			used[index] = false;
		}
		return false;
	}
	private static boolean makeOperation(int depth) {
		if (depth >= numOfNumbers - 1) {
			if (Math.abs(calType0() - target) < almostZero) {
//				System.out.println(getMath(0));
				return true;
			}
			if (Math.abs(calType1() - target) < almostZero) {
//				System.out.println(getMath(1));
				return true;
			}
			return false;
		}
		for (int index = 0; index < opType.length; index ++) {
			ops[depth] = index;
			if (makeOperation(depth + 1))
				return true;
		}
		return false;
	}
	private static double calType1() {
		// ((a#b)#(c#d))
		isCalRight = true;
		double cal1 = calProcess(opType[ops[0]], nowNumber[0], nowNumber[1]);
		double cal2 = calProcess(opType[ops[2]], nowNumber[2], nowNumber[3]);
		double ans = calProcess(opType[ops[1]], cal1, cal2);
		if (!isCalRight)
			return target + 1;
		else
			return ans;
	}
	private static double calType0() {
		// (((a#b)#c)#d)
		isCalRight = true;
		double cal1 = calProcess(opType[ops[0]], nowNumber[0], nowNumber[1]);
		double cal2 = calProcess(opType[ops[1]], cal1, nowNumber[2]);
		double ans = calProcess(opType[ops[2]], cal2, nowNumber[3]);
		if (!isCalRight)
			return target + 1;
		else
			return ans;
	}
	private static double calProcess(char c, double num0, double num1) {
		double result = Integer.MAX_VALUE;
		switch(c) {
		case '+':
			result = num0 + num1;
			break;
		case '-':
			result = num0 - num1;
			break;
		case '*':
			result = num0 * num1;
			break;
		case '/':
			if (Math.abs(num1 - 0) < almostZero )
				isCalRight = false;
			else
				result = num0 / num1;
			break;
		case '!':
			result = num1 - num0;
			break;
		case '@':
			if (Math.abs(num0 - 0) < almostZero)
				isCalRight = false;
			else
				result = num1 / num0;
			break;
		}
		return result;
	}
	public static String getMath(int id) {
		String string = null;
		switch (id) {
		case 0:
			string = String.format("((%d %c %d) %c %d) %c %d",
					nowNumber[0], opType[ops[0]], nowNumber[1], opType[ops[1]], nowNumber[2], opType[ops[2]], nowNumber[3]);
			break;
		case 1:
			string = String.format("(%d %c %d) %c (%d %c %d)",
					nowNumber[0], opType[ops[0]], nowNumber[1], opType[ops[1]], nowNumber[2], opType[ops[2]], nowNumber[3]);
		default:
			break;
		}
		return string;
	}
}
