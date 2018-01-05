package nowcoder.leetcode;

import tools.ListNode辅助.ListNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        链表_删除链表中的重复节点.java
 * @date        2017年7月4日 下午7:20:15
 * @details     剑指Offer
 */
public class 链表_删除链表中的重复节点 {
    static public class Solution {
        public ListNode deleteDuplication(ListNode n) {
            if (n == null) return null;
            int v = n.val;
            v -= 1;
            ListNode vh = new ListNode(v);
            vh.next = n;
            ListNode p = vh;
            ListNode t = n;
            while (t != null) {
                
                boolean duplicated = false;
                while (t.next != null && t.next.val == t.val) {
                    t = t.next;
                    duplicated = true;
                }
                if (duplicated) {
                    p.next = t.next;
                    t = t.next;
                } else {
                    p = t;
                    t = t.next;
                }
                
                if (t == null || t.next == null) {
                    return vh.next;
                }
                
            }
            return vh.next;
        }
    }
}
