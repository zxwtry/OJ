package leetcode;

import java.util.Arrays;
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
		int n = 100;
		int min = -n;
		int max = n;
		int[] A = tools.Random随机生成器.A_生成一个不重复随机数据(n, min, max);
		int[] B = tools.Random随机生成器.A_生成一个不重复随机数据(n, min, max);
		int[] C = tools.Random随机生成器.A_生成一个不重复随机数据(n, min, max);
		int[] D = tools.Random随机生成器.A_生成一个不重复随机数据(n, min, max);
		debugSortSolution(A, B, C, D);
		debugSolution(A, B, C, D);
	}
	static void debugSortSolution(int[] A, int[] B, int[] C, int[] D) {
		SortSolution s = new SortSolution();
		System.out.println(s.fourSumCount(A, B, C, D));
	}
	static void debugSolution(int[] A, int[] B, int[] C, int[] D) {
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
	/*
	 * 	WA
	 * 	提交上去发现，
	 * 	数组中是有重复数据的。
	 * 	修改之后，光荣TLE了。
	 */
	static class SortSolution {
		public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
			int ans = 0, len = A.length;
			Arrays.sort(D);
			for (int ai = 0; ai < len; ai ++) {
				for (int bi = 0; bi < len; bi ++) {
					for (int ci = 0; ci < len; ci ++) {
						int target = - A[ai] - B[bi] - C[ci];
						int sti = 0, eni = len - 1, mid = 0;
						boolean isFound = false;
						while (sti <= eni && ! isFound) {
							mid = (sti + eni) / 2;
							if (D[mid] == target) {
								isFound = true;
							} else if (D[mid] < target) {
								sti = mid + 1;
							} else {
								eni = mid - 1;
							}
						}
						if (isFound) {
							sti = mid;
							eni = mid;
							while (sti > 0 && D[sti - 1] == target) {
								sti --;
							}
							while (eni < len - 1 && D[eni + 1] == target) {
								eni ++;
							}
							ans += eni - sti + 1;
						}
					}
				}
			}
			return ans;
		}
	}
}
