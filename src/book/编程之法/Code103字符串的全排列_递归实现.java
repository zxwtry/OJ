package book.编程之法;
/*
 * 	这个全排列，非常重要
 */

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
				swap(chs, i, sta);
				solve(chs, sta+1, end);
				swap(chs, i, sta);
			}
		}
	}
	static void swap(char[] chs, int i, int j) {
		int end = 0;
		if (chs == null || (end=chs.length-1) < 1 ||
				i < 0 || j < 0 || i > end || j > end)
			return;
		char tmp = chs[i];
		chs[i] = chs[j];
		chs[j] = tmp;
	}
}
