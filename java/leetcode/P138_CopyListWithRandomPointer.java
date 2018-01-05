package leetcode;

/*
 * 	A linked list is given such that each node contains an additional
 * 	 random pointer which could point to any node in the list or null.

	Return a deep copy of the list.
 */

public class P138_CopyListWithRandomPointer {
	public static void main(String[] args) {
		RandomListNode r1 = new RandomListNode(1);
		RandomListNode r2 = new RandomListNode(2);
		RandomListNode r3 = new RandomListNode(3);
		RandomListNode r4 = new RandomListNode(4);
		RandomListNode r5 = new RandomListNode(5);
		r1.next = r2;
		r2.next = r3;
		r3.next = r4;
		r4.next = r5;
		r1.random = r3;
		r2.random = r1;
		r3.random = r5;
		r4.random = r2;
		r5.random = r4;
		RandomListNode r_travel = r1;
		while (r_travel != null) {
			System.out.println(r_travel);
			r_travel = r_travel.next;
		}
		System.out.println("===============");
		Solution s = new Solution();
		r_travel = s.copyRandomList(r1);
		while (r_travel != null) {
			System.out.println(r_travel);
			r_travel = r_travel.next;
		}
		System.out.println("===============");
		r_travel = r1;
		while (r_travel != null) {
			System.out.println(r_travel);
			r_travel = r_travel.next;
		}
	}
	/*
	 * 	3 ms
	 * 	67.50%
	 */
	static class Solution {
	    public RandomListNode copyRandomList(RandomListNode head) {
	    	if (head == null) {
	    		return head;
	    	}
	    	RandomListNode travel = head, travel_next_save = null;
	    	while (travel != null) {
	    		travel_next_save = travel.next;
	    		
	    		RandomListNode travel_ghost = new RandomListNode(travel.label);
	    		travel_ghost.next = travel_next_save;
	    		travel.next = travel_ghost;
	    		
	    		travel = travel_next_save;
	    	}
	    	travel = head;
	    	while (travel != null) {
	    		travel_next_save = travel.next;
	    		travel_next_save.random = travel.random == null ? null : travel.random.next;
	    		travel = travel_next_save.next;
	    	}
	    	
	    	RandomListNode list1 = head, list1_save = null;
	    	RandomListNode list2 = head.next, list2_save = null;
	    	RandomListNode ans = list2;
	    	while (list1 != null) {
	    		list1_save = list1.next == null ? null : list1.next.next;
	    		list2_save = list2.next == null ? null : list2.next.next;
	    		list1.next = list1_save;
	    		list2.next = list2_save;
	    		list1 = list1_save;
	    		list2 = list2_save;
	    	}
	        return ans;
	    }
	}
	static class RandomListNode {
		int label;
		RandomListNode next, random;
		RandomListNode(int x) { this.label = x; }
		@Override
		public String toString() {
			return String.format("label: %d \tnext: %d \trandom: %d\t", label, next== null ? 0 : next.label, random == null ? 0 : random.label);
		}
	}
}
