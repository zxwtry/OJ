package nowcoder.zuo;

public class C04添加回文串 {
	static int center, end;
	public static void main(String[] args) {
		String string = "abbbbbaa";
		System.out.println(addToPalindrome(string, 0));
		System.out.println(center+".."+end);
	}
    public static String addToPalindrome(String A, int n) {
		travelIndex(A);
		if (center == end) {
			StringBuilder stringBuilder = new StringBuilder(A);
			stringBuilder.deleteCharAt(A.length()-1);
			stringBuilder.reverse();
			return stringBuilder.toString();
		} else {
			StringBuilder stringBuilder = new StringBuilder(A.substring(0, 2*center-end+1));
			stringBuilder.reverse();
			return stringBuilder.toString();
		}
    }
	static void travelIndex(String string) {
		center = 0;
		end = string.length();
		int endIndex = end - 1;
		while(string.charAt(center) != string.charAt(endIndex)){
			center ++;
		}
		while(center < endIndex) {
			if (string.charAt(center) != string.charAt(endIndex))
				end = endIndex;
			else {
				center ++;
				endIndex --;
			}
		}
	}
}
