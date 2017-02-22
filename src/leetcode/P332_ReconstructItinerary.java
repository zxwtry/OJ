package leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 	Given a list of airline tickets represented by pairs of departure and arrival 
 * 	airports [from, to], reconstruct the itinerary in order. 
 * 	All of the tickets belong to a man who departs from JFK. Thus, 
 * 	the itinerary must begin with JFK.
 *  
 *  Note:
 *  If there are multiple valid itineraries, you should return the itinerary 
 *  that has the smallest lexical order when read as a single string. 
 *  For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than 
 *  ["JFK", "LGB"].
 *  All airports are represented by three capital letters (IATA code).
 *  You may assume all tickets form at least one valid itinerary.
 *  Example 1:
 *  tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 *  Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
 *  Example 2:
 *  tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 *  Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
 *  Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. 
 *  But it is larger in lexical order.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P332_ReconstructItinerary.java
 * @type        P332_ReconstructItinerary
 * @date        2017年1月10日 下午10:07:15
 * @details     
 */
public class P332_ReconstructItinerary {
	static class Solution {
	    public List<String> findItinerary(String[][] tickets) {
	        
	    }
	}
	static class Solution1 {
	    public List<String> findItinerary(String[][] tickets) {
	        for (String[] ticket : tickets)
	            targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
	        visit("JFK");
	        return route;
	    }

	    Map<String, PriorityQueue<String>> targets = new HashMap<>();
	    List<String> route = new LinkedList();

	    void visit(String airport) {
	        while(targets.containsKey(airport) && !targets.get(airport).isEmpty())
	            visit(targets.get(airport).poll());
	        route.add(0, airport);
	    }
	}
}
