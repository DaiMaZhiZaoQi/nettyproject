<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<div >
			<div align="center">文件列表</div>
			<table style="width: 100%; margin-top: 20px;">
				<thead>
					<tr>
						<td style="width:25%;"align="center">编号</td>
						<td style="width:25%;" align="center">文件名称</td>
						<td style="width:25%;" align="center">文件路径</td>
						<td style="width:25%;" align="center">文件操作</td>
					</tr>
									
				</thead>
				<tbody>
					<c:forEach  items="${files}" var="file" varStatus="vs">
						<tr>
							<th scope="row" style="width:25%;"align="center">${vs.index+1 }</th>
							<td style="width:25%;"align="center">${file.filename}</td>
							<td style="width:25%;"align="center">${file.filepath}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<ul>
				<li><a href="#">上一页</a></li>
				<li><a href="#">1</a> </li>
				<li><a href="#">下一页</a> </li>
			</ul>
		
		</div>
</body>
</html>