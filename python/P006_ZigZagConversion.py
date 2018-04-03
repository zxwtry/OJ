#coding=utf-8

'''
    url: leetcode.com/problems/zigzag-conversion/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年3月26日
    @details:    Solution: AC 198ms 14.38%
'''

class Solution(object):
    def accessString(self, row_i, col_j, row_num, s, s_len):
        index = (2 * row_num - 2) * col_j + row_i
        return "" if index >= s_len else s[index]
    def convert(self, s, row_num):
        """
        :type s: str
        :type numRows: int
        :rtype: str
        """
        if row_num < 2: return s
        s_len = 0 if s == None else len(s)
        col_num = s_len // (2 * row_num - 2)
        ans = []
        for row_i in range(row_num):
            for col_j in range(col_num + 1):
                v = self.accessString(row_i, col_j, row_num, s, s_len)
                if v != "": ans.append(v)
                if row_i != 0 and row_i != row_num - 1:
                    v = self.accessString( 2 * row_num - 2 - row_i, col_j, row_num, s, s_len)
                    if v != "": ans.append(v)
        return "".join(ans)

if __name__ == "__main__":
    s = "012345678"
    sol = Solution()
    v = sol.convert(s, 3)
    print(v)