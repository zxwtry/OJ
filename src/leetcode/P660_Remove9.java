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
    
    public static void main(String[] args) {
        for (int i = 1; i < 90; i ++) {
            System.out.printf("%03d  %03d  %03d\n", i, standard(i), new Solution().newInteger(i));
        }
    }
    
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
            return n - get9Cnt(n);
        }
        
        public int get9Cnt(int n) {
            if (n < 90) {
                int v = n / 10;
                int u = n % 10;
                return u < 9 ? v : v + 1;
            }
            if (n <= 100) {
                
            }
            return 0;
        }
        
    }
}
