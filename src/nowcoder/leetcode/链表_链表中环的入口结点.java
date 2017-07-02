package nowcoder.leetcode;

import tools.ListNode辅助.ListNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        链表_链表中环的入口结点.java
 * @date        2017年7月2日 下午9:31:23
 * @details     剑指Offer
 */
public class 链表_链表中环的入口结点 {
    static public class Solution {
        public ListNode EntryNodeOfLoop(ListNode n) {
            if (n == null)
                return null;
            ListNode f = n.next;
            ListNode s = n.next;
            f = f == null ? null : f.next;
            while (f != s) {
                f = f.next;
                if (f == null)
                    return null;
                f = f.next;
                s = s.next;
            }
            if (f == null)
                return null;
            f = n;
            while (f != s) {
                f = f.next;
                s = s.next;
            }
            return f;
        }
    }
}
