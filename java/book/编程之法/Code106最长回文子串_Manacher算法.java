package book.编程之法;

/*
 * 	书中的Manacher算法是错误的(不是最优)，
 * 	下面的代码是根据左大神的讲解
 * 	通过nowcoder http://www.nowcoder.com/courses/6/4/2
 */
public class Code106最长回文子串_Manacher算法 {
	public static void main(String[] args) {
//		longestPalindrome("abcdcbaf".toCharArray());
//		longestPalindrome("abcdcbafab".toCharArray());
//		longestPalindrome("abacabacab".toCharArray());
		longestPalindrome("baabccc".toCharArray());
	}
	static int longestPalindrome(char[] arr) {
		int max = 0, arrEnd = 0, manEnd = 0;
		int mr = 0, l = 0, r = 0, t = 0, mc = 0, mi = 0;
		if (arr == null || (arrEnd = arr.length - 1) < 0)
			return 0;
		manEnd = (arrEnd << 1) + 2;
		char[] man = new char[manEnd + 1];
		for (int i = 0; i <= manEnd; i ++) {
			if ((i&0x1) == 0) {
				man[i] = '#';
			} else {
				man[i] = arr[i >> 1];
			}
		}
		int[] c = new int[manEnd+1];
		c[0] = 0; mi = 0;
		for (int i = 1; i <= manEnd; i ++) {
			if (mr >= manEnd)
				break;
			if (i >= mr) {
				l = i; r = i;
				while (--l >= 0 && ++r <= manEnd && man[l] == man[r]) {}
				if ((t= (r-l-1) >> 1) > mc)	mc = t;
				c[i] = t;
				mi = i;
			} else {
				int nr = man[(mi << 1) - i] + i;
				if (nr == manEnd) {
					l = i; r = i;
					while (--l >= 0 && ++r <= manEnd && man[l] == man[r]) {}
					if ((t= (r-l-1) >> 1) > mc)	mc = t;
					c[i] = t;
					mi = i;
				} else if (nr < manEnd) {
					c[i] = nr - i;
				} else {
					c[i] = mr - i;
				}
			}
		}
		System.out.println(mc);
		return max;
	}
}
