package book.编程之法;

/*
 * 	"abcdef"  2-->  "defabc"
 */

public class Code101字符串旋转 {
	public static void main(String[] args) {
		char[] chs = "abcdef".toCharArray();
		System.out.println(String.valueOf(chs));
		solve(chs, 2);
		System.out.println(String.valueOf(chs));
	}
	static void reverse(char[] chs, int sta, int end) {
		while (sta < end) {
			char tmp = chs[sta];
			chs[sta++] = chs[end];
			chs[end--] = tmp;
		}
	}
	static void solve(char[] chs, int m) {
		int end = chs.length-1;
		reverse(chs, 0, m);
		reverse(chs, m+1, end);
		reverse(chs, 0, end);
	}
}
