package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/*
 * 	The gray code is a binary numeral system where two successive values
 *  differ in only one bit.

	Given a non-negative integer n representing the total number of bits
	 in the code, print the sequence of gray code. A gray code sequence must 
	 begin with 0.
	
	For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
	
	00 - 0
	01 - 1
	11 - 3
	10 - 2
	Note:
	For a given n, a gray code sequence is not uniquely defined.
	
	For example, [0,2,3,1] is also a valid gray code sequence according to
	 the above definition.
	
	For now, the judge is able to judge based on one instance of gray code sequence.
	 Sorry about that.
 */

public class P089_GrayCode {
	public static void main(String[] args) {
		List<Integer> ans = new Solution().grayCode(0);
		tools.Utils.B_打印List_Integer(ans);;
	}
	/*
	 * 	WA了一个输入为0
	 * 	AC
	 * 	5 ms
	 */
	static class Solution {
		HashSet<Integer> set = new HashSet<>();
		List<Integer> ans = new LinkedList<Integer>();
		int[] arr = null;
		int count = 0, n = 0, set_max_size = 0;
		boolean isDone = false;
	    public List<Integer> grayCode(int n) {
	        if (n < 0) {
	        	return ans;
	        }
	        this.n = n;
	        set_max_size = 1 << n;
	        arr = new int[n];
	        ans.add(0);
	        set.add(0);
	        search();
	        return ans;
	    }
		private void search() {
			if (set.size() == set_max_size) {
				isDone = true;
				return;
			}
			if (isDone) {
				return;
			}
			for (int i = n - 1;! isDone &&  i > -1; i --) {
				arr[i] = arr[i] == 0 ? 1 : 0;
				if (add()) {
					search();
				}
				arr[i] = arr[i] == 0 ? 1 : 0;
			}
		}
		private boolean add() {
			int val = 0;
			for (int i = 0; i != arr.length; i ++) {
				val = val * 2 + arr[i];
			}
			if (! set.contains(val)) {
				ans.add(val);
				set.add(val);
				return true;
			} else {
				return false;
			}
		}
	}
}
