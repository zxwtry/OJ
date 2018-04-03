#coding=utf-8

'''
    url: leetcode.com/problems/scramble-string
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月21日
    @details:    Solution: 95ms 60.64%
'''

class Solution(object):
    def search(self, s1, i1, j1, n1, s2, i2, j2, n2):
        m, e = {}, True
        for k in range(j1-i1+1):
            e = s1[i1+k] == s2[i2+k]
            if not e: break
        if e: return True
        for k in range(i1, j1+1):
            s = s1[k]
            if s in m: m[s] = m[s]+1
            else: m[s] = 1
        for k in range(i2, j2+1):
            if s2[k] not in m or m[s2[k]] <= 0:
                return False
            m[s2[k]] = m[s2[k]]-1
        for k in range(j1-i1):
            if self.search(s1, i1, i1+k, n1, s2, j2-k, j2, n2) \
                and self.search(s1, i1+k+1, j1, n1, s2, i2, j2-k-1, n2):
                return True
            if self.search(s1, i1, i1+k, n1, s2, i2, i2+k, n2) \
                and self.search(s1, i1+k+1, j1, n1, s2, i2+k+1, j2, n2):
                return True
        return False    
            
    def isScramble(self, s1, s2):
        """
        :type s1: str
        :type s2: str
        :rtype: bool
        """
        n1 = 0 if s1 == None else len(s1)
        n2 = 0 if s2 == None else len(s2)
        if n1 != n2: return False
        return self.search(s1, 0, n1-1, n1, s2, 0, n2-1, n2)
    
if __name__ == "__main__":
    m = [
            "a",
            "great",
            "great",
            "great",
            "abc",
            "abcdefghijklmn",
            "oatzzffqpnwcxhejzjsnpmkmzngneo"
        ]
    n = [
            "b",
            "rgeat",
            "rgtae",
            "eatgr",
            "cba",
            "efghijklmncadb",
            "acegneonzmkmpnsjzjhxwnpqffzzto"
        ]
    for i in range(len(m)):
        print(Solution().isScramble(m[i], n[i]))