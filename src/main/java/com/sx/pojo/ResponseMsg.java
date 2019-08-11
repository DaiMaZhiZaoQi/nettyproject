package com.sx.pojo;
/**
 * 响应的实体类
 * @author Administrator
 *
 * @param <T>
 * 2019年6月24日-下午11:11:51
 */
public class ResponseMsg<T> {
	
	private String msg;
	private int code;
	private T t;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	

}
