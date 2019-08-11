package com.sx.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sx.dao.UserMapper;
import com.sx.dao.UserfileMapper;
import com.sx.myException.MyException;
import com.sx.pojo.ResponseMsg;
import com.sx.pojo.User;
import com.sx.pojo.UserExample;
import com.sx.pojo.UserExample.Criteria;
import com.sx.pojo.Userfile;
import com.sx.service.IUserService;
import com.sx.util.Constant;
import com.sx.util.DESEncode;
import com.sx.util.StringUtils;
import com.sx.util.UserLoginMessage;
@Service
public class UserServiceImp implements IUserService{

	@Resource
	UserMapper mUserMapper;
	
	@Resource
	private UserfileMapper mfileMapper;
	
	
	public UserfileMapper getMfileMapper() {
		return mfileMapper;
	}

	public void setMfileMapper(UserfileMapper mfileMapper) {
		this.mfileMapper = mfileMapper;
	}

	public UserMapper getmUserMapper() {
		return mUserMapper;
	}

	public void setmUserMapper(UserMapper mUserMapper) {
		this.mUserMapper = mUserMapper;
	}

	/**
	 * 登录时获取响应数据
	 * @throws Exception 
	 */
	@Override
	public Map getUser(User user) throws Exception {
		String msg=null;
		Map<String,UserLoginMessage<User>> map=new HashMap<>();
		UserLoginMessage<User> userLogin=new UserLoginMessage<>();
		int accoutStatus=0;			//   -1  账户被冻结   0，用户不存在  1，已注册用户
		boolean fault=false;
		if(user==null) {
			msg="参数异常，空用户";
			fault=true;
			map.put("userMessage",userLogin);
		}
		String userName=user.getUsername();
		if(null==userName&&!fault) {
			fault=true;
			msg="参数异常，用户名为空";
			map.put("userMessage", userLogin);
		}
		String password=user.getPassword();
		if(null==password&&!fault) {
			fault=true;
			msg="参数异常，密码为空";
			map.put("userMessage", userLogin);
		}
		if(fault){
			userLogin.setMessage(msg);
			userLogin.setUser(user);
			map.put("userMessage", userLogin);
			return map;
		}
		User userSql = mUserMapper.selectByUsername(userName);
		if(null==userSql) {
			userLogin.setMessage("参数异常，账户名不存在");
			userLogin.setUser(user);
			map.put("userMessage", userLogin);
			return map;
		}
		String passwordSql=userSql.getPassword();
		String enPassword=DESEncode.encrypt(password, Constant.KEY);	//  密码加密和数据库比较
		if(enPassword.equals(passwordSql)) {
			Integer status = userSql.getStatus();
			if(status==1) {										// 用户状态正常  密码解密处理
//				DESEncode.decrypt(data, key)
				userSql.setPassword(password);
				
				accoutStatus=1;
				msg="登录成功";
			}else {
				accoutStatus=-1;
				msg="账户异常,账户被冻结";
			}
		}else {
			accoutStatus=0;
			msg="账户异常,密码错误";
		}
		userLogin.setFlag(accoutStatus);
		userLogin.setMessage(msg);
		userLogin.setUser(userSql);
		map.put("userMessage", userLogin);
		return map;
	}

	@Override
	public String checkValid(ResponseMsg<User> respMsg,String msg) {
		User selectByUsername = mUserMapper.selectByUsername(msg);
		if(selectByUsername!=null) {
			respMsg.setCode(209);
			return "用户已存在";
		}
		respMsg.setCode(200);
		return "用户不存在";
	}
	
	@Override
	public int registerUser(User user) throws Exception {
		int result=0;
		if(user==null) {
			throw new MyException("参数错误");
		}
		String userName=user.getUsername();
		String password = user.getPassword();
		Integer sex = user.getSex();
		
		if(StringUtils.isEmpty(userName)) {
			throw new MyException("用户名不能为空");
		}else if(StringUtils.isEmpty(password)) {
			throw new MyException("密码不能为空");
		}else if(sex==null) {
			throw new MyException("请选择性别");
		}else {
			User user2 = mUserMapper.selectByUsername(userName);
			if(user2!=null) {
				throw new MyException("用户名已存在!");
			}else {
				user.setPassword(DESEncode.encrypt(password, Constant.KEY));
				user.setStatus(1);
				result= mUserMapper.insert(user);
			}
		}
		
		
		return result;
	}

	
	
	@Override
	public void updateUserMsg(User user) throws Exception {
		// 加密用户密码
		String beforePwd = user.getPassword();
		user.setPassword(DESEncode.encrypt(beforePwd, Constant.KEY));
		// 为更新的用户设置条件
		try {
			UserExample example = new UserExample();
			Criteria ca = example.createCriteria();
			ca.andUsernameEqualTo(user.getUsername());
			// 调用DAO层方法更新用户数据
			mUserMapper.updateByExampleSelective(user, example);
		} catch (Exception e) {
			throw new MyException("很抱歉，系统出错！");
		}
		
	}

	@Override
	public User userSelectServie(String username) {
		return mUserMapper.selectByUsername(username);
		
	}

	@Override
	public void uploadFileService(Userfile userFile) {
		mfileMapper.insertSelective(userFile);
		
	}

	@Override
	public List<Userfile> searchFileServie(User user, Integer flag) {
		
		return mfileMapper.selectUserFileByUserId(user);
	}
	
	

}
