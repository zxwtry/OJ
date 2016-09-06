package leetcode;

/*
 * 	Given a non-negative number represented as an array of digits,
 *  plus one to the number.

	The digits are stored such that the most significant digit is 
	at the head of the list.
 */

public class P066_PlusOne {
	public static void main(String[] args) {
		int[] ans = new Solution().plusOne(new int[] { 9, 9, 8 });
		tools.Utils.printArray(ans, 10);
	}
	/*
	 * 	之前没看懂啥意思，就是一个数字进位。
	 * 	0 ms
	 * 	43.01% 
	 */
	static class Solution {
	    public int[] plusOne(int[] digits) {
	    	if (digits == null || digits.length == 0)
	    		return new int[] {1};
	    	digits[digits.length - 1] ++;
	    	int carry = 0, index = digits.length - 1;
	    	do {
	    		if (index == -1) {
	    			int[] temp = new int[digits.length + 1];
	    			System.arraycopy(digits, 0, temp, 1, digits.length);
	    			temp[0] = 1;
	    			digits = temp;
	    			break;
	    		} else {
	    			digits[index] += carry;
	    			carry = digits[index] / 10;
	    			digits[index] = digits[index] % 10;
	    			index --;
	    		}
	    	} while (carry != 0);
	        return digits;
	    }
	}
}
