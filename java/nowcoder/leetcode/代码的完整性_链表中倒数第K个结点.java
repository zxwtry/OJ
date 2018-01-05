package nowcoder.leetcode;

import tools.ListNode辅助.ListNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        代码的完整性_链表中倒数第K个结点.java
 * @date        2017年6月30日 上午9:53:08
 * @details     剑指Offer
 */
public class 代码的完整性_链表中倒数第K个结点 {
    static public class Solution {
        public ListNode FindKthToTail(ListNode h,int k) {
            ListNode p = h;
            if (k < 1) return null;
            int c = 0;
            while (p != null) {
                c ++;
                p = p.next;
                if (c == k) break;
            }
            if (p == null) return c== k ?  h : null;
            while (p != null) {
                p = p.next;
                h = h.next;
            }
            return h;
        }
    }
}
