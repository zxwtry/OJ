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
	static class LRUCache3 {
	    static class Node {
	        Node pre, nxt; 
	        int val;
	        public Node(int val) {
	            this.val = val;
	        }
	    }
	    int cap, size;
	    Node head, tail;
	    HashMap<Integer, Node> map;
	    public LRUCache3(int capacity) {
	        this.cap  = capacity;
	        this.size = 0;
	        this.head = null;
	        this.tail = null;
	        this.map  = new HashMap<>();
	    }
	    public int get(int key) {
	        Node nodeVal = map.get(key);
	        if (nodeVal == null) return -1;
	        moveNodeToHead(nodeVal);
	        return nodeVal.val;
	    }
	    private void moveNodeToHead(Node nodeVal) {
            Node nodePre = nodeVal.pre;
            Node nodeNxt = nodeVal.nxt;
            if (nodePre != null) nodePre.nxt = nodeNxt;
            if (nodeNxt != null) nodeNxt.pre = nodePre;
            if (nodeVal == tail && head != tail) tail = nodePre;
            if (size == cap + 1 && tail != null) {
                if (tail.pre != null)   tail = tail.pre;
                tail.nxt = null;
                size --;
            }
            if (nodeVal != head) {
                nodeVal.nxt = head;
                head = nodeVal;
            }
	    }
 	    public void put(int key, int val) {
 	        Node nodeVal = map.get(key);
 	        if (nodeVal == null) {
 	            size ++;
 	            nodeVal = new Node(val);
 	            map.put(key, nodeVal);
 	            
 	        } else {
 	            
 	        }
 	    }
	}

	static public class LRU<E> {
	    private int size;
	    private int occupiedBlocks = 0;
	    private Node<E> leastRecentlyUsedElement;
	    private Node<E> mostRecentlyUsedElement;
	    private Map<E, Node<E>> cache = new HashMap<E, Node<E>>();
	    public LRU(int size) {
	        this.size = size;
	    }

	    public void add(E element) {
	        if (leastRecentlyUsedElement == null) {
	            Node<E> node = createNode(element);
	            leastRecentlyUsedElement = node;
	            mostRecentlyUsedElement = node;
	            cache.put(element, node);
	            occupiedBlocks++;
	        } else {
	            if (cache.containsKey(element)) {
	                Node<E> elementNode = cache.get(element);
	                addElementToFront(elementNode);
	            } else {
	                Node<E> node = createNode(element, mostRecentlyUsedElement, null);
	                if (occupiedBlocks >= size) {
	                    cache.remove(leastRecentlyUsedElement.value);
	                    leastRecentlyUsedElement = leastRecentlyUsedElement.next;
	                    leastRecentlyUsedElement.prev = null;
	                    occupiedBlocks--;
	                }
	                mostRecentlyUsedElement.next = node;
	                mostRecentlyUsedElement = node;
	                cache.put(element, node);
	                occupiedBlocks++;
	            }
	        }
	    }

	    private void addElementToFront(Node<E> elementNode) {
	        if (elementNode.next != null) {
	            if (elementNode.prev == null) {
	                mostRecentlyUsedElement.next = leastRecentlyUsedElement;
	                leastRecentlyUsedElement = leastRecentlyUsedElement.next;
	                mostRecentlyUsedElement.next = null;
	            } else {
	                elementNode.prev.next = elementNode.next;
	                elementNode.next.prev = elementNode.prev;
	                mostRecentlyUsedElement.next = elementNode;
	                elementNode.prev = mostRecentlyUsedElement;
	                mostRecentlyUsedElement = mostRecentlyUsedElement.next;
	            }
	        }
	    }

	    private LRU<E>.Node<E> createNode(E element, Node<E> prev, Node<E> next) {
	        Node<E> node = new Node<E>(element, prev, next);
	        return node;
	    }

	    private Node<E> createNode(E element) {
	        return createNode(element, null, null);
	    }

	    public void printCache() {
	        Node<E> temp = mostRecentlyUsedElement;
	        System.out.println("==============");
	        do {
	            System.out.println(temp.value);
	            temp = temp.prev;
	        } while (temp != null);
	        System.out.println("==============");
	    }

	    @SuppressWarnings("hiding")
        public class Node<E> {
	        private Node<E> next;
	        private Node<E> prev;
	        private E value;

	        public Node(E element, Node<E> prev, Node<E> next) {
	            this.value = element;
	            this.prev = prev;
	            this.next = next;
	        }

	        public Node<E> getNext() {
	            return next;
	        }

	        public void setNext(Node<E> next) {
	            this.next = next;
	        }

	        public Node<E> getPrev() {
	            return prev;
	        }

	        public void setPrev(Node<E> prev) {
	            this.prev = prev;
	        }

	        public E getValue() {
	            return value;
	        }

	        public void setValue(E value) {
	            this.value = value;
	        }

	        @Override
	        public String toString() {
	            return "Node [next=" + next + ", prev=" + prev + ", value=" + value + "]";
	        }

	    }

	    @Override
	    public String toString() {
	        return "LRU [size=" + size + ", occupiedBlocks=" + occupiedBlocks + ", leastRecentlyUsedElement=" + leastRecentlyUsedElement + ", mostRecentlyUsedElement=" + mostRecentlyUsedElement
	                + ", cache=" + cache + "]";
	    }

	}
}
