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
		debugBookSolution();
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
	
}
