package leetcode;

public class P052_NQueensII {
	public static void main(String[] args) {
		System.out.println(new Solution1().totalNQueens(8));
	}
	/*
	 * 	2 ms
	 * 	80.45%
	 */
	static class Solution1 {
		int ans = 0;
		boolean[] col = null, rup = null, lup = null;
	    public int totalNQueens(int n) {
	    	if (n < 1)
				return ans;
			col = new boolean[n];
			rup = new boolean[(n << 1) - 1];
			lup = new boolean[rup.length];
			search(0);
			return ans;
	    }
	    private void search(int index) {
			if (index == col.length) {
				ans ++;
				return;
			}
			for (int i = 0; i != col.length; i ++) {
				if (! col[i] && ! lup[index + i] && ! rup[col.length - 1 + index - i]) {
					col[i] = lup[index + i] = rup[col.length - 1 + index - i] = true;
					search(index + 1);
					col[i] = lup[index + i] = rup[col.length - 1 + index - i] = false;
				}
			}
		}
	}
}
