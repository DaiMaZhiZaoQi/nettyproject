package com.sx.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sx.dao.UserMapper;
import com.sx.dao.UserfileMapper;
import com.sx.pojo.User;
import com.sx.pojo.UserExample;
import com.sx.pojo.Userfile;
import com.sx.pojo.UserfileExample;
import com.sx.service.LoginAndRegisterService;
import com.sx.util.Constant;
import com.sx.util.DESEncode;
import com.sx.util.UserLoginMessage;

@Service
public class LoginAndRegisterServiceImpl implements LoginAndRegisterService {
	private int current = 1;// 用于记录当前页数
	@Resource
	private UserMapper userMapper = null;
	@Resource
	private UserfileMapper fileMapper = null;

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	/**
	 * 用户登录
	 */
	@Override
	public Map loginServie(User user) throws Exception {
		User loginUser = null;
		String message = null;// 记录用户登录信息
		int flag = 0;// 判断用户状态：1代表注册用户，0代表用户不存在，-1代表用户被冻结
		UserLoginMessage<User> userMessage = new UserLoginMessage<User>();
		Map<String, UserLoginMessage<User>> map = new HashMap<String, UserLoginMessage<User>>();
		if (user.getUsername().trim() != null) {
			loginUser = userMapper.selectByUsername(user.getUsername());
			if (loginUser != null) {
				String prePassword = user.getPassword();
				if (prePassword.trim() == null) {
					message = "密码不能为空！";
				} else {
					// 对用户密码加密处理
					String toPassword = DESEncode.encrypt(user.getPassword(), Constant.KEY);
					if (toPassword.equals(loginUser.getPassword())) {
						if (loginUser.getStatus() == 1) {
							// 对用户密码解密处理
							String password = loginUser.getPassword();
							String priPassword = DESEncode.decrypt(password, Constant.KEY);
							loginUser.setPassword(priPassword);
							flag = 1;
						} else {
							message = "该用户被冻结！";
							flag = -1;
						}
					} else {
						message = "密码不正确！";
					}
				}
			} else {
				message = "用户名不存在！";
			}
		} else {
			message = "用户名为空！";
		}
		userMessage.setFlag(flag);
		userMessage.setMessage(message);
		userMessage.setUser(loginUser);
		map.put("userMessage", userMessage);
		return map;
	}

	/**
	 * 用户注册
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
	@Override
	public int registerServie(User user) throws Exception {
		// 后台判断用户、密码和性别是否为空
		int m = 0;
//		if (user.getUsername() == null || "".equals(user.getUsername().trim())) {
//			throw new MyException("用户姓名不能为空！");
//		} else if (user.getPassword() == null || "".equals(user.getPassword().trim())) {
//			throw new MyException("用户密码不能为空！");
//		} else if (user.getSex() == null) {
//			throw new MyException("用户性别不能为空！");
//		} else {
//			User loginUser = userMapper.selectByUsername(user.getUsername());
//			if (loginUser != null) {
//				throw new MyException("用户名已存在！");
//			} else {
//				// 对用户密码进行加密
//				String beforePwd = user.getPassword();
//				user.setPassword(DESEncode.encrypt(beforePwd, Constant.KEY));
//				// 设置用户状态：1表示用户激活，0表示用户冻结
//				user.setStatus(1);
//				m = userMapper.insertSelective(user);
//			}
//		}
		return m;
	}

	/**
	 * 用户名查询
	 */
	@Override
	public User userSelectServie(String username) {
		// TODO Auto-generated method stub
		User user = userMapper.selectByUsername(username);
		return user;
	}

	/**
	 * 查询所有用户
	 */
	@Override
	public List<User> selectAllUser() {
		// TODO Auto-generated method stub
		List<User> list = userMapper.selectByExample(null);
		return list;
	}

	/**
	 * 更新用户信息
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
	@Override
	public void updateUserMsg(User user) throws Exception {
		// 加密用户密码
//		String beforePwd = user.getPassword();
//		user.setPassword(DESEncode.encrypt(beforePwd, Constant.KEY));
//		// 为更新的用户设置条件
//		try {
//			UserExample example = new UserExample();
//			Criteria ca = example.createCriteria();
//			ca.andUsernameEqualTo(user.getUsername());
//			// 调用DAO层方法更新用户数据
//			userMapper.updateByExampleSelective(user, example);
//		} catch (Exception e) {
//			throw new MyException("很抱歉，系统出错！");
//		}
	}

//注销用户
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
	public void deleteUserServie(String username) throws Exception {
		// 新建用户对象并设置用户名和状态
//		try {
//			User user = new User();
//			user.setUsername(username);
//			user.setStatus(0);
//			// 设置更新条件
//			UserExample example = new UserExample();
//			Criteria ca = example.createCriteria();
//			ca.andUsernameEqualTo(user.getUsername());
//			// 调用DAO层方法更新用户数据
//			userMapper.updateByExampleSelective(user, example);
//		} catch (Exception e) {
//			throw new MyException("很抱歉，系统出错！");
//		}
	}

//批量导入用户数据
	/*
	 * @Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,
	 * readOnly=false)
	 */
	@Override
	public void insertUsersTest(@Param(value = "list") List<User> list) {
		userMapper.insertUsers(list);
	}

//查询用户上传文件
	@Override
	public List<Userfile> searchFileServie(User user, Integer flag) {
		UserfileExample example = new UserfileExample();
		UserfileExample.Criteria ca = example.createCriteria();
		if (user.getUsername() != null) {
			User user1 = userMapper.selectByUsername(user.getUsername());
			ca.andIdEqualTo(user1.getId());
		} else if (user.getId() != null) {
			ca.andIdEqualTo(user.getId());
		}
		if (flag != null) {
			if (flag.intValue() == -1) {
				current = current - 1;
			} else if (flag.intValue() == 1) {
				current = current + 1;
			}
		}
		// 查询用户上传文件的中条数并算出总页数sum
		List<Userfile> sumfiles = fileMapper.selectByExample(example);
		int sum = (sumfiles.size() % 5 == 0) ? (sumfiles.size() / 5) : (sumfiles.size() / 5 + 1);
		System.out.println(sumfiles.size());
		// 判断请求是否超出界限
		if (current < 1) {
			current = 1;
		}
		if (current > sum) {
			current = sum;
		}
		// 分页查询
		RowBounds rowBounds = new RowBounds(5 * current - 5, 5);
		List<Userfile> files = fileMapper.selectByExampleWithRowbounds(example, rowBounds);
		return files;
	}

	@Override
	public void uploadFileService(Userfile userFile) throws Exception {
		fileMapper.insertSelective(userFile);
	}
}
