package nowcoder.zuo;

/*
 * 	STATUS : AC
 */

import java.util.Arrays;

public class C04最长回文子串 {
	public static void main(String[] args) {
//		String A = "01232101232";
//		String A = "012";
		String A = "baabccc";
		System.out.println(getLongestPalindrome(A, A.length()));
	}
    public static int getLongestPalindrome(String A, int n) {
    	char[] c = new char[(n<<1)+1];
    	Arrays.fill(c, '#');
    	for (int index = 0; index < n; index ++) {
    		c[(index << 1) + 1] = A.charAt(index);
    	}
    	int arr[] = new int[c.length];
    	arr[0] = 1;
    	int RightEdge = 1, indexOfCenter = 0;
    	int newLeft, newRight;
    	int recordOfMaxLength = 1;
    	for (int index = 1; index < c.length; index ++) {
    		if (index == 13 || index == 1 || index == c.length - 1) {
    			System.out.println();
    		}
    		if (RightEdge >= c.length) {
    			break;
    		}
    		if (index >= RightEdge) {
    			indexOfCenter = index;
    			newLeft = index;
    			newRight = index;
    			while(newLeft >= 0 && newRight < c.length) {
    				if (c[newLeft] != c[newRight])	break;
    				newLeft --;
    				newRight ++;
    			}
    			arr[index] = (newRight - newLeft) >> 1;
    			if (arr[index] + index > RightEdge)
    				RightEdge = arr[index] + index;
    			if (arr[index] > recordOfMaxLength)
    				recordOfMaxLength = arr[index];
    		} else {
    			int mirror = (indexOfCenter << 1) - index;
    			int length = arr[mirror] + index;
    			if (length == RightEdge) {
        			indexOfCenter = index;
        			newLeft = index;
        			newRight = index;
        			while(newLeft >= 0 && newRight < c.length) {
        				if (c[newLeft] != c[newRight])	break;
        				newLeft --;
        				newRight ++;
        			}
        			arr[index] = (newRight - newLeft) >> 1;
        			if (arr[index] + index > RightEdge)
        				RightEdge = arr[index] + index;
        			if (arr[index] > recordOfMaxLength)
        				recordOfMaxLength = arr[index];
    			} else if (length < RightEdge){
    				arr[index] = arr[mirror];
    			} else {
    				arr[index] = RightEdge - index;
    			}
    		}
    	}
    	return (recordOfMaxLength - 1);
    }
}
