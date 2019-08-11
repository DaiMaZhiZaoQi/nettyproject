package tesppackage;


import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.logging.Logger;

import javax.annotation.Resource;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sun.xml.internal.rngom.binary.Pattern;
import com.sx.dao.UserMapper;
import com.sx.pojo.User;
import com.sx.util.AopTarget;
import com.sx.web.Target2;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration("classpath:spring.xml") 
public class TestProject {   //加载配置文件 
	@Resource
	private UserMapper userMapper;
	
	/**
	 * 测试Spring IOC,DI 功能是否生效，一般都采用注解的方式来声明
	 * 
	 */
	@Test
	public void testSpring() {
		Logger global = Logger.getGlobal();
		User loginUser = userMapper.selectByUsername("admin");
//		System.out.println();
		global.info("loginUser-->"+loginUser.getPassword());
	}
	
	/**
	 * 实现一个基于SpringAOP技术 ，基于jdk动态代理
	 */
	@Resource
	private AopTarget aopTarget;
	@Resource
	private Target2 target2;
	@Test
	public void testAop() {
		aopTarget.outTargetMessage();
		target2.outTargetMessage();
	}
	/**
	 * 测试正则表达式
	 */
	@Test
	public void testRegex() {
		//  匹配电话号码  18666163064
		long beginTime=System.currentTimeMillis();
		String regex="^1[^397]\\w{0,9}$";    
		String phone="18666163064";
		boolean matches = phone.matches(regex); 
		long endTime=System.currentTimeMillis();
		long result=endTime-beginTime;
		System.out.println("testRegex-->"+matches+"-->need time-->"+result);
		String str="￥……&**（）";
		String regec="^￥\\W{0,9}$";
		boolean matches2 = str.matches(regec);
		System.out.println("-->"+matches2);
		
		String str2="    *";
		String regex2="\\s{0,9}\\S{0,9}$";
		boolean matches3 = str2.matches(regex2);
		System.out.println("-->"+matches3);
		
		String str3="door";
		String regex3="do?or";			//  ? 匹配前面字符串{0,1} 次
		boolean matches4 = str3.matches(regex3);
		System.out.println("-->"+matches4);
		
		//匹配邮箱
		String email="a423432@163.com.cn";
		String regex4="(\\w{1,})@(\\w{1,}(\\.\\w+)+)";
		boolean matches5 = email.matches(regex4);
		System.out.println("-->"+matches5);
				
		String str4="12345f6";
		String str5="abcdHf";
		String regex5="\\d{6,12}";
		String regex6="\\D{6,12}";
		boolean matches6 = str5.matches(regex6);
		System.out.println("-->"+matches6);
				
	}
 
}
