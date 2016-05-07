package nowcoder.zuo;

public class C01折纸问题递归 {
	public static void main(String[] args) {
		String[] re = foldPaper(4);
		for (int index = 0; index < re.length; index ++) {
			System.out.print(re[index] + "\t");
		}
		System.out.println();
	}
	public static String[] foldPaper(int n) {
		if (n <= 0) {
    		return null;
    	}
    	final int len = (1 << n) - 1;
        String[] returnString = new String[len];
        findAll(returnString, 0, len-1, true);
        return returnString;
	}
	static void findAll(String[] returnString, int begin, int end, boolean isDown) {
		if (begin > end) {
			return;
		}
		int mid = (begin+end) >> 1;
		findAll(returnString, begin, mid-1, true);
		returnString[mid] = isDown ? "down" : "up";
		findAll(returnString, mid+1, end, false);
	}
}
