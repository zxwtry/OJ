package leetcode;

import java.util.Vector;

/*
 * 	The set [1,2,3,…,n] contains a total of n! unique permutations.

	By listing and labeling all of the permutations in order,
	We get the following sequence (ie, for n = 3):
	
	"123"
	"132"
	"213"
	"231"
	"312"
	"321"
	Given n and k, return the kth permutation sequence.
	
	Note: Given n will be between 1 and 9 inclusive.
 */

public class P060_PermutationSequence {
	public static void main(String[] args) {
		Solution s = new Solution();
		for (int i = 1; i != 25; i ++)
			System.out.println(i + "..."+s.getPermutation(4, i));
//		tools.Utils.printArray(s.cs, 10);
//		tools.Utils.printArray(s.jiecheng, 10);
	}
	/*
	 *	使用暴力方法肯定不能够完成
	 *	肯定有O(n)的方法
	 *	先用最好理解的方法
	 *	leetcode 网站打不开23333'
	 *	除了一次没有引包Vector，一次AC，好爽！！！
	 *	2 ms
	 *	73.96%
	 */
	static class Solution {
		int[] jiecheng = null;
		int[] cs = null;
	    public String getPermutation(int n, int k) {
	    	if (n < 1)
	    		return "";
	    	k --;
	    	generateJiecheng(n);
	    	cs = new int[n];
	    	for (int i = 0; i != n; i ++) {
	    		cs[i] = k / jiecheng[n - 1- i];
	    		k = k % jiecheng[n - 1- i];
	    	}
	    	Vector<Integer> v = new Vector<Integer>(n);
	    	for (int i = 0; i != n; i ++)
	    		v.add(i);
	    	char[] temp = new char[n];;
	    	for (int i = 0; i != n; i ++) {
	    		temp[i] = (char)('1' + v.get(cs[i]));
	    		v.remove(cs[i]);
	    	}
	        return new String(temp);
	    }
	    void generateJiecheng(int n) {
	    	jiecheng = new int[n];
	    	jiecheng[0] = 1;
	    	for (int i = 1; i != n; i ++)
	    		jiecheng[i] = i * jiecheng[i - 1];
	    }
	}
	
}
