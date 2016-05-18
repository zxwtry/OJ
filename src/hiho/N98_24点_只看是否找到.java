package hiho;

import java.util.Arrays;
import java.util.Scanner;

public class N98_24点_只看是否找到 {
	final static int numOfNumbers = 4;
	final static int target = 24;
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
			System.out.println(makeNumber(0));
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
			if (calType0() == target) {
//				System.out.println(getMath(0));
				return true;
			}
			if (calType1() == target) {
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
	private static int calType1() {
		// ((a#b)#(c#d))
		isCalRight = true;
		int cal1 = calProcess(opType[ops[0]], nowNumber[0], nowNumber[1]);
		int cal2 = calProcess(opType[ops[2]], nowNumber[2], nowNumber[3]);
		int ans = calProcess(opType[ops[1]], cal1, cal2);
		if (!isCalRight)
			return target + 1;
		else
			return ans;
	}
	private static int calType0() {
		// (((a#b)#c)#d)
		isCalRight = true;
		int cal1 = calProcess(opType[ops[0]], nowNumber[0], nowNumber[1]);
		int cal2 = calProcess(opType[ops[1]], cal1, nowNumber[2]);
		int ans = calProcess(opType[ops[2]], cal2, nowNumber[3]);
		if (!isCalRight)
			return target + 1;
		else
			return ans;
	}
	private static int calProcess(char c, int num0, int num1) {
		int result = Integer.MAX_VALUE;
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
			if (num1 == 0 || num0 % num1 != 0)
				isCalRight = false;
			else
				result = num0 / num1;
			break;
		case '!':
			result = num1 - num0;
			break;
		case '@':
			if (num0 == 0 || num1 % num0 != 0)
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
