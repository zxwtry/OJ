package nowcoder.zuo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Book002_由两个栈组成的队列 {
	public static void main(String[] args) {
		test();
	}
	private static void test() {
		Solution1 s = new Solution1();
		Queue<Integer> stk = new LinkedList<>();
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(10000, 0, 10000);
		for (int val : arr) {
			stk.add(val);
			s.add(val);
		}
		boolean isTrue = true;
		while (! stk.isEmpty()) {
			int peekSolution = s.peek();
			int peekQueue = stk.peek();
			int pollSolution = s.poll();
			int pollQueue = stk.poll();
			System.out.println(peekQueue + "..." + peekSolution);
			System.out.println(pollQueue + "..." + pollSolution);
			isTrue &= peekSolution == peekQueue;
			isTrue &= pollSolution == pollQueue;
		}
		isTrue &= s.isEmpty();
		if (! isTrue) {
			tools.Utils.printArray(arr, 100);
		}
		System.out.println(isTrue);
	}
	static class Solution1 {
		Stack<Integer> stk1 = new Stack<>();
		Stack<Integer> stk2 = new Stack<>();
		public void add(int val) {
			stk1.add(val);
		}
		public int poll() {
			if (stk2.isEmpty()) {
				while (! stk1.isEmpty()) {
					stk2.push(stk1.pop());
				}
			}
			if (stk2.isEmpty()) {
				throw new RuntimeException("Stack is Empty!");
			}
			return stk2.pop();
		}
		public int peek() {
			if (stk2.isEmpty()) {
				while (! stk1.isEmpty()) {
					stk2.push(stk1.pop());
				}
			}
			if (stk2.isEmpty()) {
				throw new RuntimeException("Stack is Empty!");
			}
			return stk2.peek();
		}
		public boolean isEmpty() {
			return stk1.isEmpty();
		}
	}
}
