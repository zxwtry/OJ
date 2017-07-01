package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        时间效率_整数中1出现的次数.java
 * @date        2017年7月1日 上午10:40:36
 * @details     剑指Offer
 */
public class 时间效率_整数中1出现的次数 {
    static public class Solution {
        final int v99 = 20;
        final int v199 = 140;
        final int v999 = 300;
        final int v1999 = 1600;
        public int NumberOf1Between1AndN_Solution(int n) {
            if (n < 100) return s100(n);
            if (n < 1000) return s1000(n);
            if (n < 10000) return s10000(n);
            return solve(n);
        }
        private int s100(int n) {
            if (n < 1) return 0;    //没有
            if (n < 10) return 1;   //就1
            if (n < 11) return 2;
            if (n < 20) return 4 + (n - 11);
            if (n < 21) return 12;
            return 12 + ((n - 11) / 10);
        }
        //[100, 999]
        private int s1000(int n) {
            if (n < 200) {
                int baiwei = n - 99;
                return v99 + baiwei + s100(n - 100);
            }
            int times = n / 100;
            return (times - 2) * v99 + v199 + s100(n - times * 100);
        }
        //[1000, 9999]
        private int s10000(int n) {
            if (n < 2000) {
                int qianwei = n - 999;
                return v999 + qianwei + s1000(n - 1000);
            }
            int times = n / 1000;
            return (times - 2) * v999 + v1999 + s1000(n - times * 1000);
        }
        private int solve(int n) {
            if (n < 100) return s100(n);
            if (n < 1000) return s1000(n);
            if (n < 10000) return s10000(n);
            char[] cs = String.valueOf(n).toCharArray();
            int len = cs.length;
            int small = 0;
            for (int i = 1; i < len; i ++)
                small = small * 10 + (cs[i] - '0');
            int smallV = solve(small);
            int wei = 1;
            for (int i = 1; i < len; i ++) wei = wei * 10;
            wei --;
            if (cs[0] == '1') {
                int newWei = n - wei;
                return solve(wei) + newWei + smallV;
            }
            int times = cs[0] - '0';
            return (times - 2) * solve(wei) + solve(2 * wei + 1) + smallV;
        }
    }
    static int cnt(int n ) {
        int cnt = 0;
        for (int i = 1; i <= n; i ++) {
            char[] cs = String.valueOf(i).toCharArray();
            for (char c : cs) {
                if (c == '1') {
                    cnt ++;
                }
            }
        }
        return cnt;
    }
}
