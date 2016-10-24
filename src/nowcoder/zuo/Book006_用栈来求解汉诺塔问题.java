package nowcoder.zuo;

import java.util.Stack;


public class Book006_用栈来求解汉诺塔问题 {
	public static void main(String[] args) {
		String left = "左";
		String mid = "中";
		String right = "右";
		int num = 8;
//		debugStandardSolution(num, left, mid, right);
//		debugSolution(num, left, mid, right);
//		debugSolutionNoneRecur(num, left, mid, right);
		testRecursionAndNoneRecursion(num, left, mid, right);
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
		public void hanoi(int num, String left, String mid, String right) {
			move(num, left, mid, right);
		}
		public void move(int num, String left, String mid, String right) {
			if (num > 0) {
				move(num - 1, left, right, mid);
				System.out.printf("Move disk %d from %s to %s\r\n", num, left, right);
				move(num - 1, mid, left, right);
			}
		}
	}
}
