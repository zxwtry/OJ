package leetcode;

import java.math.BigInteger;

/**
 * 	Additive number is a string whose digits can form additive sequence.
 *	
 *	A valid additive sequence should contain at least three numbers. 
 *	Except for the first two numbers, each subsequent number in the sequence 
 *	must be the sum of the preceding two.
 *	
 *	For example:
 *	"112358" is an additive number because the digits can form an additive 
 *	sequence: 1, 1, 2, 3, 5, 8.
 *	
 *	1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 *	"199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
 *	1 + 99 = 100, 99 + 100 = 199
 *	Note: Numbers in the additive sequence cannot have leading zeros, 
 *	so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 *	
 *	Given a string containing only digits '0'-'9', write a function to 
 *	determine if it's an additive number.
 *	
 *	Follow up:
 *	How would you handle overflow for very large input integers?
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P306_AdditiveNumber.java
 * @type        P306_AdditiveNumber
 * @date        2016年12月29日 下午8:50:40
 * @details     Solution1: AC 9ms 10.76%
 * @details     Solution2: AC 4ms 31.51%
 */
public class P306_AdditiveNumber {
	static class Solution1 {
		public boolean isAdditiveNumber(String num) {
			if (num == null || num.length() < 3) return false;
			for (int oneLength = 1; oneLength <= num.length() / 2; oneLength ++) {
				if (num.charAt(0) == '0' && oneLength > 1) continue;
				for (int twoLength = 1; Math.max(oneLength, twoLength) <= num.length() - oneLength - twoLength; twoLength ++) {
					if (num.charAt(oneLength) == '0' && twoLength > 1) continue;
					BigInteger oneBigInteger = new BigInteger(num.substring(0, oneLength));
					BigInteger twoBigInteger = new BigInteger(num.substring(oneLength, oneLength + twoLength));
					String sumString = "";
					boolean isSuccess = true;
					for (int numIndex = oneLength + twoLength; isSuccess && numIndex != num.length(); numIndex += sumString.length()) {
						twoBigInteger = oneBigInteger.add(twoBigInteger);
						oneBigInteger = twoBigInteger.subtract(oneBigInteger);
						sumString = twoBigInteger.toString();
						if (! num.startsWith(sumString, numIndex)) isSuccess = false;
					}
					if (isSuccess) return true;
				}
			}
			return false;
		}
	}
}
