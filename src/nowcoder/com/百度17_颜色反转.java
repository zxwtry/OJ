package nowcoder.com;

import java.util.Scanner;

/**
 * 在 Web 开发中，通常使用 16 进制 RGB 表示一个颜色。
	    
	例 如 #0000FF 表示蓝色，#FF7F50 表示珊瑚红。在这道题中，我们要求把给定的一种颜色取反，
	方法是把 RGB 的每个位转换成十进制 rgb，再用 255 减去，再转换成 16 进制。例如蓝色取反是黄色（#FFFF00）。
	
									
	输入
	请从控制台读取输入，输入不超过50行，每行包含一个16进制 RGB 表示一种颜色，保证题目中的字母均大写。
	样例输入
	#0000FF
	#FFFF00
	
	输出
	请向控制台输出你的结果，对于读取的每一行输入，输出一行格式相同的 16 进制 RGB 表示取反后的颜色，要求字母也是大写。
	样例输出
	#FFFF00
	#0000FF
	
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
 * @file        百度17_颜色反转.java
 * @type        百度17_颜色反转
 * @date        2016年11月27日 下午9:58:44
 * @details     
 */
public class 百度17_颜色反转 {
	public static void main(String[] args) {
		solve1();
	}

	/**
	 * @method      solve1
	 * @parameter   
	 * @return      void
	 * @details     AC
	 */
	static void solve1() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			char[] cs = sc.nextLine().trim().toCharArray();
			for (int index = 1; index < cs.length; index ++) {
				cs[index] = solve1_char_minus(cs[index]);
			}
			System.out.println(new String(cs));
		}
		sc.close();
	}
	
	static char solve1_char_minus(char small) {
		int small_val = char_to_int(small);
		return int_to_char(15 - small_val);
	}
	
	static int char_to_int(char c) {
		if (c >= 'A' && c <= 'Z') {
			return 10 + c - 'A';
		} else if (c >= 'a' && c <= 'a') {
			return 10 + c - 'a';
		} else if (c >= '0' && c <= '9') {
			return c - '0';
		} else {
			return 0;
		}
	}
	
	static char int_to_char(int v) {
		if (v >= 10 && v <= 15) {
			return (char)(v - 10 + 'A');
		} else if (v >= 0 && v <= 9) {
			return (char)(v + '0');
		} else {
			return '0';
		}
	}
}
