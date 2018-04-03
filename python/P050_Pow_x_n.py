#coding=utf-8

'''
    url: leetcode.com/problems/powx-n
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月9日
    @details:    Solution: 52ms 37.49%
'''

class Solution(object):
    def myPow(self, x, n):
        """
        :type x: float
        :type n: int
        :rtype: float
        """
        bit_s, bit_n=[False]*32, 0
        sign = n < 0
        n = abs(n)
        while n != 0:
            bit_s[bit_n]=True if n%2==1 else False
            bit_n+=1
            n //= 2
        x_arr, ans=[x], 1
        for i in range(1, bit_n):
            x_arr.append(x_arr[i-1]*x_arr[i-1])
        for i in range(bit_n):
            ans *= x_arr[i] if bit_s[i] else 1
        return 1 / ans if sign else ans

if __name__ == "__main__":
    print(Solution().myPow(2, -7))
        