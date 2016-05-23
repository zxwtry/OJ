package stl;

import java.util.Arrays;

/*
 * 	工作流问题：
 * 		选择了一项，在该任务结束之前不能选择其他任务
 * 		求的是，参与项目最多
 * 	现在知道的算法是：选择最早完成的任务
 */

public class Book1_2_2_贪心_2_2_2_时间区间问题 {
	public static void main(String[] args) {
		ps = new P[5];
		ps[0] = new P(1, 3);
		ps[1] = new P(2, 5);
		ps[2] = new P(4, 7);
		ps[3] = new P(6, 9);
		ps[4] = new P(8, 10);
		System.out.printf("%d\n",solve());
	}
	static P[] ps;
	static int solve() {
		Arrays.sort(ps);
		int tn = -1;
		int tc = 0;
		for (int pi = 0; pi < ps.length; pi ++) {
			if (tn < ps[pi].start) {
				tc ++;
				tn = ps[pi].end;
			}
		}
		return tc;
	}
	static class P implements Comparable<P>{
		int start, end;
		public P(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(P o) {
			return end-o.end;
		}
	}
}
