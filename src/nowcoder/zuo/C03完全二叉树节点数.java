package nowcoder.zuo;

public class C03完全二叉树节点数 {
	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		TreeNode node8 = new TreeNode(8);
		TreeNode node9 = new TreeNode(9);
		TreeNode node10 = new TreeNode(10);
		TreeNode node11 = new TreeNode(11);
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.left = node6;
		node3.right = node7;
		node4.left = node8;
		node4.right = node9;
		node5.left = node10;
		node5.right = node11;
		System.out.println(nodeNum(node1));
	}
    public static int nodeNum(TreeNode head) {
    	if (null == head) {
    		return 0;
    	}
    	if (head.left == null && head.right == null) {
    		return 1;
    	}
    	if (head.left != null && head.right == null) {
    		return 2;
    	}
    	int len0 = lenOfTree(head);
    	int len1 = lenOfTree(head.right);
    	if (len0 == len1 + 1) {
    		return (1 << len1) + nodeNum(head.right);
    	} else {
    		return (1 << (len1)) + nodeNum(head.left) ;
    	}
    }
    static int lenOfTree(TreeNode head) {
    	TreeNode copy = head;
    	int len = 0;
    	while(copy != null) {
    		len ++;
    		copy = copy.left;
    	}
    	return len;
    }
    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val) {
            this.val = val;
        }
    }
}
