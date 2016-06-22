package book.编程之法;

import java.util.Arrays;

public class Code202寻找和为定值的两个数_排序再往中靠 {
	public static void main(String[] args) {
		int[] arr = null;
		arr = new int[] {1, 9, 2, 8, 3, 7, 4, 6, 5};
		solve(arr, 16);
	}
	static void solve(int[] arr, int val) {
		Arrays.sort(arr);
		int var1 = 0, var2 = arr.length-1;
		int sum = arr[var1] + arr[var2];
		while (sum != val && var1 < var2) {
			if (sum < val) 	sum = arr[++ var1] + arr[var2];
			else sum = arr[var1] + arr[-- var2];
		}
		System.out.println("var1: "+var1+"   var2: "+var2);
		System.out.println("var1: "+arr[var1]+"   var2: "+arr[var2]);
	}
}