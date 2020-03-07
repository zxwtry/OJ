import tensorflow as tf
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
from mpl_toolkits.mplot3d import Axes3D


def loadDataSet():
    dataMat = []
    labelMat = []
    fr = open('D:/code/github/sample/python/proj/chi/data/nihe_tf_train.data')
    for line in fr.readlines():
        lineArr = line.strip().split()
        dataMat.append([float(lineArr[0]), float(lineArr[1])])
        labelMat.append([float(lineArr[2])])
    return dataMat, labelMat


def real_export(nomalized_data, array):  # 归一化
    maxcols = array.max(axis=0)
    mincols = array.min(axis=0)
    data_shape = array.shape
    data_rows = data_shape[0]
    data_cols = data_shape[1]
    t = np.empty((data_rows, data_cols))
    for i in range(data_cols):
        t[:, i] = (nomalized_data[:, i]*(maxcols[i]-mincols[i]))+mincols[i]
    return t


import_data, export_data = loadDataSet()

exF = 'D:/code/github/sample/python/proj/chi/data/nihe_tf_train.xlsx'

W_L1 = pd.read_excel(exF, sheet_name="W_L1")
W_L2 = pd.read_excel(exF, sheet_name="W_L2")
W_L3 = pd.read_excel(exF, sheet_name="W_L3")
b_L1 = pd.read_excel(exF, sheet_name="b_L1")
b_L2 = pd.read_excel(exF, sheet_name="b_L2")
b_L3 = pd.read_excel(exF, sheet_name="b_L3")


# W_L1 = np.loadtxt('W_L1.txt')
# W_L2 = np.loadtxt('W_L2.txt')
# W_L3 = np.loadtxt('W_L3.txt')
# b_L1 = np.loadtxt('b_L1.txt')
# b_L2 = np.loadtxt('b_L2.txt')
# b_L3 = np.loadtxt('b_L3.txt')


X_1 = np.arange(0, 1, 0.01)
X_2 = np.arange(0, 1, 0.01)
X_1, X_2 = np.meshgrid(X_1, X_2)

with tf.compat.v1.Session() as sess:
    end = np.zeros([100, 100])
    num = 0
    for i in range(100):
        num = num + 1
        oo = np.zeros([100, 2])
        oo[:, 0] = X_1[:, i]
        oo[:, 1] = X_2[:, i]
        p1 = tf.nn.relu(tf.matmul(tf.cast(oo, dtype=tf.float64), W_L1) + b_L1)
        p2 = tf.nn.relu(tf.matmul(p1, W_L2) + b_L2)
        p3 = tf.matmul(p2, W_L3) + b_L3
        nomal_result = np.array(sess.run(p3))
        end[:, i] = nomal_result[:, 11]
        print(num)
fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')
ax.plot_surface(X_1, X_2, end, rstride=1, cstride=1, cmap='rainbow')
plt.show()
