package leetcode;

import java.util.HashMap;

/*
 * 	Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) 
 * 	there are such that A[i] + B[j] + C[k] + D[l] is zero.

	To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. 
	All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.
	
	Example:
	
	Input:
	A = [ 1, 2]
	B = [-2,-1]
	C = [-1, 2]
	D = [ 0, 2]
	
	Output:
	2
	
	Explanation:
	The two tuples are:
	1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
	2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */

public class P454_4SumII {
	public static void main(String[] args) {
		debugSolution();
	}
	static void debugSolution() {
		int[] A = new int[] {1, 2};
		int[] B = new int[] {-2,-1};
		int[] C = new int[] {-1, 2};
		int[] D = new int[] {0, 2};
		Solution s = new Solution();
		System.out.println(s.fourSumCount(A, B, C, D));
	}
	/*
	 * 	AC
	 * 	261 ms
	 * 	这是一种号称时间O(N^2) 空间O(N^2)的方法
	 * 	实际上效率非常低
	 */
	static class Solution {
	    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
	        HashMap<Integer, Integer> m1 = new HashMap<Integer, Integer>();
	        HashMap<Integer, Integer> m2 = new HashMap<Integer, Integer>();
	        generate(A, B, m1);
	        generate(C, D, m2);
	        int ans = 0;
	        for (int sum1 : m1.keySet()) {
	        	if (m2.containsKey(-sum1)) {
	        		ans += m1.get(sum1) * m2.get(-sum1);
	        	}
	        }
	        return ans;
	    }
		void generate(int[] a, int[] b, HashMap<Integer, Integer> m) {
			for (int ai = 0; ai < a.length; ai ++) {
				for (int bi = 0; bi < b.length; bi ++) {
					int sum = a[ai] + b[bi];
					if (m.containsKey(sum)) {
						m.put(sum, m.get(sum) + 1);
					} else {
						m.put(sum, 1);
					}
				}
			}
		}
	}
}
