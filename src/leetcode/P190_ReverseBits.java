package leetcode;

/*
 * 	Reverse bits of a given 32 bits unsigned integer.

	For example, given input 43261596 (represented in binary as 00000010100101000001111010011100),
	 return 964176192 (represented in binary as 00111001011110000010100101000000).
	
	Follow up:
	If this function is called many times, how would you optimize it?
	
	Related problem: Reverse Integer
 */

public class P190_ReverseBits {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.reverseBits(1));
	}
	/*	
	 * 	3 ms
	 * 	12.09%
	 */
	static class Solution {
	    // you need treat n as an unsigned value
		// 将n看成是无符号的数
		// 就是
	    public int reverseBits(int n) {
//	    	boolean isAnswerNegative = n % 2 == 1;
	    	int sti = 0, eni = 31;
//	    	long nn = (long) n;
	    	int valSti = 0, valEni = 0;
	    	int excSti = 0, excEni = 0;
	    	while (sti < eni) {
	    		valSti = n & (1 << sti);
	    		valEni = n & (1 << eni);
	    		excSti = valEni == 0 ? 0 : 1 << sti;
	    		excEni = valSti == 0 ? 0 : 1 << eni;
	    		n = n - valSti - valEni + excSti + excEni;
	    		sti ++;
	    		eni --;
	    	}
	        return n;
	    }
	}
}
