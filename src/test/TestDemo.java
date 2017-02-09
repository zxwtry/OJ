package test;

/**
 * 	这是用于测试的样式
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     test
 * @file        TestDemo.java
 * @type        TestDemo
 * @date        2017年2月9日 下午9:24:48
 * @details     
 */
public class TestDemo {
	public static void main(String[] args) {
//		testDemo();
		String tf = getTraceInfo();
		System.out.println("=============");
		System.out.println(tf);
	}
	static void testDemo() {
		Runtime runtime = Runtime.getRuntime();
		Class classNow = runtime.getClass();
		System.out.println(classNow.getName());
	}
	public static String getTraceInfo(){    
		  StringBuffer sb = new StringBuffer();     
		      
		  StackTraceElement[] stacks = new Throwable().getStackTrace();    
		  int stacksLen = stacks.length;    
		  sb.append("class: " ).append(stacks[1].getClassName()).append("; method: ").append(stacks[1].getMethodName()).append("; number: ").append(stacks[1].getLineNumber());    
		  
		  return sb.toString();
	}    
}
