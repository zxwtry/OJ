package nowcoder.zuo;

/*
 * 	大意：
 * 		一个N长的数组，本来是存储 1-N 上面所有的正整数的。
 * 		现在有某些数字丢失，找到丢失数字中，最小的那个。
 * 		如果没有数字丢失，那么返回N+1
 * 		要求是：
 * 			时间 O(N), 空间 O(1)
 * 		补充：
 * 			传入的数组是可以进行更改的
 */
public class C06丢失的数字 {
	public static void main(String[] args) {
		int[] arr= {1,2,3,5,4};
		System.out.println(missNum(arr));
		for (int a : arr){
			System.out.printf("%d ",a);
		}
			
	}
	static int missNum(int[] arr) {
		int l = 0;				//表示 1-l 范围内的数据已经收集完成了
		int r = arr.length;		//表示最乐观的情况下，可以收集 1-r 范围内的所有数据
		while (l < r) {
			if (arr[l] == l + 1)
				l ++;
				//　收集到的范围增加，同时表示可以去遍历下面一个
			else if (arr[l] <= l || arr[l] > r || arr[arr[l]-1] == arr[l])
				arr[l] = arr[--r];
				// 继续在l这个位置上判断
			else
				swap(arr, l, arr[l]-1);
		}
		return l + 1;
	}
	static void swap(int[] arr, int l, int i) {
		arr[l] = arr[l]^arr[i];
		arr[i] = arr[l]^arr[i];
		arr[l] = arr[l]^arr[i];
	}
}
