package poj10x;

/*
 * 	status : Accept
 * 	Memory	Time
 * 	3056K	125MS
 */
/*
 * 	大意：
 * 		一种特殊二进制数，就是在普通二进制数上加一个正负号，而且是加在位上
 * 		举例：二进制 1101，加上正负号ppnn，那么值是8+4+0+(-1) = 11
 * 		以最低位为例，如果数是奇数，那么无论是正号还是负号，在二进制上都是1
 * 					 如果数是偶数，那么无论是正号还是负号，在二进制上都是0
 * 		处理完最低位，向右移一位，又是处理最低位。
 * 		对于正负号对值的影响，可用进位退位来理解。
 */

import java.util.Arrays;
import java.util.Scanner;

public class P1023 {
	static boolean[] isPositive = null;
	static boolean[] mapOfPositive = null;
	static Long target;
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numOfTest = scanner.nextInt();
		while(numOfTest -- > 0) {
			scanner.nextInt();
			char[] PN = scanner.next().trim().toCharArray();
			isPositive = new boolean[PN.length];
			for (int index = 0; index < PN.length; index ++) {
				isPositive[index] = PN[index] == 'p' ? true : false;
			}
			mapOfPositive = new boolean[PN.length];
			Arrays.fill(mapOfPositive, false);
			target = scanner.nextLong();
			if (target == 0l) {
				for (int index = 0; index < PN.length; index ++) {
					System.out.print(0);
				}
				System.out.println();
				continue;
			}
			for (int index = PN.length - 1; index >= 0; index --) {
				if (isPositive[index]) {
					if ( (target & 0x1) == 1) {
						mapOfPositive[index] = true;
						target = (target - 1) >> 1;
					} else {
						target = target >> 1;
					}
				} else {
					if ( (target & 0x1) == 1) {
						mapOfPositive[index] = true;
						target = (target + 1) >> 1;
					} else {
						target = target >> 1;
					}
				}
			}
			if (target == 0l) {
				for (int index = 0; index < PN.length; index ++) {
					if (mapOfPositive[index]) {
						System.out.print(1);
					} else {
						System.out.print(0);
					}
				}
				System.out.println();
			} else {
				System.out.println("Impossible");
			}
		}
		scanner.close();
	}
}
