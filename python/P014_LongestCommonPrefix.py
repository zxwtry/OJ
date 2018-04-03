#coding=utf-8

'''
    url: leetcode.com/problems/longest-common-prefix/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年3月27日
    @details:    BackTraceSolution: TLE
    @details:    DPSolution: AC 65ms 24.64%
'''

class Solution(object):
    def longestCommonPrefix(self, strs):
        """
        :type strs: List[str]
        :rtype: str
        """
        ln = 0 if strs == None else len(strs)
        if ln == 0: return ""
        sn = (1 << 31) - 1
        for i in range(ln):
            sn = min(sn, 0 if strs[i] == None else len(strs[i]))
        for j in range(sn):
            at = True
            for i in range(ln):
                at &= strs[i][j] == strs[0][j]
            if not at:
                return strs[0][:j]
        return strs[0][:sn]

if __name__ == "__main__":
    strs = ["aa", "ab"]
    sol = Solution()
    print(sol.longestCommonPrefix(strs))