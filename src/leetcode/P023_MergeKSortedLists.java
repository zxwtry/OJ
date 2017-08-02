package leetcode;

import tools.ListNode辅助.ListNode;

public class P023_MergeKSortedLists {
    
	static class Solution {
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
}

