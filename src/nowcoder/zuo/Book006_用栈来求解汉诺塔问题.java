package nowcoder.zuo;
/*
 * 	修改游戏规则， 
 * 	
 */
public class Book006_用栈来求解汉诺塔问题 {
	public static void main(String[] args) {
		String left = "左";
		String mid = "中";
		String right = "右";
		int num = 3;
		debugStandardSolution(num, left, mid, right);
	}
	static void debugStandardSolution(int num, String left, String mid, String right) {
		StandardSolution ss = new StandardSolution();
		ss.hanoi(num, left, mid, right);
	}
	static class Solution {
		public int hanoi(int num, String left, String mid, String right) {
			if (num < 1) {
				return 0;
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
				System.out.printf("Move disk %d from %s to %s\r\n", num, left, mid);
				move(num - 1, mid, right, left);
			}
		}
	}
}
