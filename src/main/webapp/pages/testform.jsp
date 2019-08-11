<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("form button[type='button']").click(function(){
			alert("提交");
			$("form").submit();
		});
	})
</script>
</head>
<body> 
	<form action="updatePerson.action" method="post">
		<table>
			<tr><td>姓名:<input type="text" name="username" value="${user.username }"></td></tr>
			<tr><td>年龄:<input type="text" name="age" value="${user.age}"></td></tr>
			<tr><td>性别:<input type="text" name="sex1" value="${user.sex==0?'男':'女'}"></td></tr>
			<tr><td>出生日期:<input type="text" name="birthday" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${user.birthday}"></fmt:formatDate>'></td></tr>
			<tr><td>密码:<input type="password" name="password" value="${user.password}"><td></tr>
			<tr><td>爱好:<input type="text" name="favorite" value="${user.favorite}"></td></tr>
			<tr><td>职位:<input type="text" name="position" value="${user.position }"></td></tr>
			<tr><td><button type="button"/>编辑信息</td></tr>
		</table>
	</form>
</body>
</html>