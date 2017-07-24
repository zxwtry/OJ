package leetcode;

import java.util.Arrays;
import java.util.List;

/**

In English, we have a concept called root, which can be followed by
 some other words to form another longer word - 
 let's call this word successor. For example, 
 the root an, followed by other, which can form another word another.

Now, given a dictionary consisting of many roots and a sentence. 
You need to replace all the successor in the sentence with the root forming it.
 If a successor has many roots can form it, replace it with the root 
 with the shortest length.

You need to output the sentence after the replacement.

Example 1:
Input: dict = ["cat", "bat", "rat"]
sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"
Note:
The input will only have lower-case letters.
1 <= dict words number <= 1000
1 <= sentence words number <= 1000
1 <= root length <= 100
1 <= sentence words length <= 1000

 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P648_ReplaceWords.java
 * @date        2017年7月23日 上午10:17:28
 * @details     
 */
public class P648_ReplaceWords {
    
    public static void main(String[] args) {
        List<String> dict = Arrays.asList("a", "aa", "aaa", "aaaa");
        String s =  "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa";
//      List<String> dict = Arrays.asList("cat", "bat", "rat");
//      String s =  "the cattle was rattled by the battery";
        System.out.println(new Solution().replaceWords(dict, s));
    }
    
    static public class Solution {
        public String replaceWords(List<String> dict, String s) {
            TN root = new TN();
            for (String key : dict) {
                insert(key, root);
            }
            TN rootNow = null;
            StringBuilder ans = new StringBuilder();
            int sn = s == null ? 0 : s.length();
            if (sn == 0) return "";
            for (int i = 0; i < sn; i ++) {
                char c = s.charAt(i);
                if (c >= 'a' && c <= 'z') {
                    int needSolve = 0;
                    rootNow = root;
                    int j = i;
                    while (j < sn) {
                        char nc = s.charAt(j);
                        if (nc >= 'a' && nc <= 'z') {
                            if (needSolve == 0) {
                                ans.append(nc);
                                rootNow = rootNow.nexts[nc - 'a'];
                                if (rootNow == null) {
                                    needSolve = 2;
                                } else {
                                    if (rootNow == root) {
                                        needSolve = 2;
                                    }
                                    if (rootNow.isEndOfAWord) {
                                        needSolve = 1;
                                    }
                                }
                            } else if (needSolve == 1) {
                                
                            } else if (needSolve == 2) {
                                ans.append(nc);
                            }
                        } else {
                            break;
                        }
                        j ++;
                    }
                    i = j - 1;
                } else {
                    ans.append(c);
                }
            }
            return ans.toString();
        }
        
        static void insert(String key, TN root) {
            TN rootNow = root;
            int keyLength = key.length();
            for (int i = 0; i < keyLength; i ++) {
                int nextsIndex = key.charAt(i) - 'a';
                if (null != rootNow.nexts[nextsIndex]) {
                    rootNow = rootNow.nexts[nextsIndex];
                } else {
                    TN tn = new TN();
                    rootNow.nexts[nextsIndex] = tn;
                    rootNow = tn;
                }
            }
            rootNow.isEndOfAWord = true;
        }
        static class TN {
            boolean isEndOfAWord = false;
            TN[] nexts = new TN[26];
            TN suffix = null;
        }
    }
}
