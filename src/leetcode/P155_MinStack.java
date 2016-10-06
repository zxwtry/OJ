package leetcode;

import java.util.Stack;

/*
 * 	Design a stack that supports push, pop, top, 
 * 	and retrieving the minimum element in constant time.

	push(x) -- Push element x onto stack.
	pop() -- Removes the element on top of the stack.
	top() -- Get the top element.
	getMin() -- Retrieve the minimum element in the stack.
	Example:
	MinStack minStack = new MinStack();
	minStack.push(-2);
	minStack.push(0);
	minStack.push(-3);
	minStack.getMin();   --> Returns -3.
	minStack.pop();
	minStack.top();      --> Returns 0.
	minStack.getMin();   --> Returns -2.
 */

public class P155_MinStack {
	public static void main(String[] args) {
		int i = 0;
		MinStack minStack = new MinStack();
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		i = minStack.getMin();   //--> Returns -3.
		System.out.println(i);
		minStack.pop();
		i = minStack.top();      //--> Returns 0.
		System.out.println(i);
		i = minStack.getMin();   //--> Returns -2.
		System.out.println(i);
	}
	/*
	 * 	117 ms
	 * 	53.24%
	 */
	static class MinStack {
		Stack<Integer> min = null;
		Stack<Integer> stk = null;
		
		public MinStack() {
	        min = new Stack<Integer>();
	        stk = new Stack<Integer>();
	    }
	    
	    public void push(int x) {
	        stk.push(x);
	        if (min.isEmpty() || min.peek() > x) {
	        	min.push(x);
	        } else {
	        	min.push(min.peek());
	        }
	    }
	    
	    public void pop() {
	        min.pop();
	        stk.pop();
	    }
	    
	    public int top() {
	        return stk.peek();
	    }
	    
	    public int getMin() {
	    	return min.peek(); 
	    }
	}
}
