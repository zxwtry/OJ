package nowcoder.leetcode;

import tools.ListNode辅助.ListNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        代码的鲁棒性_合并两个排序的链表.java
 * @date        2017年6月30日 上午10:08:56
 * @details     剑指Offer
 */
public class 代码的鲁棒性_合并两个排序的链表 {
    public class Solution {
        public ListNode Merge(ListNode l,ListNode r) {
            if (l == null || r == null) return l != null ? l : r;
            ListNode a = null, n = null;
            if (l.val < r.val) {
                a = l;
                l = l.next;
            } else {
                a = r;
                r = r.next;
            }
            n = a;
            while (r != null && l != null) {
                if (l.val < r.val) {
                    n.next = l;
                    l = l.next;
                } else {
                    n.next = r;
                    r = r.next;
                }
                n = n.next;
            }
            if (r != null) n.next = r;
            if (l != null) n.next = l;
            return a;
        }
    }
}
