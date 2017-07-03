package leetcode;

import java.util.Iterator;

/**
 * 	Given an Iterator class interface with methods: next() and hasNext(), 
 * 	design and implement a PeekingIterator that support the peek() operation 
 * 	-- it essentially peek() at the element that will be returned by the next 
 * 	call to next().
 * 	
 * 	Here is an example. Assume that the iterator is initialized to the 
 * 	beginning of the list: [1, 2, 3].
 * 	
 * 	Call next() gets you 1, the first element in the list.
 *	
 *	Now you call peek() and it returns 2, the next element. Calling 
 *	next() after that still return 2.
 *	
 *	You call next() the final time and it returns 3, the last element. 
 *	Calling hasNext() after that should return false.
 *	
 *	Hint:
 *	
 *	Think of "looking ahead". You want to cache the next element.
 *	Is one variable sufficient? Why or why not?
 *	Test your design with call order of peek() before next() vs next() before peek().
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P284_PeekingIterator.java
 * @type        P284_PeekingIterator
 * @date        2016年12月14日 下午10:26:13
 * @details     PeekingIterator 103ms 61.30%
 */
public class P284_PeekingIterator {
	static class PeekingIterator implements Iterator<Integer> {
		private Iterator<Integer> iterator = null;
		private Integer peekValueInteger = 0;
		private boolean hasNextBoolean = false;
		public PeekingIterator(Iterator<Integer> iterator) {
		    this.iterator = iterator;
		    internalSolve();
		}
		private void internalSolve() {
			hasNextBoolean = iterator.hasNext();
			if (hasNextBoolean) {
				peekValueInteger = iterator.next();
			}
		}
		public Integer peek() {
			return peekValueInteger;
		}
		@Override
		public Integer next() {
			Integer returnValue = peekValueInteger;
			internalSolve();
		    return returnValue;
		}
		@Override
		public boolean hasNext() {
		    return hasNextBoolean;
		}
        @Override
        public void remove() {
        }
	}
	
}
