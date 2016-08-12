package leetcode;

import java.util.Arrays;

/*
6. ZigZag Conversion  QuestionEditorial Solution  My Submissions
Total Accepted: 101242
Total Submissions: 406283
Difficulty: Easy
The string "PAYPALISHIRING" is written in a zigzag 
pattern on a given number of rows like this: 
(you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 */


public class P006_ZigZagConversion {
	public static void main(String[] args) {
		System.out.println(new Solution1().convert("PAYPALISHIRING", 3));
	}
	/*
	 * 	这道题非常烦，没有同一的方法原型，很困难。
	 * 	 82.70%
	 * 	4ms
	 */
	static class Solution1 {
		public String convert(String s, int n) {
			if (s == null || s.length() == 0)
				return "";
			if (n == 1)
				return s;
			char[] c = new char[s.length()];
			int[] index = new int[c.length];
			int circle = (n << 1) - 2;
			int[] help_last = new int[n];
			Arrays.fill(help_last, 0);
			int count_last = c.length - (c.length / circle) * circle;
			for (int i_last = 0; i_last < count_last; i_last ++) {
				if (i_last < n)
					help_last[i_last] ++;
				else
					help_last[- i_last + circle] ++;
			}
			int nums_group = c.length / circle;
			int cur_index = 0;
			for (int row = 0; row < n; row ++) {
				// i == 0
				index[cur_index] = row;
				if ( ++ cur_index >= index.length)
					break;
				if (row != 0 && row != n - 1 && nums_group != 0) {
					index[cur_index] = circle - index[cur_index - 1];
					if ( ++ cur_index >= index.length)
						break;
				}
				for (int i = 1; i < nums_group; i ++) {
					if (row == 0 || row == n - 1) {
						index[cur_index] = index[cur_index - 1] + circle;
						if ( ++ cur_index >= index.length)
							break;
					} else {
						index[cur_index] = index[cur_index - 2] + circle;
						if ( ++ cur_index >= index.length)
							break;
						index[cur_index] = index[cur_index - 2] + circle;
						if ( ++ cur_index >= index.length)
							break;
					}
				}
				if ((row == 0 || row == n - 1) && (help_last[row] == 1)) {
					if (nums_group != 0) {
						index[cur_index] = index[cur_index - 1] + circle;
						if ( ++ cur_index >= index.length)
							break;
					}
				} else {
					if (help_last[row] == 1) {
						if (nums_group != 0) { 
							index[cur_index] = index[cur_index - 2] + circle;
							if ( ++ cur_index >= index.length)
								break;
						}
					} else if (help_last[row] == 2) {
						if (nums_group != 0) {
							index[cur_index] = index[cur_index - 2] + circle;
							if ( ++ cur_index  >= index.length)
								break;
							index[cur_index] = index[cur_index - 2] + circle;
							if ( ++ cur_index  >= index.length)
								break;
						} else {
							index[cur_index] = circle - index[cur_index - 1];
							if ( ++ cur_index  >= index.length)
								break;
						}
					}
				}
			}
			for (int i = 0; i != c.length; i ++) {
				c[i] = s.charAt(index[i]);
			}
			return new String(c);
		}
	}
}
