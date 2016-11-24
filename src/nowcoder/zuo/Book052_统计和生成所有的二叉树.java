package nowcoder.zuo;

import java.util.LinkedList;
import java.util.List;

import tools.TreeNode辅助.TreeNode;

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book052_统计和生成所有的二叉树.java
 * @type        Book052_统计和生成所有的二叉树
 * @date        2016年11月24日 下午7:46:33
 * @details     给定一个整数N，　N<1，代表空树
 * @details     否则，代表中序遍历是{1, 2, 3, 4, 5.... N}
 * @details    　	 请返回可能的二叉树结构有多少
 */
public class Book052_统计和生成所有的二叉树 {
	public static void main(String[] args) {
		debugBookSolution2();
	}
	
	static void debugBookSolution2() {
		int[] arr = new int[] {4, 2, 6, 1, 3, 5, 7};
		for (int index = 0; index < arr.length; index ++) arr[index] = index;
		TreeNode[] ns = new TreeNode[arr.length];
		for (int index = 0; index < ns.length; index ++)  ns[index] = new TreeNode(arr[index]);
		for (int index = 0; index < (ns.length + 1) / 2 - 1; index ++) {
			ns[index].left = ns[index * 2 + 1];
			ns[index].right = ns[index * 2 + 2];
		}
		BookSolution2 s = new BookSolution2();
		List<TreeNode> list = s.generateTrees(arr.length);
		BookSolution1 ss = new BookSolution1();
		System.out.println(ss.numOfTrees(arr.length) == list.size());
		
		
		System.out.println("#####");
		for ( TreeNode t : list) {
			System.out.println(t == null ? "null" : t.val);
		}
	}

	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book052_统计和生成所有的二叉树.java
	 * @type        BookSolution1
	 * @date        2016年11月24日 下午7:50:19
	 * @details     容易理解，
	 */
	static class BookSolution1 {
		public int numOfTrees(int n) {
			if (n < 2)	return 1;
			int[] num = new int[n + 1];
			num[0] = 1;
			for (int i = 1; i < n + 1; i ++) {
				for (int j = 1; j < i + 1; j ++) {
					num[i] += num[j - 1] * num[i - j];
				}
			}
			return num[n];
		}
	}
	
	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book052_统计和生成所有的二叉树.java
	 * @type        BookSolution2
	 * @date        2016年11月24日 下午7:53:31
	 * @details     如果要生成所有中序遍历是 {a, b, c ... d}的所有结构
	 * @details     从a一直到b，枚举每一个值作为头节点，把每次生成的二叉树
	 * @details     结构的头节点都保存下来
	 * @details     
	 * @details    　　这个是进阶问题， 
	 * @details     N的意义不变，假设可能的二叉树结构有M种，返回M个二叉树的头节点.
	 */
	static class BookSolution2 {
		public List<TreeNode> generateTrees(int n) {
			return generateTrees_internal(1, n);
		}

		private List<TreeNode> generateTrees_internal(int sti, int eni) {
			List<TreeNode> res = new LinkedList<TreeNode>();
			if (sti > eni)	res.add(null);
			TreeNode head = null;
			for (int i = sti; i <= eni; i ++) {
				head = new TreeNode(i);
				List<TreeNode> lsub = generateTrees_internal(sti, i - 1);
				List<TreeNode> rsub = generateTrees_internal(i + 1, eni);
				for (TreeNode l : lsub) {
					for (TreeNode r : rsub) {
						head.left = l;
						head.right = r;
						res.add(cloneTree(head));
					}
				}
				
			}
			return res;
		}

		private TreeNode cloneTree(TreeNode head) {
			if (head == null)	return null;
			TreeNode res = new TreeNode(head.val);
			res.left = cloneTree(head.left);
			res.right = cloneTree(head.right);
			return res;
		}
		
	}
	
}
