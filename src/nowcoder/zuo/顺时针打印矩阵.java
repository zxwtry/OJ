package nowcoder.zuo;

public class 顺时针打印矩阵 {
	public static void main(String[] args) {
//		int mat[][] = {{4,46,89},{28,66,99},{26,21,71}};
		int mat[][] = {{98,30,17},{38,96,90},{17,0,50},{44,12,67},{12,79,43},{43,63,40},{19,93,48}};
		int[] re = clockwisePrint(mat, mat.length, mat[0].length);
		for (int index = 0; index < re.length; index ++) {
			System.out.print(re[index] + " ");
		}
		System.out.println();
	}
    public static int[] clockwisePrint(int[][] mat, int n, int m) {
        int [] re = new int[m*n];
        int min = Math.min(n, m);
        int indexEnd = (min+ 1) >> 1;
        int reIndex = 0;
        int rown = 0, rowm = 0;
        for (int index = 0; index < indexEnd; index ++) {
        	rown = index; rowm = index;
        	for (rowm = index; rowm < m - 1 - index; rowm ++) {
        		re[reIndex ++] = mat[rown][rowm];
        	}
        	for (rown = index; rown < n - 1- index; rown ++) {
        		re[reIndex ++] = mat[rown][rowm];
        	}
        	if (index*2 + 1 == min) {
        		continue;
        	}
        	for (rowm = m - 1 - index; rowm > index; rowm --) {
        		re[reIndex ++] = mat[rown][rowm];
        	}
        	for (rown = n - 1 - index; rown > index; rown --) {
        		re[reIndex ++] = mat[rown][rowm];
        	}
        }
        if (reIndex == m*n - 1)
        	re[reIndex] = mat[rown][rowm];
        return re;
    }
}
