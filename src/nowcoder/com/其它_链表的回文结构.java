package nowcoder.com;

import tools.ListNode辅助.ListNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        其它_链表的回文结构.java
 * @date        2017年7月20日 下午10:16:44
 * @details     
 */
public class 其它_链表的回文结构 {
    public static void main(String[] args) {
        ListNode A = tools.ListNode辅助.A_一维生成器(new int[] {1, 2, 3, 4, 5, 6});
        System.out.println(new PalindromeList().chkPalindrome(A));
    }
    static public class PalindromeList {
        public boolean chkPalindrome(ListNode A) {
            int len = len(A);
            if (len < 2) return true;
            int half = len / 2;
            ListNode pre = null;
            ListNode now = A;
            ListNode nxt = null;
            for (int i = 0; i < half; i ++) {
                nxt = now.next;
                now.next = pre;
                pre = now;
                now = nxt;
            }
            ListNode right = nxt;
            if (len % 2 == 1) right = right == null ? null : right.next;
            ListNode left = pre;
            if (left == null || right == null) return false;
            while (left != null && right != null) {
                if (left.val != right.val) return false;
                left = left.next;
                right = right.next;
            }
            return left == right;
        }
        int len(ListNode n) {
            int l = 0;
            while (n != null) {
                n = n.next;
                l ++;
            }
            return l;
        }
    }
}
