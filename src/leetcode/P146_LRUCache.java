package leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;


/*
 * 	Design and implement a data structure for Least Recently Used (LRU) cache. 
 * 	It should support the following operations: get and set.

	get(key) - Get the value (will always be positive) of the key 
	if the key exists in the cache, otherwise return -1.
	set(key, value) - Set or insert the value if the key is not already present. 
	When the cache reached its capacity, it should invalidate the least
	 recently used item before inserting a new item.
 */


/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P146_LRUCache.java
 * @type        P146_LRUCache
 * @date        2016年12月26日 上午10:39:49
 * @details     LRUCache1  14ms 96.60%
 * @details     LRUCache2 225ms 11.95%
 */
public class P146_LRUCache {
	static class LRUCache1 extends LinkedHashMap<Integer, Integer> {
		private static final long serialVersionUID = 1L;
		private int capacity;
		public LRUCache1(int capacity) {
			super(16, 0.75f, true);
			this.capacity = capacity;
		}
		//重写父类get，为null时范围-1
		public Integer get(Object key) {
			Integer v = super.get(key);
			if (v != null)
				return v;
			else
				return -1;
		}
		//重写父类方法，当超过缓存容量时，就删除最老的
		public boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
			return size() > capacity;
		}
		public void set(int key, int value) {
			super.put(key, value);
		}
	}
	static class LRUCache2 {
	    static class A implements Comparable<A> {
	        int val;
	        int rank;
	        int key;
            @Override
            public int compareTo(A a) {
                return this.rank - a.rank;
            }
            @Override
            public int hashCode() {
                return val;
            }
            @Override
            public boolean equals(Object obj) {
                if (! (obj instanceof A)) return false;
                A a = (A) obj;
                return a.val == this.val;
            }
	    }
	    private TreeSet<A> setA = new TreeSet<A>();
	    private HashMap<Integer, A> map = new HashMap<>();
	    private int id = 0;
	    int cap;
	    public LRUCache2(int capacity) {
	        this.cap = capacity;
	    }
	    public int get(int key) {
	        A v = map.get(key);
	        if (v == null) return -1;
	        setA.remove(v);
	        v.rank = id ++;
	        setA.add(v);
	        return v.val;
	    }
	    public void put(int key, int value) {
	        A v = map.get(key);
	        if (v == null) v = new A();
	        else setA.remove(v);
	        v.val = value;
            v.rank = id ++;
            v.key = key;
            setA.add(v);
            map.put(key, v);
            if (setA.size() > cap) {
                v = setA.pollFirst();
                map.remove(v.key);
            }
	    }
	}
}
