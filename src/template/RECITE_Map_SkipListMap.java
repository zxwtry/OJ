package template;

import java.util.Random;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        RECITE_Map_SkipListMap.java
 * @date        2017年7月31日 上午10:15:41
 * @details     http://www.acmerblog.com/skip-list-impl-java-5773.html
 */
public class RECITE_Map_SkipListMap {
    
    public static void main(String[] args) {
        SkipListMap S = new SkipListMap();

        S.printHorizontal();
        System.out.println("------");
        // S.printVertical();
        // System.out.println("======");

        S.put("ABC", 123);
        S.printHorizontal();
        System.out.println("------");
        // S.printVertical();
         //System.out.println("======");

        S.put("DEF", 123);
        S.printHorizontal();
        System.out.println("------");
        // S.printVertical();
        // System.out.println("======");

        S.put("KLM", 123);
        S.printHorizontal();
        System.out.println("------");
        // S.printVertical();
        // System.out.println("======");

        S.put("HIJ", 123);
        S.printHorizontal();
        System.out.println("------");
        // S.printVertical();
        // System.out.println("======");

        S.put("GHJ", 123);
        S.printHorizontal();
        System.out.println("------");
        // S.printVertical();
        // System.out.println("======");

        S.put("AAA", 123);
        S.printHorizontal();
        System.out.println("------");
        // S.printVertical();
        // System.out.println("======");
    }
    
    static public class SkipListEntry {
        public String key;
        public Integer value;

        public int pos; //主要为了打印 链表用

        public SkipListEntry up, down, left, right; // 上下左右 四个指针

        public static String negInf = new String("-oo"); // 负无穷
        public static String posInf = new String("+oo"); // 正无穷

        public SkipListEntry(String k, Integer v) {
            key = k;
            value = v;

            up = down = left = right = null;
        }

        public Integer getValue() {
            return value;
        }

        public String getKey() {
            return key;
        }

        public Integer setValue(Integer val) {
            Integer oldValue = value;
            value = val;
            return oldValue;
        }

        public boolean equals(Object o) {
            SkipListEntry ent;
            try {
                ent = (SkipListEntry) o; // 检测类型
            } catch (ClassCastException ex) {
                return false;
            }
            return (ent.getKey() == key) && (ent.getValue() == value);
        }

        public String toString() {
            return "(" + key + "," + value + ")";
        }
    }
    
    static public class SkipListMap {
        public SkipListEntry head; // 顶层的第一个元素
        public SkipListEntry tail; // 顶层的最后一个元素

        public int n; // 跳跃表中的元素个数

        public int h; // 跳跃表的高度
        public Random r; // 投掷硬币

        public SkipListMap() // 默认构造函数...
        {
            SkipListEntry p1, p2;

            p1 = new SkipListEntry(SkipListEntry.negInf, null);
            p2 = new SkipListEntry(SkipListEntry.posInf, null);

            head = p1;
            tail = p2;

            p1.right = p2;
            p2.left = p1;

            n = 0;
            h = 0;
            r = new Random();
        }

        /** 返回 包含的元素个数 */
        public int size() {
            return n;
        }

        /** 跳跃表是否为空 */
        public boolean isEmpty() {
            return (n == 0);
        }

         //在最下面一层，找到要插入的位置前面的那个key
        public SkipListEntry findEntry(String k) {
            SkipListEntry p;
            p = head;

            while (true) {
                /**
                 * 一直向右找，例: k=34. 
                 * 10 ---> 20 ---> 30 ---> 40 ^ | p 会在30处停止
                 * --------------------------------------------
                 ***/
                while (p.right.key != SkipListEntry.posInf
                        && p.right.key.compareTo(k) <= 0) {
                    p = p.right;
                //  System.out.println(">>>> " + p.key);
                }
                // 如果还有下一层，就到下一层继续查找
                if (p.down != null) {
                    p = p.down;
                     //System.out.println("vvvv " + p.key);
                } else
                    break; // 到了最下面一层 就停止查找
            }

            return (p); // p.key <= k
        }

        /** 返回和key绑定的值 */
        public Integer get(String k) {
            SkipListEntry p;

            p = findEntry(k);

            if (k.equals(p.getKey()))
                return (p.value);
            else
                return (null);
        }

        /** 放一个key-value到跳跃表中, 替换原有的并返回 */
        public Integer put(String k, Integer v) {
            SkipListEntry p, q;
            int i;

            p = findEntry(k);

            if (k.equals(p.getKey())) {
                Integer old = p.value;
                p.value = v;
                return (old);
            }

            q = new SkipListEntry(k, v);
            q.left = p;
            q.right = p.right;
            p.right.left = q;
            p.right = q;

            i = 0; // 当前层 level = 0

            while (r.nextDouble() < 0.5) {

                //如果超出了高度，需要重新建一个顶层
                if (i >= h) {
                    SkipListEntry p1, p2;

                    h = h + 1;
                    p1 = new SkipListEntry(SkipListEntry.negInf, null);
                    p2 = new SkipListEntry(SkipListEntry.posInf, null);

                    p1.right = p2;
                    p1.down = head;

                    p2.left = p1;
                    p2.down = tail;

                    head.up = p1;
                    tail.up = p2;

                    head = p1;
                    tail = p2;
                }

                while (p.up == null) {
                    p = p.left;
                }
                p = p.up;

                SkipListEntry e;

                e = new SkipListEntry(k, null); 
                e.left = p;
                e.right = p.right;
                e.down = q;

                p.right.left = e;
                p.right = e;
                q.up = e;

                q = e; // q 进行下一层迭代
                i = i + 1; // 当前层 +1

            }
            n = n + 1;

            return (null); // No old value
        }

        public Integer remove(String key) {
            return (null);
        }

        public void printHorizontal() {
            String s = "";
            int i;
            SkipListEntry p;

            p = head;

            while (p.down != null) {
                p = p.down;
            }

            i = 0;
            while (p != null) {
                p.pos = i++;
                p = p.right;
            }

            p = head;
            while (p != null) {
                s = getOneRow(p);
                System.out.println(s);

                p = p.down;
            }
        }

        //用了打印测试
        public String getOneRow(SkipListEntry p) {
            String s;
            int a, b, i;

            a = 0;

            s = "" + p.key;
            p = p.right;

            while (p != null) {
                SkipListEntry q;

                q = p;
                while (q.down != null)
                    q = q.down;
                b = q.pos;

                s = s + " <-";

                for (i = a + 1; i < b; i++)
                    s = s + "--------";

                s = s + "> " + p.key;

                a = b;

                p = p.right;
            }

            return (s);
        }

        //用了打印测试
        public void printVertical() {
            String s = "";
            SkipListEntry p;
            p = head;
            while (p.down != null)
                p = p.down;

            while (p != null) {
                s = getOneColumn(p);
                System.out.println(s);

                p = p.right;
            }
        }
        //用了打印测试
        public String getOneColumn(SkipListEntry p) {
            String s = "";
            while (p != null) {
                s = s + " " + p.key;
                p = p.up;
            }
            return (s);
        }
    }
    
    
}
