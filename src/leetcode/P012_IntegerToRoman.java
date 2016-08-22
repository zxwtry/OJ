package leetcode;

/*
 * 	
 * 	12. Integer to Roman  QuestionEditorial Solution  My Submissions
	Total Accepted: 73611
	Total Submissions: 180456
	Difficulty: Medium
	Given an integer, convert it to a roman numeral.
	Input is guaranteed to be within the range from 1 to 3999.
 * 	
 */

public class P012_IntegerToRoman {
	public static void main(String[] args) {
		System.out.println(new Solution().intToRoman(1));
		System.out.println(new Solution().intToRoman(11));
		System.out.println(new Solution().intToRoman(111));
		System.out.println(new Solution().intToRoman(1111));
	}
	/*
	 * 	10 ms
	 * 	38.91%
	 */
	static class Solution {
		private  String [][]str = {
            {"","I","II","III","IV","V","VI","VII","VIII","IX"},
            {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"},
            {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"},
            {"","M","MM","MMM"}
		};
		public String intToRoman(int num) {
			StringBuilder st = new StringBuilder(16);
	        st.append(str[3][num / 1000 % 10]);
	        st.append(str[2][num / 100 % 10]);
	        st.append(str[1][num / 10 % 10]);
	        st.append(str[0][num % 10]);
	        return st.toString();
		}
	}
}
