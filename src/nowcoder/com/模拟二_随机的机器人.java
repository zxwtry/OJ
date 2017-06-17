package nowcoder.com;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;


/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        模拟二_随机的机器人.java
 * @date        2017年6月17日 下午10:22:32
 * @details     
 */
public class 模拟二_随机的机器人 {
      public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
//      while (in.hasNextInt()) {//注意while处理多个case
//          int a = in.nextInt();
//          int b = in.nextInt();
//          System.out.println(a + b);
//      }
      int n = in.nextInt();
      int off = n + 2;
      int[] arr = new int[2 * n + 5];
//      Queue<Integer> q = new LinkedList<>();
//      q.add(off);
      HashMap<Integer, Block> m = new HashMap<>();
      Block b = new Block();
      b.max = off;
      b.min = off;
      m.put(off, b);
      for (int i = 0; i < n; i ++) {
          HashMap<Integer, Block> mm = new HashMap<>();
          for (Entry<Integer, Block> e : m.entrySet()) {
              
          }
      }
    }
      static class Block {
          int max;
          int min;
          int now;
          double v;
      }
}
