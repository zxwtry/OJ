package stl;

public class Book1_2_2_贪心_2_2_1_硬币问题 {
	public static void main(String[] args) {
		solve(167);
	}
	static int[] coin = {1, 2, 5, 10, 20, 50, 100};
	static int[] count = {5, 5, 5, 5, 5, 5, 5};
	static void solve(int money) {
		for (int coinIndex = coin.length - 1; coinIndex >= 0; coinIndex --) {
			int times = Math.min(money/coin[coinIndex], count[coinIndex]);
			money -= times * coin[coinIndex];
			System.out.printf("%d: %d  ", coin[coinIndex], times);
		}
	}
}
