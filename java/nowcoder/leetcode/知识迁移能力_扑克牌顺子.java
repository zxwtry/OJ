package nowcoder.leetcode;

import java.util.Arrays;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        知识迁移能力_扑克牌顺子.java
 * @date        2017年7月2日 上午9:12:12
 * @details     剑指Offer
 */
public class 知识迁移能力_扑克牌顺子 {
    static public class Solution {
        public boolean isContinuous(int [] n) {
            int nn = n == null ? 0 : n.length;
            if (nn < 2) return nn == 1;
            Arrays.sort(n);
            int numOfZero = 0;
            while (n[numOfZero] == 0) numOfZero ++;
            
            for (int i = numOfZero + 1; i < nn; i ++) {
                if (n[i] - n[i - 1] == 0) {
                    return false;
                } else if (n[i] - n[i - 1] != 1) {
                    if (numOfZero == 0)
                        return false;
                    else 
                        numOfZero -= n[i] - n[i - 1] - 1;
                }
            }
            return numOfZero >= 0;
        }
    }
}
