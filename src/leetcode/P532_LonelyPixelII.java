package leetcode;

/**
 *  Given a picture consisting of black and white pixels, and a positive integer N, 
 *  find the number of black pixels located at some specific row R and column C that 
 *  align with all the following rules:
 *  
 *  Row R and column C both contain exactly N black pixels.
 *  For all rows that have a black pixel at column C, they should be exactly the same as row R
 *  The picture is represented by a 2D char array consisting of 'B' and 'W', which means 
 *  black and white pixels respectively.
 *  
 *  Example:
 *  Input:                                            
 *  [['W', 'B', 'W', 'B', 'B', 'W'],    
 *   ['W', 'B', 'W', 'B', 'B', 'W'],    
 *   ['W', 'B', 'W', 'B', 'B', 'W'],    
 *   ['W', 'W', 'B', 'W', 'B', 'W']] 
 *  
 *  N = 3
 *  Output: 6
 *  Explanation: All the bold 'B' are the black pixels we need (all 'B's at column 1 and 3).
 *          0    1    2    3    4    5         column index                                            
 *  0    [['W', 'B', 'W', 'B', 'B', 'W'],    
 *  1     ['W', 'B', 'W', 'B', 'B', 'W'],    
 *  2     ['W', 'B', 'W', 'B', 'B', 'W'],    
 *  3     ['W', 'W', 'B', 'W', 'B', 'W']]    
 *  row index
 *  
 *  Take 'B' at row R = 0 and column C = 1 as an example:
 *  Rule 1, row R = 0 and column C = 1 both have exactly N = 3 black pixels. 
 *  Rule 2, the rows have black pixel at column C = 1 are row 0, row 1 and row 2. 
 *  They are exactly the same as row R = 0.
 *  
 *  Note:
 *  The range of width and height of the input 2D array is [1,200].
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P532_LonelyPixelII.java
 * @type        P532_LonelyPixelII
 * @date        2017年3月5日 上午11:25:38
 * @details     Solution1: AC 21ms
 */
public class P532_LonelyPixelII {
    static class Solution1 {
        public int findBlackPixel(char[][] p, int N) {
            if (p == null || p.length == 0 || p[0].length == 0) return 0;
            int row = p.length, col = p[0].length;
            int[] rowCount = new int[row];
            int count = 0;
            for (int i = 0; i < row; i ++) {
                count = 0;
                for (int j = 0; j < col; j ++) {
                    count += (p[i][j] == 'B' ? 1 : 0);
                }
                rowCount[i] = count;
            }
            int[] colCount = new int[col];
            for (int j = 0; j < col; j ++) {
                count = 0;
                for (int i = 0; i < row; i ++) {
                    count += (p[i][j] == 'B' ? 1 : 0);
                }
                colCount[j] = count;
            }
            boolean[][] map = new boolean[row][col];
            int answer = 0;
            for (int i = 0; i < row; i ++) {
                if (rowCount[i] != N) continue;
                for (int j = 0; j < col; j ++) {
                    if (colCount[j] != N || map[i][j] || p[i][j] != 'B') continue;
                    boolean isAllTrue = true;
                    for (int k = 0; k < row; k ++)
                        if (k != i)
                        if (p[k][j] == 'B')
                            if ( !cmp(p[i], p[k]))
                                isAllTrue = false;
                    if (isAllTrue) {
                        for (int k = i + 1; k < row; k ++) 
                            if (p[k][j] == 'B')
                                map[k][j] = true;
                        answer += N;
                    }
                    map[i][j] = true;
                }
            }
            return answer;
        }
        private boolean cmp(char[] c1, char[] c2) {
            if (c1.length != c2.length) return false;
            for (int i = 0; i < c1.length; i ++)
                if (c1[i] != c2[i]) return false;
            return true;
        }
    }
}
