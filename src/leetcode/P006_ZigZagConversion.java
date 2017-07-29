package leetcode;

/**
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
//		System.out.println(new Solution2().convert("PAYPALISHIRING", 3));
		System.out.println(new Solution2().convert("AB", 1));
	}
	static class Solution2 {
	    boolean isDown = true;
	    int i , j, sn, n;
	    char[][] m;
	    public String convert(String s, int n) {
	        if (n == 1) return s; 
	        sn = s == null ? 0 : s.length();
	        this.n = n;
	        m = new char[n][sn];
	        i = 0; j = 0;
	        for (int si = 0; si < sn; si ++) {
	            m[i][j] = s.charAt(si);
	            next();
	        }
	        char[] ans = new char[sn];
	        int ai = 0;
	        for (int mi = 0; mi < n; mi ++) {
    	        for (int mj = 0; mj < sn; mj ++) {
    	            if (m[mi][mj] != (char)0) {
    	                ans[ai ++] = m[mi][mj];
    	            }
    	        }
	        }
	        return new String(ans);
	    }
	    void next() {
	        if (i == 0) isDown = true;
	        if (i == n - 1) isDown = false;
	        if (isDown) {
	            i ++;
	        } else {
	            i --;
	            j ++;
	        }
	    }
	}
}
