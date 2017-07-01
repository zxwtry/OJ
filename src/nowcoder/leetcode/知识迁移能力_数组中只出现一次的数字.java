package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        知识迁移能力_数组中只出现一次的数字.java
 * @date        2017年7月1日 下午9:43:26
 * @details     剑指Offer
 */
public class 知识迁移能力_数组中只出现一次的数字 {
    static public class Solution {
        public void FindNumsAppearOnce(int [] a,int num1[] , int num2[]) {
            int xor = 0;
            for (int v : a) xor = xor ^ v;
            int off = 0;
            while ((xor >>> off) % 2 == 0) {
                off ++;
            }
            int a1 = 0, a2 = 0;
            for (int v : a) {
                if ((v >> off) % 2 == 0) {
                    a1 = a1 ^ v;
                } else {
                    a2 = a2 ^ v;
                }
            }
            num1[0] = a1;
            num2[0] = a2;
        }
    }
}
