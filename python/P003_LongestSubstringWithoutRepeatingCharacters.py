#coding=utf-8

'''
    url: leetcode.com/problems/longest-substring-without-repeating-characters/
    dict保存上一次字符出现的位置，出现重复，从下一个开始计数
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年2月12日
    @details:    Solution1: AC 102ms 71.02%
    @details:    Solution1: AC 192ms 17.26%
'''

class Solution1(object):
    def lengthOfLongestSubstring(self, s):
        """
        :type s: str
        :rtype: int
        """
        if s == None or len(s) == 0: return 0
        d,m,u = {},1,0
        for i in range(len(s)):
            p = d.get(s[i])
            d[s[i]] = i
            if p == None: p = -1
            elif p >= u:
                m = max(m, i - u)
                u = p + 1
        m = max(m, len(s) - u)
        return m

class Solution2(object):
    def lengthOfLongestSubstring(self, s):
        """
        :type s: str
        :rtype: int
        """
        if s ==  None or len(s) == 0:
            return 0
        d, m , u = [-1] * 256, -1, -1
        for j in range(len(s)):
            p = d[ord(s[j])]
            d[ord(s[j])] = j
            m = max(m, j - u)
            u = max(u, p + 1)
        m = max(m, len(s) - u)    
        return m
        
if __name__ == "__main__":
    d = "abba"
    s = Solution2()
    print(s.lengthOfLongestSubstring(d))
