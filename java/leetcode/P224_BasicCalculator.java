package leetcode;

import java.util.Stack;
import javax.script.ScriptEngineManager;

/**
 * 	Implement a basic calculator to evaluate a simple expression string.
 *	
 *	The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 *	
 *	You may assume that the given expression is always valid.
 *	
 *	Some examples:
 *	"1 + 1" = 2
 *	" 2-1 + 2 " = 3
 *	"(1+(4+5+2)-3)+(6+8)" = 23
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P224_BasicCalculator.java
 * @type        P224_BasicCalculator
 * @date        2017年2月3日 下午6:51:00
 * @details     Solution1: AC 16ms 75.51%
 * @details     Solution2: 可惜leetcode的编译器不支持javax.script.ScriptEngineManager
 */
public class P224_BasicCalculator {
	static class Solution1 {
		final int addOperator = 1, subtractOperator = -1;
	    public int calculate(String s) {
	    	long ans = 0;
	    	int signOfOperator = addOperator;
	    	char c = '\0';
	    	Stack<Long> stkAns = new Stack<Long>(); 
	    	Stack<Integer> stkSgn = new Stack<Integer>(); 
	        for (int i = 0; i < s.length(); i ++) {
	        	c = s.charAt(i);
	        	switch(c) {
	        	case '+':
	        		signOfOperator = addOperator;
	        		break;
	        	case '-':
	        		signOfOperator = subtractOperator;
	        		break;
	        	case '(':
	        		stkAns.add(ans);
	        		ans = 0;
	        		stkSgn.add(signOfOperator);
	        		signOfOperator = addOperator;
	        		break;
	        	case ')':
	        		ans = stkAns.pop() + (stkSgn.pop() == addOperator ? ans : - ans);
	        		signOfOperator = addOperator;
	        		break;
	        	case ' ': 
	        		break;
	        	default:
	        		int v = c - '0';
	        		while (i+1 < s.length() && Character.isDigit(s.charAt(i+1)))
	        			v = v * 10 + s.charAt(++ i) - '0';
	        		ans  = ans + (signOfOperator == addOperator ? v : -v);
	        		break;
	        	}
	        }
	        return (int) ans;
	    }
	}
	static class Solution2 {
		public int calculate(String s) {
			int ans = 0;
			try {
				ans = (int)(double)new ScriptEngineManager().getEngineByName("js").eval(s);
			} catch (Exception e) {}
			return ans;
		}
	}
}
