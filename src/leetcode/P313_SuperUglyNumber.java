package leetcode;

/**
 * 	Write a program to find the nth super ugly number.

 *	Super ugly numbers are positive numbers whose all prime factors 
 *	are in the given prime list primes of size k. For example, 
 *	[1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of 
 *	the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.
 *	
 *	Note:
 *	(1) 1 is a super ugly number for any given primes.
 *	(2) The given numbers in primes are in ascending order.
 *	(3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P313_SuperUglyNumber.java
 * @type        P313_SuperUglyNumber
 * @date        2016年12月29日 下午10:24:20
 * @details     Solution1: AC 29ms 59.72%
 */
public class P313_SuperUglyNumber {
	static class Solution1 {
		public int nthSuperUglyNumber(int n, int[] primes) {
		    int[] ugly = new int[n];
		    int[] idx = new int[primes.length];

		    ugly[0] = 1;
		    for (int i = 1; i < n; i++) {
		        //find next
		        ugly[i] = Integer.MAX_VALUE;
		        for (int j = 0; j < primes.length; j++)
		            ugly[i] = Math.min(ugly[i], primes[j] * ugly[idx[j]]);
		        
		        //slip duplicate
		        for (int j = 0; j < primes.length; j++) {
		            while (primes[j] * ugly[idx[j]] <= ugly[i]) idx[j]++;
		        }
		    }

		    return ugly[n - 1];
		}
	}
}
