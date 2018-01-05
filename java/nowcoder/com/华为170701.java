package nowcoder.com;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class 华为170701 {
    public static void main(String[] args) {
//        String s = "(combine (combine (combine \"a()def\" \"cd\") \"cd\") (combine \"abcdef\" \"cd\"))";
////        s = "(reverse \"a b c\")";
////        s = "(search \"abcdef\" \"cd\")";
//        s = "(search (combine \"123456\" \"abcdefgh\" \"123566\") (reverse \"dc\"))";
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine())
        System.out.println(solve(sc.nextLine()));
        sc.close();
    }
    static final String search = new String("(search ");
    static final String quote = new String("(quote ");
    static final String combine = new String("(combine ");
    static final String reverse = new String("(reverse ");
    static String solve(String s) {
        Stack<String> stk = new Stack<>();
        int len = s.length();
        int i = 0;
        ArrayList<String> arg = new ArrayList<>();
        StringBuilder st = new StringBuilder();
        while (i < len) {
            char c = s.charAt(i);
            if (c == '(') {
                char c1 = s.charAt(i + 1);
                if (c1 == 's') {
                    stk.push(search);
                    i += search.length();
                } else if (c1 == 'q') {
                    stk.push(quote);
                    i += quote.length();
                } else if (c1 == 'c') {
                    stk.push(combine);
                    i += combine.length();
                } else if (c1 == 'r') {
                    stk.push(reverse);
                    i += reverse.length();
                } else {
                    throw new RuntimeException();
                }
            } else if (c == '"') {
                int pi = i;
                i ++;
                while (s.charAt(i) != '"') i ++;
                stk.push(s.substring(pi + 1, i));
                i ++;
            } else if (c == ')') {
                while (arg.size() != 0) {
                    arg.remove(arg.size() - 1);
                }
                while (true) {
                String oper = stk.pop();
                    if (isOper(oper)) {
                        if (oper == search) {
                            if (arg.size() != 2) {
                                throw new RuntimeException();
                            }
                            int i0 = kmp(arg.get(1), arg.get(0));
                            if (i0 == -1) stk.push("");
                            else stk.push(arg.get(1).substring(i0));
                        } else if (oper == combine) {
                            if (arg.size() == 0) {
                                throw new RuntimeException();
                            }
                            st.delete(0, st.length());
                            for (int argi = arg.size() - 1; argi > -1; argi --) {
                                st.append(arg.get(argi));
                            }
                            stk.push(st.toString());
                        } else if (oper == quote) {
                            if (arg.size() != 1) {
                                throw new RuntimeException();
                            }
                            stk.push(arg.get(0));
                        } else {
                            if (arg.size() != 1) {
                                throw new RuntimeException();
                            }
                            st.delete(0, st.length());
                            st.append(arg.get(0));
                            stk.push(st.reverse().toString());
                        }
                        break;
                    } else {
                        arg.add(oper);
                    }
                }
                i ++;
            } else if (c == ' ') {
                i ++;
            } else {
                throw new RuntimeException();
            }
        }
        if (stk.size() != 1) {
            throw new RuntimeException();
        }
        return "\"" + stk.pop() + "\"";
    }
    
    static boolean isOper(String s) {
        if (s == search) return true;
        if (s == combine) return true;
        if (s == quote) return true;
        if (s == reverse) return true;
        return false;
    }
    private static int kmp(String s, String p) {
        int sn = s == null ? 0 : s.length();
        int pn = p == null ? 0 : p.length();
        if (pn == 0) return 0;
        if (sn == 0) return -1;
        int[] next = getNext(p, pn);
        int pi = 0, si = 0;
        while (si < sn)
            if (s.charAt(si) == p.charAt(pi)) {
                si ++;
                pi ++;
                if (pi == pn) {
                  return si - pn;
                }
            } else if (next[pi] == -1) {
                si ++;
            } else pi = next[pi];
        return -1;
    }
    private static int[] getNext(String p, int pn) {
        if (pn < 2) return new int[] {-1};
        int[] next = new int[pn];
        next[0] = -1;
        next[1] = 0;
        int fi = 2, bi = 0;
        while (fi < pn)
            if (p.charAt(fi - 1) == p.charAt(bi)) {
                next[fi ++] = ++ bi;
            } else if (bi <= 0) {
                next[fi ++] = 0;
            } else bi = next[bi];;
        return next;
    }
}
