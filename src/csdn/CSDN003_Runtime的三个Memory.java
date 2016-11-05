package csdn;

import java.util.Scanner;

/*
 * 	在JVM的java.lang.Runtime对象中，
 * 		有三个非常有意思的方法：
 * 			maxMemory
 * 			totalMemory
 * 			freeMemory
 * 		 按照正常的理解：
 * 			内存不就两个值嘛：
 * 				一个表示总内存，一个表示已经使用的内存
 * 			这三个值，在我的笔记中有这么个公式	
 * 				avail = max - total + free	
 * 			 我已经不记得当时的想法，现在想重新学学
 */
/*
根据JDK的文档：
freeMemory
public long freeMemory()
返回 Java 虚拟机中的空闲内存量。
调用 gc 方法可能导致 freeMemory 返回值的增加。 

返回：
供将来分配对象使用的当前可用内存的近似总量，以字节为单位。

--------------------------------------------------------------------------------

totalMemory
public long totalMemory()
返回 Java 虚拟机中的内存总量。
此方法返回的值可能随时间的推移而变化，这取决于主机环境。 
注意，保存任意给定类型的一个对象所需的内存量可能取决于实现方法。 

返回：
目前为当前和后续对象提供的内存总量，以字节为单位。

--------------------------------------------------------------------------------

maxMemory
public long maxMemory()
返回 Java 虚拟机试图使用的最大内存量。
如果内存本身没有限制，则返回值 Long.MAX_VALUE。 

返回：
虚拟机试图使用的最大内存量，以字节为单位。
从以下版本开始： 
1.4 
 */

/*
 * 	在Eclipse中的JVM配置相对复杂，
 * 	这里采用简单版的JVM配置。
 * 		java -Xmx2048m -Xms1024m Main的方式运行
 * 	在运行的过程中，采用新建复杂对象，
 * 		然后丢失引用的方式，创建可被GC的对象
 */

/*
 * 	note1:
 * 		01,	对应的方法是：runTestIntArray()
 * 		02,	int[] arr = new int[len];	会消耗多大内存？
 * 			下面都是根据代码在运行，每一次测试都是独立的。
 * 			初始情况是：
 * 			max: 1864192	 free: 989327	 total: 1005056
 * 			新建1<<10长度的数组之后是：	对应int内存4KB 			--- 没有分配内存
 * 			max: 1864192	 free: 989327	 total: 1005056
 * 			新建1<<20长度的数组之后是：	对应int内存4*1024KB		---	没有分配内存		
 * 			max: 1864192	 free: 989327	 total: 1005056
 *			新建1<<21长度的数组之后是：	对应int内存8*1024KB		---	正确分配内存
 *			max: 1864192	 free: 981135	 total: 1005056		
 *			新建1<<24长度的数组之后是：	对应int内存64*1024KB		---	正确分配内存
 *			max: 1864192	 free: 923791	 total: 1005056		
 *			新建1<<27长度的数组之后是：	对应int内存512*1024KB	---	正确分配内存
 *			max: 1864192	 free: 465039	 total: 1005056
 *			新建1<<28长度的数组之后是：	对应int内存1024*1024KB	---	正确分配内存
 *			max: 1864192	 free: 639631	 total: 1703936
 *			(看27~28的过程，total进行了扩展，在这里可以认为是：从-Xms扩展到-Xmx)
 *			(也解释清楚了avail = free + (max - total))
 *			(free：是已经分配的余量		max-total：是可能扩展的余量)
 *		03,	在02中，为什么10和20，free没有变化呢？
 *			新建对象之后，再对对象进行赋值看看．
 *			没有变化，还是一样的结果．
 *		04,	对这个过程唯一的解释就是：
 *			对象存储在缓存中．
 *		05,	马上会有另外一个问题
 *			这份缓存能够复用吗？程序在runTestIntArrayReuse()
 *			初始情况是：
 *			max: 1864192	 free: 989327	 total: 1005056
 *			新建1<<20长度的数组之后是：		
 *			max: 1864192	 free: 989327	 total: 1005056
 *			新建1<<20长度的数组之后是：(连续的过程，并没有退出JVM)
 *			max: 1864192	 free: 984084	 total: 1005056
 *			在这两个过程中间，添加
 *			arr = null;
 *			System.gc();
 *			初始情况是：
 *			max: 1864192	 free: 989327	 total: 1005056
 *			新建1<<20长度的数组之后是：		
 *			max: 1864192	 free: 989327	 total: 1005056
 *			新建1<<20长度的数组之后是：(连续的过程，并没有退出JVM)
 *			max: 1864192	 free: 999083	 total: 1005056
 *			貌似也在意料之中．
 *		06,	突然想到一个问题：
 *			在我的代码中，都没有在得到这三个Memory之前，
 *			进行全局System.gc()，这样貌似很不对．．．
 *			回到runTestIntArray，在每次获得Memory之前都进行gc
 *			初始情况是：
 *			max: 1864192	 free: 1004345	 total: 1005056
 *			新建1<<20长度的数组之后是：
 *			max: 1864192	 free: 1004359	 total: 1005056
 *			另外一个测试：
 *			初始情况是：
 *			max: 1864192	 free: 1004345	 total: 1005056
 *			新建1<<21长度的数组之后是：
 *			max: 1864192	 free: 996167	 total: 1005056
 *			这能说明：JVM启动之后，会对保留一定的内存空间用于快速使用．
 *			当分配的空间，小于目标预设，那么会出现新建内存空间之后，再gc，free会增加．
 *			考虑1<<21这种情况，对应int内存8*1024KB，也就是说，没有使用保留空间．
 */
public class CSDN003_Runtime的三个Memory {
	public static void main(String[] args) {
		runTestIntArray();
		runTestIntArrayReuse();
	}
	static void runTestIntArrayReuse() {
		Runtime rt = Runtime.getRuntime();
		Scanner sc = new Scanner(System.in);
		long max = 0;
		long free = 0;
		long total = 0;
		max = rt.maxMemory() >> 10;			//转换为kB
		free = rt.freeMemory() >> 10;
		total = rt.totalMemory() >> 10;
		System.out.println("初始情况是：");
		System.out.printf("max: %d\t free: %d\t total: %d\r\n", max, free, total);
		int leftMoveBits = sc.nextInt();
		int len = 1 << leftMoveBits;
		int[] arr = new int[len];
		for (int k = 0; k < len; k ++) {
			arr[k] = k;
			//添加了这个过程，消耗的内存是一样的．
		}
		max = rt.maxMemory() >> 10;			//转换为kB
		free = rt.freeMemory() >> 10;
		total = rt.totalMemory() >> 10;
		System.out.printf("新建1<<%d长度的数组之后是：\r\n", leftMoveBits);
		System.out.printf("max: %d\t free: %d\t total: %d\r\n", max, free, total);
		leftMoveBits = sc.nextInt();
		len = 1 << leftMoveBits;
		arr = new int[len];
		for (int k = 0; k < len; k ++) {
			arr[k] = k;
			//添加了这个过程，消耗的内存是一样的．
		}
		max = rt.maxMemory() >> 10;			//转换为kB
		free = rt.freeMemory() >> 10;
		total = rt.totalMemory() >> 10;
		System.out.printf("新建1<<%d长度的数组之后是：\r\n", leftMoveBits);
		System.out.printf("max: %d\t free: %d\t total: %d\r\n", max, free, total);
		sc.close();
	}
	static void runTestIntArray() {
		Runtime rt = Runtime.getRuntime();
		Scanner sc = new Scanner(System.in);
		long max = 0;
		long free = 0;
		long total = 0;
		max = rt.maxMemory() >> 10;			//转换为kB
		free = rt.freeMemory() >> 10;
		total = rt.totalMemory() >> 10;
		System.out.println("初始情况是：");
		System.out.printf("max: %d\t free: %d\t total: %d\r\n", max, free, total);
		int leftMoveBits = sc.nextInt();
		sc.close();
		int len = 1 << leftMoveBits;
		int[] arr = new int[len];
		for (int k = 0; k < len; k ++) {
			arr[k] = k;
			//添加了这个过程，消耗的内存是一样的．
		}
		max = rt.maxMemory() >> 10;			//转换为kB
		free = rt.freeMemory() >> 10;
		total = rt.totalMemory() >> 10;
		System.out.printf("新建1<<%d长度的数组之后是：\r\n", leftMoveBits);
		System.out.printf("max: %d\t free: %d\t total: %d\r\n", max, free, total);
	}
}