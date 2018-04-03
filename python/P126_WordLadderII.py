#coding=utf-8

'''
    url: leetcode.com/problems/word-ladder-ii
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月5日
    @details:    Solution:  86ms 36.12%
'''
from jinja2._compat import unichr
from re import search

class Solution(object):
    def backtrace(self, m, b, e, rec, ans):
        rec.insert(0, e)
        if b == e:
            ans.append(list(rec))
        else:
            for v in m[e]:
                self.backtrace(m, b, v, rec, ans)
        rec.remove(rec[0])
        
    def findLadders(self, b, e, w):
        """
        :type b: str
        :type e: str
        :type w: List[str]
        :rtype: List[List[str]]
        """
        n = 0 if b == None else len(b)
        if n == 0: return [[""]]
        nv, hv = set(w), set()
        m = {w[i]:set() for i in range(len(w))}
        m[b] = set()
        nv.add(b)
        q, isFind = [b], False
        while len(q) != 0:
            size = len(q)
            while size > 0: 
                size = size-1
                v = q.pop()
                cs = [v[i] for i in range(n)]
                for i in range(n):
                    for k in range(26):
                        cs[i] = unichr(97+k)
                        cc = "".join(cs)
                        if not (cc in nv): continue
                        if cc == e: isFind = True
                        if (cc not in hv):
                            q.append(cc)
                            hv.add(cc)
                        m[cc].add(v)
                    cs[i] = v[i]
            if isFind: break
            for vv in hv: nv.remove(vv)
            hv.clear()
        ans, rec = [], []
        print(m)
        self.backtrace(m, b, e, rec, ans)
        return ans
        
        
if __name__ == "__main__":
    b = 'hit'
    e = 'cog'
    w = ["hot","dot","dog","lot","log","cog"]
    print(Solution().findLadders(b, e, w))
    