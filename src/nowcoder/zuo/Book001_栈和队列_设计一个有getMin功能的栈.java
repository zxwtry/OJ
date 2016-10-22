package nowcoder.zuo;

import java.util.Stack;

public class Book001_栈和队列_设计一个有getMin功能的栈 {
	public static void main(String[] args) {
		test();
	}
	static void test() {
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(9000, 0, 10000);
		tools.Utils.printArray(arr, 100);
		int[] min = new int[arr.length];
		Stack<Integer> standard = new Stack<>();
		for (int i = 0; i < arr.length; i ++) {
			standard.push(arr[i]);
		}
		boolean isEqual = true;
		for (int i = 0; i < arr.length; i ++) {
			if (0 == i) {
				min[i] = arr[i];
			} else {
				min[i] = Math.min(min[i - 1], arr[i]);
			}
		}
		Solution1 s = new Solution1();
		for (int val : arr) {
			s.push(val);
		}
		while (! standard.isEmpty()) {
			int index = standard.size() - 1;
			int popStandard = standard.pop();
			int minStandard = min[index];
			int minSolution = s.getMin();
			int popSolution = s.pop();
			isEqual &= popSolution == popStandard;
			isEqual &= minSolution == minStandard;
			System.out.println(popStandard + "..." + popSolution);
			System.out.println(minStandard + "..." + minSolution);
		}
		System.out.println(isEqual);
	}
	static class Solution1 {
		Stack<Integer> stk = new Stack<>();
		Stack<Integer> min = new Stack<>();
		public int pop() {
			if (this.stk.isEmpty()) {
				throw new RuntimeException("Stack is Empty!");
			}
			//为什么stk.peek() == min.peek()就不对呢？
			if (stk.peek() == this.getMin()) {
				min.pop();
			}
			return stk.pop();
		}
		public void push(int val) {
			if (stk.isEmpty()) {
				stk.push(val);
				min.push(val);
			} else {
				if (val <= min.peek()) {
					min.push(val);
					stk.push(val);
				} else {
					stk.push(val);
				}
			}
		}
		public int getMin() {
			if (min.isEmpty()) {
				throw new RuntimeException("Stack is Empty!");
			}
			return min.peek();
		}
	}
}
