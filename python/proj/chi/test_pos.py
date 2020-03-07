from pos import PSO
import matplotlib.pyplot as plt
import numpy as np


dim = 2         # 维度
size = 10       # 粒子个数
iter_num = 500  # 迭代次数
x_max = 10
max_vel = 0.05  # 最大速度

# 设置粒子维度为2维，种群大小即粒子个数为20，迭代次数为1000，粒子初始化的位置在（- 10, 10）之间，粒子的最大速度为0.5

pso = PSO(dim, size, iter_num, x_max, max_vel)
fit_var_list1, best_pos1 = pso.update()
print("PSO best position:" + str(best_pos1))
print("PSO best answer:" + str(fit_var_list1[-1]))
plt.plot(np.linspace(0, iter_num, iter_num),
         fit_var_list1, c="r", alpha=0.5, label="PSO")


plt.show()
