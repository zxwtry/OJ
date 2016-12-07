package nowcoder.com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 	可扩展标记语言（英语：Extensible Markup Language，简称：XML），是一种标记语言。
	XML 设计用来传送及携带数据信息，不用来表现或展示数据，HTML语言则用来表现数据，
	所以 XML 用途的焦点是它说明数据是什么，以及携带数据信息。
	例如，下面是一段 XML 标签。
	＜recipe＞
	      ＜recipename＞Ice Cream Sundae＜/recipename＞
	      ＜ingredlist＞
	           ＜listitem＞
	                 ＜quantity＞3＜/quantity＞
	                 ＜itemdescription＞chocolate syrup or chocolate fudge＜/itemdescription＞
	           ＜/listitem＞
	           ＜listitem＞
	                 ＜quantity＞1＜/quantity＞
	                 ＜itemdescription＞nuts＜/itemdescription＞
	           ＜/listitem＞
	           ＜listitem＞
	                 ＜quantity＞1＜/quantity＞
	                 ＜itemdescription＞cherry＜/itemdescription＞
	           ＜/listitem＞
	      ＜/ingredlist＞
	      ＜preptime＞5 minutes＜/preptime＞
	＜/recipe＞
	在这个问题中，你需要在给定的文本 XML 中，查找出给定模式 XML 的所有出现的位置。
	文本 XML 中的每个标签按照出现的顺序编号，根节点的编号为 1，
	例如上面的第一个 ＜listitem＞ 标签的编号为 4，文本和模式标签有且仅有一个根节点，
	输出每组匹配中，模式 XML 的根节点标签在文本 XML 中的编号。
	
	
									
	输入
	输入包含两段合法的 XML 标签，分别表示文本 XML和模式 XML，每段 XML有且仅有一个根节点。 
	输入数据不超过 10000 个字符，文本和模式 XML 中所包含的标签数不超过 100。
	样例输入
	＜recipe＞
	      ＜recipename＞Ice Cream Sundae＜/recipename＞
	      ＜ingredlist＞
	           ＜listitem＞
	                 ＜quantity＞3＜/quantity＞
	                 ＜itemdescription＞chocolate syrup or chocolate fudge＜/itemdescription＞
	           ＜/listitem＞
	           ＜listitem＞
	                 ＜quantity＞1＜/quantity＞
	                 ＜itemdescription＞nuts＜/itemdescription＞
	           ＜/listitem＞
	           ＜listitem＞
	                 ＜quantity＞1＜/quantity＞
	                 ＜itemdescription＞cherry＜/itemdescription＞
	           ＜/listitem＞
	      ＜/ingredlist＞
	      ＜preptime＞5 minutes＜/preptime＞
	＜/recipe＞
	＜listitem＞＜quantity＞＜/quantity＞＜/listitem＞
	
	输出
	第一行输出匹配发生的次数，第二行从小到大输出所有匹配的位置编号。
	样例输出
	3
	4 7 10
	
	时间限制
	C/C++语言：1000MS其它语言：3000MS	
	内存限制
	C/C++语言：65536KB其它语言：589824KB
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        百度17_SearchInXML.java
 * @type        百度17_SearchInXML
 * @date        2016年11月30日 下午10:28:10
 * @details     
 */
public class 百度17_SearchInXML {
	public static void main(String[] args) {
		try {
			solve1();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void solve1() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] cs = new char[10020];
		int LEN = br.read(cs);
		HashMap<String, Integer> m = new HashMap<String, Integer>();
		int mi = 1;
		int[] L = new int[200] , R = new int[200];
		for (int j = 0; j < 200; j ++) {
			L[j] = -1;
			R[j] = -1;
		}
		int J = 0;
		for (int i = 0; i < LEN; i ++) {
			if (L[J] == -1 && cs[i] == '<') L[J] = i; 
			if (R[J] == -1 && cs[i] == '>') {
				R[J] = i;
				J ++;
			}
		}
		for (int j = 0; j < J; j ++) {
			if (cs[L[j] + 1] != '/') {
				String s = new String(cs, L[j] + 1, R[j] - L[j] - 1);
				Integer val = m.get(s);
				if (val == null) m.put(s, mi ++);
			}
		}
		V h = null, p = null;
		Stack<V> stk = new Stack<V>();
		for (int j = 0; j < J; j ++) {
			if (cs[L[j] + 1] != '/') {
				String s = new String(cs, L[j] + 1, R[j] - L[j] - 1);
				V v = new V(m.get(s), j + 1);
				if (null == h) h = v;
				else if (stk.isEmpty()) {
					p = v;
				}
				if (! stk.isEmpty())
					stk.peek().l.add(v);
				stk.push(v);
			} else {
				stk.pop();;
			}
		}
		
		Queue<V> q = new LinkedList<V>();
		q.add(h);
		LinkedList<Integer> ans = new LinkedList<Integer>();
		while (! q.isEmpty()) {
			h = q.poll();
			for (V v : h.l) q.add(v);
			if (h.id == p.id) {
				if (contain(h, p)) {
					ans.add(h.number);
				}
			}
		}
		
		System.out.println(ans.size());
		Iterator<Integer> it = ans.iterator();
		while (it.hasNext()) {
			System.out.print(it.next());
			if (it.hasNext())
				System.out.print(" ");
		}
		System.out.println();
		br.close();
	}
	
	static boolean contain(V h, V q) {
		if (q == null)	return true;
		if (h == null)	return false;
		if (q.l.size() == 0) return true; 
		if (q.l.size() > h.l.size()) return false;
		
		return false;
	}

	static class V {
		int id, number;
		ArrayList<V> l = new ArrayList<V>();
		public V(int id, int number) {this.id = id; this.number = number;}
	}
	
}
