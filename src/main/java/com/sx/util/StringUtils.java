package com.sx.util;

public class StringUtils {

	public static boolean isEmpty(String str) {
		return null==str||str.length()==0||str.equals("")||str.equals("null");
	}
}
