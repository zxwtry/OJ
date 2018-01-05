package nowcoder.zuo;

/**
 * 	给定一个字符串s和一个整数k，
 * 	如果s中正好有连续的k个'0'字符出现时，
 * 	把k个连续'0'字符去除，返回处理后的字符串
 * 
 * 	举例：
 * 		s="A00B"，k=2，返回"AB"
 * 		s="A0000B000"，k=3，返回"A0000B"
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book071_去掉字符串中连续出现K个0的子串.java
 * @type        Book071_去掉字符串中连续出现K个0的子串
 * @date        2016年12月17日 下午4:28:21
 * @details     
 */
public class Book071_去掉字符串中连续出现K个0的子串 {
	/**
	 * @author      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book071_去掉字符串中连续出现K个0的子串.java
	 * @type        Solution1
	 * @date        2016年12月17日 下午4:38:12
	 * @details     这是书上提供的解答，有问题。
	 */
	static class Solution1 {
		public String removeKZeros(String s, int k) {
			if (s == null || s.length() == 0 || k < 1) return s;
			char[] c = s.toCharArray();
			int cou = 0, sta = -1;	
			for (int i = 0; i <= c.length; i ++) {
				if (i != c.length && c[i] == '0') {
					cou ++;
					sta = sta == -1 ? i : sta;
				} else {
					if (cou == k)
						while (cou -- != 0)
							c[sta ++] = 0;
					cou = 0;
					sta = -1;
				}
			}
			return String.valueOf(c);
		}
	}
	static class Solution2 {
		public String removeKZeros(String s, int k) {
			if (s == null || s.length() == 0 || k < 1) return s;
			char[] c = s.toCharArray();
			int cou = 0, sta = -1;	
			for (int i = 0; i <= c.length; i ++) {
				if (i != c.length && c[i] == '0') {
					cou ++;
					sta = sta == -1 ? i : sta;
				} else {
					if (cou == k)
						while (cou -- != 0)
							c[sta ++] = 0;
					cou = 0;
					sta = -1;
				}
			}
			sta = 0;
			for (int i = 0; i < c.length; i ++) {
				if (c[i] != 0) {
					c[sta ++] = c[i];
				}
			}
			return new String(c, 0, sta);
		}
	}
}
