package tools;

public class Random随机生成器 {
	public static int[] A_生成一个随机数据(int n, int min, int max) {
		int[] ans = new int[n];
		int base = min;
		int range = max - min;
		for (int i = 0; i < n; i ++) {
			ans[i] = base + (int)(Math.random() * range);
		}
		return ans;
	}
}
