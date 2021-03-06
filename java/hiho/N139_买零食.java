package hiho;

import java.util.Scanner;

/**
 *  描述
 *  小Ho很喜欢在课间去小卖部买零食。然而不幸的是，这个学期他又有在一教的课，而一教的小卖部姐姐以冷若冰霜著称。
 *  第一次去一教小卖部买零食的时候，小Ho由于不懂事买了好一大堆东西，被小卖部姐姐给了一个“冷若冰霜”的眼神，食欲都下降了很多。
 *  
 *  从那以后，小Ho就学乖了，去小卖部买东西只敢同时买3包以内的零食，并且价格加起来必须是5的整数倍，方便小卖部姐姐算价格。
 *  
 *  但是小Ho不擅长计算，所以他把小卖部里所有零食的价格以及他对这个零食的渴望度都告诉了你，
 *  希望你能够帮他计算出在不惹恼小卖部姐姐的前提下，能够买到零食的渴望度之和最高是多少？
 *  
 *  输入
 *  每个输入文件包含多组测试数据，在每个输入文件的第一行为一个整数Q，表示测试数据的组数。
 *  
 *  每组测试数据的第一行为一个正整数N，表示小卖部中零食的数量。
 *  
 *  接下来的N行，每行为一个正实数A和一个正整数B，表示这种零食的价格和小Ho对其的渴望度。
 *  
 *  一种零食仅有一包。
 *  
 *  对于100%的数据，满足1 <= Q <= 10，1<=N<=50，0<A<=10，1<=B<=100。
 *  
 *  对于100%的数据，满足A的小数部分仅可能为0.5或0。
 *  
 *  输出
 *  对于每组测试数据，输出一个整数Ans，表示小Ho可以获得最大的渴望度之和。
 *  
 *  样例输入
 *  1
 *  4
 *  0.5 6
 *  4.5 7
 *  5.0 4
 *  2.0 9
 *  样例输出
 *  17
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     hiho
 * @file        N139_买零食.java
 * @type        N139_买零食
 * @date        2017年3月1日 上午9:25:00
 * @details     solve1: AC 855ms 19MB *
 */
public class N139_买零食 {
    static int[] prices = new int[55];
    static int[] desires = new int[55];
    static int num = 0;
    static int maxDesires = 0;
    static int sumPrices = 0;
    static int sumDesires = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int times = scanner.nextInt(); times > 0; times --) {
            num = scanner.nextInt();
            for (int numIndex = 0; numIndex < num; numIndex ++) {
                prices[numIndex] = (int) (2 * scanner.nextFloat());
                desires[numIndex] = scanner.nextInt();
            }
            maxDesires = 0;
            solve1();
            System.out.println(maxDesires);
        }
        scanner.close();
    }
    static void solve1() {
        for (int i = 0; i < num; i ++) {
            solve1JudgeSumPrices(i, -1, -1);
            for (int j = i + 1; j < num; j ++) {
                solve1JudgeSumPrices(i, j, -1);
                for (int k = j + 1; k < num; k ++) {
                    solve1JudgeSumPrices(i, j, k);
                }
            }
        }
    }
    static void solve1JudgeSumPrices(int i, int j, int k) {
        sumPrices = solve1Access(prices, i) + solve1Access(prices, j) + solve1Access(prices, k);
        sumDesires = solve1Access(desires, i) + solve1Access(desires, j) + solve1Access(desires, k);
        if (sumPrices % 10 == 0) maxDesires = Math.max(maxDesires, sumDesires);
    }
    static int solve1Access(int[] arr, int index) {
        if (index == -1) return 0;
        return arr[index];
    }
}
