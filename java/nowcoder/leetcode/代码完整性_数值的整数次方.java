package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        代码完整性_数值的整数次方.java
 * @date        2017年6月29日 下午10:22:39
 * @details     剑指Offer
 */
public class 代码完整性_数值的整数次方 {
    public class Solution {
        public double Power(double b, int e) {
            if (e < 0) return 1 / Power(b, -e);
            if (e == 0) return 1;
            if (e % 2 == 1) {
                return b * Power(b, e - 1);
            }
            double dd = Power(b, e / 2);
            return dd * dd;
        }
    }
}
