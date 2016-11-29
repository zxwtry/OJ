package nowcoder.com;

import java.util.Scanner;

/**
 * 	在 Web 开发过程中，为了防止歧义， ＜＆＞＂ 等特殊字符通常会进行转义。
	转义的规则如下：
	＜: ＆lt;
	＞: ＆gt;
	＆: ＆amp;
	＂: ＆quot;
	 : ＆nbsp; (空格)
	
	例如下面的段落，
	＜div＞Wings, you＇re next TI champion!＜/div＞
	会被转义成
	＆lt;div＆gt;Wings,＆nbsp;you＇re＆nbsp;next＆nbsp;TI＆nbsp;champion! ＆lt;/div＆gt;
	
	编程输入转义过后的句子，返回转义之前的句子。
	
	
									
	输入
	输入文件包含不超过 50行，每行对应一个转义过后的句子。
	所有的输入都是区分大小写的，每行最多含200个ASCII字符。
	样例输入
	CN dota, Best dota!
	输出
	对于每一个句子，输出一行表示转义之前的句子。
	样例输出
	CN dota, Best dota!
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
 * @file        百度17_Web转义符转换.java
 * @type        百度17_Web转义符转换
 * @date        2016年11月29日 下午4:36:35
 * @details     
 */
public class 百度17_Web转义符转换 {
	public static void main(String[] args) {
//		solve1();
		solve2();
	}

	/**
	 * @method      solve2
	 * @parameter   
	 * @return      void
	 * @details   	这个竟然能够AC  
	 * @details   	实在无语了
	 */
	static void solve2() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String line = sc.nextLine();
			line = line.replaceAll("&lt;", "<");
			line = line.replaceAll("&gt;", ">");
			line = line.replaceAll("&amp;", "&");
			line = line.replaceAll("&quot;", "\"");
			line = line.replaceAll("&nbsp;", " ");
			System.out.println(line);
		}
		sc.close();
	}

	/**
	 * @method      solve1
	 * @parameter   
	 * @return      void
	 * @details     未能AC
	 * @details     原因位置
	 */
	static void solve1() {
		char[][] c = new char[][] {
			{'&', 'l', 't', ';'},
			{'&', 'g', 't', ';'},
			{'&', 'a', 'm', 'p', ';'},
			{'&', 'q', 'u', 'o', 't', ';'},
			{'&', 'n', 'b', 's', 'p', ';'}
		};
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String line = sc.nextLine();
			int[] cov = new int[line.length()];
			int len = cov.length;
			for (int index = 0; index < cov.length; index ++) {
				int covVal = convert((int)line.charAt(index));
				cov[index] = covVal;
				if (covVal != -1) {
					len += c[covVal].length - 1;
				}
			}
			char[] newStr = new char[len];
			int newIndex = 0;
			for (int index = 0; index < cov.length; index ++) {
				if (cov[index] == -1) {
					newStr[newIndex ++] = line.charAt(index);
				} else {
					for (int i = 0; i < c[cov[index]].length; i ++)
						newStr[newIndex ++] = c[cov[index]][i];
				}
			}
			System.out.println(new String(newStr));
		}
		sc.close();
	}
	
	static int convert(int c) {
		switch (c) {
		case (int)'<':
		case (int)'＜':
			return 0;
//			return new char[] {'&', 'l', 't', ';'};
		case (int)'>':
		case (int)'＞':
			return 1;
//			return new char[] {'&', 'g', 't', ';'};
		case (int)'&':
			return 2;
//			return new char[] {'&', 'a', 'm', 'p', ';'};
		case (int)'"':
			return 3;
//			return new char[] {'&', 'q', 'u', 'o', 't', ';'};
//		case ' ':
		case (int)'　':
		case 32:
			return 4;
//			return new char[] {'&', 'n', 'b', 's', 'p', ';'};
		default:
			return -1;
//			return new char[] {c};
		}
	}
}
