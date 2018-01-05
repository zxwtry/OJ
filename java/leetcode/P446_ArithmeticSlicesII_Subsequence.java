package leetcode;

import java.util.ArrayList;
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
 * @details     Solution3: AC 407ms 28.53%
 */
public class P446_ArithmeticSlicesII_Subsequence {
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
	                Integer c1 = map[i].get(d);
	                if (c1 == null) {
	                    c1 = 0;
	                }
	                Integer c2 = map[j].get(d);
	                if (c2 == null) {
	                    c2 = 0;
	                }
	                res += c2;
	                map[i].put(d, c1 + c2 + 1);
	            }
	        }
	        return res;
	    }
	}
	static class Solution3 {
	    public int numberOfArithmeticSlices(int[] A) {
	        int answer = 0;
	        ArrayList<HashMap<Integer, Integer>> listMap = new ArrayList<>(A.length);
	        for (int j = 0; j < A.length; j ++) {
	            listMap.add(new HashMap<Integer, Integer>());
	            for (int i = 0; i < j; i ++) {
	                if (checkMinus(A[j], A[i])) continue;
	                int minus = A[j] - A[i];
	                Integer val = listMap.get(i).get(minus);
	                if (val == null) {
	                    val = 0;
	                }
	                answer += val;
	                Integer minusVal = listMap.get(j).get(minus);
	                if (minusVal == null) {
	                    minusVal = 0;
	                }
	                listMap.get(j).put(minus, val + minusVal + 1);
	            }
	        }
	        return answer;
	    }
	    private boolean checkMinus(int v1, int v2) {
	        if (((v1 >>> 31) ^ (v2 >>> 31)) == 0) {
	            //同号相减没有溢出
	            return false;
	        }
	        if ((((v1 - v2) >>> 31) ^ (v1 >>> 31)) == 0) {
	            //如果相减之后(v1 - v2) 和 v1同号号，那么没有溢出
	            return false;
	        }
	        return true;
	    }
	    public boolean checkMinus_long(int v1, int v2) {
	        long minus = (long)v1 - v2;
	        if (minus > Integer.MAX_VALUE || minus < Integer.MIN_VALUE) return true;
	        return false;
	    }
	}
}
