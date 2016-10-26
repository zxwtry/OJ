package leetcode;

import java.util.LinkedList;
import java.util.List;

public class P412_FizzBuzz {
	public static void main(String[] args) {
		
	}
	/*
	 * 	AC
	 * 	5 ms
	 * 	20.25%
	 */
	static class Solution {
	    public List<String> fizzBuzz(int n) {
	        List<String> ans = new LinkedList<>();
	        int index = 1;
	        while (index <= n) {
	        	String s = null;
	        	if (index % 15 == 0) {
	        		s = "FizzBuzz";
	        	} else if (index % 3 == 0) {
	        		s = "Fizz";
	        	} else if (index % 5 == 0) {
	        		s = "Buzz";
	        	} else {
	        		s = "" + index;
	        	}
	        	ans.add(s);
	        	index ++;
	        }
	        return ans;
	    }
	}
}
