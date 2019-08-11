package com.sx.service;
/**
 * 用户服务
 * @author Administrator
 *
 * 2019年6月12日-下午10:33:50
 */

import java.util.List;
import java.util.Map;

import com.sx.myException.MyException;
import com.sx.pojo.ResponseMsg;
import com.sx.pojo.User;
import com.sx.pojo.Userfile;

public interface IUserService {
	/**
	 * 登录获取用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Map getUser(User user) throws Exception;
	/** 
	 * 检测数据是否合法
	 * @param msg
	 * @return
	 */
	String checkValid(ResponseMsg<User> respMsg,String msg);
	
	public void updateUserMsg(User user) throws Exception;
	/**
	 * 注册
	 * @param user  用户实体类  form表单提交时框架封装的实体类
	 * @return
	 * @throws Exception 
	 */
	int registerUser(User user) throws  Exception;

	public User userSelectServie(String username);
	/**
	 * 查询用户上传文件
	 * 
	 * @param user 用户名
	 * @param flag
	 * @return 返回文件集合
	 */
	List<Userfile> searchFileServie(User user, Integer flag);
	public void uploadFileService(Userfile userFile);
}
