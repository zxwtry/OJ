package nowcoder.com;

/*
 * 	题目描述
									
	大部分论坛、网站等，为了方便管理，都进行了关于敏感词的设定。
	在多数网站，敏感词一般是指带有敏感政治倾向、暴力倾向、不健康色彩的词或不文明语，
	也有一些网站根据自身实际情况，设定一些只适用于本网站的特殊敏感词。
	比如，当你发贴的时候带有某些事先设定的词时，这个贴是不能发出的。
	或者这个词被自动替换为星号 (*)，或者说是被和谐掉了。请注意敏感词只有小写字母，
	文本如果中的大写字母当做小写字母处理，出现敏感单词，即使作为子串出现也要被和谐，多个子串重叠他们都要被和谐。
	
	例如当敏感词是gre，eat 是
	Your English is Great.
	将被和谐成
	Your English is *****.
	
	请编程，输入给定的文本和关键字，将所有被和谐的部分都打上星号 (*)
	
	
									
	输入
	输入的第一行包含一个整数 n，表示敏感词的总数。
	接下来的 n 行，每行包含一个长度不超过 100 的敏感词，单词不区分大小写。
	接下来的一行包含一段长度不超过 10000的字符串表示待处理的文本。
	
	样例输入
	4
	revolution
	greatwall
	democracy
	science
	Cross the greatwall, we can reach every corner of the world.
	
	输出
	输出一行，表示和谐过后的文本。
	样例输出
	Cross the *********, we can reach every corner of the world.
	时间限制
	C/C++语言：1000MS其它语言：3000MS	
	内存限制
	C/C++语言：65536KB其它语言：589824KB
 */

public class 百度17_敏感词 {
	public static void main(String[] args) {
		
	}
}
