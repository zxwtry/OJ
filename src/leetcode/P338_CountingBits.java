package leetcode;

/**
 * 	Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate 
 *  the number of 1's in their binary representation and return them as an array.
 *	
 *	Example:
 *	For num = 5 you should return [0,1,1,2,1,2].
 *	
 *	Follow up:
 *	
 *	It is very easy to come up with a solution with run time O(n*sizeof(integer)). 
 *  But can you do it in linear time O(n) /possibly in a single pass?
 *	Space complexity should be O(n).
 *	Can you do it like a boss? Do it without using any builtin function 
 *  like __builtin_popcount in c++ or in any other language.
 *	Hint:
 *	
 *	You should make use of what you have produced already.
 *	Divide the numbers in ranges like [2-3], [4-7], [8-15] and so on. And try to generate new range from previous.
 *	Or does the odd/even status of the number help you in calculating the number of 1s?
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P338_CountingBits.java
 * @type        P338_CountingBits
 * @date        2017年2月2日 下午9:13:16
 * @details     Solution1: AC 2ms 88.09% *
 * @details     Solution2: AC 6ms 15.69% *
 */
public class P338_CountingBits {
	static class Solution1 {
	    public int[] countBits(int num) {
	        if (num == 0) return new int[1];
	        int[] answer = new int[num + 1];
	        answer[1] = 1;
	        int sign = 1;
	        for (int index = 2; index <= num; index ++) {
	            if (index == (sign << 1)) sign = index;
	            answer[index] = 1 + answer[index - sign];
	        }
	        return answer;
	    }
	}
	static class Solution2 {
	    public int[] countBits(int num) {
	        if (num == 0) return new int[1];
	        int[] answer = new int[num + 1];
	        for (int index = 0; index <= num; index ++)
	            answer[index] = getBitOf1(index);
	        return answer;
	    }
	    private int getBitOf1(int val) {
	        int bitOf1 = 0;
	        while (val != 0) {
	            bitOf1 ++;
	            val = (val & (val - 1));
	        }
	        return bitOf1;
	    }
	}
}
