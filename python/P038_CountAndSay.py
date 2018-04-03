#coding=utf-8

'''
    url: leetcode.com/problems/count-and-say/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月5日
    @details:    Solution: 72ms 13.30%
'''

class Solution(object):
    def countAndSay(self, n):
        """
        :type n: int
        :rtype: str
        """
        if n < 1: return ""
        if n == 1: return "1"
        answer="1"
        for i in range(n-1):
            record, pre_char, pre_count=[], "ddd", 0
            for j in range(len(answer)):
                if answer[j] == pre_char:
                    pre_count += 1
                else:
                    if pre_count != 0:
                        record.append(str(pre_count))
                        record.append(pre_char)
                    pre_char = answer[j]
                    pre_count = 1
            if pre_count != 0:
                record.append(str(pre_count))
                record.append(pre_char)
            answer = "".join(record)
        return answer

if __name__ == "__main__":
    print(Solution().countAndSay(4))
                    
                    
                