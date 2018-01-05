package leetcode;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 	Median is the middle value in an ordered integer list. 
 * 	If the size of the list is even, there is no middle value. 
 * 	So the median is the mean of the two middle value.
 *	
 *	Examples: 
 *	[2,3,4] , the median is 3
 *	
 *	[2,3], the median is (2 + 3) / 2 = 2.5
 *	
 *	Design a data structure that supports the following two operations:
 *	
 *	void addNum(int num) - Add a integer number from the data stream to 
 *	the data structure.
 *	double findMedian() - Return the median of all elements so far.
 *	For example:
 *	
 *	add(1)
 *	add(2)
 *	findMedian() -> 1.5
 *	add(3) 
 *	findMedian() -> 2
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P295_FindMedianFromDataStream.java
 * @type        P295_FindMedianFromDataStream
 * @date        2016年12月17日 下午10:31:58
 * @details     MedianFinder1: AC 275ms 15.58%
 * @details     MedianFinder2: AC 310ms  6.12%
 */
public class P295_FindMedianFromDataStream {
	static class MedianFinder1 {
		ArrayList<Integer> sortedList = new ArrayList<Integer>();
	    // Adds a number into the data structure.
	    public void addNum(int num) {
	        if (sortedList.size() == 0) sortedList.add(num);
	        else if (sortedList.get(0) >= num) sortedList.add(0, num);
	        else if (sortedList.get(sortedList.size() - 1) <= num) sortedList.add(num);
	        else sortedList.add(getBinaryIndex(num, 0, sortedList.size() - 1), num);
	        System.out.println(sortedList);
	    }
	    private int getBinaryIndex(int num, int startIndex, int endIndex) {
	    	int middleIndex = 0;
	    	int cut = 0;
	    	while (startIndex < endIndex) {
	    		middleIndex = (startIndex + endIndex) / 2;
	    		cut = sortedList.get(middleIndex) - num;
	    		if (cut > 0) {
	    			endIndex = middleIndex;
	    		} else if (cut == 0) {
	    			return middleIndex;
	    		} else {
	    			startIndex = middleIndex + 1;
	    		}
	    	}
			return startIndex;
		}
		// Returns the median of current data stream
	    public double findMedian() {
	        if (sortedList.size() % 2 == 1) {
	        	return sortedList.get(sortedList.size() / 2);
	        } else {
	        	return (sortedList.get(sortedList.size() / 2) + (double)sortedList.get(sortedList.size() / 2 - 1)) / 2;
	        }
	    }
	};
	
	static class MedianFinder2 {
	    private Queue<Long> small = new PriorityQueue<Long>();
	    private Queue<Long> large = new PriorityQueue<Long>();
	    public void addNum(int num) {
	        large.add((long) num);
	        small.add(-large.poll());
	        if (large.size() < small.size())
	            large.add(-small.poll());
	    }
	    public double findMedian() {
	        return large.size() > small.size()
	               ? large.peek()
	               : (large.peek() - small.peek()) / 2.0;
	    }
	};

	// Your MedianFinder object will be instantiated and called as such:
	// MedianFinder mf = new MedianFinder();
	// mf.addNum(1);
	// mf.findMedian();
}
