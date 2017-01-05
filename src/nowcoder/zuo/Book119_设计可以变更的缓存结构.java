package nowcoder.zuo;

/**
 * 	[题目]
 * 	设计一种缓存结构，该结构在构造时确定大小，假设大小为k，并且有两个功能：
 * 	1,	set(key,value):	将记录(key,value)插入该结构
 * 	2,	get(key):		返回key对应的value值
 * 	
 * 	[要求]
 * 	1,	set和get方法的时间复杂度为O(1)
 * 	2,	某个key的set或get操作一旦繁盛，认为这个key的记录成了最经常使用的。
 * 	3,	当缓存的大小超过k时，移除最不经常使用的记录，即set或get最久远的。
 * 
 * 	[举例]
 * 	假设缓存结构的实例是cache，大小为3，依次进行：
 * 	1,	cache.set("A", 1)		最经常使用的记录为("A", 1)
 * 	2,	cache.set("B", 2)		最经常使用的记录为("B", 2)	最不经常使用的记录为("A", 1)
 * 	3,	cache.set("C", 3)		最经常使用的记录为("C", 3)	最不经常使用的记录为("A", 1)
 * 	4,	cache.get("A")			最经常使用的记录为("A", 1)	最不经常使用的记录为("B", 2)
 * 	5,	cache.set("D", 4)		最经常使用的记录为("D", 4)	最不经常使用的记录为("C", 3)
 * 								其中("B", 2)从缓存结构中移除
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book119_设计可以变更的缓存结构.java
 * @type        Book119_设计可以变更的缓存结构
 * @date        2017年1月5日 下午10:03:39
 * @details     
 */
public class Book119_设计可以变更的缓存结构 {

}
