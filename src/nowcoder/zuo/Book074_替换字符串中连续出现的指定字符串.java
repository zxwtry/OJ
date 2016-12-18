package nowcoder.zuo;

/**
 * 	给定三个字符串s, f, t，已知f字符串中无重复字符，
 * 	把s中所有f的子串全部替换成t字符串，
 * 	对于连续出现f的部分，要求只替换成一个t字符串，返回最终的结果字符串。
 * 
 * 	举例：
 * 		s="123abc" f="abc" t="4567" 返回"1234567"
 * 		s="123" f="abc" t="4567" 返回"123"
 * 		s="123abcabc" f="abc" t="4567" 返回"1234567"
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book074_替换字符串中连续出现的指定字符串.java
 * @type        Book074_替换字符串中连续出现的指定字符串
 * @date        2016年12月18日 下午7:41:38
 * @details     
 */
public class Book074_替换字符串中连续出现的指定字符串 {
	static class MySolution {
		public String replace(String s, String f, String t) {
			int[] next = getNext(f);
			int[] ir = new int[s.length()];
			int fi = 0;
			for (int i = 0; i <= s.length() - f.length(); i ++) {
				while (fi < f.length() && s.charAt(fi + i) == f.charAt(fi)) fi ++;
				if (fi == f.length()) ir[i] = 1;
				fi = next[fi];
			}
			int count = 0;
			tools.Utils.printArray(ir, 1000);
			for (int i = 0; i <= s.length() - f.length(); i ++) {
				if (ir[i] == 0) continue;
				int j = i;
				while (j+f.length() <= s.length() - f.length() && ir[j+f.length()] != 0)
					j += f.length();
				if (i != j) {
					for (int k = i + f.length(); k <= j; k += f.length()) {
						ir[k] = 0;
					}
					ir[i] = (j - i) / f.length() + 1;
				}
				if (ir[i] != 0)
				count ++;
			}
			if (count == 0) return s;
			tools.Utils.printArray(ir, 1000);
			char[] c = new char[s.length() + (t.length() - f.length()) * count];
			int ci = 0;
			for (int i = 0; i < s.length(); i ++) {
				if (ir[i] != 0) {
					for (int k = 0; k < t.length(); k ++)
						c[ci++] = t.charAt(k);
					i += f.length() * ir[i];;
					i --;
				} else {
					c[ci ++] = s.charAt(i);
				}
			}
			return new String(c);
		}

		private int[] getNext(String f) {
			int[] next = new int[f.length() + 1];
			next[0] = -1;
			int bi = -1, fi = 0;
			for (; fi < f.length(); fi ++) {
				if (-1 == bi || f.charAt(bi) == f.charAt(fi)) {
					bi ++;
					fi ++;
					next[fi] = bi;
				} else {
					bi = next[bi];
				}
			}
			next[0] = 0;
			return next;
		}
	}
}
