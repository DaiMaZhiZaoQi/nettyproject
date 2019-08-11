package com.sx.util;

public class AopAdvice {
	private long beginTime=0;
	public void adviceBeforeMsg() {
		beginTime=System.currentTimeMillis();
		System.out.println("adviceBeforemsg--beginTime"+beginTime);
		
	}
	
	public void adviceAfterMsg() {
		long endTime=System.currentTimeMillis();
		long resultTime=endTime-beginTime;
		System.out.println("adviceAfterMsg---结束"+endTime+"耗时"+resultTime);
	}
}
