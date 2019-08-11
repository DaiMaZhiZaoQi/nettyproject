<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
<%@ include file="head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>
<body>
<div align="center" style="margin-top: 30px"> 
  <form method="post", action="updatePerson.action" enctype="multipart/form-data">
   		<div align="center">
		 <div class="panel panel-danger" style="width: 800px; height: auto">
            <div class="panel-heading">
              <h3 class="panel-title">修改个人资料</h3>
            </div>
            <div class="panel-body">
              <table class="table" width="600px" height="300px">
              		<tr>
              			<td>
              				<div class="form-group">
              					<label for="input-username" class="col-sm-4 control-label"> 姓名</label>
              					<div class="col-sm-8">
              					 	<input id="input-username" type="text" class="form-control" name="username" value="${user.username }"/>
              					</div>
              				</div>
              			</td>
              			<td>
              				<div class="form-group">
              					<label for="input-age" class="col-sm-4 control-label">年龄</label>
              					<div class="col-sm-8">
              						<input id="input-age" type="text" class="form-control" name="age" value="${user.age}"/>
              					</div>
              				</div>
              			</td>
              		</tr>
              		
              		<tr>
              			<!-- 性别  -->
              			<td>
              				<div class="form-group">
              					<label style="width:33.3%;float: left;position: relative;"> 性别</label>
              					<div style="width: 66.6%;float:left;position: relative;">
              						<input type="text" name="sex1" class="form-control" value="${user.sex==0?'男':'女'}"/>
              					</div>
              				</div>
              				
              			
              			</td>
              			
              			<!-- 出生日期  -->
              			<td>
              				<div class="form-group">
              					<label class="col-sm-4"> 出生日期</label>
              					<div class="col-sm-8">
              						<input type="text" name="birthday" class="form-control" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${user.birthday}'></fmt:formatDate>"value="<fmt:formatDate pattern='yyyy-MM-dd' value='${user.birthday}'></fmt:formatDate>"/>
              					</div>
              				</div>
              			</td>
              		</tr>
              		<tr>
              		 <!-- 性别  -->
              			<td>
              				<div class="form-group">
              					<label style="width:33.3%;float: left;position: relative;">密码</label>
              					<div style="width: 66.6%;float:left;position: relative;">
              						<input type="text" name="password" class="form-control" value="${user.password}"/> 
              					</div>
              				</div>
              				
              			
              			</td>
              			
              			<!-- 出生日期  -->
              			<td>
              				<div class="form-group">
              					<label class="col-sm-4">职位</label>
              					<div class="col-sm-8">
              						<input type="text" name="position" class="form-control" value="${user.position}"/>
              					</div>
              				</div>
              			</td>
              		</tr>
              		
              		<tr>
              			<td>
              				<div class="form-group">
              					<label style="float: left;width: 33.3%;position: relative;">爱好</label>
              					<div style="float: left;width: 66%;position: relative;"><input type="text" class="form-control" name="favorite" value="${user.favorite }"/> </div>
              				</div>
              			</td>
              			<td>
              				<div class="form-group">
              					<label class="col-sm-4">照片</label>
              					<div class="col-sm-8">
              						<input type="text" class="form-control" name="photo" value="${user.photo}"/>
              					</div>
              				</div>
              			</td>
              			
              		</tr>
              		
              		<tr>
              			<td>
              				<div class="form-group">
              					<label class="col-sm-4">上传或修改照片</label>
              					<div class="col-sm-8">
              						<input type="file" name="uploadFile"/>
              					</div>
              				</div>
              			</td>
              			
              			<td>
              				<div class="form-group">
              					<label class="col-sm-4">上传文件</label>
              					<div class="col-sm-8">
              						<input type="file" name="uploadFile">
              					</div>
              				</div>
              			</td>
              		</tr>
              		
              </table>
             	<div style="margin-top: 30px;">
             		<a href="#" >上传文件</a>
             		<a href="#" style="border-left: 1px solid blue; padding-left: 5px">注销用户</a>
             		<a href="#" style="border-left: 1px solid blue;padding-left: 5px">退出</a>
             	</div>
             	<button type="submit" class="btn btn-primary">保存</button>
            </div>
          </div>
	</div>
   </form>
</div>
</body>
</html>