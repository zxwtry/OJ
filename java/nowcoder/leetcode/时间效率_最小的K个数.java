package nowcoder.leetcode;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        时间效率_最小的K个数.java
 * @date        2017年7月1日 上午9:50:43
 * @details     剑指Offer
 */
public class 时间效率_最小的K个数 {
    static public class Solution2 {
        public ArrayList<Integer> GetLeastNumbers_Solution(int [] a, int k) {
            int al = a == null ? 0 : a.length;
            ArrayList<Integer> ans = new ArrayList<>(k);
            if (al < k || k == 0) {
                //for (int v : a) ans.add(v);
                return ans;
            }
            
            int[] h = new int[k];
            System.arraycopy(a, 0, h, 0, k);
            buildHeap(h, k);
            for (int i = k; i < al; i ++) {
                if (a[i] < h[0]) {
                    h[0] = a[i];
                    downHeap(h, 0, k);
                }
            }
            for (int i = k-1; i > -1; i --) {
                ans.add(h[i]);
            }
            return ans;
        }
        //大堆
        void buildHeap(int[] h, int hl) {
            //2 * i + (1 / 2)
            for (int i = (hl - 1) / 2; i > -1; i --) {
                downHeap(h, i, hl);
            }
        }
        void downHeap(int[] h, int p, int hl) {
            while(true) {
                int c = 2 * p + 1;
                if (c >= hl) break;
                if (c + 1 < hl && h[c + 1] > h[c]) c ++;
                if (h[c] > h[p]) {
                    swap(h, c, p);
                    p = c;
                } else break;
            }
        }
        void swap(int[] h, int i, int j) {
            int t = h[i];
            h[i] = h[j];
            h[j] = t;
        }
    }
    //jdk7 不支持这种PriorityQueue的构造方法
    static public class Solution {
        private static class T implements Comparable<T> {
            int val;
            T(int val) {
                this.val = val;
            }
            @Override
            public int compareTo(T o) {
                return Integer.compare(o.val, this.val);
            }
        }
        public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
            PriorityQueue<T> pq = new PriorityQueue<T>();
            for (int v : input) {
                pq.add(new T(v));
                if (pq.size() > k) {
                    pq.poll();
                }
            }
            ArrayList<Integer> ans = new ArrayList<Integer>(k);
            for (int i = 0; i < k; i ++) ans.add(0);
            for (int i = k - 1; i > -1; i --) ans.set(i, pq.poll().val);
            return ans;
        }
    }
}
