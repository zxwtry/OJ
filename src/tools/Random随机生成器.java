package tools;

import java.util.HashSet;

public class Random随机生成器 {
	//按照自己的习惯，应该将是[min, max]
	public static int[] A_生成一个随机数据(int n, int min, int max) {
		int[] ans = new int[n];
		int base = min;
		int range = max - min + 1;
		for (int i = 0; i < n; i ++) {
			ans[i] = base + (int)(Math.random() * range);
		}
		return ans;
	}
	public static int[] A_生成一个随机数据_除了某个数(int n, int min, int max, int val) {
		int[] ans = new int[n];
		int base = min;
		int range = max - min + 1;
		for (int i = 0; i < n; i ++) {
			int new_add = (int)(Math.random() * range);
			if (new_add == val - min) {
				i --;
				continue;
			}
			ans[i] = base + new_add;
		}
		return ans;
	}
	public static int[] A_生成一个不重复随机数据(int n, int min, int max) {
		HashSet<Integer> set = new HashSet<Integer>();
		int[] ans = new int[n];
		int base = min;
		int range = max - min + 1;
		for (int i = 0; i < n; i ++) {
			int new_add = (int)(Math.random() * range);
			if (set.contains(new_add)) {
				i --;
				continue;
			}
			set.add(new_add);
			ans[i] = base + new_add;
		}
		return ans;
	}
}
