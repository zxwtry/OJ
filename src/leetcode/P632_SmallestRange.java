package leetcode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**

You have k lists of sorted integers in ascending order. 
Find the smallest range that includes at least one number from each of the k lists.

We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

Example 1:
Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
Output: [20,24]
Explanation: 
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].
Note:
The given list may contain duplicates, so ascending order means >= here.
1 <= k <= 3500
-105 <= value of elements <= 105.

 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P632_SmallestRange.java
 * @date        2017年7月2日 上午11:07:53
 * @details     
 */
public class P632_SmallestRange {
    static class Solution {
        public int[] smallestRange(final List<List<Integer>> n) {
            int r = n == null ? 0 : n.size();
            if (r == 0) return new int[] {0, 0};
            final int[] rs = new int[r];
            PriorityQueue<Integer> pq = new PriorityQueue<>(r, 
                    new Comparator<Integer>() {
                        @Override
                        public int compare(Integer a, Integer b) {
                            if (rs[a] == n.get(a).size()) return -1;
                            if (rs[b] == n.get(b).size()) return 1;
                            return Integer.compare(n.get(a).get(
                                    rs[a]), n.get(b).get(rs[b]));
                        }
                    });
            int a1 = Integer.MAX_VALUE, a2 = Integer.MIN_VALUE;
            //a1最小  a2最大
            for (int i = 0; i < r; i ++) {
                a1 = Math.min(a1, n.get(i).get(0));
                a2 = Math.max(a2, n.get(i).get(0));
                pq.add(i);
            }
            int max = a2;
            int ans = a2 - a1;
            while (true) {
                int min = pq.poll();
                rs[min] ++;
                if (rs[min] == n.get(min).size()) break;
                max = Math.max(max, n.get(min).get(rs[min]));
                pq.add(min);
                int peek = pq.peek();
                if (max - n.get(peek).get(rs[peek]) < ans) {
                    a2 = max;
                    a1 = n.get(peek).get(rs[peek]);
                    ans = a2 - a1;
                } 
            }
            return new int[] {a1, a2};
        }
    }
}
