#coding=utf-8

'''
    url: leetcode.com/problems/remove-duplicates-from-sorted-array-ii
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月19日
    @details:    Solution: 79ms 36.16%
'''

class Solution(object):
    def removeDuplicates(self, n):
        """
        :type n: List[int]
        :rtype: int
        """
        nn = 0 if n == None else len(n)
        if nn == 0: return 0
        bi, fi, cnt = 0, 0, 0
        while fi < nn:
            if fi == 0 or n[fi] != n[fi-1]:
                cnt = 1
            else: cnt += 1
            if cnt < 3:
                n[bi] = n[fi]
                bi = bi+1
            fi += 1
        return bi

if __name__ == "__main__":
    n = [1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 6]
    print(Solution().removeDuplicates(n))
    print(n)