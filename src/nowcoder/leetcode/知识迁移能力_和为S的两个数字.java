package nowcoder.leetcode;

import java.util.ArrayList;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        知识迁移能力_和为S的两个数字.java
 * @date        2017年7月2日 上午8:51:59
 * @details     剑指Offer
 */
public class 知识迁移能力_和为S的两个数字 {
    static public class Solution {
        public ArrayList<Integer> FindNumbersWithSum(int [] a,int s) {
            int al = a == null ? 0 : a.length;
            if (al == 0) return new ArrayList<Integer>(0);
            //Arrays.sort(a);
            int i = 0, j = al - 1;
            ArrayList<Integer> ans = new ArrayList<Integer>(2);
            while (i < j) {
                int aa = a[i] + a[j];
                if (aa == s) {
                    if (ans.size() == 0) {
                        ans.add(a[i]);
                        ans.add(a[j]);
                    } else {
                        int m1 = ans.get(0) * ans.get(1);
                        int m2 = a[i] * a[j];
                        if (m1 > m2) {
                            ans.set(0, a[i]);
                            ans.set(1, a[j]);
                        }
                    }
                    i ++;
                    j --;
                } else if (aa < s) {
                    i ++;
                } else {
                    j --;
                }
            }
            return ans;
        }
    }
}
