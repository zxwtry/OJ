package leetcode;

import java.util.LinkedList;
import java.util.List;

/*
 * 	Given an array of words and a length L, format the text 
 * 	such that each line has exactly L characters and
 * 	 is fully (left and right) justified.

	You should pack your words in a greedy approach; 
	that is, pack as many words as you can in each line.
	 Pad extra spaces ' ' when necessary so that each line has exactly L characters.
	
	Extra spaces between words should be distributed as evenly as possible. 
	If the number of spaces on a line do not divide evenly between words, 
	the empty slots on the left will be assigned more spaces than the slots 
	on the right.
	
	For the last line of text, it should be left justified and
	 no extra space is inserted between words.
	
	For example,
	words: ["This", "is", "an", "example", "of", "text", "justification."]
	L: 16.
	
	Return the formatted lines as:
	[
	   "This    is    an",
	   "example  of text",
	   "justification.  "
	]
	Note: Each word is guaranteed not to exceed L in length.
 * 	
 */


public class P068_TextJustification {
	public static void main(String[] args) {
		List<String> ans = new Solution1().fullJustify(new String[]{"a"}, 1);
		tools.Utils.B_打印List_String(ans);
	}
	/*
	 * 	1 ms
	 * 	又是一个试错AC
	 * 	这里的逻辑本身非常复杂
	 */
	static class Solution1 {
		int[] len = null;
	    public List<String> fullJustify(String[] words, int maxWidth) {
			List<String> ans = new LinkedList<String>();
	    	if (words == null || words.length == 0)
	    		return ans;
	    	if (maxWidth == 1 && words.length == 1 && words[0].equals("")) {
	    		ans.add("");
	    		return ans;
	    	}
	    	len = new int[words.length];
	    	char[] cs = new char[maxWidth];
	    	for (int i = 0; i != len.length; i ++)
	    		len[i] = words[i] == null ? 0 : words[i].length();
	    	int sum = 0, index = -1, preIndex = 0;
	    	while (++ index != words.length) {
	    		if (index == words.length - 1) {
	    			if (sum + len[index] <= maxWidth) {
	    				int i = 0;
	    				for (int j = preIndex; j <= index; j ++) {
	    					for (int k = 0; k != len[j]; k ++)
	    						cs[i ++] = words[j].charAt(k);
	    					if (i < maxWidth)
	    						cs[i ++] = ' ';
	    				}
	    				while (i < maxWidth)
	    					cs[i ++] = ' ';
	    				ans.add(new String(cs));
	    			} else {
	    				ans.add(generateString(words, preIndex, index - 1, sum - 1, maxWidth, cs));
	    				for (int i = 0; i != maxWidth; i ++)
	    					cs[i] = i < len[index] ? words[index].charAt(i) : ' ';
	    				ans.add(new String(cs));
	    			}
	    			break;
	    		}
	    		if (sum + len[index] == maxWidth || sum + len[index] + 1 == maxWidth) {
	    			ans.add(generateString(words, preIndex, index, sum + len[index], maxWidth, cs));
	    			sum = 0;
	    			preIndex = index + 1;
	    		} else if (sum + len[index] + 1 < maxWidth) {
	    			sum += len[index] + 1;
 	    		} else {
	    			//	sum + len[index] + 1 > maxWidth
	    			ans.add(generateString(words, preIndex, index - 1, sum - 1, maxWidth, cs));
	    			sum = len[index] + 1;
	    			preIndex = index;
	    		}
	    	}
	        return ans;
	    }
	    private String generateString(String[] words, int preIndex, int newIndex, int sum, int maxWidth, char[] cs) {
	    	if (newIndex == preIndex) {
	    		for (int i = 0; i != maxWidth; i ++)
	    			cs[i] = i < len[preIndex] ? words[preIndex].charAt(i) : ' ';
	    		return new String(cs);
	    	}
	    	int allBlank = maxWidth;
	    	for (int i = preIndex; i <= newIndex; i ++)
	    		allBlank -= len[i];
	    	int blank = allBlank / (newIndex - preIndex);
	    	int moreBlank = allBlank % (newIndex - preIndex) + preIndex;
	    	int i = 0;
	    	for (int index = preIndex; index <= newIndex; index ++) {
	    		int thisBlank = blank + (index < moreBlank ? 1 : 0);
	    		for (int j = 0; j < len[index]; j ++)
	    			cs[i ++] = words[index].charAt(j);
	    		for (int j = 0; index != newIndex && j != thisBlank; j ++)
	    			cs[i ++] = ' ';
	    	}
	    	return new String(cs);
	    }
	}
}
