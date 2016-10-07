package leetcode;

/*
 * 	Compare two version numbers version1 and version2.
	If version1 > version2 return 1, if version1 < version2 
	return -1, otherwise return 0.
	
	You may assume that the version strings are non-empty 
	and contain only digits and the . character.
	The . character does not represent a decimal point 
	and is used to separate number sequences.
	For instance, 2.5 is not "two and a half" or "half 
	way to version three", it is the fifth second-level 
	revision of the second first-level revision.
	
	Here is an example of version numbers ordering:
	
	0.1 < 1.1 < 1.2 < 13.37
	Credits:
	Special thanks to @ts for adding this problem and 
	creating all test cases.
 */

public class P165_CompareVersionNumbers {
	public static void main(String[] args) {
//		int count = 0;
//		for (int times = 0; times < 1000; times ++) {
//			int val10 = (int) (Math.random() * 10000);
//			int val11 = (int) (Math.random() * 10000);
//			int val20 = (int) (Math.random() * 10000);
//			int val21 = (int) (Math.random() * 10000);
//			String version1 = String.format("%d.%d", val10, val11);
//			String version2 = String.format("%d.%d", val20, val21);
//			
//			version1 = "0000000.9";
//			version2 = "200000.9";
//			
//			Solution s = new Solution();
//			int solution = s.compareVersion(version1, version2);
//			
//			int standard = 0;
//			if (val10 != val20) {
//				standard = val10 < val20 ? -1 : 1;
//			} else if (val11 != val21) {
//				standard = val11 < val21 ? -1 : 1;
//			}
//			if (solution != standard) {
////				System.out.println(version1);
////				System.out.println(version2);
////				System.out.println(solution + "..." + standard);
//				count ++;
//			}
//		}
//		System.out.println(count);
		

//		testMyCompareEntry();
		
//		testMyCompareVersionEntry();
		
		Solution s = new Solution();
		
		System.out.println(s.compareVersion("1.0.1", "1.0.1"));
		
	}
	
	static void testMyCompareVersionEntry() {
		int count = 0;
		for (int i = 0; i < 10000; i ++) {
			if (! testMyCompareVersion()) {
				count ++;
			}
		}
		System.out.println(count);
	}
	
	static boolean testMyCompareVersion() {
		char[] c1 = new char[8];
		char[] c2 = new char[8];
		char[] c3 = new char[8];
		char[] c4 = new char[8];
		for (int i = 0; i < c1.length; i ++) {
			c1[i] = (char) ('0' + (int) (Math.random() * 10));
			c2[i] = (char) ('0' + (int) (Math.random() * 10));
			c3[i] = (char) ('0' + (int) (Math.random() * 10));
			c4[i] = (char) ('0' + (int) (Math.random() * 10));
//			c1[i] = c3[i];
//			c2[i] = c4[i];
		}
		String s1 = new String(c1);
		String s2 = new String(c2);
		String s3 = new String(c3);
		String s4 = new String(c4);
		
		int c1Int = Integer.parseInt(s1);
		int c2Int = Integer.parseInt(s2);
		int c3Int = Integer.parseInt(s3);
		int c4Int = Integer.parseInt(s4);
		
		int standardAnswer = 0;
		if (c1Int != c3Int) {
			standardAnswer = c1Int > c3Int ? 1 : -1;
		}
		if (standardAnswer == 0 && c2Int != c4Int) {
			standardAnswer = c2Int > c4Int ? 1 : -1;
		}
		
		String version1 = s1 + "." + s2;
//		String version2 = s3 + "." + s4;
		String version2 = s3;
		
		int solutionAnswer = new Solution().compareVersion(version1, version2);
		
		System.out.println(standardAnswer + "..." + solutionAnswer);
		
		return standardAnswer == solutionAnswer;
		
	}
	
	static void testMyCompareEntry() {
		int count = 0;
		for (int i = 0; i < 900; i ++) {
			if (! testMyCompare()) {
				count ++;
			}
		}
		System.out.println(count);
	}
	
	static boolean testMyCompare() {
		char[] c1 = new char[8];
		char[] c2 = new char[8];
		for (int i = 0; i < c1.length; i ++) {
			c1[i] = (char) ('0' + (int) (Math.random() * 10));
			c2[i] = (char) ('0' + (int) (Math.random() * 10));
		}
		int c1Int = Integer.parseInt(new String(c1));
		int c2Int = Integer.parseInt(new String(c2));
		
		int standardAnswer = 0;
		if (c1Int != c2Int) {
			standardAnswer = c1Int > c2Int ? 1 : -1;
		}
		
		int solutionAnswer = new Solution().myCompare(new String(c1), new String(c2));
		
		return standardAnswer == solutionAnswer;
		
	}
	/*
	 * 	写得越完善的代码，越是易读
	 * 	3 ms
	 * 	26.95%
	 */
	static class Solution {
	    public int compareVersion(String version1, String version2) {
	    	String[] parts1 = version1.split("\\.");
	    	String[] parts2 = version2.split("\\.");
	    	int p1i = 0, p2i = 0;
	    	int p1End = parts1.length - 1, p2End = parts2.length - 1;
	    	while (p1i <= p1End && p2i <= p2End) {
	    		int cut = myCompare(parts1[p1i], parts2[p2i]);
	    		if (cut == 0) {
		    		p1i ++;
		    		p2i ++;
	    		} else {
	    			return cut < 0 ? -1 : 1;
	    		}
	    	}
	    	if (p1i <= p1End) {
	    		for (int i = p1i; i <= p1End; i ++) {
	    			if (! isStringAll0(parts1[i])) {
	    				return 1;
	    			}
	    		}
	    	}
	    	if (p2i <= p2End) {
	    		for (int i = p2i; i <= p2End; i ++) {
	    			if (! isStringAll0(parts2[i])) {
	    				return -1;
	    			}
	    		}
	    	}
	        return 0;
	    }
	    boolean isStringAll0(String str) {
	    	for (int i = 0; i < str.length(); i ++) {
	    		if (str.charAt(i) != '0') {
	    			return false;
	    		}
	    	}
	    	return true;
	    }
	    int myCompare(String str1, String str2) {
	    	int i1 = 0, i1End = str1.length() - 1;
	    	int i2 = 0, i2End = str2.length() - 1;
	    	while (i1 <= i1End) {
	    		if (str1.charAt(i1) == '0') {
	    			i1 ++;
	    		} else {
	    			break;
	    		}
	    	}
	    	while (i2 <= i2End) {
	    		if (str2.charAt(i2) == '0') {
	    			i2 ++;
	    		} else {
	    			break;
	    		}
	    	}
    		int cut = i1End - i1 - i2End + i2;
    		if (cut != 0) {
    			return cut > 0 ? 1 : -1;
    		}
	    	while (i1 <= i1End && i2 <= i2End) {
 	    		char c1 = str1.charAt(i1);
	    		char c2 = str2.charAt(i2);
	    		if (c1 != c2) {
	    			return c1 > c2 ? 1 : -1;
	    		}
	    		i1 ++;
	    		i2 ++;
	    	}
	    	if (i1 > i1End && i2 <= i2End) {
	    		return -1;
	    	}
	    	if (i2 > i2End && i1 <= i1End) {
	    		return 1;
	    	}
	    	return 0;
	    }
	}
}
