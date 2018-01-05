package leetcode;

public class P008_StringToInteger {
	public static void main(String[] args) {
//		System.out.println();
		System.out.println(new Solution1().myAtoi("   -1123u3761867"));
	}
	//重点还是在判断溢出上面。
	/*
	 * 	atoi多奇葩
	 * 	4 ms
	 * 	35.83%
	 */
	static class Solution1 {
	    public int myAtoi(String str) {
	    	int len = 0;
	        if (str == null)
	        	return 0;
	        str = str.trim();
	        if ((len = str.length()) == 0)
	        	return 0;
	        int offset = 0;
	        boolean isNegative = false;
	        switch (str.charAt(0)) {
	        case '-':
	        	offset = 1;
	        	isNegative = true;
	        	break;
	        case '+':
	        	offset = 1;
	        	break;
        	default:
        		break;
	        }
	        int ans = 0;
	        for (int i = offset ; i != len; i ++) {
	        	char c = str.charAt(i);
	        	if (c >= '0' && c <= '9') {
	        		int add = c - '0';
	        		if (ans > 214748364 || (ans == 214748364 && add > 7 + (isNegative ? 1 : 0)))
	        			return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
	        		if (ans == 214748364 && add == 8 && isNegative && i == len - 1)
	        			return Integer.MIN_VALUE;
	        		ans = ans * 10 + c - '0';
	        	} else
	        		return isNegative ? -ans : +ans;
	        }
	        return isNegative ? -ans : +ans;
	    }
	}
	/*
	 * 	81.44%
	 * 	3ms
	 * 	最短。
	 */
	static class Solution2 {
	    public int myAtoi(String str) {
	    	int len = 0;
	        if (str == null)
	        	return 0;
	        if ((len = str.length()) == 0)
	        	return 0;
	        int offset = 0;
	        while (str.charAt(offset) == ' ') {
	        	offset ++;
	        }
	        if (offset >= len)
	        	return 0;
	        boolean isNegative = false;
	        switch (str.charAt(offset)) {
	        case '-':
	        	offset ++;
	        	isNegative = true;
	        	break;
	        case '+':
	        	offset ++;
	        	break;
        	default:
        		break;
	        }
	        int ans = 0;
	        for (int i = offset ; i != len; i ++) {
	        	char c = str.charAt(i);
	        	if (c >= '0' && c <= '9') {
	        		int add = c - '0';
	        		if (ans > 214748364 || (ans == 214748364 && add > 7 + (isNegative ? 1 : 0)))
	        			return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
	        		if (ans == 214748364 && add == 8 && isNegative && i == len - 1)
	        			return Integer.MIN_VALUE;
	        		ans = ans * 10 + c - '0';
	        	} else
	        		return isNegative ? -ans : +ans;
	        }
	        return isNegative ? -ans : +ans;
	    }
	}
}
