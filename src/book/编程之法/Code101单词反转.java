package book.编程之法;
/*
 * 	"I am a student."  -->  "student. a am I"
 */


public class Code101单词反转 {
	public static void main(String[] args) {
		char[] chs = "  I am a student. google".toCharArray();
		solve(chs);
		System.out.println(String.valueOf(chs));
	}
	static void reverse(char[] chs, int sta, int end) {
		int chs_end = chs.length - 1;
		if (chs == null || chs_end <= 0 || sta >= end ||
				sta < 0 || end > chs_end)
			return;
		while (sta < end) {
			char tmp = chs[sta];
			chs[sta++] = chs[end];
			chs[end--] = tmp;
		}
	}
	static void solve(char[] chs) {
		int chs_end = chs.length-1;
		if (chs == null || chs_end <= 1)
			return;
		reverse(chs, 0, chs_end);
		int sta = 0, end = 0;
		while (end <= chs_end && sta <= chs_end) {
			if (chs[end] == ' ') {
				reverse(chs, sta, end-1);
				sta = end+1;
			}
			end ++;
		}
		reverse(chs, sta, end-1);
	}
}
