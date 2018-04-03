#coding=utf-8

'''
    url: leetcode.com/problems/valid-number
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月14日
    @details:    Solution: 56ms 86.13%
'''

class Solution(object):
    def isNumber(self, s):
        """
        :type s: str
        :rtype: bool
        """
        try:
            float(s)
            return True
        except:
            return False