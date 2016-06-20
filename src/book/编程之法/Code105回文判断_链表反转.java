package book.编程之法;

import tools.Models.Node;
import tools.Utils;

public class Code105回文判断_链表反转 {
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
		Utils.printNode(node0);
		System.out.println("==========");
		node2.next = reverse(node3, node8);
		Utils.printNode(node0);
	}
	/*
	 * 	注意链表反转的第二个参数不是加入反转的Node
	 */
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
