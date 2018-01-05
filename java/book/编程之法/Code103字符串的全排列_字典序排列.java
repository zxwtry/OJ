package book.编程之法;

public class Code103字符串的全排列_字典序排列 {
	public static void main(String[] args) {
		char[] chs = "12345".toCharArray();
		solve(chs);
	}
	static void solve(char[] chs) {
		while (next(chs)) {
			System.out.println(String.valueOf(chs));
		}
	}
	/*
	 * 	这里的next方法与c++STL中的next_permutation函数是类似的。
	 */
	static boolean next(char[] chs) {
		int i = 0, chsEnd = 0, k = 0;
		if (chs == null || (chsEnd = chs.length-1) < 0)
			return false;
		for (i = chsEnd-1; (i >= 0) && (chs[i] >= chs[i+1]); --i) {}
		if (i < 0)	return false;
		for (k = chsEnd; (k > i) && (chs[k] < chs[i]); --k) {}
		tools.Utils.swap(chs, i, k);
		tools.Utils.reverse(chs, i+1, chsEnd);
		return true;
	}
}
