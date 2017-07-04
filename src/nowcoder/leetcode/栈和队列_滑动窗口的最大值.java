package nowcoder.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        栈和队列_滑动窗口的最大值.java
 * @date        2017年7月4日 下午9:27:31
 * @details     剑指Offer
 */
public class 栈和队列_滑动窗口的最大值 {
    static public class Solution {
        public ArrayList<Integer> maxInWindows(int [] n, int z) {
            int len = n == null ? 0 : n.length;
            if (len == 0 || z > len || z == 0) return new ArrayList<Integer>(0);
            ArrayList<Integer> ans = new ArrayList<>(len );
            LinkedList<Integer> ll = new LinkedList<>();
            for (int i = 0; i < z; i ++) {
                while (ll.size() != 0 && n[i] >= n[ll.peekLast()]) {
                    ll.pollLast();
                }
                ll.addLast(i);
            }
            ans.add(n[ll.peekFirst()]);
            for (int i = z; i < len; i ++) {
                if (i - ll.peekFirst() >= z) {
                    ll.pollFirst();
                }
                while (ll.size() != 0 && n[i] >= n[ll.peekLast()]) {
                    ll.pollLast();
                }
                ll.addLast(i);
                ans.add(n[ll.peekFirst()]);
            }
            return ans;
        }
    }
}
