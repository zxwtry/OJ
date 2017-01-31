package leetcode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 	Equations are given in the format A / B = k, 
 * 	where A and B are variables represented as strings, 
 * 	and k is a real number (floating point number). 
 * 	Given some queries, return the answers. 
 * 	If the answer does not exist, return -1.0.
 *	
 *	Example:
 *	Given a / b = 2.0, b / c = 3.0. 
 *	queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
 *	return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 *	
 *	The input is: vector<pair<string, string>> euqations, 
 *	vector<double>& values, vector<pair<string, string>> query .
 *	 where equations.size() == values.size(),the values are positive. 
 *	 this represents the equations.return vector<double>. . 
 *	The example above: equations = [ ["a", "b"], ["b", "c"] ]. 
 *	values = [2.0, 3.0]. queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 *	
 *	The input is always valid. You may assume that evaluating
 *	 the queries will result in no division by zero and there is no contradiction.
 */


public class P399_EvaluateDivision {
	public static void main(String[] args) {
//		System.out.println((int)Character.MAX_VALUE);
//		System.out.println("abd".compareTo("abc"));
		String[][] equations = new String[][] {
			{"a", "b"},
			{"b", "c"}
		};
		double[] values = new double[] {
			2, 3
		};
		String[][] query = new String[][] {
			{"a", "c"},
			{"b", "c"}
		};
		double[] ans = new Solution().calcEquation(equations, values, query);
		for (int i = 0; i != ans.length; i ++)
			System.out.println(ans[i]);
	}
	/*
	 * 	一次WA
	 * 	AC
	 * 	4 ms
	 */
	static class Solution {
		HashMap<String, HashMap<String, Double>> map = new HashMap<>();
		HashSet<String> visit = new HashSet<String>();
		double[] ans = null;
		double cur = 1.0;
		boolean isFind = false;
	    public double[] calcEquation(String[][] equations, double[] values, String[][] query) {
	        generate(equations, values);
	        ans = new double[query.length];
	        for (int i = 0; i != query.length; i ++) {
	        	if (! (map.containsKey(query[i][0]) && map.containsKey(query[i][1]))) {
	        		ans[i] = -1;
	        		continue;
	        	}
	        	visit.clear();
	        	search(query[i][0], query[i][1], i);
	        	if (!isFind)
	        		ans[i] = - 1;
	        }
	    	return ans;
	    }
	    private void search(String start, String target, int i) {
	    	HashMap<String, Double> temp_map = map.get(start);
	    	visit.add(start);
	    	for (String key : temp_map.keySet()) {
	    		if (key.equals(target)) {
	    			isFind = true;
	    			ans[i] = cur * temp_map.get(target);
	    			return;
	    		}
	    		if (visit.contains(key))
	    			continue;
	    		cur *= temp_map.get(key);
	    		search(key, target, i);
	    		cur /= temp_map.get(key);
	    	}
		}
		private void generate(String[][] equations, double[] values) {
	    	for (int i = 0; i != values.length; i ++) {
	    		if (equations[i][1].equals(equations[i][0]))
	    			continue;
	    		if (map.containsKey(equations[i][0])) {
	    			map.get(equations[i][0]).put(equations[i][1], values[i]);
	    		} else {
	    			HashMap<String, Double> temp = new HashMap<>();
	    			temp.put(equations[i][1], values[i]);
	    			map.put(equations[i][0], temp);
	    		}
	    		if (map.containsKey(equations[i][1])) {
	    			map.get(equations[i][1]).put(equations[i][0], 1 / values[i]);
	    		} else {
	    			HashMap<String, Double> temp = new HashMap<>();
	    			temp.put(equations[i][0], 1 / values[i]);
	    			map.put(equations[i][1], temp);
	    		}
	    	}
	    }
	} 
}
