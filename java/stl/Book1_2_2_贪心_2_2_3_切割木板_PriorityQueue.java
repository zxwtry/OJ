package stl;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Book1_2_2_贪心_2_2_3_切割木板_PriorityQueue {
	static int n;
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		for (int ni = 0; ni < n; ni ++) {
			pq.add(scanner.nextLong());
		}
		long ans = 0;
		while (n > 1) {
			long p1 = pq.poll();
			long p2 = pq.poll();
			pq.add(p1+p2);
			ans += p1+p2;
			n--;
		}
		System.out.println(ans);
		scanner.close();
	}
}
