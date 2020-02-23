# coding=utf-8

import jieba
import io
import sys
sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf8')

fileName = "D:\code\github\sample\python\jieba\消失的春招.txt"
text = open(fileName, 'r', encoding='utf-8').read()
wordsls = jieba.lcut(text)
wcdict = {}
for word in wordsls:
    if len(word) == 1:
        continue
    else:
        wcdict[word] = wcdict.get(word, 0)+1

# word在wcdict中没有找到对应的词语，则返回0
wcls = list(wcdict.items())
wcls.sort(key=lambda x: x[1], reverse=True)

for i in range(len(wcls) // 3):
    print(wcls[i])
