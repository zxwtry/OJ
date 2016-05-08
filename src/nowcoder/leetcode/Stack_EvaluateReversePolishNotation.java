package nowcoder.leetcode;

import java.util.Stack;

public class Stack_EvaluateReversePolishNotation {
	public static void main(String[] args) {
//		String[] tokens = {"2", "1", "+", "3", "*"};
		String[] tokens = {"3","-4","+"};
		System.out.println(evalRPN(tokens));
	}
    public static int evalRPN(String[] tokens) {
    	Stack<Integer> stack = new Stack<Integer>();
    	for (int index = 0; index < tokens.length; index ++) {
    		switch(tokens[index]) {
    		case "+":
    			stack.add(stack.pop() + stack.pop());
    			break;
    		case "-":
    			stack.add(-stack.pop() + stack.pop());
    			break;
    		case "*":
    			stack.add(stack.pop() * stack.pop());
    			break;
    		case "/":
    			int a1 = stack.pop();
    			int a2 = stack.pop();
    			stack.add(a2/a1);
    			break;
    		default:
    			stack.add(Integer.parseInt(tokens[index]));
    			break;
    		}
    	}
        return stack.pop();
    }
}
