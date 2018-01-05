package nowcoder.com;

import java.util.Scanner;

/**
 * 在 Blog 写作的过程中，经常一言不合就添加一些超链接。
	例如：
	I am a typical INFPs.
	http://www.16personalities.com/infp-personality
	为了方便阅读，我们希望将这些超链接转换成直接在网页中可以单击跳转的形式。
	I am a typical INFPs.
	http://www.16personalities.com/infp-personality
	在这题中，我们认为超链接的格式是以 http:// 或者 https:// 开头，
	以空格或换行结束的一个字符串。超链接有可能出现在行中。
									
	输入
	输入文件包含不超过50行，每行包含一个字符串。
	所有的输入都是区分大小写的，每行最多含200个ASCII字符。
	样例输入
	You are next. https://http://http:// Next what?
	输出
	输出经过处理过后的字符串。
	样例输出
	You are next. ＜a href="https://http://http://"＞https://http://http://＜/a＞ Next what? 
	
	时间限制
	C/C++语言：1000MS其它语言：3000MS	
	内存限制
	C/C++语言：65536KB其它语言：589824KB
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        百度17_超链接1.java
 * @type        百度17_超链接1
 * @date        2016年11月30日 下午10:25:10
 * @details     
 */
public class 百度17_超链接1 {
	public static void main(String[] args) {
		solve1();
	}

	final static char[][] c = {"http://".toCharArray(), "https://".toCharArray()};
	final static String format = "<a href=\"%s\">%s</a>";
	
	/**
	 * @method      solve1
	 * @parameter   
	 * @return      void
	 * @details     AC
	 */
	static void solve1() {
		Scanner sc = new Scanner(System.in);
		int len = Integer.MAX_VALUE;
		for (char[] ci : c) len = Math.min(ci.length, len);
		while (sc.hasNext()) {
			StringBuilder st = new StringBuilder(sc.nextLine());
			int i = 0;
			for (; i < st.length() - len; i ++) {
				int s = solve1Judge(st, i);
				if (s == -1) continue;
				int j = i + 1;
				while (j < st.length())
					if (st.charAt(j) == ' ') break; 
					else j ++;
				String args = st.substring(i, j);
				st.replace(i, j, String.format(format, args, args));
				i += 2 * (j - i) + 15;
			}
			System.out.println(st.toString());
		}
		sc.close();
	}
	
	static int solve1Judge(StringBuilder st, int index) {
		for (int i = 0; i < c.length; i ++) {
			boolean f = true;
			for (int j = 0; f && j < c[i].length; j ++) {
				if (index+j >= st.length()) f = false;
				else f &= st.charAt(index + j) == c[i][j];
			}
			if (f) return i;
		}
		return -1;
	}
	
}
