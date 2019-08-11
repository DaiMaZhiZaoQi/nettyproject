package com.sx.web;

public class Target2 {

	public void outTargetMessage() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Target2-->message out");
	}
}
