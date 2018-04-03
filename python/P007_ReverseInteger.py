#coding=utf-8

'''
    url: leetcode.com/problems/reverse-integer/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年3月26日
    @details:    Solution: AC 58ms 66.96%
'''

class Solution(object):
    def reverse(self, x):
        """
        :type x: int
        :rtype: int
        """
        if x < 0: return -self.reverse(-x)
        s = str(x)
        i, j = 0, len(s) - 1
        l = []
        for i in range(j, -1, -1):
            l.append(s[i])
        v = int("".join(l))
        if v > 2147483647 or v < -2147483648:
            return 0
        return v
    
if __name__ == "__main__":
    v = -247483647
    s = Solution()
    print(s.reverse(v))