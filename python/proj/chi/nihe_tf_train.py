import pandas as pd
import numpy as np
import tensorflow as tf
tf.compat.v1.disable_eager_execution()

# 归一化函数


def maxminnorm(array):
    maxcols = array.max(axis=0)
    mincols = array.min(axis=0)
    data_shape = array.shape
    data_rows = data_shape[0]
    data_cols = data_shape[1]
    t = np.empty((data_rows, data_cols))
    for i in range(data_cols):
        t[:, i] = (array[:, i]-mincols[i])/(maxcols[i]-mincols[i])
    return t

# 反归一化函数


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

# 加载数据集函数


def loadDataSet():
    dataMat = []
    labelMat = []
    fr = open('D:/code/github/sample/python/proj/chi/data/nihe_tf_train.data')
    for line in fr.readlines():
        lineArr = line.strip().split()
        dataMat.append([float(lineArr[0]), float(lineArr[1])])
        labelMat.append([float(lineArr[2])])
    return dataMat, labelMat


'''
********************************************
***             main code                ***
********************************************
'''
import_data, export_data = loadDataSet()

x_data1 = maxminnorm(np.array(import_data))
y_data1 = maxminnorm(np.array(export_data))

x_data = x_data1
y_data = y_data1

# 定义两个palceholder
with tf.name_scope('input'):

    x = tf.compat.v1.placeholder(tf.float32, name="x_input")
    y = tf.compat.v1.placeholder(tf.float32, name="y_input")

# 构建神经网络中间层
with tf.name_scope('layer1'):
    with tf.name_scope('W'):
        Weight_L1 = tf.Variable(
            tf.compat.v1.truncated_normal([2, 800], stddev=0.1))
    with tf.name_scope('b'):
        biases_L1 = tf.Variable(tf.zeros([800]))
    with tf.name_scope('W1'):
        W1 = tf.matmul(x, Weight_L1) + biases_L1
    with tf.name_scope('L1'):
        L1 = tf.nn.relu(W1)

with tf.name_scope('layer2'):
    with tf.name_scope('W'):
        Weight_L2 = tf.Variable(
            tf.compat.v1.truncated_normal([800, 1000], stddev=0.1))
    with tf.name_scope('b'):
        biases_L2 = tf.Variable(tf.zeros([1000])+0.1)
    with tf.name_scope('W2'):
        W2 = tf.matmul(L1, Weight_L2) + biases_L2
    with tf.name_scope('L2'):
        L2 = tf.nn.relu(W2)

'''
Weight_L3 = tf.Variable(tf.truncated_normal([300,300],stddev=0.1))
biases_L3 = tf.Variable(tf.zeros([300])+0.1)
W3 = tf.matmul(L2,Weight_L3) + biases_L3
L3 = tf.nn.relu(W3)
'''
# 定义输出层
with tf.name_scope('layer3'):
    with tf.name_scope('W'):
        Weight_L4 = tf.Variable(
            tf.compat.v1.truncated_normal([1000, 12], stddev=0.1))
    with tf.name_scope('b'):
        biases_L4 = tf.Variable(tf.zeros([12])+0.1)
    with tf.name_scope('W3'):
        Wx_4 = tf.matmul(L2, Weight_L4)+biases_L4
    with tf.name_scope('L3'):
        prediction = Wx_4

# 交叉熵代价函数
# loss = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(labels = y,logits = prediction))

# 二次代价函数
with tf.name_scope('loss'):
    loss = tf.reduce_mean(tf.square(y - prediction))

# 使用梯度下降法
with tf.name_scope('train_step'):
    train_step = tf.compat.v1.train.GradientDescentOptimizer(
        0.005).minimize(loss)

sess = tf.compat.v1.Session()
writer = tf.compat.v1.summary.FileWriter('logs', sess.graph)

# 运行神经网络
with tf.compat.v1.Session() as sess:
    sess.run(tf.compat.v1.global_variables_initializer())
    # 修改便于测试
    for _ in range(0, 100000):
        sess.run(train_step, feed_dict={x: x_data, y: y_data})

        if _ % 100 == 0:

            oo = np.array([[0.25, 0.75]], dtype=float)
            prediction_value = sess.run(prediction, feed_dict={x: oo})
            print(prediction_value)

    oo = np.array([[0.25, 0.75]], dtype=float)
    p1 = tf.nn.relu(tf.matmul(tf.cast(oo, dtype=tf.float32),
                              sess.run(Weight_L1)) + sess.run(biases_L1))
    p2 = tf.nn.relu(tf.matmul(p1, sess.run(Weight_L2)) + sess.run(biases_L2))
    p3 = tf.matmul(p2, sess.run(Weight_L4))+sess.run(biases_L4)

    prediction_value = sess.run(prediction, feed_dict={x: oo})

    print('prediction value is:', sess.run(p3))
    print('prediction value is:', prediction_value)
    result = real_export(np.array(prediction_value), np.array(export_data))
    print('The result is:', result[0, :])

    # save Mat_data
    W_L1 = pd.DataFrame(sess.run(Weight_L1))
    b_L1 = pd.DataFrame(sess.run(biases_L1))
    W_L2 = pd.DataFrame(sess.run(Weight_L2))
    b_L2 = pd.DataFrame(sess.run(biases_L2))
    W_L3 = pd.DataFrame(sess.run(Weight_L4))
    b_L3 = pd.DataFrame(sess.run(biases_L4))

    np.savetxt(
        "D:/code/github/sample/python/proj/chi/data/W_L1.txt", sess.run(Weight_L1))
    np.savetxt("D:/code/github/sample/python/proj/chi/data/B_L1.txt",
               sess.run(biases_L1))
    np.savetxt(
        "D:/code/github/sample/python/proj/chi/data/W_L2.txt", sess.run(Weight_L2))
    np.savetxt("D:/code/github/sample/python/proj/chi/data/B_L2.txt",
               sess.run(biases_L2))
    np.savetxt("D:/code/github/sample/python/proj/chi/data/W_L4.txt",
               sess.run(Weight_L4))
    np.savetxt(
        "D:/code/github/sample/python/proj/chi/data/B_L4.txt", sess.run(biases_L4))

    # 写入Excel文件
    writer = pd.ExcelWriter(
        'D:/code/github/sample/python/proj/chi/data/nihe_tf_train.xlsx')
    W_L1.to_excel(writer, 'W_L1', float_format='%.5f')
    b_L1.to_excel(writer, 'b_L1', float_format='%.5f')
    W_L2.to_excel(writer, 'W_L2', float_format='%.5f')
    b_L2.to_excel(writer, 'b_L2', float_format='%.5f')
    W_L3.to_excel(writer, 'W_L3', float_format='%.5f')
    b_L3.to_excel(writer, 'b_L3', float_format='%.5f')

    writer.save()
    writer.close()
