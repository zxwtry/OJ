package nowcoder.zuo;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 	给定一个字符串数组ss，返回字典序最小的拼接字符串
 * 	举例：
 * 		ss={"abc", "de"}，返回"abcde"
 * 		ss={"b", "ba"}，返回"bab"
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book081_拼接所有字符串产生字典顺序最小的拼接字符串.java
 * @type        Book081_拼接所有字符串产生字典顺序最小的拼接字符串
 * @date        2016年12月21日 下午5:37:18
 * @details     
 */
public class Book081_拼接所有字符串产生字典顺序最小的拼接字符串 {
	static class Solution {
		public class MC implements Comparator<String> {
			@Override
			public int compare(String a, String b) {
				String c = a+b;
				String d = b+a;
				return c.compareTo(d);
			}
		}
		public String pinedString(String[] ss) {
			if (ss == null || ss.length == 0) return "";
			Arrays.sort(ss, new MC());
			StringBuilder st = new StringBuilder();
			for (String s : ss)
				st.append(s);
			return st.toString();
		}
	}
}
