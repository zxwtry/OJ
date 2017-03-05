package leetcode;

/**
 *  Given a picture consisting of black and white pixels, find the number of black lonely pixels.
 *  
 *  The picture is represented by a 2D char array consisting of 'B' and 'W', 
 *  which means black and white pixels respectively.
 *  
 *  A black lonely pixel is character 'B' that located at a specific position 
 *  where the same row and same column don't have any other black pixels.
 *  
 *  Example:
 *  Input: 
 *  [['W', 'W', 'B'],
 *   ['W', 'B', 'W'],
 *   ['B', 'W', 'W']]
 *  
 *  Output: 3
 *  Explanation: All the three 'B's are black lonely pixels.
 *  Note:
 *  The range of width and height of the input 2D array is [1,500].
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P531_LonelyPixelI.java
 * @type        P531_LonelyPixelI
 * @date        2017年3月5日 上午11:13:36
 * @details     Solution1: AC 42ms 50%
 */
public class P531_LonelyPixelI {
    static class Solution1 {
        public int findLonelyPixel(char[][] p) {
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
            int answer = 0;
            for (int i = 0; i < row; i ++) {
                for (int j = 0; j < col; j ++) {
                    if (p[i][j] == 'B' && rowCount[i] == 1 && colCount[j] == 1)
                        answer ++;
                }
            }
            return answer;
        }
    }
}
