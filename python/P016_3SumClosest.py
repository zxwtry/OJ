#coding=utf-8

'''
    url: leetcode.com/problems/3sum-closest
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年3月28日
    @details:    Solution: AC 199ms 33.81%
'''

class Solution(object):
    def threeSumClosest(self, n, t):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        ln = 0 if n == None else len(n)
        if ln < 3: return 0
        n.sort(key=None, reverse=False)
        a = n[0] + n[1] + n[2]
        ran = abs(a - t)
        for i in range(ln):
            if i != 0 and n[i - 1] == n[i]: continue
            l, r = i + 1, ln - 1
            while l < r:
                s = n[i] + n[l] + n[r]
                if s == t:
                    return t
                if abs(s - t) < ran:
                    a = s
                    ran = abs(s - t)
                if s < t:
                    while True:
                        l += 1
                        if l >= r or n[l - 1] != n[l]:
                            break
                else:
                    while True:
                        r -= 1
                        if l >= r or n[r + 1] != n[r]:
                            break
        return a
    
if __name__ == "__main__":
    n = [-1, 2, 1, -4]
    t = -2
    sol = Solution()
    print(sol.threeSumClosest(n, t))
        