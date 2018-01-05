package nowcoder.com;



/*
 * 题目描述
									
	真是一个无聊的夜晚啊，小B和小C在宿舍中实在无聊之极，因此决定玩一个数字游戏打发时间。小B首先开始，她给小C的问题是：
	
	给定任意一个整数，小C可以交换该数中任意的不同位，能够得到的最小数值是多少？前提是不能有前导0。
	
	小C很快就给出了一个答案，小B希望你来帮她检验答案的正确性，你能帮忙吗？
	
									
	输入
	输入中有多组测试数据。每组测试数据的第一行为一个没有前导0的整数n（0=＜n =＜10^9），
	第二行为另一个整数m（0=＜m=＜10^9），小C给出的问题答案。
	样例输入
	3310
	1033
	4
	5
	
	输出
	对每组测试数据，在单独的行中输出结果，若小C的答案是正确的，输出YES，否则输出NO。
	样例输出
	YES
	NO
	
	时间限制
	C/C++语言：1000MS其它语言：3000MS	
	内存限制
	C/C++语言：65536KB其它语言：589824KB
 */

import java.util.Arrays;
import java.util.Scanner;
public class 乐视17_数字游戏 {
	public static void main(String[] args) {
		solve1();
	}
	static void solve1() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String line = sc.nextLine().trim();
			String ans = sc.nextLine().trim();
			if (Integer.parseInt(ans) == 0 && Integer.parseInt(line) == 0 && line.length() != ans.length()) {
				System.out.println("NO");
				continue;
			}
			if (ans.equals(solve1(line))) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
		sc.close();
	}
	private static String solve1(String line) {
		char[] c = line.toCharArray();
		Arrays.sort(c);
		if (c[0] == '0') {
			char c_0 = '\0';int i = 0;
			for (; i < c.length; i ++) {
				if (c[i] != '0') {
					c_0 = c[i];
					break;
				}
			}
			if (i == c.length) {
				return "0";
			}
			c[i] = '0';
			c[0] = c_0;
		}
		return new String(c);
	}
}
