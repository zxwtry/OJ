package book.编程之法;

public class Code106最长回文子串_中心扩展法 {
	public static void main(String[] args) {
		char[] arr = "ttAtt".toCharArray();
		System.out.println(longestPalindrome(arr));
	}
	static int longestPalindrome(char[] arr) {
		int max = 0, arrEnd = 0, l = 0, r = 0, c = 0;
		if (arr == null || (arrEnd = arr.length - 1) < 0)
			return 0;
		for (int i = 0; i <= arrEnd; i  ++) {
			// 奇数个
			l = i - 1; r = i + 1; c = 1;
			while (l >= 0 && r <= arrEnd && 
					arr[l--] == arr[r++])
				c += 2;
			if (c > max)	max = c;
			// 偶数个
			l = i; r = i + 1; c = 0;
			while (l >= 0 && r <= arrEnd && 
					arr[l--] == arr[r++])
				c += 2;
			if (c > max)	max = c;
		}
		return max;
	}
}
