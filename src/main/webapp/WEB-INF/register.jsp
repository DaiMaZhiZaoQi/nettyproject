<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/> 
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<link rel="stylesheet" href="../css/bootstrap-theme.min.css" />
<script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js" ></script>
<script type="text/javascript" src="${path}/js/register.js" ></script>
<style type="text/css">
	body{
		margin:100px;
	}
	.centerPart{
		width:150px;
	}
</style>


</head>
<body>
		<div align="center">
		<!-- /sxpro/abc/registerUser.action   一个/表示地址栏网上退一个即为根目录  -->
		<form action="registerUser.action" method="post">
			 <div class="panel panel-info" style="width:500px; height:auto;">
	            <div class="panel-heading">
	              <h3 class="panel-title">Panel title</h3>
	            </div>
	            <div class="panel-body">
	             	<table class="table">
	             		<tr class="active">
	             			<th scope="row">用户名&nbsp;:</th>
	             			<td class="centerPart"><input type="text" name="username" onblur="checkValid('username')" id="username"></td>
	             			<td width="150px" align="left"><div id="nameMsg"></div></td>
	             		</tr>
	             		<tr >
	             			<th scope="row">性别&nbsp;:</th>
	             			<td class="centerPart"><input type="radio" name="sex" value="0" onblur="checkValid('sex')"/>&nbsp;男<span style="padding: 0px 30px 0px 30px;"></span><input type="radio" name="sex" value="1" onblur="checkValid('sex')">&nbsp;女</td>
	     					<td width="150px" align="left"><div id="sexMsg"></div></td>        		
	             		</tr>
	             		<tr class="active">
	             			<th scope="row">密码&nbsp;:</th>
	             			<td class="centerPart"><input type="password" name="password" onblur="checkPassWord()"></td>
	             			<td width="150px" align="left"><div id="passwordMsg"></div></td>
	             		</tr>
	             		<tr >
	             			<th scope="row">确认密码&nbsp;:</th>
	             			<td class="centerPart"><input type="password" name="confirmPassword" onblur="checkConfirPassword()"></td>
	             			<td width="150px" align="left"><div id="confirmPassMsg"></div></td>
	             		</tr>
	             		<tr>
	             			<th scopt="row">出生日期&nbsp;:</th>
	             			<td class="centerPart"><input type="date" name="birthday"/>
	             			<td></td>
	             		</tr>
	             		<tr class="active">
	             			<th scopt="row">爱好&nbsp;:</th>
	             			<td class="centerPart" colspan="2">
	             			<input type="checkbox" name="favorite" value="看书"/>看书
	             			<span style="padding: 0px 10px 0px 10px;"></span>
	             			<input type="checkbox" name="favorite" value="听音乐"/>听音乐
	             			<span style="padding:0px 10px 0px 10px;"></span>
	             			<input type="checkbox" name="favorite" value="跑步"/>跑步
	             			<span style="padding:0px 10px 0px 10px;"></span>
	             			<input type="checkbox" name="favorite" value="打篮球">打篮球
	             			</td>
	             			<!-- <td width="150px" align="left"><div id="hobbys"></div></td> -->
	             		</tr>
	             	</table>
	             	<td colspan="2" width="80px"><button type="button" onclick="submitMsg()" class="btn btn-primary btn-lg active">注册</button> </td>
	            </div>
	          </div>
		</form>
		</div>
	
		
</body>
</html>