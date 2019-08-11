<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<!--获取个人信息 -->
<html>
<head>
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<link rel="stylesheet" href="../css/bootstrap-theme.min.css" />
<script src="../js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<meta charset="UTF-8">
<title>个人主页</title>
<script type="text/javascript">
	function clearCookie(){
		$.removeCookie('userName', { path:'/sxpro' });
		$.removeCookie('password',{path:'/sxpro'})
		window.location.href="http://localhost:8080/sxpro/login.jsp";
	}
</script>
</head>
<body>
	<div align="center" style="margin-top: 100px">
		<form action="" method="post">
			<div class="panel panel-danger" style="width:800px;height=500px">
				<div class="panel-heading">
					<h3 class="panel-title" align="center">个人资料</h3>
				</div>
			</div>
			<!-- 个人信息内容  -->
			<table style="width:800px;">
				<tr>
					<td align="left">
						<label for="input_username"/>用户名</label>
						<input id="input_username" type="text" readonly="readonly" value="${user.username}"/>
					</td>
					<td align="left">
						<label for="input_favorite">生日</label>
						<input id="input_favorite" type="text" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${user.birthday}"/>'/>
					</td>
				</tr>
				
				<tr>
				
				</tr>
			</table>
		
		</form>
		<div> 
			<a href="allFile.action">上传的文件</a>
			<a href="#">注销用户</a>
			<a href="#" onclick="clearCookie()">退出</a>
		</div>
	</div>

</body>
</html>