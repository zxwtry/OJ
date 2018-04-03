#coding=utf-8

'''
    url: leetcode.com/problems/anagrams
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月9日
    @details:    Solution: 229ms 80.11%
'''

class Solution(object):
    def groupAnagrams(self, s):
        """
        :type s: List[str]
        :rtype: List[List[str]]
        """
        ans, d=[], {}
        for ss in s:
            if ss in d:
                d[ss].append(ss)
                continue
            ss_c=list(ss)
            ss_c.sort(key=None, reverse=False)
            ss_key="".join(ss_c)
            if ss_key in d:
                d[ss_key].append(ss)
            else: d[ss_key]=[ss]
        for key in d:
            ans.append(d[key])
        return ans

if __name__ == "__main__":
    s = ["eat", "tea", "tan", "ate", "nat", "bat"]
    print(Solution().groupAnagrams(s))
