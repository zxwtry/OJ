package csdn;

import java.util.Stack;

public class CSDN001_两个栈peek的比较 {
	public static void main(String[] args) {
//		int[] arr = {-130, -129, -128, -127, -126, 126, 127, 128, 129, 130};
//		for (int val : arr) {
//			test(val);
//		}
//		newInteger1();
//		newInteger2();
	}
	static void newInteger1() {
		Integer i1 = Integer.valueOf(12);
		Integer i2 = Integer.valueOf(12);
		System.out.println(i1 + "\tInteger.valueOf两个对象，直接使用==判断两个对象在内存中是不是一个对象：" + (i1 == i2));
		i1 = Integer.valueOf(128);
		i2 = Integer.valueOf(128);
		System.out.println(i1 + "\tInteger.valueOf两个对象，直接使用==判断两个对象在内存中是不是一个对象：" + (i1 == i2));
	}
	static void newInteger2() {
		Integer i1 = new Integer(12);
		Integer i2 = new Integer(12);
		System.out.println(i1 + "\tnew Integer两个对象，直接使用==判断两个对象在内存中是不是一个对象：" + (i1 == i2));
		i1 = new Integer(128);
		i2 = new Integer(128);
		System.out.println(i1 + "\tnew Integer两个对象，直接使用==判断两个对象在内存中是不是一个对象：" + (i1 == i2));
	}
	static void test(int pushVal) {
		Stack<Integer> stk1 = new Stack<>();
		Stack<Integer> stk2 = new Stack<>();
		stk1.push(pushVal);
		stk2.push(pushVal);
		int val1 = stk1.peek();
		int val2 = stk2.peek();
		System.out.println(pushVal + "\t弹出之后保存为int再进行比较\t\t" + (val1 == val2));
		System.out.println(pushVal + "\t直接peek再进行比较        \t\t" + (stk1.peek() == stk2.peek()));
	}
}
