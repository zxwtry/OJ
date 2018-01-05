package nowcoder.zuo;

import java.util.HashMap;

/**
 * 	[题目]
 * 	设计一种结构，在该结构中有如下三个功能
 * 	1,	insert(key):		将某个key加入到该结构，做到不重复加入
 * 	2,	delete(key):		移除某个key
 * 	3,	getRandom():		等概率随机返回结构中的任何一个key
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book120_设计一个RandomPool结构.java
 * @type        Book120_设计一个RandomPool结构
 * @date        2017年1月5日 下午10:13:55
 * @details     
 */
public class Book120_设计一个RandomPool结构 {
	static class RandomPool<T> {
		private HashMap<T, Integer> keyIndexMap;
		private HashMap<Integer, T> indexKeyMap;
		private int size;
		public RandomPool() {
			this.keyIndexMap = new HashMap<T, Integer>();
			this.indexKeyMap = new HashMap<Integer, T>();
			this.size = 0;
		}
		public void insert(T key) {
			if (! this.keyIndexMap.containsKey(key)) {
				this.keyIndexMap.put(key, this.size);
				this.indexKeyMap.put(this.size ++, key);
			}
		}
		public void delete(T key) {
			if (this.keyIndexMap.containsKey(key)) {
				int deleteIndex = this.keyIndexMap.get(key);
				int lastIndex = -- this.size;
				T lastKey = this.indexKeyMap.get(lastIndex);
				this.keyIndexMap.put(lastKey, deleteIndex);
				this.indexKeyMap.put(deleteIndex, lastKey);
				this.keyIndexMap.remove(key);
				this.indexKeyMap.remove(lastIndex);
			}
		}
		public T getRandom() {
			if (this.size == 0) {
				return null;
			}
			int randomIndex = (int) (Math.random() * this.size);
			return this.indexKeyMap.get(randomIndex);
		}
	}
}
