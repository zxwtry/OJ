package leetcode;

/*
 * 	Validate if a given string is numeric.

	Some examples:
	"0" => true
	" 0.1 " => true
	"abc" => false
	"1 a" => false
	"2e10" => true
	Note: It is intended for the problem statement to be ambiguous. 
	You should gather all requirements up front before implementing one.

	Update (2015-02-10):
	The signature of the C++ function had been updated. 
	If you still see your function signature accepts a const char * argument, 
	please click the reload button  to reset your code definition.
 */

public class P065_ValidNumber {
	public static void main(String[] args) {
		System.out.println(new Solution().isNumber("  4e+ "));
	}
	/*
	 * 	艰难的试错AC
	 * 	4 ms
	 * 	61.35% 
	 */
	static class Solution {
	    public boolean isNumber(String s) {
	    	char[] cs = s.toCharArray();
	    	int sti = 0, eni = cs.length - 1;
	    	while (sti <= eni && cs[sti] == ' ')	sti ++;
	    	while (eni >= sti && cs[eni] == ' ')	eni --;
	    	if (sti > eni)
	    		return false;
	    	if (sti <= eni && (cs[sti] == '-' || cs[sti] == '+'))
	    		sti ++;
	    	boolean isHasE = false, isHasDot = false, isHasNum = false;
	    	int indexOfE = -1;
	    	for (int i = sti; i <= eni; i ++) {
	    		if (cs[i] == ' ')
	    			return false;
	    		if (cs[i] == 'e' || cs[i] == 'E') {
	    			if (!isHasNum || isHasE || i == sti || i == eni)
	    				return false;
	    			isHasE = true;
	    			indexOfE = i;
	    			if (cs[i + 1] == '-' || cs[i + 1] == '+') {
	    				if (i + 1 == sti)
	    					return false;
	    				i ++;
	    			}
	    			isHasNum = false;
	    			continue;
	    		}
	    		if (cs[i] == '.') {
	    			if (isHasDot)
	    				return false;
	    			isHasDot = true;
	    			if (indexOfE != -1 && indexOfE < i)
	    				return false;
	    			continue;
	    		}
	    		if (! (cs[i] >= '0' && cs[i] <= '9'))
	    			return false;
	    		isHasNum = true;
	    	}
	        return isHasNum;
	    }
	}
}
