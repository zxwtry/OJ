package csdn;
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
public class CSDN003_Runtime的三个Memory {
	
}
