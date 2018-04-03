#coding=utf-8

'''
    url: leetcode.com/problems/substring-with-concatenation-of-all-words/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月1日
    @details:    Solution: 82ms 93.41%
'''

class Solution(object):
    def findSubstring(self, s, w):
        """
        :type s: str
        :type w: List[str]
        :rtype: List[int]
        """
        wn = 0 if w == None else len(w)
        sn = 0 if s == None else len(s)
        #return invalid
        if wn == 0 or sn < sn:
            return []
        ans, wl, wm, sm = [], len(w[0]), {}, {}
        for i in range(wn):
            if w[i] in wm:
                wm[w[i]] += 1
            else: wm[w[i]] = 1
        for i in range(wl):
            sm.clear()
            ii = i
            cnt = 0
            for j in range(i, sn, wl):
                ss = s[j: j + wl]
                #continue ss not in wm
                #clear all record
                if not ss in wm:
                    sm.clear()
                    ii = j + wl
                    cnt = 0
                    continue
                #complete add ss to sm
                if ss in sm:
                    sm[ss] += 1
                else:sm[ss] = 1
                cnt += 1
                #check add valid
                while sm[ss] > wm[ss]:
                    sm[s[ii: ii + wl]] -= 1
                    ii += wl
                    cnt -= 1
                if cnt == wn:
                    ans.append(ii)
        return ans
        
if __name__ == "__main__":
    s = "barfoofoobarthefoobarman"
    w = ["bar","foo","the"]
    print(Solution().findSubstring(s, w))
