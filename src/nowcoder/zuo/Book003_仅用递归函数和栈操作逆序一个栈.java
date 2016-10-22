package nowcoder.zuo;

import java.util.Stack;

public class Book003_仅用递归函数和栈操作逆序一个栈 {
	public static void main(String[] args) {
		test();
	}
	static void test() {
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(10, 0, 20);
		Stack<Integer> stack = new Stack<>();
		for (int val : arr) {
			stack.push(val);
		}
		Solution1 s = new Solution1();
		s.reverseStack(stack);
		boolean isAllTrue = true;
		for (int val : arr) {
			int pop = stack.pop();
			isAllTrue &= (val == pop);
			System.out.println(val + "..." + pop);
		}
		System.out.println(isAllTrue);
		
	}
	static class Solution1 {

		public void reverseStack(Stack<Integer> stack) {
			
		}
		
	}
}