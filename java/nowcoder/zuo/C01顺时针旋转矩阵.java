package nowcoder.zuo;

public class C01顺时针旋转矩阵 {
	public static void main(String[] args) {
		int[][] mat = new int[5][5];
		int matVal = 1;
		for (int index0 = 0; index0 < mat.length; index0 ++) {
			for (int index1 = 0; index1 < mat[0].length; index1 ++) {
				mat[index0][index1] = matVal ++;
			}
		}
		int n = 5;
		int[][] re = rotateMatrix(mat, n);
		for (int index0 = 0 ;index0 < n ;index0 ++) {
			for (int index1 = 0; index1 < n; index1 ++) {
				System.out.print(re[index0][index1] +"\t");
			}
			System.out.println();
		}
	}
    public static int[][] rotateMatrix(int[][] mat, int n) {
        int pre = Integer.MIN_VALUE;
        final int indexEnd = n >> 1;
        for (int index = 0; index < indexEnd; index ++) {
        	for (int indexLay = index; indexLay < n - index - 1; indexLay ++) {
        		pre = mat[index][indexLay];
        		mat[index][indexLay] = mat[n-1-indexLay][index];
        		mat[n-1-indexLay][index] = mat[n-1-index][n-1-indexLay];
        		mat[n-1-index][n-1-indexLay] = mat[indexLay][n-1-index];
        		mat[indexLay][n-1-index] = pre;
        	}
        }
        return mat;
    }
}