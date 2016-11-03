package nowcoder.zuo;

import tools.ListNode辅助.ListNode;

class Book027_向有序的环形单链表里面插入新节点 {
	public static void main(String[] args) {
		test();
	}
	static void test() {
		int n = 10;
		int min = 0;
		int max = 100;
		ListNode head = tools.ListNode辅助.A_一维生成器(tools.Random随机生成器.A_生成一个不重复随机数据(n, min, max));
		int num = (int)(Math.random() * (max - min + 1));
		InsertSolution is = new InsertSolution();
		ListNode newHead = is.insert(head, num);
		int sizeOfLine = n + 100;
		tools.ListNode辅助.B_打印链表_一行(newHead, sizeOfLine);
	}
	static class InsertSolution {
		public ListNode insert(ListNode head, int num) {
			ListNode node = new ListNode(num);
			if (head == null) {
				node.next = node;
				return node;
			}
			ListNode pre = head;
			ListNode cur = head.next;
			while (cur != head) {
				if (pre.val < num && cur.val >= num) {
					break;
				}
				pre = cur;
				cur = cur.next;
			}
			pre.next = node;
			node.next = cur;
			return head.val < num ? head : node;
		}
	}
}
