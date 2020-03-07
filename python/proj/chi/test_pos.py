from pos import PSO
import matplotlib.pyplot as plt
import numpy as np


dim = 2
size = 10
iter_num = 500  # 迭代次数
x_max = 10
max_vel = 0.05  # 最大速度

pso = PSO(dim, size, iter_num, x_max, max_vel)
fit_var_list1, best_pos1 = pso.update()
print("PSO best position:" + str(best_pos1))
print("PSO best answer:" + str(fit_var_list1[-1]))
plt.plot(np.linspace(0, iter_num, iter_num),
         fit_var_list1, c="r", alpha=0.5, label="PSO")


plt.show()
