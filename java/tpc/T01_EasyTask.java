package main;

import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Scanner;
import java.io.File;

public class T01_EasyTask {
    static Comparator<Long> cmp = new Comparator<Long>() {
        public int compare(Long e1, Long e2) {
            if (e1 > e2) {
                return -1;
            } else if (e1 < e2) {
                return 1;
            } else {
                return 0;
            }
        }
    };

    public static void main(String[] args) throws Exception {
        // File f = new File("D:\\code\\github\\sample\\java\\tpc\\t01_input.txt");
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            long[] arr = new long[n];
            for (int ni = 0; ni < n; ni++) {
                arr[ni] = sc.nextLong();
            }
            solve(arr);
        }
        sc.close();
    }

    public static void solve(long[] arr) {
        PriorityQueue<Long> q1 = new PriorityQueue<>();
        PriorityQueue<Long> q2 = new PriorityQueue<>(cmp);
        for (int ai = 0; ai < arr.length; ai++) {
            q1.add(arr[ai]);
            q2.add(arr[ai]);
        }
        while (true) {
            long min = q1.poll();
            long max = q2.poll();
            if (max == min) {
                System.out.printf("%d", min);
                return;
            }
            q1.remove(max);
            q2.remove(min);
            long calc = max - min;
            q1.add(calc);
            q2.add(calc);
            q1.add(calc);
            q2.add(calc);
        }
    }
}