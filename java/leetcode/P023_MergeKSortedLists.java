package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

import tools.ListNode辅助.ListNode;

public class P023_MergeKSortedLists {
    
	static class Solution1 {
        public ListNode mergeKLists(ListNode[] ls) {
            int ln = ls == null ? 0 : ls.length;
            if (ln == 0) return null;
            return merge(ls, ln);
        }

        private ListNode merge(ListNode[] ls, int ln) {
            int k = ln - 1;
            while (k != 0) {
                merge(ls, 0, k);
                k = k / 2;
            }
            return ls[0];
        }

        private void merge(ListNode[] ls, int i, int j) {
            if (i == j) return;
            while (j > i) {
                if (ls[i] == null) {
                    ls[i] = ls[j];
                } else if (ls[j] != null) {
                    if (ls[i].val > ls[j].val) 
                        swap(ls, i, j);
                    merge(ls[i], ls[j]);
                }
                i ++;
                j --;
            }
        }
        
        void merge(ListNode l, ListNode r) {
            ListNode lc = l.next;
            ListNode rc = r;
            ListNode p = l;
            while (lc != null && rc != null) {
                if (lc.val < rc.val) {
                    p.next = lc;
                    p = lc;
                    lc = lc.next;
                } else {
                    p.next = rc;
                    p = rc;
                    rc = rc.next;
                }
            }
            if (lc == null) p.next = rc;
            if (rc == null) p.next = lc;
        }
        
        void swap(ListNode[] ls, int i, int j) {
            ListNode tmp = ls[i];
            ls[i] = ls[j];
            ls[j] = tmp;
        }
	}
	
	static class Solution2 {
        public ListNode mergeKLists(final ListNode[] ls) {
            int ln = ls == null ? 0 : ls.length;
            if (ln == 0) return null;
            PriorityQueue<Integer> pq = new PriorityQueue<>(ln, new Comparator<Integer>() {
                @Override
                public int compare(Integer ai, Integer bi) {
                    ListNode a = ls[ai];
                    ListNode b = ls[bi];
                    return Integer.compare( a == null ? Integer.MAX_VALUE : a.val, 
                                            b == null ? Integer.MAX_VALUE : b.val);
                }
            });
            for (int i = 0; i < ls.length; i ++) pq.add(i);
            int i = pq.poll();
            ListNode ans = ls[i];
            if (ans == null) return null;
            ls[i] = ls[i].next;
            pq.add(i);
            ListNode t = ans;
            while (true) {
                i = pq.poll();
                ListNode now = ls[i];
                if (now == null) break;
                ls[i] = ls[i].next;
                pq.add(i);
                t.next = now;
                t = now;
            }
            return ans;
        }
	}
	
}

