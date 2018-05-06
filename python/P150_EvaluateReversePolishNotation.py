"""
User:  zxwtry
File:  P150_EvaluateReversePolishNotation.py 
Date:  2018/5/6
Time:  21:22
Desc:  计算逆波兰表达式
"""

class Solution:
    def evalRPN(self, tokens):
        """
        :type tokens: List[str]
        :rtype: int
        """
        if len(tokens) == 1:
            return int(tokens[0])
        l = [int(tokens[0]), int(tokens[1])]
        for i in range(2, len(tokens), 1):
            v = tokens[i]
            if len(l) > 1:
                v1, v2 = int(l[len(l) - 2]), int(l[len(l) - 1])
            operated = False
            if v == '+':
                v1 = v1 + v2
            elif v == '-':
                v1 = v1 - v2
            elif v == '*':
                v1 = v1 * v2
            elif v == '/':
                v1 = int(v1 / v2)
            else:
                v1, v2 = v2, int(v)
                operated = True
            if operated:
                l.append(v2)
            else:
                l.pop(len(l) - 1)
                l.pop(len(l) - 1)
                l.append(v1)

        return l[0]
        

if __name__ == "__main__":
    tokens = ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
    print(tokens)
    print(Solution().evalRPN(tokens))
    print(int(1 / (-2)))
