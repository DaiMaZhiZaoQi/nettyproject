package com.sx.util;

public class AopTarget {
	
	public void outTargetMessage() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("AopTarget-->message out");
	}

}
