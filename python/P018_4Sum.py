#coding=utf-8

'''
    url: leetcode.com/problems/4sum/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年3月28日
    @details:    Solution1: TLE
    @details:    Solution2: 745ms 53.60%
'''

class Solution1(object):
    def search(self, a, n, s, t, i, I, p, pi):
        if pi == 4:
            #Python2 LeetCode Judge is Python2
            #if s == t: a.append(p[:])
            #Python3
            if s == t: a.append(p.copy())
            return
        for j in range(i, I):
            if j != i and n[j - 1] == n[j]: continue
            p[pi] = n[j]
            self.search(a, n, s + n[j], t, j + 1, I, p, pi + 1)
        
    def fourSum(self, n, t):
        """
        :type n: List[int]
        :type t: int
        :rtype: List[List[int]]
        """
        a = []
        nn = 0 if n == None else len(n)
        if nn == 0: return a
        n.sort(key=None, reverse=False)
        self.search(a, n, 0, t, 0, nn, [0] * 4, 0)
        return a
    
class Solution2(object):
    def fourSum(self, n, target):
        a = []
        nn = 0 if n == None else len(n)
        if nn == 0: return a
        n.sort(key=None, reverse=False)
        for i in range(nn):
            if i != 0 and n[i - 1] == n[i]: continue
            for j in range(i + 1, nn):
                if j != i + 1 and n[j - 1] == n[j]: continue
                l, r, t = j + 1, nn - 1, target-n[i]-n[j]
                while l < r:
                    s = n[l] + n[r]
                    if s == t:
                        a.append([n[i], n[j], n[l], n[r]])
                        while True:
                            l += 1
                            if l >= r or n[l-1] != n[l]: break
                        while True:
                            r -= 1
                            if l >= r or n[r+1] != n[r]: break
                    elif s < t:
                        l += 1
                    else:
                        r -= 1
        return a

if __name__ == "__main__":
    n = [1,0,-1,0,-2,2]
    t = 0
    print(Solution2().fourSum(n, t))
        