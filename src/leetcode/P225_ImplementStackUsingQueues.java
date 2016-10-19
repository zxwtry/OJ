package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 	Implement the following operations of a stack using queues.

	push(x) -- Push element x onto stack.
	pop() -- Removes the element on top of the stack.
	top() -- Get the top element.
	empty() -- Return whether the stack is empty.
	Notes:
	You must use only standard operations of a queue -- 
	which means only push to back, peek/pop from front,
	 size, and is empty operations are valid.
	Depending on your language, queue may not be supported natively. 
	You may simulate a queue by using a list or deque (double-ended queue), 
	as long as you use only standard operations of a queue.
	You may assume that all operations are valid 
	(for example, no pop or top operations will be called on an empty stack).
	Update (2015-06-11):
	The class name of the Java function had been updated to MyStack instead of Stack.
 */

public class P225_ImplementStackUsingQueues {
	public static void main(String[] args) {
		MyStack stack = new MyStack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		System.out.println(stack.empty());
	}
	/*	
	 * 	106 ms
	 * 	47.16%
	 */
	static class MyStack {
		Queue<Integer> q1 = new LinkedList<>();
		Queue<Integer> q2 = new LinkedList<>();
	    // Push element x onto stack.
	    public void push(int x) {
	        q1.add(x);
	    }

	    // Removes the element on top of the stack.
	    public void pop() {
	    	int size = q1.size();
	    	for (int i = 1; i < size; i ++) {
	    		q2.add(q1.poll());
	    	}
	    	q1.poll();
	    	for (int i = 1; i < size; i ++) {
	    		q1.add(q2.poll());
	    	}
	    }

	    // Get the top element.
	    public int top() {
	    	int size = q1.size();
	    	for (int i = 1; i < size; i ++) {
	    		q2.add(q1.poll());
	    	}
	    	int top = q1.peek();
	    	q2.add(q1.poll());
	    	for (int i = 0; i < size; i ++) {
	    		q1.add(q2.poll());
	    	}
	    	return top;
	    }

	    // Return whether the stack is empty.
	    public boolean empty() {
	        return q1.size() == 0;
	    }
	}
}
