import numpy as np
from sklearn.linear_model import LinearRegression
from sklearn.preprocessing import PolynomialFeatures
import matplotlib.pyplot as plt
import seaborn as sns
sns.set()
plt.rcParams['font.sans-serif'] = ['SimHei']
# legend(loc='upper left')
X_train = [[2015], [2016], [2017], [2018]]
y_train = [[7], [9], [13], [17.5]]

y1 = [[0.2916], [0.38242], [0.44593], [0.55624]]
y2 = [[0.31587], [0.35656], [0.47805], [0.58842]]
y3 = [[0.05058], [0.05771], [0.07337], [0.095]]
y4 = [[0.07292], [0.08705], [0.11383], [0.13621]]
y5 = [[0.02691], [0.03178], [0.04261], [0.05496]]
y6 = [[0.02756], [0.03253], [0.04072], [0.04893]]
y7 = [[0.03801], [0.04595], [0.0593], [0.06731]]
y8 = [[0.06763], [0.07786], [0.10398], [0.13279]]
y9 = [[0.03844], [0.04799], [0.06468], [0.06982]]
y10 = [[0.0058], [0.00645], [0.00807], [0.00921]]
y11 = [[0.06465], [0.06669], [0.08242], [0.10209]]

X_test = [[6], [8], [11], [16]]
y_test = [[8], [12], [15], [18]]


def skl_func(y_train, l):

    # 简单线性回归
    # model = LinearRegression()
    # model.fit(X_train, y_train)
    xx = np.linspace(2015, 2023, 100)
    # y = model.predict(xx.reshape(xx.shape[0], 1))
    # plt.scatter(x=X_train, y=y_train, color='k')
    # plt.plot(xx, y, '-g')
    plt.scatter(x=X_train, y=y_train)
    # 多项式回归
    quadratic_featurizer = PolynomialFeatures(degree=8)
    X_train_quadratic = quadratic_featurizer.fit_transform(X_train)
    X_test_quadratic = quadratic_featurizer.fit_transform(X_test)
    model2 = LinearRegression()
    model2.fit(X_train_quadratic, y_train)
    xx2 = quadratic_featurizer.transform(xx[:, np.newaxis])
    yy2 = model2.predict(xx2)
    plt.plot(xx, yy2, label=l)


skl_func(y1, "广州")
skl_func(y2, "深圳")
skl_func(y3, "珠海")
skl_func(y4, "佛山")
skl_func(y5, "江门")
skl_func(y6, "肇庆")
skl_func(y7, "惠州")
skl_func(y8, "东莞")
skl_func(y9, "中山")
skl_func(y10, "澳门")
skl_func(y11, "香港")

print('X_train:\n', X_train)
# print('X_train_quadratic:\n', X_train_quadratic)
print('X_test:\n', X_test)
# print('X_test_quadratic:\n', X_test_quadratic)
# print('r2:', model2.score(X_test_quadratic, y_test))
plt.legend()
plt.show()
