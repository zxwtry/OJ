#coding=utf-8

'''
    url: leetcode.com/problems/string-to-integer-atoi/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年3月27日
    @details:    Solution: AC 72ms 63.62%
'''

class Solution(object):
    def myAtoiHelper(self, s):
        s_len = len(s)
        int_val = 0
        ord_i, ord_0, ord_9 = 0,ord('0'),ord('9')
        for i in range(s_len):
            ord_i = ord(s[i])
            if ord_i >= ord_0 and ord_i <= ord_9:
                int_val = int_val * 10 + ord_i - ord_0
            else:
                return int_val
        return int_val
        
    def myAtoi(self, s):
        """
        :type s: s
        :rtype: int
        """
        if s == None: return 0
        s = s.strip()
        s_len = len(s)
        if s_len == 0: return 0
        val = 0
        if s[0] == '+': val = self.myAtoiHelper(s[1:])
        elif s[0] == '-': val = -self.myAtoiHelper(s[1:])
        else: val = self.myAtoiHelper(s)
        if val > 2147483647:
            val = 2147483647
        elif val < -2147483648:
            val = -2147483648
        return val

if __name__ == "__main__":
    s = "  214748a3648"
    sol = Solution()
    print(sol.myAtoi(s))