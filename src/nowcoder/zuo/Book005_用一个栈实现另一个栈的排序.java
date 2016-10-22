package nowcoder.zuo;

import java.util.Stack;

public class Book005_用一个栈实现另一个栈的排序 {
	public static void main(String[] args) {
		test();
	}
	private static void test() {
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(100, 0, 10000);
		Stack<Integer> stack = new Stack<>();
		Solution s = new Solution();
		s.sortStackByStack(stack);
		boolean isTrue = true;
		for (int val : arr) {
			isTrue &= val == stack.pop();
		}
		System.out.println(isTrue);
	}
	static class Solution {
		public void sortStackByStack(Stack<Integer> stack) {
			Stack<Integer> help = new Stack<>();
			while (! stack.isEmpty()) {
				int cur = stack.pop();
				while(! help.isEmpty() && help.peek() < cur) {
					stack.push(help.pop());
				}
				help.push(cur);
			}
			while(! help.isEmpty()) {
				stack.push(help.pop());
			}
		}
	}
}
