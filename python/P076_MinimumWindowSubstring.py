#coding=utf-8

'''
    url: leetcode.com/problems/minimum-window-substring
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月18日
    @details:    Solution: 152ms 77.17%
'''

class Solution(object):
    def minWindow(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: str
        """
        m, sn, tn = [0]*128, len(s), len(t)
        i, j, d, h = 0, 0, 1 << 30, 0
        cnt = tn
        for k in range(tn): m[ord(t[k])] += 1
        while j < sn:
            if m[ord(s[j])] > 0: cnt -= 1
            m[ord(s[j])] = m[ord(s[j])] - 1
            j += 1
            while cnt == 0:
                if j-i < d:
                    d = j-i
                    h = i
                if m[ord(s[i])] == 0:
                    cnt += 1
                m[ord(s[i])] = m[ord(s[i])] + 1
                i += 1
        if d == (1 << 30): return ""
        return s[h:h+d]

if __name__ == "__main__":
    s = "cabwefgewcwaefgcf"
    t = "cae"
    print(Solution().minWindow(s, t))
        