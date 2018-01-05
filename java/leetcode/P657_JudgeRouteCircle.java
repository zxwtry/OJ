package leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P657_JudgeRouteCircle.java
 * @date        2017年8月13日 上午9:30:31
 * @details     
 */
public class P657_JudgeRouteCircle {
    
    static class Solution {
        public boolean judgeCircle(String moves) {
            int mn = moves == null ? 0 : moves.length();
            int x = 0, y = 0;
            for (int i = 0; i < mn; i ++) {
                char c = moves.charAt(i);
                if (c == 'L') {
                    x --;
                } else if (c == 'R') {
                    x ++;
                } else if (c == 'U') {
                    y ++;
                } else if (c == 'D') {
                    y --;
                }
            }
            return x == 0 & y == 0;
        }
    }
}
