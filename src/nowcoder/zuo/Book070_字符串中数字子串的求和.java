package nowcoder.zuo;

/**
 * 	给定一个字符串s，求其中全部数字串所代表的数字之和
 * 	要求：
 * 		1，	忽略小数点字符，例如"A1.3"，其中包含两个数字1和3
 * 		2，	如果紧贴数字子串的左侧出现字符"-"，
 * 			当连续出现的数量为奇数，数字为负
 * 			当连续出现的数量为偶数，数字为正
 * 			例如："A-1BC--12"，其中包含数字-1和12。
 * 	举例：
 * 		s="A1CD2E33-1--2---3"，返回34
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book070_字符串中数字子串的求和.java
 * @type        Book070_字符串中数字子串的求和
 * @date        2016年12月17日 下午4:01:12
 * @details     
 */
public class Book070_字符串中数字子串的求和 {
	static class Solution {
		public int sum(String s) {
			int n = 0;
			int ans = 0;
			boolean p = true;
			char c = '\0';
			for (int i = 0; i < s.length(); i ++) {
				c = s.charAt(i);
				if (c >= '0' && c <= '9') {
					n = n * 10 + c - '0';
				} else {
					ans += p ? n : -n;
					n = 0;
					if (c == '-') {
						p = ! p;
						if (i != 0 && s.charAt(i - 1) != '-')
							p = false;
					} else {
						p = true;
					}
				}
			}
			ans += p ? n : -n;
			return ans;
		}
	}
}
