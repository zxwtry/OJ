package leetcode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given a nested list of integers, implement an iterator to flatten it.
 *	
 *	Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *	
 *	Example 1:
 *	Given the list [[1,1],2,[1,1]],
 *	
 *	By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
 *	
 *	Example 2:
 *	Given the list [1,[4,[6]]],
 *	
 *	By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P341_FlattenNestedListIterator.java
 * @type        P341_FlattenNestedListIterator
 * @date        2017年2月2日 下午9:14:49
 * @details     NestedIterator1: AC 11ms 26.17% *
 * @details     NestedIterator2: AC  9ms 57.64% *
 */
public class P341_FlattenNestedListIterator {
	static class NestedIterator1 implements Iterator<Integer> {
	    LinkedList<Integer> integerList = new LinkedList<Integer>();
	    public NestedIterator1(List<NestedInteger> nestedList) {
	        bfs(nestedList);
	    }
	    private void bfs(List<NestedInteger> nestedList) {
	        for (NestedInteger nestedInteger : nestedList) {
	            if (nestedInteger.isInteger()) {
	                integerList.add(nestedInteger.getInteger());
	            } else {
	                bfs(nestedInteger.getList());
	            }
	        }
        }
        @Override
	    public Integer next() {
	        return integerList.pollFirst();
	    }
	    @Override
	    public boolean hasNext() {
	        return ! integerList.isEmpty();
	    }
        @Override
        public void remove() {
        }
	}
	
	static class NestedIterator2 implements Iterator<Integer> {
	    Stack<NestedInteger> stack = new Stack<>();
	    public NestedIterator2(List<NestedInteger> nestedList) {
	        for(int i = nestedList.size() - 1; i >= 0; i--) {
	            stack.push(nestedList.get(i));
	        }
	    }
	    @Override
	    public Integer next() {
	        return stack.pop().getInteger();
	    }
	    @Override
	    public boolean hasNext() {
	        while(!stack.isEmpty()) {
	            NestedInteger curr = stack.peek();
	            if(curr.isInteger()) {
	                return true;
	            }
	            stack.pop();
	            for(int i = curr.getList().size() - 1; i >= 0; i--) {
	                stack.push(curr.getList().get(i));
	            }
	        }
	        return false;
	    }
        @Override
        public void remove() {
        }
	}
	
	static class NestedInteger {
	    public boolean isInteger() {
	        return false;
	    }
	    public Integer getInteger() {
	        return 1;
	    }
	    public List<NestedInteger> getList() {
	        return new LinkedList<NestedInteger>();
	    }
	}
}

/**
 *      // This is the interface that allows for creating nested lists.
 *      // You should not implement it, or speculate about its implementation
 *      public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
