#coding=utf-8

'''
    url: leetcode.com/problems/multiply-strings
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月7日
    @details:    Solution: 366ms 61.53%
'''

class Solution(object):
    def multiply(self, n1, n2):
        """
        :type n1: str
        :type n2: str
        :rtype: str
        """
        n1n = 0 if n1 == None else len(n1)
        n2n = 0 if n2 == None else len(n2)
        if n1n == 0 or n2n == 0:
            return "0"
        a, val, l = [], 0, [0] * (n1n + n2n)
        for i1 in range(n1n-1, -1 , -1):
            for i2 in range(n2n-1, -1, -1):
                l[n1n-1-i1+n2n-1-i2] += int(n1[i1])*int(n2[i2])
        for i in range(0, n1n+n2n):
            val += l[i]
            a.append(str(val%10))
            val //= 10
        while val != 0:
            a.append(str(val%10))
            val //= 10
        while len(a) != 1 and a[len(a) - 1] == '0':
            a.pop()
        a.reverse()
        return "".join(a)

if __name__ == "__main__":
    print(Solution().multiply("789", "999999"))
        