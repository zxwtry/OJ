package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeMap;

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
 * @details     Solution1: AC 100ms  5.04%
 * @details     Solution2: AC  14ms 50.12%  *
 * @details     Solution21: AC 12ms 65.71%  *
 * @details     Solution3: AC  31ms 20.62%  *
 * @details     Solution3: AC  19ms 30.46%  *
 */
public class P332_ReconstructItinerary {
	static class Solution1 {
	    Map<String, PriorityQueue<String>> targets = new HashMap<>();
        List<String> route = new LinkedList<String>();
	    public List<String> findItinerary(String[][] tickets) {
	        for (String[] ticket : tickets)
	            targets.computeIfAbsent(ticket[0], k -> new PriorityQueue<String>()).add(ticket[1]);
	        visit("JFK");
	        return route;
	    }
	    void visit(String airport) {
	        while(targets.containsKey(airport) && !targets.get(airport).isEmpty())
	            visit(targets.get(airport).poll());
	        route.add(0, airport);
	    }
	}
	static class Solution2 {
	    public List<String> findItinerary(String[][] tickets) {
	        List<String> ans = new ArrayList<String>();
	        if(tickets == null || tickets.length == 0) return ans;
	        Map<String, PriorityQueue<String>> ticketsMap = new HashMap<>();
	        for(int i = 0; i < tickets.length; i++) {
	            if(!ticketsMap.containsKey(tickets[i][0])) ticketsMap.put(tickets[i][0], new PriorityQueue<String>());
	            ticketsMap.get(tickets[i][0]).add(tickets[i][1]);
	        }
	        String curr = "JFK";
	        Stack<String> drawBack = new Stack<String>();
	        for(int i = 0; i < tickets.length; i++) {
	            while(!ticketsMap.containsKey(curr) || ticketsMap.get(curr).isEmpty()) {
	                drawBack.push(curr);
	                curr = ans.remove(ans.size()-1);
	            }
	            ans.add(curr);
	            curr = ticketsMap.get(curr).poll();
	        }
	        ans.add(curr);
	        while(!drawBack.isEmpty()) ans.add(drawBack.pop());
	        return ans;
	    }
	}
	static class Solution21 {
        public List<String> findItinerary(String[][] tickets) {
            LinkedList<String> ans = new LinkedList<String>();
            if(tickets == null || tickets.length == 0) return ans;
            Map<String, PriorityQueue<String>> ticketsMap = new HashMap<>();
            for(int i = 0; i < tickets.length; i++) {
                if(!ticketsMap.containsKey(tickets[i][0])) ticketsMap.put(tickets[i][0], new PriorityQueue<String>());
                ticketsMap.get(tickets[i][0]).add(tickets[i][1]);
            }
            String curr = "JFK";
            Stack<String> drawBack = new Stack<String>();
            for(int i = 0; i < tickets.length; i++) {
                while(!ticketsMap.containsKey(curr) || ticketsMap.get(curr).isEmpty()) {
                    drawBack.push(curr);
                    curr = ans.removeLast();
                }
                ans.add(curr);
                curr = ticketsMap.get(curr).poll();
            }
            ans.add(curr);
            while(!drawBack.isEmpty()) ans.add(drawBack.pop());
            return ans;
        }
    }
}
