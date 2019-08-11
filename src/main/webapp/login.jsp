<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.net.URLDecoder"%> 
<!DOCTYPE html>
<!-- 登录 jsp文件  -->
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#div_password{
		margin-top: 10px;
	}
	

	.baseStyle{
		margin: 20px 10px 0px 10px;
	}
</style>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js" ></script>
<script type="text/javascript" src="js/login-register.js"></script>


</head>
<body>
	<!-- 登陆功能实现 -->
	<center>
		<h3 align="center">登录</h3>
		<table>
			<tr>
				<td width="30%" align="right">用户名：</td>
				<td width="40%"><input id="userName" type="text" name="userName" placeholder="用户名"></td>
				<td width="30%" align="right"><span id="spUserName"></span></td>
			</tr>
			<tr>
				<td width="40%" align="right">密码：</td>
				<td><input width="40%" id="password" type="password" name="password" placeholder="密码"></td>
				<td width="30%" align="right"><span id="spPassWord"></span></td>
			</tr>
		</table>
			<!--  checked="checked"  -->
			<input class="baseStyle" id="checkBox" type="checkbox"/> 记住密码
		<div>
			<input class="baseStyle" id="login" type="button" name="login" value="登录" onClick="login()"/>
			<input class="baseStyle" id="register" type="button" name="register" value="注册" onClick="register()"/>
		</div>
	</center>


</body>
</html>