package book.编程之法;

import tools.Models.Node;

/*
 * 	这个不保证实现
 * 	比这个更加重要的是链表的反转
 * 	
 */

public class Code105回文判断_链表回文 {
	public static void main(String[] args) {
		Node node9 = new Node(9);
		Node node8 = new Node(8, node9);
		Node node7 = new Node(7, node8);
		Node node6 = new Node(6, node7);
		Node node5 = new Node(5, node6);
		Node node4 = new Node(4, node5);
		Node node3 = new Node(3, node4);
		Node node2 = new Node(2, node3);
		Node node1 = new Node(1, node2);
		Node node0 = new Node(0, node1);
		solve1(node0);
	}
	/*
	 * 	list是可以修改的
	 */
	static void solve1(Node list) {
		System.out.println(linkedlistPalindrome(list, 0, 8));
	}
	static boolean linkedlistPalindrome(Node list, int sta, int end) {
		if (list == null || sta < 0 || end < 0 || sta > end)
			return false;
		int len = 0;
		Node tmp = list, node_sta = null, node_end = null, node_pre = null;
		while (true) {
			tmp = tmp.next;
			if (tmp == null)
				break;
			if (len == sta-1)
				node_pre = tmp;
			if (len  == sta)
				node_sta = tmp;
			if (len == end)
				node_end = tmp;
			len ++;
		}
		if (sta > len || end > len)
			return false;
		if (node_pre == null) {
			node_sta = reverse(node_sta, node_end.next);
		} else {
			node_pre.next = reverse(node_sta, node_end.next);
		}
		System.out.println(node_sta.data);
		System.out.println(node_end.data);
		return true;
	}
	static Node reverse(Node sta, Node end_next) {
		Node node = sta, pre = null;
		while (node != end_next) {
			Node tmp = node.next;
			node.next = pre;
			pre = node;
			node = tmp;
		}
		sta.next = end_next;
		return pre;
	}
}
