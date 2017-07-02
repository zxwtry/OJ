package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        发散思维能力_求1_n.java
 * @date        2017年7月2日 下午7:02:29
 * @details     
 */
public class 发散思维能力_求1_n {
    static public class Solution {
        public int Sum_Solution(int n) {
            int sum = n;
            boolean t = (n != 1) && (sum += Sum_Solution(n - 1)) > 0;
            return sum;
        }
    }
}
