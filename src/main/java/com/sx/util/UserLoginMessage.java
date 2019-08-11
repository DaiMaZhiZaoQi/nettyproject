package com.sx.util;
/**
 * 该类设计的很巧妙，把一些必要信息（User实体类也包裹进来）因为返回给前端不仅包含不要的对象，还要包含状态信息
 * @author Administrator
 *
 * @param <User>
 * 2019年6月12日-下午11:12:21
 */
public class UserLoginMessage<User> {
	private int flag;
	private String message;
	private User user;

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
