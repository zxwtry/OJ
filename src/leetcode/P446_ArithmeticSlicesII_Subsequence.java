package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 	A sequence of numbers is called arithmetic if it 
 * 	consists of at least three elements and if the difference between 
 * 	any two consecutive elements is the same.
 *	
 *	For example, these are arithmetic sequences:
 *	
 *	1, 3, 5, 7, 9
 *	7, 7, 7, 7
 *	3, -1, -5, -9
 *	The following sequence is not arithmetic.
 *	
 *	1, 1, 2, 5, 7
 *	
 *	A zero-indexed array A consisting of N numbers is given. 
 *	A subsequence slice of that array is any sequence of integers 
 *	(P0, P1, ..., Pk) such that 0 ≤ P0 < P1 < ... < Pk < N.
 *	
 *	A subsequence slice (P0, P1, ..., Pk) of array A is 
 *	called arithmetic if the sequence A[P0], A[P1], ..., A[Pk-1], A[Pk] 
 *	is arithmetic. In particular, this means that k ≥ 2.
 *	
 *	The function should return the number of arithmetic 
 *	subsequence slices in the array A.
 *	
 *	The input contains N integers. Every integer is 
 *	in the range of -231 and 231-1 and 0 ≤ N ≤ 1000. 
 *	The output is guaranteed to be less than 231-1.
 *	
 *	
 *	Example:
 *	
 *	Input: [2, 4, 6, 8, 10]
 *	
 *	Output: 7
 *	
 *	Explanation:
 *	All arithmetic subsequence slices are:
 *	[2,4,6]
 *	[4,6,8]
 *	[6,8,10]
 *	[2,4,6,8]
 *	[4,6,8,10]
 *	[2,4,6,8,10]
 *	[2,6,10]
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P446_ArithmeticSlicesII_Subsequence.java
 * @type        P446_ArithmeticSlicesII_Subsequence
 * @date        2017年3月11日 下午9:11:33
 * @details     Solution2: AC 319ms 82.06%
 */
public class P446_ArithmeticSlicesII_Subsequence {
	static class Solution {
	    public int numberOfArithmeticSlices(int[] arr) {
	        int answer = 0;
	        for (int i = 0; i < arr.length; i ++) {
	            for (int j = i + 1; j < arr.length; j ++) {
	                int irange = j - i;
	                int vrange = arr[j] - arr[i];
	                int k = j, v = arr[j];
	                int count = 2;
	                while (true) {
	                    k += irange;
	                    v += vrange;
	                    if (k < arr.length && arr[k] == v) {
	                        count ++;
	                    } else break;
	                }
	                answer += (count - 2);
	            }
	        }
            return answer;
	    }
	}
	static class Solution2 {
	    public int numberOfArithmeticSlices(int[] A) {
	        int res = 0;
	        @SuppressWarnings("unchecked")
            Map<Integer, Integer>[] map = new Map[A.length];
	            
	        for (int i = 0; i < A.length; i++) {
	            map[i] = new HashMap<>(i);
	                
	            for (int j = 0; j < i; j++) {
	                long diff = (long)A[i] - A[j];
	                if (diff <= Integer.MIN_VALUE || diff > Integer.MAX_VALUE) continue;
	                    
	                int d = (int)diff;
	                int c1 = map[i].getOrDefault(d, 0);
	                int c2 = map[j].getOrDefault(d, 0);
	                res += c2;
	                map[i].put(d, c1 + c2 + 1);
	            }
	        }
	            
	        return res;
	    }
	}
}
