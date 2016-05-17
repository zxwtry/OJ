package nowcoder.zuo;

import java.util.Arrays;

public class C04最长回文子串 {
	public static void main(String[] args) {
		String A = "01232101232";
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
    		if (index == 13 || index == 1) {
    			System.out.println();
    		}
    		if (RightEdge >= c.length)	break;
    		if (index >= RightEdge) {
    			indexOfCenter = index;
    			newLeft = index - 1;
    			newRight = index + 1;
    			RightEdge ++;
    			while(newLeft >= 0 && newRight < c.length) {
    				if (c[newLeft] != c[newRight])	break;
    				newLeft --;
    				newRight ++;
    				RightEdge ++;
    			}
    			arr[index] = ((newRight - newLeft + 1) >> 1) + 1;
    			if (arr[index] > recordOfMaxLength)
    				recordOfMaxLength = arr[index];
    		} else {
    			int mirror = (indexOfCenter << 1) - index;
    			int length = arr[mirror] + indexOfCenter - mirror;
    			if (length == arr[indexOfCenter]) {
        			indexOfCenter = index;
        			newLeft = index - 1;
        			newRight = index + 1;
        			RightEdge ++;
        			while(newLeft >= 0 && newRight < c.length) {
        				if (c[newLeft --] != c[newRight ++])	break;
        				RightEdge ++;
        			}
        			arr[index] = newRight - newLeft + 1;
        			if (arr[index] > recordOfMaxLength)
        				recordOfMaxLength = arr[index];
    			} else if (arr[mirror] + indexOfCenter - mirror < arr[indexOfCenter]){
    				arr[index] = arr[mirror];
    			} else {
    				arr[index] = RightEdge - index;
    			}
    		}
    	}
    	return (recordOfMaxLength>>1)-1;
    }
}
