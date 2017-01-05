package nowcoder.zuo;

import java.util.HashMap;

/**
 * 	[题目]
 * 	哈希表常见的三个操作是put, get和containsKey，而且这三个操作
 * 	的时间复杂度为O(1)。现在想加一个setAll的功能，就是将所有记录的
 * 	value都设成统一值。
 * 	设计这样一种哈希表，并且put，get，containsKey和setAll的
 * 	时间复杂度都为O(1)
 * 
 * 	[解答]
 * 	加入一个时间戳结构，一切问题都能解决。具体如下：
 * 	1,	把每一个记录都加上一个时间，标记每条记录何时建立的。
 * 	2,	设置一个setAll记录也加上一个时间，标记setAll记录建立的时间。
 * 	3,	查询记录时，如果某条记录的时间早于setAll记录的时间，
 * 		说明setAll是最新数据，返回setAll记录的值。
 * 		查询记录时，如果某条记录的时间晚于setAll记录的时间，
 * 		说明返回该条记录的值。
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book117_设计有setAll功能的哈希表.java
 * @type        Book117_设计有setAll功能的哈希表
 * @date        2017年1月5日 下午3:00:23
 * @details     
 */
public class Book117_设计有setAll功能的哈希表 {
	static class MyHashMap<K, V> extends HashMap<K, V> {
		private static final long serialVersionUID = 1L;
		private HashMap<K, Long> timeMap = new HashMap<K, Long>();
		private V setAllV = null;
		private long setAllTime = 0l;
		@Override
		public V put(K key, V value) {
			this.timeMap.put(key, System.currentTimeMillis());
			return super.put(key, value);
		}
		public void setAll(V value) {
			setAllTime = System.currentTimeMillis();
			setAllV = value;
		}
		@Override
		public V get(Object key) {
			if (timeMap.get(key) <= setAllTime) {
				return setAllV;
			}
			return super.get(key);
		}
	}
}
