package nowcoder.zuo;

public class C08最长上升子序列 {
	static int[] arr;
	public static void main(String[] args) {
		
	}
	static void solve() {
		int len = arr.length;
		P[] ps = new P[len];
		ps[0] = new P(arr[0], -1, 1);
		for (int index = 1; index < len; index ++) {
			if ()
		}
	}
	static class P {
		int pre;
		int cou;
		int val;
		public P (int val) {
			this.val = val;
		}
		public P (int val, int pre, int cou) {
			this.val = val;
			this.pre = pre;
			this.cou = cou;
		}
	}
}
