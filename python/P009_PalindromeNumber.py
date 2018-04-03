#coding=utf-8

'''
    url: leetcode.com/problems/palindrome-number/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年3月27日
    @details:    Solution: AC 249ms 56.14%
'''

class Solution(object):
    def isPalindrome(self, x):
        """
        :type x: int
        :rtype: bool
        """
        if x < 0: return False
        v = x
        c = 0
        lst = []
        while v != 0:
            lst.append(v % 10)
            v //= 10
            c += 1
        l,r = c -1,0
        while l > r:
            if lst[l] != lst[r]:
                return False
            l -= 1
            r += 1
        return True

if __name__ == "__main__":
    x = -0
    sol = Solution()
    print(sol.isPalindrome(x))