package nowcoder.zuo;

import tools.TreeNode辅助.TreeNode;

public class Book041_判断t1树中是否有与t2树拓扑结构完全相同的子树 {
	public static void main(String[] args) {
		
	}
	static class BookSolution {
		public boolean isSubtree(TreeNode t1, TreeNode t2) {
			String t1Str = serialByPre(t1);
			String t2Str = serialByPre(t2);
			return getIndexOf(t1Str, t2Str) != -1;
		}
		private String serialByPre(TreeNode head) {
			if (head == null) {
				return "#!";
			}
			String res = head.val + "!";
			res += serialByPre(head.left);
			res += serialByPre(head.right);
			return res;
		}
		private int getIndexOf(String s, String p) {
			if (s == null || p == null || s.length() == 0 || p.length() == 0) {
				return -1;
			}
			char[] cs = s.toCharArray();
			char[] cp = p.toCharArray();
			int[] next = getNext(cp);
			int si = 0;
			int pi = 0;
			while (si < cs.length && pi < cp.length) {
				if (cs[si] == cp[pi]) {
					si ++;
					pi ++;
				} else if (next[pi] == -1) {
					si ++;
				} else {
					pi = next[pi];
				}
			}
			return pi == cp.length ? si - pi : -1;
		}
		private int[] getNext(char[] cp) {
			if (cp.length == 1) {
				return new int[]{ -1 };
			}
			int[] next = new int[cp.length];
			next[0] = -1;
			next[1] = 0;
			int pos = 2;
			int cn = 0;
			while (pos < cp.length) {
				if (cp[pos - 1] == cp[cn]) {
					next[pos ++] = next[cn];
				} else if (cn > 0) {
					cn = next[cn];
				} else {
					next[pos ++] = 0;
				}
			}
			return next;
		}
	}
}
