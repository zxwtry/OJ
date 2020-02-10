import re

pattern = re.compile(r'[[](.*?)["]rgb[(]', re.S)
#pattern = re.compile(r'(.*?)["]rgb[(]', re.S)
#pattern = re.compile(r'(.*?)("?)rgb(\(?)', re.S)
#pattern = re.compile(r'[?<=//].*?[?=/]', re.S)

s = ',["https://encrypted-tbn0.gstatic.com/images?q\u003dtbn%3AANd9GcQiJOe3_PR7ssSdg8zn7WaTqusZCgqU39Wi7E3oIejmIk01zrwA",168,299],["https://a57.foxnews.com/static.foxnews.com/foxnews.com/content/uploads/2020/01/931/524/Norwegian-Forest-cat-iStock.jpg?ve\u003d1\u0026tl\u003d1",524,931],null,0,'

t = ',[1,[0,"iTDdZf-6ULD6XM",["https://encrypted-tbn0.gstatic.com/images?q\\u003dtbn%3AANd9GcRKRKJFA4G8QjefS4bAhfS5HBn2Ab7fQ2XQnAcY7MUXvSNkTTuH",194,259] ["https://icatcare.org/app/uploads/2018/07/Thinking-of-getting-a-cat.png",600,1200] ,null,0,"rgb(153,156,153)sdada'


m = pattern.findall(t)


print("#####")
for i in range(len(m)):
    print (m[i])
    print("++++++++++++++++++++")