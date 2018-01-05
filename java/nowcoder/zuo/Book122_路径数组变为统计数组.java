package nowcoder.zuo;

/**  
 * 	[题目]
 * 	给定一个路径数组path, 表示一张图。
 * 	paths[i]==j代表城市i连向城市j，
 * 	如果paths[i]==i, 则表示i城市是首都，
 * 	一张图里只会有一个首都且图中除首都指向自己之外，
 * 	不会有环。例如，paths={9,1,4,9,0,4,8,9,0,1}
 * 
 * 	[要求]
 * 	时间O(N)，空间O(1)
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book122_路径数组变为统计数组.java
 * @type        Book122_路径数组变为统计数组
 * @date        2017年1月6日 下午4:34:21
 * @details     
 */
public class Book122_路径数组变为统计数组 {
	static class Solution {
		public void pathsToNums(int[] paths) {
			if (paths == null || paths.length == 0) return;
			pathsToDistans(paths);
			distansToNums(paths);
		}
		public void pathsToDistans(int[] paths) {
			int cap = 0;
			for (int i = 0; i < paths.length; i ++) {
				if (paths[i] == i) {
					cap = i;
				} else if (paths[i] > -1) {
					int curI = paths[i];
					paths[i] = -1;
					int preI = i;
					while (paths[curI] != curI) {
						if (paths[curI] > -1) {
							int nextI = paths[curI];
							paths[curI] = preI;
							preI = curI;
							curI = nextI;
						} else break;
	 				}
					int value = paths[curI] == curI ? 0 : paths[curI];
					while (paths[preI] != -1) {
						int lastPreI = paths[preI];
						paths[preI] = -- value;
						curI = preI;
						preI = lastPreI;
					}
					paths[preI] = --value;
				}
			}
			paths[cap] = 0;
		}
		public void distansToNums(int[] disArr) {
			for (int i = 0; i < disArr.length; i ++) {
				int index = disArr[i];
				if (index < 0) {
					disArr[i] = 0;
					while (true) {
						index = -index;
						if (disArr[index] > -1) {
							disArr[index] ++;
							break;
						} else {
							int nextIndex = disArr[index];
							disArr[index] = 1;
							index = nextIndex;
						}
					}
				}
			}
			disArr[0] = 1;
		}
	}
}
