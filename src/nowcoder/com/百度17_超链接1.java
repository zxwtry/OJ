package nowcoder.com;

/**
 * 在 Blog 写作的过程中，经常一言不合就添加一些超链接。
	例如：
	I am a typical INFPs.
	http://www.16personalities.com/infp-personality
	为了方便阅读，我们希望将这些超链接转换成直接在网页中可以单击跳转的形式。
	I am a typical INFPs.
	http://www.16personalities.com/infp-personality
	在这题中，我们认为超链接的格式是以 http:// 或者 https:// 开头，
	以空格或换行结束的一个字符串。超链接有可能出现在行中。
									
	输入
	输入文件包含不超过50行，每行包含一个字符串。
	所有的输入都是区分大小写的，每行最多含200个ASCII字符。
	样例输入
	You are next. https://http://http:// Next what?
	输出
	输出经过处理过后的字符串。
	样例输出
	You are next. ＜a href="https://http://http://"＞https://http://http://＜/a＞ Next what? 
	
	时间限制
	C/C++语言：1000MS其它语言：3000MS	
	内存限制
	C/C++语言：65536KB其它语言：589824KB
 */

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        百度17_超链接1.java
 * @type        百度17_超链接1
 * @date        2016年11月30日 下午10:25:10
 * @details     
 */
public class 百度17_超链接1 {
	public static void main(String[] args) {
		
	}
}
