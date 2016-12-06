package template;

import java.util.Scanner;

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        RECITE_String_KMP.java
 * @type        RECITE_String_KMP
 * @date        2016年12月6日 下午4:12:12
 * @details     http://hihocoder.com/problemset/problem/1015
 */
public class RECITE_String_KMP {
	
	final static int maxp = 10020, maxs = 1000020;
	final static char[] p = new char[maxp];
	final static char[] s = new char[maxs];
	final static int[] next = new int[maxp];
	static int pl, sl;
	
	static int kmp() {
		getNext();
		int ans = 0;
		int pi = 0;
		for (int si = 0; si <= sl-pl; si ++) {
			for (; pi < pl; pi ++) {
				if (s[si + pi] != p[pi]) break;
			}
			if (pi == pl) ans ++;
			pi = next[pi];
		}
		
		return ans;
	}
	
	static void getNext() {
		next[0] = -1;
		int bi = -1, fi = 0;
		while (fi < pl) {
			if (-1 == bi || p[bi] == p[fi]) {
				bi ++;
				fi ++;
				next[fi] = bi; 
			} else {
				bi = next[bi];
			}
		}
		next[0] = 0;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int times = sc.nextInt();
		for (int timesIndex = 1; timesIndex <= times; timesIndex ++) {
			String ps = sc.next(), ss = sc.next();
			pl = ps.length();
			sl = ss.length();
			for (int pi = 0; pi < pl; pi ++)
				p[pi] = ps.charAt(pi);
			for (int si = 0; si < sl; si ++)
				s[si] = ss.charAt(si);
			System.out.println(kmp());
		}
		sc.close();
	}
	
}
