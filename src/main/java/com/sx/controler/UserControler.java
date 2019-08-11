package com.sx.controler;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sx.pojo.ResponseMsg;
import com.sx.pojo.User;
import com.sx.pojo.Userfile;
import com.sx.service.IUserService;
import com.sx.util.RandomStringUtil;
import com.sx.util.StringUtils;
import com.sx.util.UserLoginMessage;

@Controller
@RequestMapping(value="/user")
public class UserControler {
	
	@Resource
	private IUserService mIUserService;
 	
	 
	
	public IUserService getIUserService() {
		return mIUserService;
	}


	public void setIUserService(IUserService iUserService) {
		mIUserService = iUserService;
	}


	@RequestMapping(value="/login.action")
	@ResponseBody
	public Map login(User user,HttpServletRequest request,HttpServletResponse resp,Integer sign) throws Exception {
		System.out.println("user-->"+user.toString()+"--sign--"+sign);
		int isSavePass=sign.intValue();
		boolean cookValid=false;
		if(isSavePass==1) {		//    获取cookie自动登录
			Cookie[] cookies=request.getCookies();
			String cookName=null;  
			String password=null;
			
			for(Cookie cookie:cookies) {
				String cookieName=cookie.getName();
				if("userName".equals(cookieName)) {
					
//					int maxAge = cookie.getMaxAge();		//   不能通过这种方式来判断cookie是否过期，cookie过期了浏览器就不会向后台发送
						cookName=cookie.getValue();
				}
				if("password".equals(cookieName)) {
//					int maxAge = cookie.getMaxAge();
						password=cookie.getValue();
				}
			}
			System.out.println("cookName"+cookName);
			if(!StringUtils.isEmpty(cookName)&&!StringUtils.isEmpty(password)) {
				System.out.println("cookie begin logining");
				user=new User();
				user.setUsername(cookName);
				user.setPassword(password);
				cookValid=true;
			}else {
				System.out.println("cookie失效");
			}
		} 
		Map mapUser = mIUserService.getUser(user);
		@SuppressWarnings("unchecked")
		UserLoginMessage<User> userLoginMsg=(UserLoginMessage<User>) mapUser.get("userMessage");
		int flag = userLoginMsg.getFlag();
		if(flag==1&&isSavePass==1&&!cookValid) {			//  登录成功			
			HttpSession session = request.getSession();			//    使用的是Ajax请求，用Session会话来实现不同页面之间的参数传递
			session.setAttribute("user", userLoginMsg.getUser());
//			request.setAttribute("user", userLoginMsg.getUser());
//			session.invalidate();								//  不能这么使用
			System.out.println("设置session-->");
			User us=userLoginMsg.getUser();
			
			Cookie cookUserNam=new Cookie("userName", us.getUsername());
			cookUserNam.setMaxAge(60);
			cookUserNam.setPath("/sxpro");
			resp.addCookie(cookUserNam);
			
			Cookie cookPassWord=new Cookie("password", us.getPassword());
			cookPassWord.setMaxAge(60);
			cookPassWord.setPath("/sxpro");
			resp.addCookie(cookPassWord);
			
		}
		return mapUser;
	}
/*	@RequestMapping(value="/logOut.action",method=RequestMethod.GET)
	public String logOut(HttpServletResponse response) {
		Cookie cookieUserName=new Cookie("userName","");
		cookieUserName.setPath("/sxpro");
		Cookie cookiePassword=new Cookie("passWord", "");
		cookiePassword.setPath("/sxpro");
		response.addCookie(cookieUserName);
		response.addCookie(cookiePassword);
		return "redirect:/login.jsp";   

	}	*/	
	
	@RequestMapping(value="/userDetailSelect.action",method=RequestMethod.GET)
	public String userMsg(HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		User user=(User)request.getAttribute("user");
//		System.out.println("user-->"+user.toString());
//		User user=(User)session.getAttribute("user");
		return "forward:/pages/personalMsg.jsp";
	}
	@RequestMapping(value="/register.action",method=RequestMethod.GET)
	public String register() {
		System.out.println("register-->");
		return "forward:/WEB-INF/register.jsp";
	}
	
	/**
	 * 检测用户名是否有效
	 * @param msg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkValid.action",method=RequestMethod.GET)
	public ResponseMsg checkValid(String msg) {
		ResponseMsg<User> respMsg=new ResponseMsg<>();
		String checkResult=mIUserService.checkValid(respMsg,msg);
		respMsg.setMsg(checkResult);
//		Map<String,ResponseMsg<User>> map=new HashMap<>();
//		map.put("respMsp", respMsg);
		return respMsg; 
	}
	@ResponseBody
	@RequestMapping(value="/registerUser.action",method=RequestMethod.POST)
	public ModelAndView register(User user) throws Exception {
		System.out.println("user-->"+user.toString());
		ResponseMsg<User> respMsg=new ResponseMsg<>();
		int registerUser = mIUserService.registerUser(user);
		ModelAndView modelAndView=null;
		if(registerUser>0) {
			respMsg.setCode(200);
			respMsg.setMsg("注册成功");
			respMsg.setT(user);
			modelAndView= new ModelAndView();
			modelAndView.addObject("user",user);
			modelAndView.setViewName("personalEditPage");
		}
		return modelAndView;
	}
	
	
	/**
	 * 修改用户资料
	 * @param user
	 * @throws Exception 
	 * @RequestParam(value="user",required=true)
	 * @return
	 */
	@RequestMapping(value="/updatePerson.action",method=RequestMethod.POST) 
	public ModelAndView updatePerson(User user,String sex1,HttpServletRequest request,@RequestParam(value="uploadFile") MultipartFile[] multiPart) throws Exception {
		System.out.println("updatePerson-->"+"user-->"+"user-->"+user.toString());
		//   接收并保存文件
		String msg="";
		if(multiPart!=null&&multiPart.length>0) {
			for(MultipartFile mp:multiPart) {
				String filename = mp.getOriginalFilename(); //  上传文件名
				boolean isEmpty=mp.isEmpty();
				if(!isEmpty) {								//  存在文件
					String fileSuffix="gif,png,jpg,jpeg,bmp";
					String suffix = filename.substring(filename.lastIndexOf(".")+1).toLowerCase().trim();
					 List<String> listSuffix= Arrays.asList(fileSuffix.split(","));
					 if(listSuffix.contains(suffix)) {		//	是图片文件
						 String realPath = request.getServletContext().getRealPath("upload");   		//upload的真实路径
						 File f=new File(realPath);
						 if(!f.exists()) { 
							 f.mkdirs();
						 }
						 File targetFile = new File(f,filename);
					
							mp.transferTo(targetFile);
							String filePath="/upload/"+filename;
							user.setPhoto(filePath);
							System.out.println("文件路径"+filePath+"-->"+targetFile.getAbsolutePath());
					 }else {						 //   判断文件是否为图片，图片则设置为头像，普通文件怎添加到文件列表
						 String realPath = request.getServletContext().getRealPath("/upload/userfile");
						 File file=new File(realPath);
						 if(!file.exists()) {
							 file.mkdirs();
						 }
						 String saveFileName=RandomStringUtil.getString(16)+"."+suffix;
						 File targetFile = new File(file,saveFileName);
						 mp.transferTo(targetFile);
						 Userfile userfile = new Userfile();
						 String savePath="/upload/userfile/"+saveFileName;
						 userfile.setFilepath(savePath);
						 userfile.setFilename(filename.substring(0, filename.lastIndexOf(".")));
						 User userSelectServie = mIUserService.userSelectServie(user.getUsername());
						 userfile.setFileid(userSelectServie.getId());
						 mIUserService.uploadFileService(userfile);
						 
					 }

				}
			}
		}else {
			msg="没有上传文件";
		}
		Integer sex="男".equals(sex1)?0:1;  //   修改数据库
		user.setSex(sex);
		mIUserService.updateUserMsg(user);
//		ResponseMsg<User> respMsg=new ResponseMsg<>();
//		respMsg.setCode(200);
//		respMsg.setMsg("");
//		respMsg.setT(user); 
		ModelAndView modelAndView=new ModelAndView(); 
		modelAndView.addObject("user",user);
		modelAndView.setViewName("personalMsg"); 
		return modelAndView;
		 
	}
	
	@RequestMapping(value="/allFile.action",method=RequestMethod.GET)
	public String allFile(HttpServletRequest request,Integer flag) {
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		System.out.println("allFile-->"+user.toString());
		List<Userfile> files= mIUserService.searchFileServie(user,flag);
		System.out.println("files-->"+files.toString());
		request.setAttribute("files", files);
		return "allfile";
	}
	
	@RequestMapping(value="/loginaaa.action")
	@ResponseBody
	public Map loginaaa(User user,HttpServletRequest request,HttpServletResponse resp){
		System.out.println("loginaaa--->"+user.toString());
		return null;
		
	}
	
	
	@RequestMapping(value="/doRegister.action",method=RequestMethod.GET)
	public String doRegister() {
		return "";
	}
	

}
