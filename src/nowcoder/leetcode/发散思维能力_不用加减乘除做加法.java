package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        发散思维能力_不用加减乘除做加法.java
 * @date        2017年7月2日 下午7:18:18
 * @details     剑指Offer
 */
public class 发散思维能力_不用加减乘除做加法 {
    static public class Solution {
        public int Add(int n1,int n2) {
            int s1 = 0, s2 = 0;
            while (n2 != 0) {
                s1 = n1;
                s2 = n2;
                n1 = s1 ^ s2;
                n2 = s1 & s2;
                n2 <<= 1;
            }
            return n1;
        }
    }
}
