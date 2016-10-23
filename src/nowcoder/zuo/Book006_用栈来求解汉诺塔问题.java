package nowcoder.zuo;

public class Book006_用栈来求解汉诺塔问题 {
	public static void main(String[] args) {
		String left = "左";
		String mid = "中";
		String right = "右";
		int num = 3;
//		debugStandardSolution(num, left, mid, right);
		debugSolution(num, left, mid, right);
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
					return 1;
				} else {
					System.out.printf("Move 1 from %s to %s\r\n", from, mid);
					System.out.printf("Move 1 from %s to %s\r\n", mid, to);
					return 2;
				}
			} else {
				if (from.equals(mid) || to.equals(mid)) {
					String another = (from.equals(left) || to.equals(left)) ? right : left;
					int part1 = process(num - 1, left, mid, right, from, another);
					int part2 = 1;
					System.out.printf("Move %d from %s to %s\r\n", num, from, to);
					int part3 = process(num - 1, left, mid, right, another, to);
					return part1 + part2 + part3;
				} else {
					int part1 = process(num - 1, left, mid, right, from, to);
					int part2 = 1;
					System.out.printf("Move %d from %s to %s\r\n", num, from, mid);
					int part3 = process(num - 1, left, mid, right, to, from);
					int part4 = 1;
					System.out.printf("Move %d from %s to %s\r\n", num, mid, to);
					int part5 = process(num - 1, left, mid, right, from, to);
					return part1 + part2 + part3 + part4 + part5;
				}
			}
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
				System.out.printf("Move disk %d from %s to %s\r\n", num, left, mid);
				move(num - 1, mid, right, left);
			}
		}
	}
}
