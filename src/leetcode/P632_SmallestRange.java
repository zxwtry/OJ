package leetcode;

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
    static public class Solution {
        public int[] smallestRange(int[][] n) {
            int nl = n == null ? 0 : n.length;
            if (nl == 0) {
                return new int[] {0, 0};
            }
            PriorityQueue<T> queue = new PriorityQueue<>();
            int curMax = Integer.MIN_VALUE;
            for (int i = 0; i < nl; i++) {
                int[] num = n[i];
                curMax = Math.max(curMax, num[0]);
                int next = num.length > 1 ? num[1] : Integer.MAX_VALUE;
                queue.add(new T(num[0], i, 0, next));
            }
            int[] answer = new int[]{queue.peek().value, curMax};
            while (true) {
                T node = queue.poll();
                int[] newAnswer = new int[]{node.value, curMax};
                if (shorter(newAnswer, answer)) {
                    answer = newAnswer;
                }
                int[] num = n[node.index];
                if (node.arrayIndex + 1 >= num.length) {
                    break;
                }
                int value = num[node.arrayIndex + 1];
                curMax = Math.max(curMax, value);
                int next = node.arrayIndex + 2 < num.length ? num[node.arrayIndex + 2] : Integer.MAX_VALUE;
                queue.add(new T(value, node.index, node.arrayIndex + 1, next));
            }
            return answer;
        }
        private boolean shorter(int[] newAnswer, int[] answer) {
            return newAnswer[1] - newAnswer[0] < answer[1] - answer[0] || (
                    newAnswer[1] - newAnswer[0] == answer[1] - answer[0] && newAnswer[1] < newAnswer[0]
            );
        }
        private static class T implements Comparable<T> {
            int value;
            int index;
            int arrayIndex;
            int next;
            T(int value, int index, int arrayIndex, int next) {
                this.value = value;
                this.index = index;
                this.arrayIndex = arrayIndex;
                this.next = next;
            }
            @Override
            public int compareTo(T o) {
                if (this.value == o.value) {
                    return Integer.compare(this.next, o.next);
                }
                return Integer.compare(this.value, o.value);
            }
        }
    }
}
