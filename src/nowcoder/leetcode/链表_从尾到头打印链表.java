package nowcoder.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;

import tools.ListNode辅助.ListNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        链表_从尾到头打印链表.java
 * @date        2017年6月29日 下午9:19:20
 * @details     剑指offer
 */
public class 链表_从尾到头打印链表 {
    public class Solution {
        public ArrayList<Integer> printListFromTailToHead(ListNode l) {
            LinkedList<Integer> ll = new LinkedList<>();
            while (l != null) {
                ll.addFirst(l.val);
                l = l.next;
            }
            ArrayList<Integer> a = new ArrayList<>(ll.size());
            for (Integer v : ll) {
                a.add(v);
            } 
            return a;
        }
    }
}
