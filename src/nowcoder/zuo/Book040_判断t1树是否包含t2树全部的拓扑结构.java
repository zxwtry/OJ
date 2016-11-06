package nowcoder.zuo;

import com.sun.org.apache.xml.internal.utils.Hashtree2Node;

import tools.TreeNode辅助.TreeNode;

public class Book040_判断t1树是否包含t2树全部的拓扑结构 {
	public static void main(String[] args) {
		
	}
	static class BookSolution {
		public boolean contains(TreeNode t1, TreeNode t2) {
			return check(t1, t2) || contains(t1.left, t2) || contains(t1.right, t2);
		}
		boolean check(TreeNode h, TreeNode t) {
			if (h == null) {
				return true;
			}
			if (h == null || h.val != t.val) {
				return false;
			}
			return check(h.left, t.left) && check(h.right, t.right);
		}
	}
}