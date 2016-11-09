package nowcoder.com;

/*
 * 	题目描述
									
	度度熊收到了一个只有小写字母的字符串S，他对S的子串产生了兴趣，S的子串为S中任意连续的一段。
	他发现，一些子串只由一种字母构成，他想知道在S中一共有多少种这样的子串。
	例如在串”aaabbaa”中，度度熊想找的子串有”a”,”aa”,”aaa”,”b”,”bb”五种。
	
	（本题只考虑子串的种数，相同的子串在多个位置出现只算一次）
 */

import java.util.Scanner;

public class 百度17_度度熊找子串 {
	public static void main(String[] args) {
//		solve1();
		solve2();
	}
	//时间O(N) 空间O(1)
	static void solve2() {
		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine();
		if (line == null || line.length() == 0) {
			System.out.println(0);
		} else {
			int pre = 1;
			int[] map = new int[26];
			for (int i = 1; i <= line.length(); i ++) {
				if (i != line.length() && line.charAt(i) == line.charAt(i - 1)) {
					pre = pre + 1;
				} else {
					int mapIndex = line.charAt(i - 1) - 'a';
					map[mapIndex] = Math.max(map[mapIndex], pre);
					if (i != line.length()) {
						pre = 1;
					}
				}
			}
			long ans = 0;
			for (int val : map) {
				ans += val;
			}
			System.out.println(ans);
		}
		scan.close();
	}
	//时间O(N) 空间O(N)
	static void solve1() {
		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine();
		if (line == null || line.length() == 0) {
			System.out.println(0);
		} else {
			int[] arr = new int[line.length()];
			int[] map = new int[26];
			arr[0] = 1;
			for (int i = 1; i <= arr.length; i ++) {
				if (i != arr.length && line.charAt(i) == line.charAt(i - 1)) {
					arr[i] = arr[i - 1] + 1;
					arr[i - 1] = 0;
				} else {
					int mapIndex = line.charAt(i - 1) - 'a';
					map[mapIndex] = Math.max(map[mapIndex], arr[i - 1]);
					if (i != arr.length) {
						arr[i] = 1;
					}
				}
			}
			long ans = 0;
			for (int val : map) {
				ans += val;
			}
			System.out.println(ans);
		}
		scan.close();
	}
}