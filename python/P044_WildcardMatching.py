#coding=utf-8

'''
    url: leetcode.com/problems/wildcard-matching
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月7日
    @details:    Solution: 1845ms 22.70%
'''

class Solution(object):
    def isMatch(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: bool
        """
        sn = 0 if s == None else len(s)
        pn = 0 if p == None else len(p)
        m = [[False for pi in range(pn+1)] for si in range(sn+1)]
        m[0][0]=True
        #m[0][0], si, pi = True, 0, 0
        for pi in range(0, pn):
            if p[pi]=='*' and m[0][pi]:
                m[0][pi+1] = True
        for si in range(0, sn):
            for pi in range(0, pn):
                if s[si]==p[pi] or p[pi]=='?':
                    m[si+1][pi+1]=m[si][pi]
                elif p[pi]=='*':
                    if m[si][pi+1]: #match 0 time
                        m[si+1][pi+1]=True
                        continue
                    if m[si][pi]: #match 1 time
                        m[si+1][pi+1]=True
                        continue
                    if m[si+1][pi]: #match many times
                        m[si+1][pi+1]=True
                        continue
        return m[sn][pn]

if __name__ == "__main__":
    print(Solution().isMatch("", "*"))
        