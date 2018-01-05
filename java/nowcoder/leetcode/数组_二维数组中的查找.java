package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        数组_二维数组中的查找.java
 * @date        2017年6月29日 下午9:08:44
 * @details     剑指offer
 */
public class 数组_二维数组中的查找 {
    static public class Solution {
        public boolean Find(int target, int [][] array) {
            int row = array == null ? 0 : array.length;
            if (row == 0) return false;
            int col = array[0] == null ? 0 : array[0].length;
            if (col == 0) return false;
            return find(target, array, 0, row - 1, 0, col - 1);
        }
        boolean find(int target, int[][] array, int rowi, int rowj, int coli, int colj) {
            if (rowi > rowj || coli > colj) return false;
            
            int rowMid = (rowi + rowj) / 2;
            int colMid = (coli + colj) / 2;
            
            if (array[rowMid][colMid] == target) {
                return true;
            } else if (array[rowMid][colMid] > target) {
                return find(target, array, rowi, rowj, coli,colMid-1 ) ||
                    find(target, array, rowi, rowMid-1,colMid, colj);
            } else {
                return find(target, array, rowMid+1, rowj, coli, colj) ||
                    find(target, array, rowi,rowMid ,colMid+1, colj);
            }
        }
    }
}
