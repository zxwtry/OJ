package leetcode;

import java.util.HashMap;

/*
 * 	Design and implement a data structure for Least Recently Used (LRU) cache. 
 * 	It should support the following operations: get and set.

	get(key) - Get the value (will always be positive) of the key 
	if the key exists in the cache, otherwise return -1.
	set(key, value) - Set or insert the value if the key is not already present. 
	When the cache reached its capacity, it should invalidate the least
	 recently used item before inserting a new item.
 */


public class P146_LRUCache {
	public static void main(String[] args) {
//		int[] arr = new int[] {
//			0, 1, 2, 3, 4, 5, 6, 7
//		};
//		int[] arr_copy = new int[4];
//		System.arraycopy(arr, 0, arr, 1, 4);
//		tools.Utils.printArray(arr, 100);
//		int[] arr = new int[] {
//			4, 3, 4, 2, 3, 10, 4, 2, 4, 3, 4, 2, 3, 10, 4, 2
//		};
//		int[] val = new int[] {
//			-4, -3, -4, -2, -3, -10, -4, -2, -4, -3, -4, -2, -3, -10, -4, -2
//		};
		LRUCache3 l = new LRUCache3(2);
//		for (int i = 0; i < arr.length; i ++) {
//			l.set(arr[i], val[i]);
//		}
		l.set(2, 1);
		l.set(1, 1);
		System.out.println("get: " + l.get(2));
		l.set(4, 1);
		System.out.println("get: " + l.get(1));
		System.out.println("get: " + l.get(2));
	}
	/*
	 * 	TLE
	 * 	 应该是多用HashMap和LinkedList
	 */
	static class LRUCache {
		HashMap<Integer, Integer> map = null;
		int capacity = 0;
		int[] keys = null;
	    public LRUCache(int capacity) {
	    	map = new HashMap<>(capacity * 2);
	    	this.capacity = capacity;
	    	keys = new int[capacity];
	    }
	    public int get(int key) {
	    	if (map.containsKey(key)) {
	    		int i = 0;
	    		for (; i < capacity; i ++) {
	    			if (keys[i] == key) {
	    				break;
	    			}
	    		}
	    		for (int j = i; j > 0; j --) {
	    			keys[j] = keys[j - 1];
	    		}
	    		keys[0] = key;
	    		return map.get(key);
	    	} else {
	    		return - 1;
	    	}
	    }
	    public void set(int key, int value) {
	        if (map.containsKey(key)) {
	        	map.put(key, value);
	        	int i = 0;
	    		for (; i < capacity; i ++) {
	    			if (keys[i] == key) {
	    				break;
	    			}
	    		}
	    		for (int j = i; j > 0; j --) {
	    			keys[j] = keys[j - 1];
	    		}
	    		keys[0] = key;
	        } else {
	        	if (map.size() == capacity) {
	        		map.remove(keys[capacity - 1]);
	        		for (int i = capacity - 1; i > 0; i --) {
	        			keys[i] = keys[i - 1];
	        		}
	        		keys[0] = key;
	        		map.put(key, value);
	        	} else {
	        		int size = map.size();
	        		for (int i = size; i > 0; i --) {
	        			keys[i] = keys[i - 1];
	        		}
	        		keys[0] = key;
	        		map.put(key, value);
	        	}
	        }
	        tools.Utils.printArray(keys, 10);
	    }
	}
	/*
	 * 	还是TLE
	 */
	static class LRUCache2 {
		HashMap<Integer, Integer> map = null;
		int capacity = 0;
		int[] keys = null;
		int[] keys_ghost = null;
	    public LRUCache2(int capacity) {
	    	map = new HashMap<>(capacity * 2);
	    	this.capacity = capacity;
	    	keys = new int[capacity];
	    	keys_ghost = new int[capacity];
	    }
	    public int get(int key) {
	    	if (map.containsKey(key)) {
	    		int i = 0;
	    		for (; i < capacity; i ++) {
	    			if (keys[i] == key) {
	    				break;
	    			}
	    		}
	    		System.arraycopy(keys, 0, keys, 1, i);
//	    		for (int j = i; j > 0; j --) {
//	    			keys[j] = keys[j - 1];
//	    		}
	    		keys[0] = key;
	    		return map.get(key);
	    	} else {
	    		return - 1;
	    	}
	    }
	    public void set(int key, int value) {
	        if (map.containsKey(key)) {
	        	map.put(key, value);
	        	int i = 0;
	    		for (; i < capacity; i ++) {
	    			if (keys[i] == key) {
	    				break;
	    			}
	    		}
	    		System.arraycopy(keys, 0, keys, 1, i);
//	    		for (int j = i; j > 0; j --) {
//	    			keys[j] = keys[j - 1];
//	    		}
	    		keys[0] = key;
	        } else {
	        	if (map.size() == capacity) {
	        		map.remove(keys[capacity - 1]);
	        		System.arraycopy(keys, 0, keys, 1, capacity - 1);
//	        		for (int i = capacity - 1; i > 0; i --) {
//	        			keys[i] = keys[i - 1];
//	        		}
	        		keys[0] = key;
	        		map.put(key, value);
	        	} else {
	        		int size = map.size();
	        		System.arraycopy(keys, 0, keys, 1, size);
//	        		for (int i = size; i > 0; i --) {
//	        			keys[i] = keys[i - 1];
//	        		}
	        		keys[0] = key;
	        		map.put(key, value);
	        	}
	        }
	        tools.Utils.printArray(keys, 10);
	    }
	}
	/*
	 * 	WA
	 * 	10,[set(10,13),set(3,17),set(6,11),set(10,5),
	 * 	set(9,10),get(13),set(2,19),get(2),get(3),
	 * 	set(5,25),get(8),set(9,22),set(5,5),set(1,30),
	 * 	get(11),set(9,12),get(7),get(5),get(8),get(9),
	 * 	set(4,30),set(9,3),get(9),get(10),get(10),set(6,14),
	 * 	set(3,1),get(3),set(10,11),get(8),set(2,14),get(1),
	 * 	get(5),get(4),set(11,4),set(12,24),set(5,18),get(13),
	 * 	set(7,23),get(8),get(12),set(3,27),set(2,12),get(5),
	 * 	set(2,9),set(13,4),set(8,18),set(1,7),get(6),set(9,29),
	 * 	set(8,21),get(5),set(6,30),set(1,12),get(10),set(4,15),
	 * 	set(7,22),set(11,26),set(8,17),set(9,29),get(5),set(3,4),
	 * 	set(11,30),get(12),set(4,29),get(3),get(9),get(6),set(3,4),
	 * 	get(1),get(10),set(3,29),set(10,28),set(1,20),set(11,13),
	 * 	get(3),set(3,12),set(3,8),set(10,9),set(3,26),get(8),get(7),
	 * 	get(5),set(13,17),set(2,27),set(11,15),get(12),set(9,19),
	 * 	set(2,15),set(3,16),get(1),set(12,17),set(9,1),set(6,19),
	 * 	get(4),get(5),get(5),set(8,1),set(11,7),set(5,2),set(9,28),
	 * 	get(1),set(2,2),set(7,4),set(4,22),set(7,24),set(9,26),set(13,28),set(11,26)]
	 * 
	 * 
	 * 	ANSWER:
	 * 	[-1,19,17,-1,-1,-1,5,-1,12,3,5,5,1,-1,30,5,30,-1,-1,24,18,-1,18,-1,18,
	 * 	-1,4,29,30,12,-1,29,17,22,18,-1,20,-1,18,18,20]
	 */
	static class LRUCache3 {
		HashMap<Integer, Integer> map = null;
		HashMap<Integer, KEY> key_map = null;
		KEY head = null, tail = null;
		int capacity = 0;
	    public LRUCache3(int capacity) {
	    	map = new HashMap<>(capacity * 2);
	    	key_map = new HashMap<>(capacity * 10);
	    	this.capacity = capacity;
	    }
	    public int get(int key) {
	    	if (map.containsKey(key)) {
	    		KEY k_now = key_map.get(key);
	    		if (k_now == head) {
	    			return map.get(key);
	    		}
	    		KEY k_pre = k_now.pre;
	    		KEY k_next = k_now.next;
	    		if (k_pre != null) {
	    			k_pre.next = k_next;
	    		}
	    		if (k_next != null) {
	    			k_next.pre = k_pre;
	    		}
	    		if (k_next == null) {
	    			tail = k_pre;
	    		}
	    		k_now.pre = null;
	    		k_now.next = head;
	    		head = k_now;
	    		return map.get(key);
	    	} else {
	    		return - 1;
	    	}
	    }
	    public void set(int key, int value) {
	        if (map.containsKey(key)) {
	        	map.put(key, value);
	        	KEY k_now = key_map.get(key);
	        	if (head == k_now) {
	        		return;
	        	}
	    		KEY k_pre = k_now.pre;
	    		KEY k_next = k_now.next;
	    		if (k_pre != null) {
	    			k_pre.next = k_next;
	    		}
	    		if (k_next != null) {
	    			k_next.pre = k_pre;
	    		}
	    		if (k_next == null) {
	    			tail = k_pre;
	    		}
	    		k_now.pre = null;
	    		k_now.next = head;
	    		head.pre = k_now;
	    		head = k_now;
	        } else {
	        	if (map.size() == capacity) {
	        		if (tail == null || head == null) {
	        			KEY key_new = new KEY(key);
	        			key_map.put(key, key_new);
	        			head = tail = key_new;
	        			map.clear();
	        			map.put(key, value);
	        		} else {
		        		map.remove(tail.key);
		        		KEY key_new = new KEY(key);
		        		key_map.put(key, key_new);
		        		key_new.next = head;
		        		head.pre = key_new;
		        		head = key_new;
		        		KEY tail_pre = tail.pre;
		        		if (tail_pre != null) {
		        			tail_pre.next = null;
		        		}
		        		tail.pre = null;
		        		tail = tail_pre;
		        		map.put(key, value);
	        		}
	        	} else {
	        		if (head == null || tail == null) {
	        			KEY key_new = new KEY(key);
	        			key_map.put(key, key_new);
	        			head = tail = key_new;
	        		} else {
		        		KEY key_new = new KEY(key);
		        		key_map.put(key, key_new);
		        		key_new.next = head;
		        		head.pre = key_new;
		        		head = key_new;
	        		}
	        		map.put(key, value);
	        	}
	        }
//	        tools.Utils.printArray(keys, 10);
	        KEY tra = head;
	        while (tra != null) {
	        	System.out.print(tra.key + " ");
	        	tra = tra.next;
	        }
	        System.out.println();
	    }
	    static class KEY {
	    	public int key;
	    	public KEY pre, next;
	    	public KEY(int key) {
	    		this.key = key;
	    	}
	    }
	}
	/*
	 * 	想想，能不能有一个更加简单的方法，这样才不容易出错
	 */
	static class LRUCache4 {
		int capacity = 0;
	    public LRUCache4 (int capacity) {
	        this.capacity = capacity;
	    }
	    public int get(int key) {
	    }
	    public void set(int key, int value) {
	        
	    }
	    static class KEY {
	    	public int key;
	    	public KEY pre, next;
	    	public KEY (int key) {
	    		this.key = key;
	    	}
	    }
	}
}
