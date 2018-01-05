package book.编程之法;
/*
 * 	这个全排列，非常重要
 */

import tools.Utils;

public class Code103字符串的全排列_递归实现 {
	public static void main(String[] args) {
		char[] chs = "ABC".toCharArray();
		solve(chs, 0, chs.length-1);
	}
	static void solve(char[] chs, int sta, int end) {
		int chs_end = 0;
		if (chs==null || (chs_end=chs.length-1)<1 || 
				sta<0 || end<0 || sta>chs_end || end>chs_end)
			return;
		if (sta == end) {
			for (int i = 0; i < end; i ++)
				System.out.printf("%c", chs[i]);
			System.out.printf("%c\n", chs[end]);
		} else {
			for (int i = sta; i <= end; i ++) {
				tools.Utils.swap(chs, i, sta);
				Utils.swap(chs, i, sta);
				solve(chs, sta+1, end);
				tools.Utils.swap(chs, i, sta);
			}
		}
	}
}
