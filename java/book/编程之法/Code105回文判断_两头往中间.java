package book.编程之法;


public class Code105回文判断_两头往中间 {
	public static void main(String[] args) {
		solve("abac".toCharArray());
	}
	static void solve(char[] arr) {
		System.out.println(isPalindrome(arr, 0, arr.length-1));
	}
	static boolean isPalindrome(char[] arr, int sta, int end) {
		int arrEnd = 0;
		if (arr == null || (arrEnd = arr.length-1) < 0 || sta < 0 || 
				end < 0 || sta > end || sta > arrEnd || end > arrEnd)
			return false;
		while (sta < end) {
			if (arr[sta++] != arr[end--])
				return false;
		}
		return true;
	}
}
