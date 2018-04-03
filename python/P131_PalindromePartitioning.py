#coding=utf-8

'''
    url: leetcode.com/problems/palindrome-partitioning
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月15日
    @details:    Solution:  162ms 77.62%
'''

class Solution(object):
    def isP(self, s, i, j):
        while i < j:
            if s[i] != s[j]: return False
            i, j = i + 1, j -1
        return True
    
    def search(self, s, sn, i, rec, ans):
        if i == sn: ans.append(list(rec))
        for j in range(i, sn):
            if self.isP(s, i, j):
                rec.append(s[i:j+1])
                self.search(s, sn, j+1, rec, ans)
                del rec[-1]
        
    def partition(self, s):
        """
        :type s: str
        :rtype: List[List[str]]
        """
        rec = []
        ans = []
        sn = 0 if s == None else len(s)
        self.search(s, sn, 0, rec, ans)
        return ans

if __name__ == "__main__":
    s = "aaab"
    #print(s[0:1])
    print(Solution().partition(s))
