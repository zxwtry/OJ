package leetcode;

import tools.ListNode辅助.ListNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P147_InsertionSortList.java
 * @date        2017年5月31日 下午8:08:05
 * @details     AC 35ms 79.98%
 */
public class P147_InsertionSortList {
    static class Solution {
        public ListNode insertionSortList(ListNode h) {
            ListNode h1 = null, h2 = h;         //h1已经排好序的头 //h2没有动的头
            ListNode tt = null, tp = null;      //tt是对h1的遍历，tp保存tt上一个位置
            ListNode t = null;                  //t是从h2中取出的头
            while (h2 != null) {
                t = h2;
                h2 = h2.next;
                if (h1 == null) {
                    h1 = t;
                    t.next = null;
                    continue;
                }
                tp = null;
                tt = h1;
                while (tt != null && tt.val < t.val) {
                    tp = tt;
                    tt = tt.next;
                }
                if (tp == null) {
                    t.next = h1;
                    h1 = t;
                } else {
                    t.next = tp.next;
                    tp.next = t;
                }
            }
            return h1;
        }
    }
}
