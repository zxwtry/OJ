#coding=utf-8

'''
    url: leetcode.com/problems/distinct-subsequences
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月1日
    @details:    Solution:  129ms 87.80%
'''

class Solution(object):
    def numDistinct(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: int
        """
        sn = 0 if s == None else len(s)
        tn = 0 if t == None else len(t)
        if (tn == 0): return sn
        if (sn < tn): return 0
        m = [0] * tn
        for i in range(sn-1, -1, -1):
            p = 1
            for j in range(tn-1, -1, -1):
                g = m[j]
                if (s[i] == t[j]):
                    m[j] += p
                p = g
        return m[0]
    
if __name__ == "__main__":
    s = "aaa"
    t = "a"
    print(Solution().numDistinct(s, t))