package nowcoder.leetcode;

import java.util.Stack;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        举例让抽象具体化_包含min函数的栈.java
 * @date        2017年6月30日 上午10:45:33
 * @details     剑指Offer
 */
public class 举例让抽象具体化_包含min函数的栈 {
    public class Solution {

        Stack<Integer> s = new Stack<Integer>();
        Stack<Integer> m = new Stack<>();
        public void push(int n) {
            s.push(n);
            if (m.size() == 0 || m.peek() >= n) {
                m.push(n);
            } else {
                m.push(m.peek());
            }
        }
        
        public void pop() {
            s.pop();
            m.pop();
        }
        
        public int top() {
            return s.peek();
        }
        
        public int min() {
            return m.peek();
        }
    }
}
