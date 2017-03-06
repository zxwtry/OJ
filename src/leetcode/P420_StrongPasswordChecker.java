package leetcode;

/**
 *  A password is considered strong if below conditions are all met:
 *	
 *	It has at least 6 characters and at most 20 characters.
 *	It must contain at least one lowercase letter, at least one uppercase letter,
 *	 and at least one digit.
 *	It must NOT contain three repeating characters in a row ("...aaa..." is weak,
 *	 but "...aa...a..." is strong, assuming other conditions are met).
 *	Write a function strongPasswordChecker(s), that takes a string s as input, 
 *	and return the MINIMUM change required to make s a strong password. 
 *	If s is already strong, return 0.
 *	
 *	Insertion, deletion or replace of any one character are all considered as one change.
 *	
 *	Subscribe to see which companies asked this question
 */


/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P420_StrongPasswordChecker.java
 * @type        P420_StrongPasswordChecker
 * @date        2016年12月8日 下午10:25:31
 * @details     Solution2: AC 4ms 67.48%
 */
public class P420_StrongPasswordChecker {
	static class Solution2 {
	    public int strongPasswordChecker(String s) {
	        if(s.length()<2) return 6-s.length();
	        char end = s.charAt(0);
	        boolean upper = end>='A'&&end<='Z', lower = end>='a'&&end<='z', digit = end>='0'&&end<='9';
	        int end_rep = 1, change = 0;
	        int[] delete = new int[3];
	        for(int i = 1;i<s.length();++i){
	            if(s.charAt(i)==end) ++end_rep;
	            else{
	                change+=end_rep/3;
	                if(end_rep/3>0) ++delete[end_rep%3];
	                end = s.charAt(i);
	                upper = upper||end>='A'&&end<='Z';
	                lower = lower||end>='a'&&end<='z';
	                digit = digit||end>='0'&&end<='9';
	                end_rep = 1;
	            }
	        }
	        change+=end_rep/3;
	        if(end_rep/3>0) ++delete[end_rep%3];
	        int check_req = (upper?0:1)+(lower?0:1)+(digit?0:1);
	        if(s.length()>20){
	            int del = s.length()-20;
	            if(del<=delete[0]) change-=del;
	            else if(del-delete[0]<=2*delete[1]) change-=delete[0]+(del-delete[0])/2;
	            else change-=delete[0]+delete[1]+(del-delete[0]-2*delete[1])/3;
	            return del+Math.max(check_req,change);
	        }
	        else return Math.max(6-s.length(), Math.max(check_req, change));
	    }
	}
}
