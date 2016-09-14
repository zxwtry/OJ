package leetcode;

import java.util.ArrayList;


/*
 * 	A message containing letters from A-Z is being encoded to numbers 
 * 	using the following mapping:

	'A' -> 1
	'B' -> 2
	...
	'Z' -> 26
	Given an encoded message containing digits, determine 
	the total number of ways to decode it.
	
	For example ,
	Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
	
	The number of ways decoding "12" is 2.
 */

public class P091_DecodeWays {
	public static void main(String[] args) {
//		System.out.println(new Solution().numDecodings("102020"));
//		System.out.println(new Solution().numDecodings("1120121"));
//		System.out.println(new Solution().numDecodings("1127121"));
		System.out.println(new Solution().numDecodings("10"));
	}
	/*
	 * 	算法题，很多都是仔细分析
	 * 	别慌，一慌什么都完了
	 * 	AC
	 * 	在慌的时候，WA好多次
	 * 	5 ms
	 */
	static class Solution {
		int[] arr = null;
		int count = 1;
		ArrayList<Integer> help = null;
		final int sign_init = -1, sign_0 = 0, sign_1 = 1, 
				sign_2 = 2, sign_3_7 = 37, sign_else = 3;
	    public int numDecodings(String s) {
	    	if (s == null || s.length() == 0) {
	    		return 0;
	    	} else if (s.equals("0")) {
	    		return 0;
	    	}
	    	arr = new int[s.length()];
	    	help = new ArrayList<Integer>();
	    	help.add(1);
	    	help.add(2);
	    	for (int i = 0; i != arr.length; i ++) {
	    		arr[i] = s.charAt(i) - '0';
	    	}
	    	int i = 0, pre_sign = sign_init;
	    	for (int j = 0; j != arr.length; j ++) {
	    		if (arr[j] < 0 || arr[j] > 26) {
	    			//出现非法输入
	    			if (i < j) {
	    				add(i, j - 1);
	    			} 
    				i = j + 1;
    				return 0;
	    		}
	    		//所有数字 [0 , 26]
	    		if (arr[j] == 0) {
	    			// 0的情况
	    			if (pre_sign == sign_1 || pre_sign == sign_2) {
	    				count *= add(i, j - 2);
	    				i = j + 1;
	    			} else {
	    				return 0;
	    			}
	    			if (j == arr.length - 1) {
	    				count *= add(i, j);
	    			}
	    			pre_sign = sign_0;
	    		} else if (arr[j] == 1) {
	    			// 1的情况
	    			if (j == arr.length - 1) {
	    				count *= add(i, j);
	    			}
	    			pre_sign = sign_1;
	    		} else if (arr[j] == 2) {
	    			// 需要注意后者是否[0 ~ 6]
	    			if (j == arr.length - 1) {
	    				count *= add(i, j);
	    			}
	    				
	    			pre_sign = sign_2;
	    		} else if (arr[j] < 7) {
	    			// 3 4 5 6
	    			// 只是需要注意前面是否是2
	    			if (pre_sign == sign_1 || pre_sign == sign_2) {
	    				count *= add(i, j);
	    				i = j + 1;
	    			} else { 
	    				// 包含
	    				// pre_sign == sign_init
	    				// pre_sign == sign_0
	    				// pre_sign == sign_3_7
	    				// pre_sign == sign_else
	    				i = j + 1;
	    			}
	    			pre_sign = sign_3_7;
	    		} else {
	    			// 7 8 9
	    			// 对arr[j] < 7的情况，只是不需要考虑2的情况
	    			if (pre_sign == sign_1) {
	    				count *= add(i, j);
	    				i = j + 1;
	    			} else if (pre_sign == sign_2) { 
	    				count *= add(i, j - 1);
	    				i = j + 1;
	    			} else {
	    				// 包含
	    				// pre_sign == sign_init
	    				// pre_sign == sign_0
	    				// pre_sign == sign_3_7
	    				// pre_sign == sign_else
	    				i = j + 1;
	    			}
	    			pre_sign = sign_else;
	    		}
	    	}
	        return count;
	    }
		private int add(int i, int j) {
			if (i > j || i < 0 || j < 0) {
				return 1;
			} else if (j - i < help.size()) {
				return help.get(j - i);
			} else {
				int k = help.size();
				int k1 = k - 1;
				int k2 = k - 2;
				for (; k <= j - i; k ++) {
					help.add(help.get(k1 ++) + help.get(k2 ++));
				}
				return help.get(j - i);
			}
		}
	}
}
