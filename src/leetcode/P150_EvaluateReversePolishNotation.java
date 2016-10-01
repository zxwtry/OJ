package leetcode;

import java.util.Stack;

/*
 * 	Evaluate the value of an arithmetic expression in Reverse Polish Notation.

	Valid operators are +, -, *, /. Each operand may be an integer or another expression.
	
	Some examples:
	  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
	  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */

public class P150_EvaluateReversePolishNotation {
	public static void main(String[] args) {
		String[] input = null;
		input = new String[] {"4", "13", "5", "/", "+"};
		Solution s = new Solution();
		System.out.println(s.evalRPN(input));
	}
	/*
	 * 	 13 ms
	 * 	 70.79%
	 */
	static class Solution {
	    public int evalRPN(String[] tokens) {
	    	Stack<Integer> stk = new Stack<>();
	    	int v1 = 0;
	    	int v2 = 0;
	    	for (String s : tokens) {
	    		switch (s) {
				case "+":
					v1 = stk.pop();
					v2 = stk.pop();
					stk.push(v1 + v2);
					break;
				
				case "-":
					v1 = stk.pop();
					v2 = stk.pop();
					stk.push(v2 - v1);
					break;
				
				case "*":
					v1 = stk.pop();
					v2 = stk.pop();
					stk.push(v1 * v2);
					break;
				
				case "/":
					v1 = stk.pop();
					v2 = stk.pop();
					stk.push(v2 / v1);
					break;
					
				default:
					stk.push(Integer.parseInt(s));
					break;
				}
	    	}
	        return stk.pop();
	    }
	    
	}
}
