package nowcoder.com;

import java.util.HashMap;

public class 腾讯2016构造回文 {
	static String line = null;
	static int[] dps = new int[3000];
	static char[] chs = new char[3000];
	static final char SIGN = '#';
	public static void main(String[] args) {
		line = "abcda";
		line = "google";
		line = "";
		dp();
	}
	static void dp() {
		if (line == null || line.length() == 0)
			return;
		int end = (line.length() << 1) - 2;
		for (int index = end; index >= 0; index --) {
			if ((index & 0x1) == 1) {
				chs[index] = SIGN;
			} else {
				chs[index] = line.charAt(index >> 1);
			}
		}
		int max_count = -1;
		for (int index = 0; index <= end; index ++) {
			int st = index-1, en = index+1, new_count = 1;
			while (st >= 0 && en <= end && chs[st] == chs[en]) {
				new_count += 2;
				st --; en ++;
			}
			if (new_count > max_count)
				max_count = new_count;
		}
		System.out.printf("%d\n", max_count);
	}
	/*
	 * 	这种算法也是错误的。
	 */
	static void solve() {
		System.out.println(line.length());
		if (line == null || line.length() == 0)
			return;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (int index = line.length()-1; index >= 0; index --) {
			char c = line.charAt(index);
			if (! map.containsKey(c)) {
				map.put(c, 1);
			} else {
				map.put(c, map.get(c) + 1);
			}
		}
		int sta = 0, end = line.length() - 1, count = 0;
		while (sta < end) {
			if (sta > end || end < 0)
				break;
			char sc = line.charAt(sta), ec = line.charAt(end);
			int sv = map.get(sc), ev = map.get(ec);
			if (sv == 1) {
				if (sta == end) count ++;
				sta ++;
				continue;
			}
			if (ev == 1) {
				if (sta == end) count ++;
				end --;
				continue;
			}
			if (sc == ec) {
				sta ++;
				end --;
				count += 2;
			} else {
				if (sv > ev) sta ++;
				else end --;
			}
		}
		if (sta == end) count ++;
		System.out.println(line.length() - count);
	}
}