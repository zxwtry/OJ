package leetcode;

import java.util.Stack;

/**
 * 	Implement the following operations of a queue using stacks.

	push(x) -- Push element x to the back of queue.
	pop() -- Removes the element from in front of queue.
	peek() -- Get the front element.
	empty() -- Return whether the queue is empty.
	Notes:
	You must use only standard operations of a stack -- 
	which means only push to top, peek/pop from top, size, 
	and is empty operations are valid.
	Depending on your language, stack may not be supported natively. 
	You may simulate a stack by using a list or deque (double-ended queue),
	 as long as you use only standard operations of a stack.
	You may assume that all operations are valid (for example, 
	no pop or peek operations will be called on an empty queue).
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P232_ImplementQueueusingStacks.java
 * @type        P232_ImplementQueueusingStacks
 * @date        2016年12月9日 下午10:10:17
 * @details     MyQueue AC 99ms 63.30%
 */
public class P232_ImplementQueueusingStacks {
	static class MyQueue {
		Stack<Integer> s1 = new Stack<Integer>();
		Stack<Integer> s2 = new Stack<Integer>();
	    // Push element x to the back of queue.
	    public void push(int x) {
	        s1.push(x);
	    }
	    // Removes the element from in front of queue.
	    public void pop() {
	    	if (! s2.isEmpty()) {
	    		s2.pop();
	    		return;
	    	}
	        while (! s1.isEmpty()) {
	        	s2.push(s1.pop());
	        }
	        s2.pop();
	    }
	    // Get the front element.
	    public int peek() {
	    	if (! s2.isEmpty()) {
	    		return s2.peek();
	    	}
	    	while (! s1.isEmpty()) {
	        	s2.push(s1.pop());
	        }
	    	return s2.peek();
	    }
	    // Return whether the queue is empty.
	    public boolean empty() {
	    	if (! s2.isEmpty()) {
	    		return false;
	    	}
	    	while (! s1.isEmpty()) {
	        	s2.push(s1.pop());
	        }
	    	return s2.isEmpty();
	    }
	}
}
