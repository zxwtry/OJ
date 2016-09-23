package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 	Given two words (beginWord and endWord), and a dictionary's word list,
 *  find the length of shortest transformation sequence
 *   from beginWord to endWord, such that:

	Only one letter can be changed at a time
	Each intermediate word must exist in the word list
	For example,
	
	Given:
	beginWord = "hit"
	endWord = "cog"
	wordList = ["hot","dot","dog","lot","log"]
	As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
	return its length 5.
	
	Note:
	Return 0 if there is no such transformation sequence.
	All words have the same length.
	All words contain only lowercase alphabetic characters.
 */

import java.util.Set;

public class P127_WordLadder {
	public static void main(String[] args) {
		String beginWord = null, endWord = null;
		Set<String> wordList = new HashSet<>();
		beginWord = "hit";
		endWord = "cog";
//		wordList.add("hot");
//		wordList.add("dot");
//		wordList.add("dog");
//		wordList.add("lot");
//		wordList.add("log");
		String[] strs = new String[] {
				"kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar"
				,"die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan"
				,"rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry"
				,"led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie"
				,"yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi"
				,"sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara"
				,"dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian"
				,"pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen"
				,"paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog"
				,"bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap"
				,"can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom"
				,"vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may"
				,"shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim"
				,"sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve"
				,"sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew"
				,"zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin"
				,"nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe"
				,"our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm"
				,"nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum"
				,"rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix"
				,"cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig"
				,"hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two"
				,"ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob"
				,"way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com"
				,"ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met"
				,"hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl"
				,"lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit"
				,"guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got"
				,"meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob"
		};
		strs = tools.FileUtils.A_读取一维数组("C:/data/a.txt");
		beginWord = "nanny";
		endWord = "aloud";
		for (int i  = 0; i < strs.length; i ++) {
			wordList.add(strs[i]);
		}
//		wordList.add("hbbh");
////	wordList.add("hhbh");
//		wordList.add("hhhh");
		Solution4 s = new Solution4();
		long t1 = System.currentTimeMillis();
		System.out.println(s.ladderLength(beginWord, endWord, wordList));
		long t2 = System.currentTimeMillis();
		System.out.println(t2 - t1);
		wordList.clear();
		for (int i  = 0; i < strs.length; i ++) {
			wordList.add(strs[i]);
		}
		Solution_Answer sa = new Solution_Answer();
		t1 = System.currentTimeMillis();
		System.out.println(sa.ladderLength(beginWord, endWord, wordList));
		t2 = System.currentTimeMillis();
		System.out.println(t2 - t1);
	}
	/*
	 *	这个结果是正确的，但是但是TLE
	 */
	static class Solution {
	    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
	    	if (beginWord.equals(endWord)) {
	    		return 0;
	    	}
	    	Queue<String> q_arr = new LinkedList<>();
	    	HashSet<String> sets = new HashSet<>();
	    	q_arr.add(beginWord);
	    	q_arr.add("");
	    	int lay_count = 1;
	    	boolean isDone = false;
	    	while (! q_arr.isEmpty() && ! isDone) {
	    		String now_str_poll = q_arr.poll();
	    		if (now_str_poll.equals("")) {
	    			lay_count ++;
	    			q_arr.add("");
	    			continue;
	    		}
	    		StringBuilder now = new StringBuilder(now_str_poll);
	    		for (int i = 0; ! isDone && i < now.length(); i ++) {
	    			char now_i = now.charAt(i);
	    			for (int j = 0; ! isDone && j < 26; j ++) {
	    				char now_j = (char)('a' + j);
	    				if (now_i == now_j) {
	    					continue;
	    				}
	    				now.setCharAt(i, now_j);
	    				String now_str = now.toString();
	    				if (sets.contains(now_str)) {
	    					continue;
	    				} else {
	    					sets.add(now_str);
	    				}
	    				if (now_str.equals(endWord)) {
	    					isDone = true;
	    					return lay_count + 1;
	    				}
	    				if (wordList.contains(now_str)) {
	    					q_arr.add(now_str);
	    				}
	    			}
    				now.setCharAt(i, now_i);
	    		}
	    	}
	    	return 0;
	    }
	}
	/*	
	 * 	真正的罪魁祸首是StringBuilder
	 * 	还是无法AC
	 */
	static class Solution2 {
	    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
	    	if (beginWord.equals(endWord)) {
	    		return 0;
	    	}
	    	Queue<String> q_arr = new LinkedList<>();
	    	HashSet<String> sets = new HashSet<>();
	    	q_arr.add(beginWord);
	    	sets.add(beginWord);
	    	q_arr.add("");
	    	int q_count = 1;
	    	while (! q_arr.isEmpty()) {
	    		String poll = q_arr.poll();
	    		if (poll.equals("")) {
	    			q_count ++;
	    			if (q_arr.isEmpty()) {
	    				return 0;
	    			}
	    			q_arr.add("");
	    			continue;
	    		}
	    		char[] now = poll.toCharArray();
	    		for (int i = now.length- 1; i > -1; i --) {
	    			char now_i = now[i];
	    			for (int j = 0; j < 26; j ++) {
	    				char now_j = (char)('a' + j);
	    				if (now_i == now_j) {
	    					continue;
	    				}
	    				now[i] = now_j;
	    				String now_str = new String(now);
	    				if (sets.contains(now_str)) {
	    					continue;
	    				} else {
	    					sets.add(now_str);
	    				}
	    				if (now_str.equals(endWord)) {
	    					return q_count + 1;
	    				}
	    				if (wordList.contains(now_str)) {
	    					q_arr.add(now_str);
	    				}
	    			}
    				now[i] = now_i;
	    		}
	    	}
	    	return 0;
	    }
	}
	/*
	 * 	还是TLE
	 */
	static class Solution3 {
		public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
			if (beginWord.equals(endWord)) {
				return 1;
			}
			if (wordList == null) {
				return 0;
			}
			wordList.add(beginWord);
			wordList.add(endWord);
			Queue<String> q_str = new LinkedList<>();
			HashSet<String> set = new HashSet<String>();
			set.add(beginWord);
			q_str.add(beginWord);
			int len = 1;
			while(! q_str.isEmpty()) {
				len ++;
				int size = q_str.size();
				for (int i = 0; i < size; i ++) {
					StringBuilder now = new StringBuilder(q_str.poll());
					for (int j = now.length() - 1; j > -1; j --) {
						char c_j = now.charAt(j);
						for (char c = 'a'; c <= 'z'; c ++) {
							if (c == c_j) {
								continue;
							}
							now.setCharAt(j, c);
							String now_temp = now.toString();
							if (now_temp.equals(endWord)) {
								return len;
							}
							if (set.contains(now_temp)) {
								continue;
							}
							if (wordList.contains(now_temp)) {
								q_str.add(now_temp);
								set.add(now_temp);
							}
						}
						now.setCharAt(j, c_j);
					}
				}
			}
			return 0;
		}
	}
	/*
	 * 	TLE
	 */
	static class Solution4 {
		long count = 0;
		long count_replace = 0;
		public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
			if (beginWord.equals(endWord)) {
				return 1;
			}
			if (wordList == null) {
				return 0;
			}
			wordList.add(beginWord);
			wordList.add(endWord);
			Queue<String> q_str = new LinkedList<>();
			HashSet<String> set = new HashSet<String>();
			set.add(beginWord);
			q_str.add(beginWord);
			int len = 1;
			char[] cs = new char[beginWord.length()];
			while(! q_str.isEmpty()) {
				len ++;
				int size = q_str.size();
				for (int i = 0; i < size; i ++) {
					cs = q_str.poll().toCharArray();
					for (int j = 0; j < cs.length; j ++) {
						char c_j = cs[j];
						for (char c = 'a'; c <= 'z'; c ++) {
							if (c == c_j) {
								continue;
							}
							cs[j] = c;
							count_replace ++;
							String now_temp = new String(cs);
							if (now_temp.equals(endWord)) {
		                    	System.out.println("count : " + count);
		                    	System.out.println("count_replace : " + count_replace);
								return len;
							}
							if (set.contains(now_temp)) {
								continue;
							}
							if (wordList.contains(now_temp)) {
								count ++;
								q_str.add(now_temp);
								set.add(now_temp);
							}
						}
						cs[j] = c_j;
					}
				}
			}
			return 0;
		}
	}
	/*
	 * 	184 ms
	 * 	6.20%
	 */
	static class Solution_Answer {
		long count = 0;
		long count_replace = 0;
	    public int ladderLength(String start, String end, Set<String> dict) {
	        if (dict == null) {
	            return 0;
	        }
	        if (start.equals(end)) {
	            return 1;
	        }
	        dict.add(start);
	        dict.add(end);
	        HashSet<String> hash = new HashSet<String>();
	        Queue<String> queue = new LinkedList<String>();
	        queue.offer(start);
	        hash.add(start);
	        int length = 1;
	        while(!queue.isEmpty()) {
	            length++;
	            int size = queue.size();
	            for (int i = 0; i < size; i++) {
	                String word = queue.poll();
	                for (String nextWord: getNextWords(word, dict)) {
	                    if (hash.contains(nextWord)) {
	                        continue;
	                    }
	                    if (nextWord.equals(end)) {
	                    	System.out.println("count : " + count);
	                    	System.out.println("count_replace : " + count_replace);
	                        return length;
	                    }
	                    count ++;
	                    hash.add(nextWord);
	                    queue.offer(nextWord);
	                }
	            }
	        }
	        return 0;
	    }

	    // replace character of a string at given index to a given character
	    // return a new string
	    private String replace(String s, int index, char c) {
	        char[] chars = s.toCharArray();
	        chars[index] = c;
	        count_replace ++;
	        return new String(chars);
	    }
	    
	    // get connections with given word.
	    // for example, given word = 'hot', dict = {'hot', 'hit', 'hog'}
	    // it will return ['hit', 'hog']
	    private ArrayList<String> getNextWords(String word, Set<String> dict) {
	        ArrayList<String> nextWords = new ArrayList<String>();
	        for (char c = 'a'; c <= 'z'; c++) {
	            for (int i = 0; i < word.length(); i++) {
	                if (c == word.charAt(i)) {
	                    continue;
	                }
	                String nextWord = replace(word, i, c);
	                if (dict.contains(nextWord)) {
	                    nextWords.add(nextWord);
	                }
	            }
	        }
	        return nextWords;
	    }
	}
}
