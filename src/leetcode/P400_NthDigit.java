package leetcode;

/**
 * 	Find the nth digit of the infinite integer sequence
 *  1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
 *	
 *	Note:
 *	n is positive and will fit within the range of a 32-bit signed integer (n < 231).
 *	
 *	Example 1:
 *	
 *	Input:
 *	3
 *	
 *	Output:
 *	3
 *	Example 2:
 *	
 *	Input:
 *	11
 *	
 *	Output:
 *	0
 *	
 *	Explanation:
 *	The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 
 *	is a 0, which is part of the number 10.
 */


public class P400_NthDigit {
	public static void main(String[] args) {
//		System.out.println(new Solution().num_k(4));
		Solution s = new Solution();
		for (int i = 788888889; i < 20 + 788888889; i ++) {
			System.out.println(i + "..." + s.findNthDigit(i));
		}	
	}
	/*
	 * 	AC
	 * 	5 ms
	 */
	static class Solution {
		int[] edge = new int[] {
				0,
				9,
				189,
				2889,
				38889,
				488889,
				5888889,
				68888889,
				788888889
		};
	    public int findNthDigit(int n) {
	    	if (n <= 0) {
	    		return 0;
	    	}
	    	int the_bits = 1;
	    	while (true) {
	    		if (the_bits > 8 || n <= edge[the_bits]) {
	    			break;
	    		}
	    		the_bits ++;
	    	}
	        return my_n_digit(the_bits, n - edge[the_bits - 1] - 1);
	    }
//	    void generate_all_k() {
//	    	int num_add = 0;
//	    	int k = 1;
//	    	while (true) {
//	    		long this_num_k = num_k(k ++);
//	    		long new_num_add = (long)num_add + this_num_k;
//	    		System.out.println(num_add);
//	    		if ((new_num_add >> 32) != 0) {
//	    			break;
//	    		}
//	    		num_add = (int)new_num_add;
//	    	}
//	    	System.out.println(k);
//	    }
//	    long num_k(int k) {
//	    	return k * 9 * (long)Math.pow(10, k - 1);
//	    }
		private int my_n_digit(int the_bits, int position) {
			int num = position / the_bits;
			num += (int) Math.pow(10, the_bits - 1);
			int num_in_int = position % the_bits;
			return ( num / (int)Math.pow(10, the_bits - 1 - num_in_int)) % 10;
		}
	}
}
