# coding=utf-8

import numpy as np
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression
from sklearn import linear_model
from sklearn.linear_model import SGDRegressor, LinearRegression, Ridge
from sklearn.preprocessing import PolynomialFeatures
plt.rcParams['font.sans-serif'] = ['SimHei']

# n = 101

xx = [2015, 2016, 2017, 2018]
x = np.array(xx).reshape(-1, 1)
yy1 = [0.2916, 0.38242, 0.44593, 0.55624]
yy2 = [0.31587, 0.35656, 0.47805, 0.58842]
yy3 = [0.05058, 0.05771, 0.07337, 0.095]
yy4 = [0.07292, 0.08705, 0.11383, 0.13621]
yy5 = [0.02691, 0.03178, 0.04261, 0.05496]
yy6 = [0.02756, 0.03253, 0.04072, 0.04893]
yy7 = [0.03801, 0.04595, 0.0593, 0.06731]
yy8 = [0.06763, 0.07786, 0.10398, 0.13279]
yy9 = [0.03844, 0.04799, 0.06468, 0.06982]
yy10 = [0.0058, 0.00645, 0.00807, 0.00921]
yy11 = [0.06465, 0.06669, 0.08242, 0.10209]
y1 = np.array(yy1)
y2 = np.array(yy2)
y3 = np.array(yy3)
y4 = np.array(yy4)
y5 = np.array(yy5)
y6 = np.array(yy6)
y7 = np.array(yy7)
y8 = np.array(yy8)
y9 = np.array(yy9)
y10 = np.array(yy10)
y11 = np.array(yy11)


def skl_func(y, l):
    '''
    lr = LinearRegression()
    lr.fit(x.reshape(-1, 1), y)
    y_hat = lr.predict(np.arange(2015, 2023, 1).reshape(-1, 1))
    plt.plot(np.arange(2015, 2023, 1), y_hat, label=l)
    '''

    '''
    poly_reg = PolynomialFeatures(degree=7)
    X_ploy = poly_reg.fit_transform(x)
    lr = LinearRegression()
    lr.fit(X_ploy, y)
    y_hat = lr.predict(np.arange(2015, 2023, 1).reshape(1, -1))
    #y_hat = lr.predict(X_ploy)
    # print('skl_fun:\tW = %f\n\tb = %f' % (lr.coef_, lr.intercept_))
    #plt.plot(np.arange(2015, 2023, 1).reshape(1, -1), y_hat, label=l)
    plt.plot(np.arange(2015, 2023, 1), y_hat, label=l)
    '''

    '''
    #poly_reg = PolynomialFeatures(degree=7)
    poly_reg = linear_model.ARDRegression()

    X_ploy = poly_reg.fit_transform(x)
    print(X_ploy.shape)
    lin_reg = LinearRegression()
    lin_reg.fit(X_ploy, y)
    y_pred = lin_reg.predict(X_ploy)

    plt.scatter(x, y, s=4)
    plt.plot(x, y_pred, label=l)
    '''

    model = linear_model.ARDRegression()
    model.fit(x, y)
    x_pre = np.arange(2015, 2023, 1)
    y_pre = model.predict(x_pre.reshape(-1, 1))
    plt.plot(x_pre, y_pre, label=l)

    print(y_pre[7])

    # y_pred = lin_reg.predict(np.arange(2015, 2023, 1).reshape(1, -1))

    '''
    poly_reg = PolynomialFeatures(degree=7)
    X_ploy = poly_reg.fit_transform(x)
    # print(X_ploy.shape)
    lin_reg = LinearRegression()
    lin_reg.fit(X_ploy, y)
    x_pre = np.arange(2018, 2026, 1)
    x_pre = lin_reg.predict(x_pre.reshape(1, -1))

    # plt.scatter(x,y,s=4)
    #plt.plot(x, y_pred, label=l)
    plt.plot(x_pre, x_pre)
    '''

# plt.plot(np.arange(2015, 2023, 1).reshape(1, -1), y_pred, label=l)


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
plt.legend(loc='upper left')
plt.show()
