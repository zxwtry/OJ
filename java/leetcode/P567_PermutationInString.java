package leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P567_PermutationInString.java
 * @type        P567_PermutationInString
 * @date        2017年4月30日 上午9:50:17
 * @details     
 */
public class P567_PermutationInString {
    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "ab";
        System.out.println(new Solution().checkInclusion(s1, s2));
    }
    static public class Solution {
        public boolean checkInclusion(String s1, String s2) {
            int n1 = s1 == null ? 0 : s1.length();
            int n2 = s2 == null ? 0 : s2.length();
            if (n1 == 0) return true;
            if (n2 == 0) return false;
            char[] c1 = s1.toCharArray();
            char[] c2 = s2.toCharArray();
            int[] t1 = new int[26];
            int[][] t2 = new int[n2+1][26];
            for (int i = 1; i <= n1; i ++) {
                t1[c1[i-1] - 'a'] ++;
            }
            for (int i = 1; i <= n2; i ++) {
                for (int j = 0; j < 26; j ++) {
                    t2[i][j] = t2[i-1][j];
                }
                t2[i][c2[i-1] - 'a'] ++;
            }
            for (int i = 0; i <= n2; i ++) {
                int j = i+n1;
                if (j > n2) break;
                boolean allTrue = true;
                for (int k = 0; allTrue & k < 26; k ++) {
                    allTrue &= (t2[j][k] - t2[i][k] == t1[k]);
                }
                if (allTrue) return true;
            }
            return false;
        }
    }
}
