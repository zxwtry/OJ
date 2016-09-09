package leetcode;

/*
 * 	Given an absolute path for a file (Unix-style), simplify it.

	For example,
	path = "/home/", => "/home"
	path = "/a/./b/../../c/", => "/c"
	click to show corner cases.
	
	Corner Cases:
	Did you consider the case where path = "/../"?
	In this case, you should return "/".
	Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
	In this case, you should ignore redundant slashes and return "/home/foo".
 */

public class P071_SimplifyPath {
	public static void main(String[] args) {
//		System.out.println(new Solution1().simplifyPath("/a/./b/../../c/"));
//		System.out.println(new Solution1().simplifyPath("/.././../"));
//		System.out.println(new Solution1().simplifyPath("/home/"));
//		System.out.println(new Solution1().simplifyPath("/home//foo/"));
//		System.out.println(new Solution1().simplifyPath("/a//b//.."));
//		System.out.println(new Solution1().simplifyPath("/home/.."));
//		System.out.println(new Solution1().simplifyPath("/a/b/c/../../../.."));
//		System.out.println(new Solution1().simplifyPath("/home/foo/./bar/"));
//		System.out.println(new Solution1().simplifyPath("/a/./b///../c/../././../d/..//../e/./f/./g/././//.//h///././/..///"));
//		System.out.println(new Solution1().simplifyPath("/a/./b/../c/../../d/../../e/f/g/h/.."));
//		System.out.println(new Solution1().simplifyPath("/a/./b/../c/../../d/../../e/f/g/h/././/.."));
		System.out.println(new Solution1().simplifyPath("/df/JE/.././qTV/jjU/./../AgSKc/../uD///yHFCu/../WxQye/tyek/.././"));
	}
	/*
	 * 	建立最简单的树形结构
	 * 	又是一个试错型AC，还好最后还是AC了
	 * 	6 ms
	 */
	static class Solution1 {
	    public String simplifyPath(String path) {
	    	int len = 0;
	    	if (path == null || (len = path.length()) < 2)
	    		return path;
	    	Node head = new Node(), cur = head;
	    	StringBuilder st = new StringBuilder();
	    	boolean isLastSlash = true;
	    	boolean isLastDot = false;
	    	boolean isDoubleDot = false;
	    	boolean isDoubleDotLast = false;
	    	for (int i = 0; i <= len; i ++) {
	    		char c = '\0';
	    		if (i == len || (c = path.charAt(i)) == '/') {
	    			if (isLastSlash || (isLastDot && ! isDoubleDot)) {
	    				if (st.length() != 0)
	    					st = new StringBuilder();
	    				isLastSlash = true;
	    				isDoubleDot = false;
	    				continue;
	    			}
	    			if (isDoubleDot) {
	    				if (st.length() == 2) {
	    					if (i != len) {
	    						cur = cur.pre == null ? cur : cur.pre;
	    						isDoubleDotLast = true;
	    					} else {
	    						if (cur.pre != null)
	    							cur.pre.next = null;
	    					}
	    				} else {
	    					if (! st.toString().equals(".")) {
		    					Node temp = new Node();
		    	    			temp.str = st.toString();
		    	    			temp.pre = cur;
		    	    			cur.next = temp;
		    	    			cur = temp;
	    					}
	    				}
		    			st = new StringBuilder();
		    			isDoubleDot = false;
	    				continue;
	    			}
	    			if (! st.toString().equals(".")) {
		    			Node temp = new Node();
		    			temp.str = st.toString();
		    			temp.pre = cur;
		    			cur.next = temp;
		    			cur = temp;
	    			}
	    			st = new StringBuilder();
	    			isDoubleDot = false;
	    			isLastDot = false;
	    			isLastSlash = true;
	    		} else if (c == '.') {
	    			if (isLastDot)
	    				isDoubleDot = true;
	    			st.append(".");
	    			isLastDot = true;
	    			isLastSlash = false;
	    			isDoubleDotLast = false;
	    		} else {
	    			st.append(c);
	    			isDoubleDot = false;
	    			isLastDot = false;
	    			isLastSlash = false;
	    			isDoubleDotLast = false;
	    		}
	    	}
	    	if (isDoubleDotLast && cur != null)
	    		cur.next = null;
	    	if (cur.str == null)
	    		cur.next = null;
	    	if (cur.next != null)
	    		cur.next = null;
	    	Node travel = head;
	    	StringBuilder ans = new StringBuilder();
	    	while (travel != null) {
	    		ans.append(travel.str == null ? "/" : travel.str);
	    		if (travel != head && travel.next != null)
	    			ans.append("/");
	    		travel = travel.next;
	    	}
			return ans.toString();
	    }
	    static class Node {
	    	String str = null;
	    	Node pre;
	    	Node next;
	    	public Node() {}
	    	public Node(String str, Node pre, Node next) {
	    		this.str = str;
	    		this.pre = pre;
	    		this.next = next;
	    	}
	    }
	}
}
