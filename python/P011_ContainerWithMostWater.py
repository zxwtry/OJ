#coding=utf-8

'''
    url: leetcode.com/problems/container-with-most-water/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年3月27日
    @details:    Solution: AC 78ms 71.02%
'''

class Solution(object):
    def maxArea(self, h):
        """
        :type height: List[int]
        :rtype: int
        """
        l, r = 0, len(h) - 1
        a = 0
        while l < r:
            a = max(a, min(h[l], h[r]) * (r - l))
            if h[l] < h[r]:
                while True:
                    l += 1
                    if l >= r or h[l - 1] < h[l]: 
                        break
            else:
                while True:
                    r -= 1
                    if l >= r or h[r + 1] < h[r]:
                        break
        return a

if __name__ == "__main__":
    h = [1, 9, 2, 8, 3, 7, 4, 6]
    print(Solution().maxArea(h))