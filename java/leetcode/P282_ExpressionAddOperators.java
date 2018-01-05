package leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 	Given a string that contains only digits 0-9 and a target value, 
 * 	return all possibilities to add binary operators (not unary) +, -, 
 * 	or * between the digits so they evaluate to the target value.
 * 	
 * 	Examples: 
 * 	"123", 6 -> ["1+2+3", "1*2*3"] 
 * 	"232", 8 -> ["2*3+2", "2+3*2"]
 * 	"105", 5 -> ["1*0+5","10-5"]
 * 	"00", 0 -> ["0+0", "0-0", "0*0"]
 * 	"3456237490", 9191 -> []
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P282_ExpressionAddOperators.java
 * @type        P282_ExpressionAddOperators
 * @date        2016年12月14日 下午10:24:14
 * @details     Solution1: AC 194ms 79.42%
 */
public class P282_ExpressionAddOperators {
	static class Solution1 {
	    public List<String> addOperators(String n, int t) {
	    	List<String> answerList = new LinkedList<String>();
	    	if (n == null || n.length() < String.valueOf(Math.abs(t)).length())
	    		return answerList;
	    	addOperatorsInternal(answerList, n, 0, t, "", 0, 0);
	    	return answerList;
	    }
		private void addOperatorsInternal(List<String> answerList, String n, int nIndex, long targetValue, String answerString, long nowValue, long multipleValue) {
			if (n.length() == nIndex) {
				if (nowValue == targetValue)  {
					answerList.add(answerString);
				}
				return;
			}
			for (int newNIndex = nIndex; newNIndex < n.length(); newNIndex ++) {
				long currentValue = Long.parseLong(n.substring(nIndex, newNIndex + 1));
				if (newNIndex != nIndex && n.charAt(nIndex) == '0') break;
				if (nIndex == 0) {
					addOperatorsInternal(answerList, n, newNIndex + 1, targetValue, n.substring(nIndex, newNIndex + 1), currentValue, currentValue);
				} else {
					addOperatorsInternal(answerList, n, newNIndex + 1, targetValue, answerString + "+" + n.substring(nIndex, newNIndex + 1), nowValue + currentValue, currentValue);
					addOperatorsInternal(answerList, n, newNIndex + 1, targetValue, answerString + "-" + n.substring(nIndex, newNIndex + 1), nowValue - currentValue, -currentValue);
					addOperatorsInternal(answerList, n, newNIndex + 1, targetValue, answerString + "*" + n.substring(nIndex, newNIndex + 1), nowValue - multipleValue + multipleValue *  currentValue, multipleValue *  currentValue);
				}
			}
		}
	}
}
