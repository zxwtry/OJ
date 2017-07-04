package nowcoder.leetcode;

import java.util.PriorityQueue;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        树_数据流中的中位数.java
 * @date        2017年7月4日 下午9:04:03
 * @details     
 */
public class 树_数据流中的中位数 {
    static public class Solution {
        PriorityQueue<Integer> min = new PriorityQueue<Integer>();
        PriorityQueue<A> max = new PriorityQueue<A>();
        boolean isDan = true;
        public void Insert(Integer num) {
            if (isDan) {
                min.add(num);
            } else {
                max.add(new A(num));
            }
            while (max.size() != 0 && min.peek() < max.peek().val) {
                Integer minV = min.poll();
                Integer maxV = max.poll().val;
                max.add(new A(minV));
                min.add(maxV);
            }
            isDan = ! isDan;
        }

        public Double GetMedian() {
            if (! isDan) {
                return (double)min.peek().intValue();
            } else {
                double v = min.peek().intValue();
                v += max.peek().val.intValue();
                return v / 2;
            }
        }
        //大堆
        static class A implements Comparable<A> {
            Integer val;
            public A(Integer val) {
                this.val = val;
            }
            @Override
            public int compareTo(A a) {
                return Integer.compare(a.val, this.val);
            }
        }
    }
}
