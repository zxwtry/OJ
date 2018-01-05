package test;

import java.util.ArrayList;
import java.util.List;

public class Sort_SleepSort {
    
    public static void main(String[] args) {
        int[] arr = {1, 3, 9, 2, 4, 5, 4, 5, 4, 5, 4 ,5, 2};
        sort(arr);
        for (int v : arr) System.out.println(v);
    }
    
    static final Object lock = new Object();
    
    private static void addList(List<Integer> list, int val) {
        synchronized (lock) {
            list.add(val);
        }
    }
    
    public static void sort(int[] arr) {
        int an = arr == null ? 0 : arr.length;
        if (an < 2) return;
        List<Integer> list = new ArrayList<Integer>();
        Sleep[] ss = new Sleep[an];
        for (int i = 0; i < an; i ++)
            ss[i] = new Sleep(arr[i], list, an*5);
        for (int i = 0; i < an; i ++)
            ss[i].start();
        while (list.size() != an) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < an; i ++)
            arr[i] = list.get(i);
    }
    
    static class Sleep extends Thread {
        private int val, times;
        private List<Integer> list;
        public Sleep(int val, List<Integer> list, int times) {
            super();
            this.val = val;
            this.list = list;
            this.times = times;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(val*times);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            addList(list, val);
        }
    }
    
}
