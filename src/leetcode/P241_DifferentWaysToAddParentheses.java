package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 	Given a string of numbers and operators, return all possible 
 * 	results from computing all the different possible ways to group
 *  numbers and operators. The valid operators are +, - and *.
 *	
 *	
 *	Example 1
 *	Input: "2-1-1".
 *	
 *	((2-1)-1) = 0
 *	(2-(1-1)) = 2
 *	Output: [0, 2]
 *	
 *	
 *	Example 2
 *	Input: "2*3-4*5"
 *	
 *	(2*(3-(4*5))) = -34
 *	((2*3)-(4*5)) = -14
 *	((2*(3-4))*5) = -10
 *	(2*((3-4)*5)) = -10
 *	(((2*3)-4)*5) = 10
 *	Output: [-34, -14, -10, -10, 10]
 *	
 *	Credits:
 *	Special thanks to @mithmatt for adding this problem and creating all test cases.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P241_DifferentWaysToAddParentheses.java
 * @type        P241_DifferentWaysToAddParentheses
 * @date        2016年12月12日 下午10:00:28
 * @details     Solution1: WA 会多一些重复项
 * @details     Solution2: AC 19ms  3.58%
 */
public class P241_DifferentWaysToAddParentheses {
	static class Solution1 {
		List<Integer> ans = null;
		int[] intSplits = null;
		String[] signSplits = null;
	    public List<Integer> diffWaysToCompute(String input) {
	        ans = new LinkedList<Integer>();
	        String[] inputSplits = input.split("[\\*+-]");
	        intSplits = new int[inputSplits.length];
	        for (int index = 0; index < inputSplits.length; index ++) {
	        	intSplits[index] = Integer.parseInt(inputSplits[index]);
	        }
	        signSplits = input.split("[0-9]+");
	        if (intSplits.length == 1 && signSplits.length == 0) {
	        	ans.add(intSplits[0]);
	        	return ans;
	        }
	        if (intSplits.length == signSplits.length && intSplits.length > 0) {
	        	return search(0);
	        }
	        return ans;
	    }
	    private LinkedList<Integer> search(int index) {
	    	if (index >= intSplits.length) return new LinkedList<Integer>();
	    	if (index == intSplits.length - 1) {
	    		LinkedList<Integer> tmp = new LinkedList<Integer>();
	    		tmp.add(intSplits[index]);
	    		return tmp;
	    	} else {
	    		//两种情况|
	    		LinkedList<Integer> tmp = new LinkedList<Integer>();
	    		int save = intSplits[index + 1];
	    		int res1_op = operate(intSplits[index], intSplits[index + 1], signSplits[index + 1]); 
	    		intSplits[index + 1] = res1_op;
	    		LinkedList<Integer> res1 = search(index + 1);
	    		intSplits[index + 1] =  save;
	    		tmp.addAll(res1);
	    		
	    		LinkedList<Integer> res2 = search(index + 1);
	    		for (int r : res2) {
	    			tmp.add(operate(intSplits[index], r, signSplits[index + 1]));
	    		}
	    		return tmp;
	    	}
 		}
		public int operate(int a, int b, String sign) {
	    	switch (sign) {
			case "+":
				return a + b;
			case "-":
				return a - b;
			case "*":
				return a * b;
			default:
				break;
			}
	    	return 0;
	    }
	}
	static class Solution2 {
		public List<Integer> diffWaysToCompute(String input) {
			List<Integer> ret = new LinkedList<Integer>();
			for (int i = 0; i < input.length(); i ++) {
				char c = input.charAt(i);
				if (c == '+' || c == '-' || c == '*') {
					List<Integer> left = diffWaysToCompute(input.substring(0, i));
					List<Integer> right = diffWaysToCompute(input.substring(i+1));
					for (int le : left) {
						for (int ri : right) {
							if (c == '+') {
								ret.add(le + ri);
							} else if (c == '-') {
								ret.add(le - ri);
							} else {
								ret.add(le * ri);
							}
						}
					}
				}
			}
			if (ret.isEmpty()) {
				ret.add(Integer.parseInt(input));
			}
			return ret;
		}
	}
}
