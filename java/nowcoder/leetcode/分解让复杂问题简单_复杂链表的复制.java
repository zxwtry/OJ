package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        分解让复杂问题简单_复杂链表的复制.java
 * @date        2017年6月30日 下午4:26:04
 * @details     
 */
public class 分解让复杂问题简单_复杂链表的复制 {
    static public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;
        RandomListNode(int label) {
            this.label = label;
        }
    }
    static public class Solution {
        public RandomListNode Clone(RandomListNode p) {
            if (p == null)
                return null;
            RandomListNode t = p;
            RandomListNode s = t;
            RandomListNode v = null;
            while (t != null) {
                s = t.next;
                v = new RandomListNode(t.label);
                t.next = v;
                v.next = s;
                t = s;
            }
            t = p;
            while (t != null) {
                s = t.next;
                if (t.random != null)
                    s.random = t.random.next;
                t = t.next.next;
            }
            t = p;
            RandomListNode ans = t.next;
            while (t != null) {
                s = t.next.next;
                v = t.next;
                if (s != null)
                    v.next = s.next;
                t.next = s;
                t = s;
            }
            return ans;
        }
    }
}
