#coding=utf-8

'''
    url: leetcode.com/problems/length-of-last-word
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月12日
    @details:    Solution: 39ms 80.79%
'''

class Solution(object):
    def lengthOfLastWord(self, s):
        """
        :type s: str
        :rtype: int
        """
        if s == None: return 0
        ans, index = 0, len(s)-1 
        while index > -1:
            if s[index] == " ":
                if ans != 0: break
                index -= 1
                continue
            ans += 1
            index -= 1
        return ans

if __name__ == "__main__":
    print(Solution().lengthOfLastWord("  ab "))