package nowcoder.zuo;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 	给定一个字符串s，s表示一个公式
 * 	公式中可能有整数、加减乘除符号和左右括号，
 * 	返回公式的计算结果。
 * 	举例：
 * 		s="48*((70-65)-43)+8*1"，返回-1816
 * 		s="3+1*4"，返回7
 * 		s="3+(1*4)"，返回7
 * 	说明：
 * 		1,	认为字符串是一个正确的公式。
 * 		2,	负数需要用括号括起来，比如"4*(-3)"
 * 			负数作为公式的开头或括号的开头，可以没有括号
 * 		3,	认为不会发生溢出。
 * 	解法：
 * 		1,	从左到右遍历，从开始遍历或者遇到'('时，就开始一个递归。
 * 			当遍历完成或者遇到')'时，这个递归结束。
 * 		2,	
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book079_公式字符串求值.java
 * @type        Book079_公式字符串求值
 * @date        2016年12月21日 上午10:39:49
 * @details     
 */
public class Book079_公式字符串求值 {
	static class Solution {
		public int getVal(String e) {
			return val(e, 0)[0];
		}
		private int[] val(String e, int i) {
			Deque<String> q = new LinkedList<String>();
			int pre = 0;
			int[] bra = null;
			while (i < e.length() && e.charAt(i) != ')') {
				if (e.charAt(i) >= '0' && e.charAt(i) <= '9') {
					pre = pre * 10 + e.charAt(i ++) - '0';
				} else if (e.charAt(i) != '(') {
					addNum(q, pre);
					q.addLast(String.valueOf(e.charAt(i)));
					i ++;
					pre = 0;
				} else {
					bra = val(e, i+1);
					pre = bra[0];
					i = bra[1] + 1;
				}
			}
			addNum(q, pre);
			return new int[]{getNum(q), i};
		}
		private int getNum(Deque<String> q) {
			int a = 0;
			boolean add = true;
			String cur = null;
			int n = 0;
			while (! q.isEmpty()) {
				cur = q.pollFirst();
				if (cur.equals("+")) {
					add = true;
				} else if (cur.equals("-")) {
					add = false;
				} else {
					n = Integer.valueOf(cur);
					a += add ? n : (-n);
				}
			}
			return a;
		}
		private void addNum(Deque<String> q, int n) {
			if (! q.isEmpty()) {
				int cur = 0;
				String top = q.pollLast();
				if (top.equals("+") || top.equals("-")) {
					q.addLast(top);
				} else {
					cur = Integer.valueOf(q.pollLast());
					n = top.equals("*") ? (cur * n) : (cur / n);
				}
			}
			q.addLast(String.valueOf(n));
		}
	}
}
