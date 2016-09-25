package leetcode;

import java.util.ArrayList;

/*
 * 	Given an integer, write an algorithm to convert it to hexadecimal. 
 * 	For negative integer, two’s complement method is used.

	IMPORTANT:
	You must not use any method provided by the library which
	 converts/formats the number to hex directly. 
	 Such solution will result in disqualification of all your submissions 
	 to this problem. Users may report such solutions after the contest ends 
	 and we reserve the right of final decision and interpretation 
	 in the case of reported solutions.
	
	Note:
	
	All letters in hexadecimal (a-f) must be in lowercase.
	The hexadecimal string must not contain extra leading 0s. 
	If the number is zero, it is represented by a single zero character '0'; 
	otherwise, the first character in the hexadecimal string will 
	not be the zero character.
	The given number is guaranteed to fit within the range of
	 a 32-bit signed integer.
	You must not use any method provided by the library which 
	converts/formats the number to hex directly.
	Example 1:
	
	Input:
	26
	
	Output:
	"1a"
	Example 2:
	
	Input:
	-1
	
	Output:
	"ffffffff"
 * 
 */


public class P405_ConvertaNumbertoHexadecimal {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(Integer.toHexString(Integer.MAX_VALUE));
		for (int i = 0; i <= Integer.MAX_VALUE; i += 100000) {
			if (! Integer.toHexString(i).equals(s.toHex(i))) {
				System.out.println("abbb");
			}
		}
	}
	static class Solution {
		final char[] ch = new char[] {
			'a', 'b', 'c', 'd', 'e', 'f'
		};
	    public String toHex(int num) {
	    	ArrayList<Integer> bits = new ArrayList<Integer>();
	    	if (num > 0) {
	    		getHex(num, bits);
	    		return convert(bits);
	    	} else if (num < 0) {
	    		if (num == Integer.MIN_VALUE) {
	    			return "80000000";
	    		}
	    		getHex(-num, bits);
	    		ArrayList<Integer> f = new ArrayList<>();
	    		quf(bits, f);
	    		return convert(f);
	    	} else {
	    		return "0";
	    	}
	    }
		private void quf(ArrayList<Integer> bits, ArrayList<Integer> f) {
			for (int i = 0; i < bits.size(); i ++) {
				f.add(15 - bits.get(i));
			}
			while (f.size() < 8) {
				f.add(15);
			}
			f.set(0, f.get(0) + 1);
			if (f.get(0) == 16) {
				f.set(0, 0);
				int carry = 1;
				for (int i = 1; carry == 1 && i < f.size(); i ++) {
					int new_val = f.get(i) + carry;
					if (new_val == 16) {
						f.set(i, 0);
					} else {
						carry = 0;
						f.set(i, new_val);
					}
				}
			}
		}
		private String convert(ArrayList<Integer> bits) {
			char[] this_str = new char[bits.size()];
			for (int i = this_str.length - 1; i > -1; i --) {
				int val = bits.get(i);
				if (val > 9) {
					this_str[this_str.length - 1 - i] = ch[val - 10];
				} else {
					this_str[this_str.length - 1 - i] = (char)('0' + val);
				}
			}
			return new String(this_str);
		}
		private void getHex(int num, ArrayList<Integer> bits) {
			while (num > 0) {
				int part = num % 16;
				bits.add(part);
				num = num / 16;
			}
		}
	}
}
