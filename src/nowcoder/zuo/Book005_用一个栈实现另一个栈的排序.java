package nowcoder.zuo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class Book005_用一个栈实现另一个栈的排序 {
	public static void main(String[] args) {
//		testDescend();
		testAscend();
	}
	static void testAscend() {
		for (int len = 0; len < 1000; len ++) {
			int[] arr = tools.Random随机生成器.A_生成一个随机数据(100, 0, 10000);
			for (int i = 0; i < arr.length; i ++) {
				arr[i] = len;
			}
			Stack<Integer> stack = new Stack<>();
			for (int val : arr) {
				stack.push(val);
			}
			Arrays.sort(arr);
			SolutionAscend s = new SolutionAscend();
			s.sortStackByStack(stack);
			boolean isTrue = true;
			for (int val : arr) {
				int stackPop = stack.pop();
				isTrue &= val == stackPop;
				System.out.println(val + "..." + stackPop);
			}
			System.out.println(isTrue);
		}
	}
	static void testDescend() {
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(100, 0, 10000);
		Stack<Integer> stack = new Stack<>();
		for (int val : arr) {
			stack.push(val);
		}
		Arrays.sort(arr);
		int startIndex = 0, endIndex = arr.length - 1;
		while (startIndex < endIndex) {
			int temp = arr[startIndex];
			arr[startIndex] = arr[endIndex];
			arr[endIndex] = temp;
			startIndex ++;
			endIndex --;
		}
		SolutionDescend s = new SolutionDescend();
		s.sortStackByStack(stack);
		boolean isTrue = true;
		for (int val : arr) {
			int stackPop = stack.pop();
			isTrue &= val == stackPop;
			System.out.println(val + "..." + stackPop);
		}
		System.out.println(isTrue);
	}
	static class SolutionDescend {
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
	static class SolutionAscend {
		public void sortStackByStack(Stack<Integer> stack) {
			Stack<Integer> help = new Stack<>();
			while (! stack.isEmpty()) {
				int cur = stack.pop();
				while(! help.isEmpty() && help.peek() > cur) {
					stack.push(help.pop());
				}
				help.push(cur);
			}
			while(! help.isEmpty()) {
				stack.push(help.pop());
			}
		}
	}
	static class MyComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}
	}
}
