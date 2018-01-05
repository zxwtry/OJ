package nowcoder.zuo;

import java.util.Stack;

public class Book003_仅用递归函数和栈操作逆序一个栈 {
	public static void main(String[] args) {
		test();
//		debug();
	}
	static void debug() {
		Solution1 s = new Solution1();
		int[] arr = {1, 2, 3, 4, 5};
		Stack<Integer> stack = new Stack<>();
		for (int val : arr) {
			stack.push(val);
		}
		System.out.println(s.getAndRemoveStackBottom(stack));
	}
	static void test() {
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(10, 0, 20);
		Stack<Integer> stack = new Stack<>();
		for (int val : arr) {
			stack.push(val);
		}
		Solution1 s = new Solution1();
		s.reverse(stack);
		boolean isAllTrue = true;
		for (int val : arr) {
			int pop = stack.pop();
			isAllTrue &= (val == pop);
			System.out.println(val + "..." + pop);
		}
		System.out.println(isAllTrue);
		
	}
	static class Solution1 {

		//外部保证stack不是Empty
		int getAndRemoveStackBottom(Stack<Integer> stack) {
			int stackValue = stack.pop();
			if (stack.isEmpty()) {
				return stackValue;
			} else {
				int last = getAndRemoveStackBottom(stack);
				stack.push(stackValue);
				return last;
			}
		}
		
		void reverse(Stack<Integer> stack) {
			if (! stack.isEmpty()) {
				int i = getAndRemoveStackBottom(stack);
				reverse(stack);
				stack.push(i);
			}
		}
	}
}