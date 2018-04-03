#coding=utf-8

'''
    url: leetcode.com/problems/word-break-ii
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月30日
    @details:    Solution:  75ms 71.43%
'''

class Solution(object):
    def search(self, s, d, m):
        if (len(s) == 0): return [""]
        if s in m: return m[s]
        a, p = [], len(s)
        for v in d:
            l = len(v)
            if (not s.startswith(v)): continue
            b = self.search(s[l:], d, m)
            g = "" if p == l else " "
            for u in b:
                a.append(v + g + u)
        m[s] = a
        return a
        
    def wordBreak(self, s, d):
        """
        :type s: str
        :type d: List[str]
        :rtype: list[str]
        """
        return self.search(s, d, {})

if __name__ == "__main__":
    s = "catsanddog"
    d = ["cat", "cats", "and", "sand", "dog"]
    print(Solution().wordBreak(s, d))