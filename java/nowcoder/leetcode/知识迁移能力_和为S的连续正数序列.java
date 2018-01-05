package nowcoder.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        知识迁移能力_和为S的连续正数序列.java
 * @date        2017年7月1日 下午10:15:16
 * @details     剑指Offer
 */
public class 知识迁移能力_和为S的连续正数序列 {
    static public class Solution {
        public ArrayList<ArrayList<Integer> > FindContinuousSequence(int s) {
            ArrayList<ArrayList<Integer> > ans = new ArrayList<ArrayList<Integer> >();
            int t = 2 * s;
            for (int i = 1; i * i <= t; i ++) {
                if (t % i == 0) {
                    int m = t / i;
                    int max = Math.max(i, m);
                    int min = Math.min(i, m);
                    int b = 0, a = 0;
                    if ((max + min) % 2 == 1) {
                        b = (max + min - 1) / 2;
                        a = max - b;
                        if (a > 0 && b > 0 && a < b) {
                            ArrayList<Integer> one = new ArrayList<>(b -a + 1);
                            for (int v = a; v <= b; v ++) {
                                one.add(v);
                            }
                            ans.add(one);
                        }
                    }
                }
            }
            Collections.sort(ans, new Comparator<ArrayList<Integer>>() {
                @Override
                public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                    return o1.get(0) - o2.get(0);
                }
            });
            return ans;
        }
    }
}
