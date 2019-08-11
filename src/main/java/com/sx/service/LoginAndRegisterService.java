package com.sx.service;

import java.util.List;
import java.util.Map;

import com.sx.pojo.User;
import com.sx.pojo.Userfile;

/**
 * 用户登录和注册控制
 * 
 * @author 方冠夫
 *
 */
public interface LoginAndRegisterService {
	/**
	 * 用户登录
	 * 
	 * @param user 实体类对象
	 * @return Map 映射类型对象
	 */
	Map loginServie(User user) throws Exception;

	/**
	 * 用户注册
	 * 
	 * @param user 实体类对象
	 * @return int 整形数据
	 */
	int registerServie(User user) throws Exception;

	/**
	 * 用户名查询
	 * 
	 * @param username 用户名
	 * @return
	 */
	User userSelectServie(String username);

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	List<User> selectAllUser();

	/**
	 * 更新用户信息
	 * 
	 * @param user 需更新的用户对象
	 */
	void updateUserMsg(User user) throws Exception;

	/**
	 * 注销用户信息
	 * 
	 * @param username 用户名
	 * @throws Exception
	 */
	void deleteUserServie(String username) throws Exception;

	/**
	 * 批量导入用户数据
	 * 
	 * @param list 用户元素的集合
	 */
	void insertUsersTest(List<User> list);

	/**
	 * 查询用户上传文件
	 * 
	 * @param user 用户名
	 * @param flag
	 * @return 返回文件集合
	 */
	List<Userfile> searchFileServie(User user, Integer flag);

	/**
	 * 用戶上传文件
	 * 
	 * @param userFile 文件实体类
	 */
	void uploadFileService(Userfile userFile) throws Exception;
}
