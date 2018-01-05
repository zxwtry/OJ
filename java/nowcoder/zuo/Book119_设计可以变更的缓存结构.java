package nowcoder.zuo;

import java.util.HashMap;

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
	static class MyCache<K, V> {
		private HashMap<K, ListNode<V>> keyNodeMap;
		private HashMap<ListNode<V>, K> nodeKeyMap;
		private DoubleLinkedList<V> nodeList;
		private int capacity;
		public MyCache(int capacity) {
			if (capacity < 1) throw new RuntimeException("capacity must be more than 0");
			this.keyNodeMap = new HashMap<K, ListNode<V>>();
			this.nodeKeyMap = new HashMap<ListNode<V>, K>();
			this.nodeList = new DoubleLinkedList<V>();
			this.capacity = capacity;
		}
		public V get(K key) {
			if (this.keyNodeMap.containsKey(key)) {
				ListNode<V> ans = this.keyNodeMap.get(key);
				this.nodeList.moveNodeToTail(ans);
				return ans.value;
			}
			return null;
		}
		public void set(K key, V value) {
			if (this.keyNodeMap.containsKey(key)) {
				ListNode<V> node = this.keyNodeMap.get(key);
				node.value = value;
				this.nodeList.moveNodeToTail(node);
			} else {
				ListNode<V> newNode = new ListNode<V>(value);
				this.keyNodeMap.put(key, newNode);
				this.nodeKeyMap.put(newNode, key);
				this.nodeList.addNode(newNode);
				if (this.keyNodeMap.size() == this.capacity + 1)
					this.removeMostUnusedCache();
			}
		}
		private void removeMostUnusedCache() {
			ListNode<V> removeNode = this.nodeList.removeHead();
			K removeKey = this.nodeKeyMap.get(removeNode);
			this.nodeKeyMap.remove(removeNode);
			this.keyNodeMap.remove(removeKey);
		}
	}
	static class DoubleLinkedList<T> {
		private ListNode<T> head, tail;
		public void addNode(ListNode<T> newNode) {
			if (newNode == null) return;
			if (this.head == null || this.tail == null) {
				this.head = newNode;
				this.tail = newNode;
			} else {
				this.tail.next = newNode;
				newNode.last = this.tail;
				this.tail = newNode;
			}
		}
		public void moveNodeToTail(ListNode<T> node) {
			if (this.tail == node) return;
			if (this.head == node) {
				this.head = node.next;
				this.head.last = null;
			} else {
				node.last.next = node.next;
				node.next.last = node.last;
			}
			node.last = this.tail;
			node.next = null;
			this.tail.next = node;
			this.tail = node;
		}
		public ListNode<T> removeHead() {
			if (this.head == null) return null;
			ListNode<T> ans = this.head;
			if (this.head == this.tail) {
				this.head = null;
				this.tail = null;
			} else {
				this.head = ans.next;
				ans.next = null;
				this.head.last = null;
			}
			return ans;
		}
	}
	static class ListNode<T> {
		public T value;
		public ListNode<T> last, next;
		public ListNode(T value) {
			this.value = value;
		}
	}
}
