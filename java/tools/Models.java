package tools;

public class Models {
	public static class Node {
		public int data = 0;
		public Node next = null;
		public Node(int data, Node next) {
			this.data = data;
			this.next = next;
		}
		public Node(int data) {
			this.data = data;
		}
	}
}