package nowcoder.com;

/*
 * 										
	给网站选择一个好的域名是一件令人头痛的事，你希望你的域名在包含给定的一组关键字的同时，最短的长度是多少。
	
									
	输入
	输入文件的第一行包含一个整数 n，表示关键字的数目。(n＜=10)
	接下来的n行，每行包含了一个长度小于等于100的字符串，表示一组关键字。 
	样例输入
	3
	ABC
	CBA
	AB
	输出
	输出一行一个数字，表示最短的长度。
	样例输出
	5
	时间限制
	C/C++语言：1000MS其它语言：3000MS	
	内存限制
	C/C++语言：65536KB其它语言：589824KB
 */

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        百度17_域名选择.java
 * @type        百度17_域名选择
 * @date        2016年11月21日 下午6:32:38
 * @details     这题太难，暂时放弃
 */

public class 百度17_域名选择 {
	public static void main(String[] args) {
		try {
//			solve1();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static void solve1() throws Exception {
		final char INNER = '#', OUTER = '$';
		Scanner sc = new Scanner(new File("D:/file/data/百度17_域名选择.txt"));
		int n = Integer.parseInt(sc.nextLine().trim());
		char[][] cs = new char[n][];
		for (int i = 0; i < n; i ++) {
			cs[i] = sc.nextLine().trim().toCharArray();
		}
		int blockLen = 0;
		boolean[] isBlock = new boolean[n];
		for (int i = 0; i < n; i ++) {
			if (isBlock[i])	continue;
			for (int j = 0; j < n; j ++) {
				if (i == j || cs[i].length < cs[j].length || isBlock[j]) {
					continue;
				}
				for (int k = 0; k <= cs[i].length - cs[j].length; k ++) {
					int t = 0;
					for (; t < cs[j].length; t ++) {
						if (cs[i][k + t] != cs[j][t])
							break;
					}
					if (t == cs[j].length) {
						isBlock[j] = true;
						blockLen ++;
					}
				}
			}
		}
		int[][] L = new int[n][n];
		for (int i = 0; i < n; i ++) {
			if (isBlock[i])	continue;
			for (int j = 0; j < n; j ++) {
				if (i == j || isBlock[j])	continue;
				int l = 0;
				while (l < cs[i].length && l < cs[j].length) {
					if (cs[i][cs[i].length - 1 - l] == cs[j][l]) {
						l ++;
					} else {
						break;
					}
				}
				L[i][j] = l;
			}
		}
		//拼了，就用HashMap做一次
		StringBuilder st = new StringBuilder();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int len = 1; len <= n - blockLen; len ++) {
			//len是集合的长度
			int lenCount = 0;
			for (int i = 0; i < n; i ++) {
				if (isBlock[i])	continue;
				st.append(INNER);
				st.append(OUTER);
				map.put(st.toString(), lenCount);
			}
		}
		for (int i = 0; i < n; i ++) {
			st.delete(0, st.length());
			
//			map.put(i + OUTER + i , cs[i].length);
		}
		
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < n; i ++) {
			if (isBlock[i])	continue;
			
		}
		System.out.println(ans);
		sc.close();
	}
}
