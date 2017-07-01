package nowcoder.leetcode;

import java.util.HashSet;

import tools.ListNode辅助.ListNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        时间空间效率的平衡_两个链表的第一个公共结点.java
 * @date        2017年7月1日 下午3:52:41
 * @details     剑指Offer
 */
public class 时间空间效率的平衡_两个链表的第一个公共结点 {
    static public class Solution {
        public ListNode FindFirstCommonNode(ListNode p1, ListNode p2) {
            if (p1 == p2)
                return p1;
            if (p1 == null || p2 == null)
                return null;
            HashSet<ListNode> s1 = new HashSet<ListNode>();
            HashSet<ListNode> s2 = new HashSet<ListNode>();
            ListNode t1 = p1, t2 = p2;
            while (t1 != null || t2 != null) {
                if (t1 == t2) return t1;
                if (t2 != null)
                    if (s1.contains(t2))
                        return t2;
                if (t1 != null)
                    if (s2.contains(t1))
                        return t1;

                if (t2 != null && t1 != null)
                    if (s1.contains(t1) && s2.contains(t2))
                        return null;

                if (t1 != null)
                    s1.add(t1);
                if (t2 != null)
                    s2.add(t2);

                if (t1 != null)
                    t1 = t1.next;
                if (t2 != null)
                    t2 = t2.next;
            }
            return null;
        }
    }
    static class Solution2 {
        public ListNode FindFirstCommonNode(ListNode p1, ListNode p2) {
            if (p1 == p2) return p1;
            if (p1 == null || p2 == null) return null;
            ListNode l1 = loop(p1);
            ListNode l2 = loop(p2);
            if (l1 == null && l2 == null) {
                //两个都无环
                int n1 = len(p1, null);
                int n2 = len(p2, null);
                return noloop(p1, n1, p2, n2);
            }
            if ((l1 == null) ^ (l2 == null)) {
                return null;
            }
            if (l1 != null && l2 != null) {
                if (l1 == l2) {
                    int n1 = len(p1, l1);
                    int n2 = len(p2, l2);
                    return noloop(p1, n1, p2, n2);
                } else {
                    boolean one = false;
                    ListNode t1 = l1.next;
                    while (t1 != l1 && ! one) {
                        if (t1 == l2) {
                            one = true;
                        } else {
                            t1 = t1.next;
                        }
                    }
                    return one ? l1 : null;
                }
            }
            return null;
        }
        ListNode noloop(ListNode p1, int len1, ListNode p2, int len2) {
            if (len1 > len2) {
                return noloop(p2, len2, p1, len1);
            }
            for (int k = len1; k < len2; k ++) p2 = p2.next;
            while (p1 != p2) {
                p1 = p1.next;
                p2 = p2.next;
            }
            return p1;
        }
        int len(ListNode n, ListNode stop) {
            int len = 0;
            while (n != stop) {
                len ++;
                n = n.next;
            }
            return len;
        }
        ListNode loop(ListNode n) {
            if (n == null) return null;
            ListNode f = n.next == null ? null : n.next.next;
            ListNode s = n.next;
            while (f != s) {
                f = f.next == null ? null : f.next.next;
                if (f == null) return null;
                s = s.next;
            }
            f = n;
            while (f != s) {
                f = f.next;
                s = s.next;
            }
            return f;
        }
    }
}
