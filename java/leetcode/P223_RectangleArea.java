package leetcode;

public class P223_RectangleArea {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
	}
	/*
	 * 	4 ms
	 * 	53.61%
	 */
	static class Solution {
	    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
	        long area1 = compute(A, B, C, D);
	        long area2 = compute(E, F, G, H);
	        int lenOfX = computeLayer(A, C, E, G);
	        int lenOfY = computeLayer(B, D, F, H);
	        long area3 = (long)lenOfX * lenOfY;
	        return (int) (area1 + area2 - area3);
	    }
		private long compute(int a, int b, int c, int d) {
			return (long)(d-b) * (c-a);
		}
		int computeLayer(int As, int Ab, int Bs, int Bb) {
			if (As >= Bb) {
				return 0;
			}
			if (Bs >= Ab) {
				return 0;
			}
			if (As >= Bs) {
				return Math.min(Bb - As, Ab - As);
			} else {
				return Math.min(Bb - Bs, Ab - Bs);
			}
		}
	}
}
