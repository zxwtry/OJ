package nowcoder.com;

import java.io.File;
import java.util.Scanner;

/*
 * 	题目描述
									
	S和T是两个字符串（它们只由小写字母构成），定义S与T相似当且仅当：
	1、S和T长度相同。
	2、对于任意两个位置i和j，如果Si和Sj相同，那么Ti和Tj相同；如果Si和Sj不同，那么Ti和Tj不同。
	（Si的含义为字符串S在第i个位置的字符，Ti的含义为字符串T在第i个位置的字符）
	与字符串”abca”相似的串有”abca”,”cdac”,”zetz”等，现在给出一个字符串S，输出与之相似的字典序最小的串。
	
									
	输入
	输入只有一行，一个字符串，长度不超过100000，只由小写字母组成。
	样例输入
	helloworld
	输出
	输出一行，与之相似的字典序最小的串（只由小写字母组成的串）。
	
	样例输出
	abccdedfcg
	时间限制
	C/C++语言：1000MS其它语言：3000MS	
	内存限制
	C/C++语言：65536KB其它语言：589824KB
 */

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        百度17_相似串.java
 * @type        百度17_相似串
 * @date        2016年11月21日 下午3:57:56
 * @details     
 */

public class 百度17_相似串 {
	public static void main(String[] args) {
		try {
			solve1();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @method      solve1
	 * @parameter   
	 * @return      void
	 * @details     AC
	 * @details     对于送分题，一定不能放掉。
	 */
	private static void solve1() throws Exception {
		Scanner sc = new Scanner(new File("D:/file/data/百度17_相似串.txt"));
		String line = sc.nextLine().trim();
		char[] cs = new char[26];
		for (int i = 0; i < cs.length; i ++) {
			cs[i] = '\0';
		}
		char now = 'a';
		char[] ans = new char[line.length()];
		for (int i = 0; i < ans.length; i ++) {
			int index = line.charAt(i) - 'a';
			if (cs[index] == '\0') {
				cs[index] = now ++;
			}
			ans[i] = cs[index];
		}
		for (char c : ans) {
			System.out.print(c);
		}
		System.out.println();
		sc.close();
	}
}
