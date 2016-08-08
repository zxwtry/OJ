package leetcode;

import java.util.Arrays;

public class P003_LongestSubstringWithoutRepeating {
	public static void main(String[] args) {
		new Solution1().lengthOfLongestSubstring("mabcdabcdmmm");
		new Solution1().lengthOfLongestSubstring("mabcd");
	}
	/*
	 * 	82.35%
	 */
	static class Solution {
		int[] map = new int[128];
	    public int lengthOfLongestSubstring(String s) {
	        if (s == null)
	        	return 0;
	        if (s.length() < 2)
	        	return s.length();
	        int[] preIndex = new int[s.length()];
	        Arrays.fill(map, -1);
	        for (int i = 0; i != preIndex.length; i ++) {
	        	int index = (int)s.charAt(i);
	        	if (map[index] == -1) {
	        		preIndex[i] = -1;
	        		map[index] = i;
	        	} else {
	        		preIndex[i] = map[index];
	        		map[index] = i;
	        	}
	        }
	        int i0 = 0, i1 = 0, max = Integer.MIN_VALUE;
	        for (; i1 != preIndex.length; i1 ++) {
	        	if (preIndex[i1] >= i0) {
	        		if (max < i1 - i0)
	        			max = i1 - i0;
	        		i0 = preIndex[i1] + 1;
	        	}
	        }
    		if (max < i1 - i0)
    			max = i1 - i0;
	        System.out.println(max);
	        return max;
	    }
	}
	/*
	 * 	将两次O(N)整合成一次O(N)
	 * 	86.95%
	 */
	static class Solution1 {
		int[] map = new int[128];
	    public int lengthOfLongestSubstring(String s) {
	        if (s == null)
	        	return 0;
	        int len = s.length();
	        if (len < 2)
	        	return len;
	        Arrays.fill(map, -1);
	        int i0 = 0, i1 = 0, max = Integer.MIN_VALUE, preIndex = 0;
	        for (; i1 != len; i1 ++) {
	        	int index = (int)s.charAt(i1);
	        	if (map[index] == -1) {
	        		preIndex = -1;
	        		map[index] = i1;
	        	} else {
	        		preIndex = map[index];
	        		map[index] = i1;
	        	}
	        	if (preIndex >= i0) {
	        		if (max < i1 - i0)
	        			max = i1 - i0;
	        		i0 = preIndex + 1;
	        	}
	        }
    		if (max < i1 - i0)
    			max = i1 - i0;
	        return max;
	    }
	}
}
