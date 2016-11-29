package nowcoder.com;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 相传在遥远的古代，有一个很出名的作坊，专门从事给别人出程序设计的题目的出题作坊。
 * 在无人记载的消逝的500年中，这个作坊由于一些不可名状的原因而长期没有生意，没落了下来。
	后来的故事嘛，努力可爱的少女小凛成为了作坊的第二百五十代继承人，并且决定振兴出题作坊。
	她对大家说，我们这不是没有生意吗，那我们就去冒险吧，去世界各地寻找需要题目的地方。
	小凛的一席话鼓舞了大家，大家都受够了无所事事的日子。于是她们将作坊卖了去买了一艘船，
	开始了属于她们的伟大冒险。
	日子一天天过去，虽然风景很美，船上的生活却枯燥乏味。无聊的小凛开始数起了她看到的生物，
	小凛每看到一个生物就将其记录在纸上，她惊奇的发现有些生物的名字竟然相互颠倒了。
	小凛对这一现象感到惊奇，她决定仔细看一看她纸上的有多少对互相颠倒的名字。
	
	
									
	输入
	本题为单样例题目，第一行输入一个n(1＜=n＜=100000)。接下来的n行，每行一个不超过10个字符的字符串。
	样例输入
	3
	fish
	hisf
	fish
	输出
	输出一行，为相互颠倒名字的对数。（提示，数字过大可能要用long long）
	样例输出
	2
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
 * @file        百度17_小凛的冒险.java
 * @type        百度17_小凛的冒险
 * @date        2016年11月29日 上午10:03:52
 * @details     
 */
public class 百度17_小凛的冒险 {
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
	 */
	static void solve1() throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine().trim());
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		long ans = 0;
		for (int index = 0; index < n; index ++) {
			String s = sc.nextLine().trim();
			char[] c = s.toCharArray();
			int sti = 0, eni = c.length - 1;
			while (sti < eni) {
				char tmp = c[sti];
				c[sti] = c[eni];
				c[eni] = tmp;
				sti ++;
				eni --;
			}
			String re = new String(c);
			if (map.containsKey(re))
				ans += map.get(re);
			if (map.containsKey(s)) {
				map.put(s, map.get(s) + 1);
			} else {
				map.put(s, 1);
			}
  		}
		System.out.println(ans);
		sc.close();
	}
}
