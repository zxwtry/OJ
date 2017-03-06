package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 	Implement a data structure supporting the following operations:
 *	
 *	Inc(Key) - Inserts a new key with value 1. Or increments
 *	 an existing key by 1. Key is guaranteed to be a non-empty string.
 *	 
 *	Dec(Key) - If Key's value is 1, remove it from the data structure. 
 *	Otherwise decrements an existing key by 1. If the key does not exist,
 *	 this function does nothing. Key is guaranteed to be a non-empty string.
 *	 
 *	GetMaxKey() - Returns one of the keys with maximal value. 
 *	If no element exists, return an empty string "".
 *	
 *	GetMinKey() - Returns one of the keys with minimal value. 
 *	If no element exists, return an empty string "".
 *	
 *	Challenge: Perform all these in O(1) time complexity.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P432_AllO_oneDataStructure.java
 * @type        P432_AllO_oneDataStructure
 * @date        2017年3月6日 下午10:07:06
 * @details     AllOne: AC 152ms 67.14%
 */
public class P432_AllO_oneDataStructure {
	static class AllOne {
	     ArrayList<HashSet<String>> list;
	     HashMap<String, Integer> map;
	    public AllOne() {
	        list = new ArrayList<HashSet<String>>();
	        map = new HashMap<String, Integer>();
	    }
	    public void inc(String key) {
	        Integer set = map.get(key);
	        if (set == null) {
	            checkList(0);
	            list.get(0).add(key);
	            map.put(key, 0);
	        } else {
	            checkList(set + 1);
	            list.get(set).remove(key);
	            list.get(set + 1).add(key);
	            map.put(key, set + 1);
	        }
	    }
	    private void checkList(int index) {
	        while(list.size() <= index + 1)
	            list.add(new HashSet<String>());
	    }
	    public void dec(String key) {
	        Integer val = map.get(key);
	        if (val == null) return;
	        if (val == 0) {
	            list.get(0).remove(key);
	            if (list.size() == 1 && list.get(0).size() == 0)
	                list.remove(0);
	            map.remove(key);
	        } else {
	            list.get(val).remove(key);
	            if (list.size() == val + 1 && list.get(val).size() == 0)
	                list.remove(val);
	            list.get(val - 1).add(key);
	            map.put(key, val - 1);
	        }
	    }
	    public String getMaxKey() {
	        while (list.size() != 0 && list.get(list.size() - 1).size() == 0)
	            list.remove(list.size() - 1);
	        if (list.size() != 0 && list.get(list.size() - 1).size() != 0)
	            return list.get(list.size() - 1).iterator().next();
	        return "";
	    }
	    public String getMinKey() {
	        int index = 0;
	        while (index < list.size()) {
	            if (list.get(index).size() != 0)
	                return list.get(index).iterator().next();
	            index ++;
	        }
	        return "";
	    }
	}

	/**
	 * Your AllOne object will be instantiated and called as such:
	 * AllOne obj = new AllOne();
	 * obj.inc(key);
	 * obj.dec(key);
	 * String param_3 = obj.getMaxKey();
	 * String param_4 = obj.getMinKey();
	 */
}
