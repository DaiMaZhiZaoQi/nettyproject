package com.sx.myException;
/**
 * 自定义异常类
 * @author Administrator
 *
 * 2019年6月30日-下午7:35:14
 */
public class MyException extends Exception {
	
	public MyException(String str) {
		super(str);
	}
	
	public MyException(String arg0,Throwable throwExc) {
		super(arg0,throwExc);
	}

}
