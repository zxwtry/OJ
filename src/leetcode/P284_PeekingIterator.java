package leetcode;

/**
 * 	Given an Iterator class interface with methods: next() and hasNext(), 
 * 	design and implement a PeekingIterator that support the peek() operation 
 * 	-- it essentially peek() at the element that will be returned by the next 
 * 	call to next().

	Here is an example. Assume that the iterator is initialized to the 
	beginning of the list: [1, 2, 3].
	
	Call next() gets you 1, the first element in the list.
	
	Now you call peek() and it returns 2, the next element. Calling 
	next() after that still return 2.
	
	You call next() the final time and it returns 3, the last element. 
	Calling hasNext() after that should return false.
	
	Hint:
	
	Think of "looking ahead". You want to cache the next element.
	Is one variable sufficient? Why or why not?
	Test your design with call order of peek() before next() vs next() before peek().
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P284_PeekingIterator.java
 * @type        P284_PeekingIterator
 * @date        2016年12月14日 下午10:26:13
 * @details     
 */
public class P284_PeekingIterator {
	public static void main(String[] args) {
		
	}
	static class PeekingIterator implements Iterator<Integer> {

		public PeekingIterator(Iterator<Integer> iterator) {
		    // initialize any member here.
		    
		}

	    // Returns the next element in the iteration without advancing the iterator.
		public Integer peek() {
	        
		}

		// hasNext() and next() should behave the same as in the Iterator interface.
		// Override them if needed.
		@Override
		public Integer next() {
		    
		}

		@Override
		public boolean hasNext() {
		    
		}
	}
	
}
