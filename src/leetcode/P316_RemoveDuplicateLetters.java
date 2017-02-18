package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeSet;

/**
 * 	Given a string which contains only lowercase letters, 
 * 	remove duplicate letters so that every letter appear once 
 * 	and only once. You must make sure your result is the smallest 
 * 	in lexicographical order among all possible results.

 *	Example:
 *	Given "bcabc"
 *	Return "abc"
 *	
 *	Given "cbacdcbc"
 *	Return "acdb"
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P316_RemoveDuplicateLetters.java
 * @type        P316_RemoveDuplicateLetters
 * @date        2017年1月4日 下午10:16:14
 * @details     Solution1: WA 数据结构有点多
 * @details     Solution3: AC 54ms  2.36%
 * @details     Solution4: AC 23ms 38.85%
 * @details     Solution5: AC  7ms 78.83%
 */
public class P316_RemoveDuplicateLetters {
    public static void main(String[] args) {
//        TreeSet<Integer> treeSet = new TreeSet<Integer>();
//        treeSet.add(2);
//        treeSet.add(8);
//        treeSet.add(-1);
//        treeSet.add(9);
//        for (int treeSetValue : treeSet)
//            System.out.println(treeSetValue);
    }
	static class Solution1 {
	    @SuppressWarnings({ "rawtypes", "unchecked" })
		public String removeDuplicateLetters(String s) {
	        TreeSet[] sets = new TreeSet[26];
	        int tmp = 0;
	        for (int i = s.length() - 1; i > -1; i --) {
	        	tmp = s.charAt(i) - 'a';
	        	if (sets[tmp] == null)
	        		sets[tmp] = new TreeSet<Integer>();
	        	sets[tmp].add(i);
 	        }
	        int len = 0;
	        int startIndex = Integer.MAX_VALUE;
	        TreeSet<Integer> ansSet = new TreeSet<Integer>();
	        for (int i = sets.length - 1; i > -1; i --) {
	        	if (sets[i] != null) {
	        		len ++;
	        		startIndex = Math.min(startIndex, (Integer)sets[i].last());
	        	}
	        }
	        char[] ans = new char[len];
	        int ansIndex = 0;
	        for (int i = 0; i < sets.length; i ++) {
	        	if (sets[i] != null) {
	        		ansSet.add((Integer)sets[i].higher(startIndex - 1));
	        	}
	        }
	        for (int i : ansSet) {
	        	ans[ansIndex ++] = s.charAt(i);
	        }
	        return new String(ans);
	    }
	}
	static class Solution2 {
	    public String removeDuplicateLetters(String string) {
	        final char startChar = 'a';
	        @SuppressWarnings("unchecked")
            TreeSet<Integer>[] treeSetArray = new TreeSet[26];
	        TreeSet<Integer> moreThanTwoSet = new TreeSet<Integer>();
	        for (int stringIndex = string.length() - 1; stringIndex > -1; stringIndex --) {
	            int treeSetArrayIndex = string.charAt(stringIndex) - startChar;
	            if (treeSetArray[treeSetArrayIndex] == null)
	                treeSetArray[treeSetArrayIndex] = new TreeSet<Integer>();
	            treeSetArray[treeSetArrayIndex].add(stringIndex);
	            if (treeSetArray[treeSetArrayIndex].size() == 2) moreThanTwoSet.add(treeSetArrayIndex);
	        }
	        while (moreThanTwoSet.size() > 0) {
	            
	        }
	        return "";
	    }
	}
	static class Solution3 {
	    public String removeDuplicateLetters(String s) {
	        int[] cnt = new int[26];
	        int pos = 0;
	        for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
	        for (int i = 0; i < s.length(); i++) {
	            if (s.charAt(i) < s.charAt(pos)) pos = i;
	            if (--cnt[s.charAt(i) - 'a'] == 0) break;
	        }
	        return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
	    }
	}
	static class Solution4 {
	    public String removeDuplicateLetters(String s) {
	        if (s == null || s.length() <= 1) return s;
	        Map<Character, Integer> lastPosMap = new HashMap<>();
	        for (int i = 0; i < s.length(); i++) {
	            lastPosMap.put(s.charAt(i), i);
	        }
	        char[] result = new char[lastPosMap.size()];
	        int begin = 0, end = findMinLastPos(lastPosMap);

	        for (int i = 0; i < result.length; i++) {
	            char minChar = 'z' + 1;
	            for (int k = begin; k <= end; k++) {
	                if (lastPosMap.containsKey(s.charAt(k)) && s.charAt(k) < minChar) {
	                    minChar = s.charAt(k);
	                    begin = k+1;
	                }
	            }
	            result[i] = minChar;
	            if (i == result.length-1) break;
	            lastPosMap.remove(minChar);
	            if (s.charAt(end) == minChar) end = findMinLastPos(lastPosMap);
	        }
	        return new String(result);
	    }
	    private int findMinLastPos(Map<Character, Integer> lastPosMap) {
	        if (lastPosMap == null || lastPosMap.isEmpty()) return -1;
	        int minLastPos = Integer.MAX_VALUE;
	        for (int lastPos : lastPosMap.values()) {
	             minLastPos = Math.min(minLastPos, lastPos);
	        }
	        return minLastPos;
	    }

	}
	static class Solution5 {
	    public String removeDuplicateLetters(String sr) {
	        int[] res = new int[26]; //will contain number of occurences of character (i+'a')
	        boolean[] visited = new boolean[26]; //will contain if character (i+'a') is present in current result Stack
	        char[] ch = sr.toCharArray();
	        for(char c: ch){  //count number of occurences of character 
	            res[c-'a']++;
	        }
	        Stack<Character> st = new Stack<>(); // answer stack
	        int index;
	        for(char s:ch){ 
	            index= s-'a';
	            res[index]--;   //decrement number of characters remaining in the string to be analysed
	            if(visited[index]) //if character is already present in stack, dont bother
	                continue;
	            //if current character is smaller than last character in stack which occurs later in the string again
	            //it can be removed and  added later e.g stack = bc remaining string abc then a can pop b and then c
	            while(!st.isEmpty() && s<st.peek() && res[st.peek()-'a']!=0){ 
	                visited[st.pop()-'a']=false;
	            }
	            st.push(s); //add current character and mark it as visited
	            visited[index]=true;
	        }

	        StringBuilder sb = new StringBuilder();
	        //pop character from stack and build answer string from back
	        while(!st.isEmpty()){
	            sb.insert(0,st.pop());
	        }
	        return sb.toString();
	    }
	}
}
