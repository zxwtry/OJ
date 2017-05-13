package test;

import java.util.concurrent.ConcurrentHashMap;

public class TestSingleton {
    static final ConcurrentHashMap<S1, Integer> map = new ConcurrentHashMap<S1, Integer>();
    static final int times = 1024 * 1024;
    static final int threads = 16;
    public static void main(String[] args) {
        int count = 0;
        for (int j = 0; j < times; j ++) {
            map.clear();
            Thread[] ts = new Thread[threads];
            for (int i = 0; i < threads; i ++)
                ts[i] = new Test();
            for (int i = 0; i < threads; i ++)
                ts[i].start();
            if (map.size() != 1) count ++;
        }
        System.out.println(count);
    }
    static class Test extends Thread {
        @Override
        public void run() {
            super.run();
            S1 s = S1.get();
            map.put(s, 1);
        }
    }
    //饿汉
    static class S1 {
        private static S1 s1 = new S1();
        public static S1 get() {return s1;}
    }
    //懒汉
    static class S2 {
        private static S2 s2 = null;
        public static S2 get() {
            if (s2 == null) s2 = new S2();
            return s2;
        }
    }
    //加锁
    static class S3 {
        private static S3 s3 = null;
        public synchronized static S3 get() {
            if (s3 == null) s3 = new S3();
            return s3;
        }
    }
    //双检验
    static class S4 {
        private volatile static S4 s4 = null;
        public synchronized static S4 get() {
            if (s4 == null) {
                synchronized (S4.class) {
                    if (s4 == null) {
                        s4 = new S4();
                    }
                }
            }
            return s4;
        }
    }
    //内部类
    static class S5 {
        private static class Holder {
            static S5 s5 = new S5();
        }
        public static S5 get() {
            return Holder.s5;
        }
    }
}
