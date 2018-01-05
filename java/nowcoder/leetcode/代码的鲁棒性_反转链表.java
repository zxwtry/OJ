package nowcoder.leetcode;

import tools.ListNode辅助.ListNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        代码的鲁棒性_反转链表.java
 * @date        2017年6月30日 上午10:08:10
 * @details     剑指Offer
 */
public class 代码的鲁棒性_反转链表 {
    static public class Solution {
        public ListNode ReverseList(ListNode h) {
            if (h == null) return null;
            ListNode p = null;
            ListNode n = h;
            ListNode s = h.next;
            while (n != null) {
                s = n.next;
                n.next = p;
                p = n;
                n = s;
            }
            return p;
        }
    }
}
