package nowcoder.zuo;

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book058_汉诺塔问题.java
 * @type        Book058_汉诺塔问题
 * @date        2016年11月30日 下午12:20:16
 * @details     汉诺塔问题
 */
public class Book058_汉诺塔问题 {
	public static void main(String[] args) {
		debugBookSolution进阶_递归版本();
		
	}
	
	static void debugBookSolution进阶_递归版本() {
		BookSolution进阶_递归版本 s = new BookSolution进阶_递归版本();
		int[] arr = new int[] {1, 3, 1};
		int index = arr.length - 1;
		int from = 1;
		int mid = 2;
		int to = 3;
		System.out.println(s.getStep(arr, index, from, mid, to));
	}

	static void debugBookSolution() {
		BookSolution s = new BookSolution();
		s.hanoi(3, "左", "中", "右");
	}

	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book058_汉诺塔问题.java
	 * @type        BookSolution
	 * @date        2016年11月30日 下午3:13:36
	 * @details     经典汉诺塔问题
	 * @details     给定一个正整数n，代表从小到大的n个圆盘
	 * @details     假设开始时候，所有的圆盘都放置在左边的柱子上
	 * @details     将所有圆盘移到右边的柱子上，并打印最优移动轨迹
	 */
	static class BookSolution {
		public void hanoi(int n, String from, String mid, String to) {
			if (n == 1) {
				System.out.println("move from " + from + " to " + to);
			} else {
				hanoi(n - 1, from, to, mid);
				hanoi(1, from, mid, to);
				hanoi(n - 1, mid, from, to);
			}
		}
	}
	
	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book058_汉诺塔问题.java
	 * @type        BookSolution进阶
	 * @date        2016年11月30日 下午5:34:56
	 * @details     依旧是经典汉诺塔问题
	 * @details     区别在于：现在使用一个arr记录解决问题中的中间过程
	 * @details     arr：长度N，每一位1~3，第i位的值代表第i+1个圆盘的位置
	 * @details     返回arr代表的状态是最优移动轨迹过程中的第几个状态，如果不是返回-1
	 * @details     进阶的要求在于：时间复杂度O(N)，空间复杂度O(1)
	 * @details    	 分析一：汉诺塔完成长度N的需要的步数是：2^N - 1
	 * @details    	 分析二：如果arr[N-1]是3，那么至少已经完成了2^(N-1) - 1步
	 * @details    	 分析三：如果arr[N-1]是2，不可能，返回-1
	 * @details    	 分析四：如果arr[N-1]是1，还没有到2^(N-1) - 1步，考察1~N-1的情况
	 */
	static class BookSolution进阶_递归版本 {
		public int getStep(int[] arr, int index, int from, int mid, int to) {
			if (index == -1)	return 0;
			if (arr[index] == from) {
				return getStep(arr, index - 1, from, to, mid);
			} else if (arr[index] == to) {
				int preSteps = getStep(arr, index - 1, mid, from, to);
				return preSteps == -1 ? -1 : preSteps + (1 << index);
			} else {
				return -1;
			}
		}
	}
	
}
