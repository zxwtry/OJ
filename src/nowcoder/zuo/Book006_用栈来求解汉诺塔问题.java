package nowcoder.zuo;

import java.util.Stack;


public class Book006_用栈来求解汉诺塔问题 {
	public static void main(String[] args) {
		String left = "左";
		String mid = "中";
		String right = "右";
		int num = 3;
//		debugSolution(num, left, mid, right);
//		debugSolutionNoneRecur(num, left, mid, right);
//		testRecursionAndNoneRecursion(num, left, mid, right);
//		debugStandardSolutionNoneRecursion(num, left, mid, right);
//		debugStandardSolution(num, left, mid, right);
//		debugStandardSolutionNoneRecursionClassBlog(num, left, mid, right);
		testStandardSolutionAndStandardSolutionNoneRecursionClassBlog(num, left, mid, right);
	}
	static void testStandardSolutionAndStandardSolutionNoneRecursionClassBlog(int num, String left, String mid,
			String right) {
		StandardSolutionNoneRecursionClassBlog ssnrcb = new StandardSolutionNoneRecursionClassBlog();
		int ssnrcbCount = ssnrcb.hanoi(num, left, mid, right);
		String ssnrcbString = ssnrcb.st.toString();
		StandardSolution ss = new StandardSolution();
		int ssCount = ss.hanoi(num, left, mid, right);
		String ssString = ss.st.toString();
		System.out.println("两者次数是否相等 : " + (ssnrcbCount == ssCount));
		System.out.println("两者比较是否相等 : " + (ssnrcbString.equals(ssString)));
		
	}
	static void debugStandardSolutionNoneRecursionClassBlog(int num, String left, String mid, String right) {
		StandardSolutionNoneRecursionClassBlog s = new StandardSolutionNoneRecursionClassBlog();
		s.hanoi(num, left, mid, right);
	}
	static void debugStandardSolutionNoneRecursion(int num, String left, String mid, String right) {
		StandardSolutionNoneRecursion ssnr = new StandardSolutionNoneRecursion();
	}
	static void testRecursionAndNoneRecursion(int num, String left, String mid, String right) {
		Solution s = new Solution();
		int sAnswer = s.hanoi(num, left, mid, right);
		String sStringAnswer = s.st.toString();
		SolutionNoneRecur snr = new SolutionNoneRecur();
		int snrAnswer = snr.hanoi(num, left, mid, right);
		String snrStringAnswer = snr.st.toString();
		System.out.println("两者次数是否相等 : " + (sAnswer == snrAnswer));
		System.out.println("两者比较是否相等 : " + (sStringAnswer.equals(snrStringAnswer)));
		
	}
	static void debugSolutionNoneRecur(int num, String left, String mid, String right) {
		SolutionNoneRecur s = new SolutionNoneRecur();
		int hanoi = s.hanoi(num, left, mid, right);
		System.out.println("hanoi : " + hanoi);
	}
	static void debugSolution(int num, String left, String mid, String right) {
		Solution s = new Solution();
		int hanoi = s.hanoi(num, left, mid, right);
		System.out.println("hanoi : " + hanoi);
	}
	static void debugStandardSolution(int num, String left, String mid, String right) {
		StandardSolution ss = new StandardSolution();
		ss.hanoi(num, left, mid, right);
	}
	/*
	 * 	修改游戏规则， 
	 * 		现在限制，不能从最左边的塔直接移动到最右边的塔
	 * 			同时，不能从最右边的塔直接移动到最左边的塔
	 */
	static class Solution {
		StringBuilder st = new StringBuilder();
		public int hanoi(int num, String left, String mid, String right) {
			if (num < 1) {
				return 0;
			}
			return process(num, left, mid, right, left, right);
		}

		private int process(int num, String left, String mid, String right, String from, String to) {
			if (num == 1) {
				if (from.equals(mid) || to.equals(mid)) {
					System.out.printf("Move 1 from %s to %s\r\n", from, to);
					st.append(String.format("Move 1 from %s to %s\r\n", from, to));
					return 1;
				} else {
					System.out.printf("Move 1 from %s to %s\r\n", from, mid);
					st.append(String.format("Move 1 from %s to %s\r\n", from, mid));
					System.out.printf("Move 1 from %s to %s\r\n", mid, to);
					st.append(String.format("Move 1 from %s to %s\r\n", mid, to));
					return 2;
				}
			} else {
				if (from.equals(mid) || to.equals(mid)) {
					String another = (from.equals(left) || to.equals(left)) ? right : left;
					int part1 = process(num - 1, left, mid, right, from, another);
					int part2 = 1;
					System.out.printf("Move %d from %s to %s\r\n", num, from, to);
					st.append(String.format("Move %d from %s to %s\r\n", num, from, to));
					int part3 = process(num - 1, left, mid, right, another, to);
					return part1 + part2 + part3;
				} else {
					int part1 = process(num - 1, left, mid, right, from, to);
					int part2 = 1;
					System.out.printf("Move %d from %s to %s\r\n", num, from, mid);
					st.append(String.format("Move %d from %s to %s\r\n", num, from, mid));
					int part3 = process(num - 1, left, mid, right, to, from);
					int part4 = 1;
					System.out.printf("Move %d from %s to %s\r\n", num, mid, to);
					st.append(String.format("Move %d from %s to %s\r\n", num, mid, to));
					int part5 = process(num - 1, left, mid, right, from, to);
					return part1 + part2 + part3 + part4 + part5;
				}
			}
		}
	}
	/*
	 * 	修改游戏规则， 
	 * 		现在限制，不能从最左边的塔直接移动到最右边的塔
	 * 			同时，不能从最右边的塔直接移动到最左边的塔
	 * 		同时不能使用递归来做
	 */
	static class SolutionNoneRecur {
		StringBuilder st = new StringBuilder();
		public enum Action {
			NO, LToM, MToL, MToR, RToM
		}
		
		public int hanoi(int num, String left, String mid, String right) {
			if (num < 1) {
				return 0;
			}
			Stack<Integer> lS = new Stack<>();
			Stack<Integer> mS = new Stack<>();
			Stack<Integer> rS = new Stack<>();
			lS.push(Integer.MAX_VALUE);
			mS.push(Integer.MAX_VALUE);
			rS.push(Integer.MAX_VALUE);
			for (int i = num; i > 0; i --) {
				lS.push(i);
			}
			Action[] record = {Action.NO}; 
			int step = 0;
			while(rS.size() != num + 1) {
				step += fStackToStack(record, Action.MToL, Action.LToM, lS, mS, left, mid);
				step += fStackToStack(record, Action.LToM, Action.MToL, mS, lS, mid, left);
				step += fStackToStack(record, Action.RToM, Action.MToR, mS, rS, mid, right);
				step += fStackToStack(record, Action.MToR, Action.RToM, rS, mS, right, mid);
			}
			return step;
		}

		private int fStackToStack(Action[] record, Action preNoAct, Action nowAct, Stack<Integer> fS, Stack<Integer> tS,
				String from, String to) {
			if (record[0] != preNoAct && fS.peek() < tS.peek()) {
				tS.push(fS.pop());
				System.out.printf("Move %d from %s to %s\r\n", tS.peek(), from , to);
				st.append(String.format("Move %d from %s to %s\r\n", tS.peek(), from , to));
				record[0] = nowAct;
				return 1;
			}
			return 0;
		}

	}
	//标准Hanoi问题
	static class StandardSolution {
		int count = 0;
		StringBuilder st = new StringBuilder();
		public int hanoi(int num, String left, String mid, String right) {
			move(num, left, mid, right);
			return count;
		}
		public void move(int num, String left, String mid, String right) {
			if (num > 0) {
				move(num - 1, left, right, mid);
				System.out.printf("Move disk %d from %s to %s\r\n", num, left, right);
				st.append(String.format("Move disk %d from %s to %s\r\n", num, left, right));
				count ++;
				move(num - 1, mid, left, right);
			}
		}
	}
	//标准Hanoi问题
	//非递归版本
	//同样需要设定规则
	//先写固定自己栈深度的版本
	static class StandardSolutionNoneRecursion {
		final int length = 10;
		int[] n = new int[length];
		char[] x = new char[length];
		char[] y = new char[length];
		char[] z = new char[length];
		int count = 1;
		void move(char x, int n, char y) {
			System.out.printf("%d. Move disk %d from %c to %c\r\n", count ++, n, x, y);
		}
//		void push()
	}
	//	见网址：　http://blog.csdn.net/u010443572/article/details/39346501
	static class StandardSolutionNoneRecursionClassBlog {
		StringBuilder st = new StringBuilder();
		static class Hanoi {
			int n;
			char x;
			char y;
			char z;
		}
		final int MAXLENGTH = 1000;
		int count = 0;
		void move(char x, int n, char y) {
			System.out.printf("%d. Move disk %d from %c to %c\r\n", count ++, n, x, y);
			st.append(String.format("Move disk %d from %c to %c\r\n", n, x, y));
		}
		void push(Hanoi[] p, int top, char x, char y, char z, int n ) {
			p[top + 1].n = n - 1;
			p[top + 1].x = x;
			p[top + 1].y = y;
			p[top + 1].z = z;
		}
		void noneRecusionHanoi(Hanoi[] p) {
			int top = 0;
			while (top >= 0) {
				while (p[top].n > 1) {		//向右到尽头
					push(p, top, p[top].x, p[top].z, p[top].y, p[top].n);
					top ++;
				}
				if (p[top].n == 1) {		//叶子节点
					move(p[top].x, p[top].n, p[top].z);
					top --;
				}
				if (top >= 0) {				//向左走一步
					move(p[top].x, p[top].n, p[top].z);
					top --;
					push(p, top, p[top + 1].y, p[top + 1].x, p[top + 1].z, p[top + 1].n);
					top ++;
				}
			}
		}
		public int hanoi(int num, String left, String mid, String right) {
			Hanoi[] p = new Hanoi[MAXLENGTH];
			for (int i = 0; i < MAXLENGTH; i ++) {
				p[i] = new Hanoi();
			}
			p[0].n = num;
			p[0].x = left.charAt(0);
			p[0].y = mid.charAt(0);
			p[0].z = right.charAt(0);
			noneRecusionHanoi(p);
			return count;
		}
	}
}
