package leetcode;

public class P050_PowXN {
	static class Solution {
	    public double myPow(double x, int n) {
	        if (n == 0) {
	            return 1;
	        }
	        if (n < 0) {
	            if (n == Integer.MIN_VALUE) {
	                return 1.0 / ( x * myPow(x, Integer.MAX_VALUE) );
	            }
	            return 1.0 / myPow(x, - n);
	        }
	        if (n % 2 == 1) {
	            double v = myPow(x, n / 2);
	            return v * v * x;
	        } else {
                double v = myPow(x, n / 2);
                return v * v;
            }
	    }
	}
	
}
