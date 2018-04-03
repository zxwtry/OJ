#coding=utf-8

'''
    url: leetcode.com/problems/longest-palindromic-substring/
    manacher
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年3月26日
    @details:    Solution: AC 632ms 41.14%
'''

class Solution(object):
    def accessString(self, s, index):
        if index % 2 == 0: return '#'
        else: return s[index // 2]
        
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: str
        """
        s_len = 0 if s == None else len(s)
        if s_len == 0: return ""
        m = [0] * (2 * s_len + 1)
        m_last_i, m_mirror_r, m_max_touch_i, m_max_r_index = 0, 0, 0, 0
        for i in range(2 * s_len + 1):
            if m_max_touch_i >= 2 * s_len: break
            m_mirror_r = m[2 * m_last_i - i]
            if i >= m_max_touch_i or i + m_mirror_r == m_max_touch_i:
                left, right = i, i
                while left - 1 > -1 and right + 1 < 2 * s_len + 1 and \
                    self.accessString(s, left - 1) == self.accessString(s, right + 1):
                    left, right = (left - 1), (right + 1)
                m[i] = (right - left) // 2
                if m[m_max_r_index] < m[i]:m_max_r_index = i
                m_last_i = i
                m_max_touch_i = right
            elif i + m_mirror_r < m_max_touch_i:
                m[i] = m[2 * m_last_i - i]
            else:
                m[i] = m_max_touch_i - i
        i = m_max_r_index // 2 - m[m_max_r_index] // 2
        return s[i : i + m[m_max_r_index]]

if __name__ == "__main__":
    s1="tabatabag"
    s = Solution()
    print("answer is \"%s\"" % s.longestPalindrome(s1))