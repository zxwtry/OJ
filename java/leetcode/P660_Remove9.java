package leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P660_Remove9.java
 * @date        2017年8月13日 上午11:29:54
 * @details     
 */
public class P660_Remove9 {
    
    static int standard(int n) {
        int cnt = 0;
        for (int i = 1; i <= n; i ++) {
            String v = String.valueOf(i);
            boolean find = false;
            for (int j = 0; j < v.length(); j ++) {
                if (v.charAt(j) == '9') { 
                    find = true;
                }
            }
            if (! find) {
                cnt ++;
            }
        }
        return cnt;
    }
    
    static public class Solution {
        public int newInteger(int n) {
            //1, 2, 3, 4, 5, 6, 7, 8, 10, 11, ..
            if (n < 1) {
                return 0;
            }
            String s = String.valueOf(n);
            int sn = s.length();
            return n - get9Cnt(s, 0, sn - 1);
        }
        
        private int get9Cnt(String s, int i, int j) {
            if (i == j) {
                return s.charAt(i) < '9' ? 0 : 1;
            }
            //创建一个 长度为 j-i的字符串
            char[] cs = new char[j - i];
            for (int csIndex = 0; csIndex < cs.length; csIndex ++) {
                cs[csIndex] = '9';
            }
            int base = get9Cnt(new String(cs), 0, cs.length - 1);
            char c = s.charAt(i);
            int ans = (c - '0') * base;
            if (c == '9') {
                //需要增加
                int nineAdd = Integer.parseInt(s.substring(i + 1)) + 1;
                ans += nineAdd;
            } else {
                ans += get9Cnt(s, i + 1, j);
            }
            return ans;
        }
    }
}
