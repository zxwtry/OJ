#coding=utf-8

'''
    url: leetcode.com/problems/valid-palindrome
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月5日
    @details:    Solution:  136ms 16.05%
'''

class Solution(object):
    def ord_val(self, v):
        return (v >= 97 and v <= 122) or (v >= 48 and v <= 57)
        
    def isPalindrome(self, s):
        """
        :type s: str
        :rtype: bool
        """
        s = s.lower()
        i, j = 0, len(s)-1 
        while i < j:
            left = ord(s[i])
            if self.ord_val(left):
                right = ord(s[j])
                if self.ord_val(right):
                    if left != right: return False
                    i, j = i+1, j-1
                else: j -= 1
            else: i += 1
        return True
                
if __name__ == "__main__":
    ss = ["A man, a plan, a canal: Panama", "race a car"]
    for s in ss:
        print(Solution().isPalindrome(s))
