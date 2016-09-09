package leetcode;

public class P069_Sqrtx {
	public static void main(String[] args) {
		System.out.println(new Solution2().mySqrt(2147395599));
	}
	/*
	 * 	hehe
	 * 	2 ms
	 */
	static class Solution {
	    public int mySqrt(int x) {
	        return (int)Math.sqrt(x);
	    }
	}
	/*
	 * 	7 = 111b
	 * 	分为两步： 	1，100b
	 * 			  	2, 10b - 100b
	 * 	97 = 1100001b
	 * 	分为两步:	1，1100001b
	 * 				2，1000000b
	 * 				3，1000b - 10000b
	 * 	其实复杂度还是挺高的，应该算在O(N^0.5)
	 * 	4 ms
	 */
	static class Solution2 {
	    public int mySqrt(int x) {
	    	if (x == 1 || x == 0)
	    		return x;
	        int firstOne = 30;
	        while ((x >> firstOne --) != 1) {}
	        firstOne = (firstOne + 1) >> 1;
	        return binarySearch(x, 1 << firstOne, 1 << (firstOne + 1));
	    }
	    private int binarySearch(int x, int start, int end) {
	    	while (start < end) {
	    		int mid = ((start + end) & 0x1) == 1 ? ((start + end) >> 1) + 1 : (start + end) >> 1;
	        	long multi = ((long)mid) * mid;
	    		if (multi < x) {
	    			start = mid;
	    		} else if (multi > x) {
	    			end = mid - 1;
	    		} else {
	    			return mid;
	    		}
	    	}
	    	return start;
	    }
	}
}