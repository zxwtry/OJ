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


/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P006_ZigZagConversion.java
 * @date        2017年7月29日 下午9:49:57
 * @details     Solution1: AC 46ms 95.49%
 * @details     Solution2: AC 77ms 32.95%
 */
public class P006_ZigZagConversion {
	static class Solution1 {
	    public String convert(String s, int n) {
	        if (n == 1) return s; 
            int sn = s == null ? 0 : s.length();
            int r = 2 * n - 2;
            int c = sn / r + 1;
            char[] ans = new char[sn];
            int ai = 0;
            for (int i = 0; i < n; i ++) {
                int tn = i == 0 || (i == n - 1) ? 1 : 2;
                for (int ci = 0; ci < c; ci ++) {
                    for (int ti = 0; ti < tn; ti ++) {
                        int si = r * ci + (ti == 0 ? i : 2*n - 2 - i);
                        if (si < sn) ans[ai ++] = s.charAt(si);
                    }
                }
            }
            return new String(ans);
	    }
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
