#coding=utf-8

'''
    url: leetcode.com/problems/word-ladder
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月12日
    @details:    Solution:  902ms 44.52%
'''

from jinja2._compat import unichr

class Solution(object):
    def ladderLength(self, b, e, w):
        """
        :type b: str
        :type e: str
        :type w: List[str]
        :rtype: int
        """
        nv = set(w)
        hv = set()
        sn = len(b)
        q = []
        nv.add(b)
        q.append(b)
        isFind = False
        count = 2
        while True:
            l = len(q)
            if l == 0: break
            while l > 0:
                l -= 1
                s = q[0]
                q.remove(q[0])
                cs = [s[i] for i in range(sn)]
                for i in range(sn):
                    for j in range(26):
                        cs[i] = unichr(97+j)
                        ns = "".join(cs)
                        if not ns in nv: continue
                        if not ns in hv:
                            hv.add(ns)
                            q.append(ns)
                        if ns == e: isFind = True
                    cs[i] = s[i]
            if isFind: break
            count += 1
            for v in hv: nv.remove(v)
            hv.clear()
        return 0 if not isFind else count
        

if __name__ == "__main__":
    b, e = "hit", "cog"
    w = ["hot","dot","hog","dog","lot","log","cog"]
#     b, e = "a", "c"
#     w = ["a", "b", "c"]
    print(Solution().ladderLength(b, e, w))
