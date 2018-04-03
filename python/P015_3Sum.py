#coding=utf-8

'''
    url: leetcode.com/problems/3sum
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年3月28日
    @details:    Solution1: AC 1619ms  8.48%
    @details:    Solution2: AC 1159ms 36.49%
'''

class Solution1(object):
    def threeSum(self, n):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        ln = 0 if n == None else len(n)
        ans = []
        if ln < 3: return ans
        m = {}
        n.sort(key=None, reverse=False)
        for i in range(ln):
            m[n[i]] = i
        for i in range(ln):
            if i != 0 and n[i - 1] == n[i]: continue
            for j in range(i + 1, ln):
                if j != i + 1 and n[j - 1] == n[j]: continue
                if -n[i]-n[j] in m and m[-n[i]-n[j]] > j:
                    ans.append([n[i], n[j], -n[i]-n[j]])
        return ans

class Solution2(object):
    def threeSum(self, n):
        ln = 0 if n == None else len(n)
        ans = []
        if ln < 3: return ans
        n.sort(key=None, reverse=False)
        for i in range(ln):
            if i != 0 and n[i - 1] == n[i]: continue
            l, r, t = i + 1, ln - 1, -n[i]
            while l < r:
                if n[l] + n[r] == t:
                    ans.append([n[i], n[l], n[r]])
                    while True:
                        l += 1
                        if l >= r or n[l - 1] != n[l]:
                            break
                    while True:
                        r -= 1
                        if l >= r or n[r + 1] != n[r]:
                            break
                elif n[l] + n[r] > t:
                    r -= 1
                else:
                    l += 1
        return ans

if __name__ == "__main__":
    n = [0, 0, 0, 0, 0]
    sol = Solution2()
    print(sol.threeSum(n))