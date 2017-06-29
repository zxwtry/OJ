package nowcoder.leetcode;

import java.util.Stack;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        栈和队列_两个栈实现一个队列.java
 * @date        2017年6月29日 下午9:31:45
 * @details     剑指offer
 */
public class 栈和队列_两个栈实现一个队列 {
    static public class Solution {
        Stack<Integer> s1 = new Stack<Integer>();       //
        Stack<Integer> s2 = new Stack<Integer>();       //辅助
        
        public void push(int n) {
            s1.push(n);
        }
        
        public int pop() {
            if (s2.size() == 0) {
                while (s1.size() != 0) {
                    s2.push(s1.pop());
                }
            }
            return s2.pop();
        }
    }
}
