package tools;

import java.util.HashSet;

public class Random随机生成器 {
	//按照自己的习惯，应该将是[min, max]
	public static int[] A_生成一个随机数据(int n, int min, int max) {
		int[] ans = new int[n];
		long range = (long)max - min + 1;
		for (int i = 0; i < n; i ++) {
			double d = Math.random() * range + min;
			if (d < 0) d -= 1;
			ans[i] = (int)(d);
		}
		return ans;
	}
	public static int[] A_生成一个随机数据_除了某个数(int n, int min, int max, int val) {
		int[] ans = new int[n];
		long range = (long)max - min + 1;
		for (int i = 0; i < n; i ++) {
			double d = Math.random() * range + min;
			if (d < 0) d -= 1;
			ans[i] = (int)(d);
			if (ans[i] == val) {
				i --;
				continue;
			}
		}
		return ans;
	}
	public static int[] A_生成一个不重复随机数据(int n, int min, int max) {
		HashSet<Integer> set = new HashSet<Integer>();
		int[] ans = new int[n];
		long range = (long)max - min + 1;
		for (int i = 0; i < n; i ++) {
			double d = Math.random() * range + min;
			if (d < 0) d -= 1;
			ans[i] = (int)(d);
			if (set.contains(ans[i])) {
				i --;
				continue;
			}
			set.add(ans[i]);
		}
		return ans;
	}
}
