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
import java.util.Scanner;

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        百度17_域名选择.java
 * @type        百度17_域名选择
 * @date        2016年11月21日 下午6:32:38
 * @details     
 */

public class 百度17_域名选择 {
	public static void main(String[] args) {
		
	}
	static void solve1() throws Exception {
		Scanner sc = new Scanner(new File("D:/file/data/百度17_域名选择.txt"));
		int n = Integer.parseInt(sc.nextLine().trim());
		char[][] cs = new char[n][];
		for (int i = 0; i < n; i ++) {
			cs[i] = sc.nextLine().trim().toCharArray();
		}
		
		sc.close();
	}
}
