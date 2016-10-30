package nowcoder.zuo;

import tools.ListNode辅助.ListNode;

class Book027_向有序的环形单链表里面插入新节点 {
	public static void main(String[] args) {
		
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
