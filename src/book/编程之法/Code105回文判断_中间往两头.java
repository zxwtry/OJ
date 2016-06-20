package book.编程之法;

public class Code105回文判断_中间往两头 {
	public static void main(String[] args) {
		solve("abcba".toCharArray());
	}
	static void solve(char[] arr) {
		System.out.println(isPalindrome(arr, 0, arr.length-1));
	}
	static boolean isPalindrome(char[] arr, int sta, int end) {
		int arrEnd = 0;
		if (arr == null || (arrEnd = arr.length-1) < 0 || sta < 0 || end < 0
				|| sta > arrEnd || end > arrEnd || sta > end)
			return false;
		int left = (sta+end) >> 1, right = left + ((sta+end) & 0x1);
		while (left >= sta && right <= end) {
			if (arr[left--] != arr[right++])
				return false;
		}
		return true;
	}
}
