package csdn;

/*
 * Runtime使用方法：
 * rt: 		当前的Runtime.getRuntime()
 * max:		Java 虚拟机试图使用的最大内存量		单位：MB
 * total:	Java 虚拟机中的内存总量				单位：MB
 * free:	Java 虚拟机中的空闲内存量				单位：MB
 */

/*
 * 	由于在Eclipse中，生成的JVM的内存是
 * 	比较小的。
 * 	在运行的过程中，可能会出现GC的情况和内存扩展的情况。
 * 	在运行的过程中，采用
 * 	java -XmsXXXXm -XmxXXXXm Main的方式去运行 
 * 	统一采用  java -Xmx8192m -Xms8192m Main的方式运行
 * 	在实际运行过程中，采用只复制部分方法的方式
 */

public class CSDN002_高级数据结构的内存占用 {
	public static void main(String[] args) {
		testMain();
	}
	static void testMain() {
		Runtime rt = Runtime.getRuntime();
		
	}
}
