/**
 *   注册和登录用的javaScript
 */

$(document).ready(function(){
	
//  使用浏览器保存的cookie方式登录，该登录方式更加安全
	var data={"sign":1};
	$.ajax({
		type:"post",
		url:"user/login.action",
		data:data,
		dataType:"json",
		success:function(data){  
//			alert("自动登录-->"+data.userMessage.message)
			var userN=data.userMessage.user.username;
			if(userN!=null){
				window.location.href='user/userDetailSelect.action';
			}
		}
	});
});




function login() {
	var userName=document.getElementById("userName");
	var userValue=userName.value;
	var password=$("#password");
	var passwordStr=password.val();
	var sign=1;
/* 	var str=getCookie("userName");	
	alert("获取到cookie-->"+str); */
	if(userValue.length==0){
		userName.focus();
		var span=document.getElementById("spUserName");
		span.innerHTML="请输入用户名";
		span.style.color="red";
		return;
	}
	if(passwordStr.length==0){
		password.focus();
		var span=$("#spPassWord");
		span.html("请输入密码");
		span.css("color","red");
		return;
	}
	var data={"username":userValue,"password":passwordStr,"sign":sign};
	$.ajax({
		type:"post",
		url:"user/login.action", 
		data:data,
		dataType:"json",
		success:function(data){
		/* 	var parsedJson = jQuery.parseJSON(data);  */
			/* alert("恭喜你，登录成功-->"+data.userMessage.message); */
			/*<%--JSON.stringify(jsonobj)  将一个json对象转成字符串
				 JSON.parse(jsonstr); 	 将json字符串转成json对象 
				 
			--%>*/
			alert("恭喜你，登录成功-->"+data.userMessage.message);
			var usern=data.userMessage.user.username;
//			alert("usern-->"+usern);
			if(usern!=null){ 
				window.location.href='user/userDetailSelect.action';
			}
			var span=$("#spPassWord");
			
			span.html(data.userMessage.message);

			span.css("color","red");
		//	setCookie();

		}
	});
}
	
	/* 前端用cookie来保存账号密码 
	不应该由前端保存cookie来控制自动登录
*/
function setCookie(){
	var checked=$("#checkBox");
	if(checked){
		var userName=$("#userName").val();
		$.cookie("userName",userName);
		
		var password=$("#password").val();
		$.cookie("password",password);
	}
}

/* 获得cookie 开始登录 */
function getCookie(){
	var userName=$.cookie("userName");
	var password=$.cookie("password");
	if(userName!=null&&password!=null){
		$("#userName").val(userName);
		$("#password").val(password);
		if($("#checkBox").is(":checked")){
			alert("自动登录");
			
		}else{
			$("#checkBox").attr("checked","true");
		}
		
		$("#login").click();
	}else{
		alert("用户名不能为空");
	}
}

/**
 * 注册
 * @returns
 */
function register(){
	window.location.href="http://localhost:8080/sxpro/user/register.action";
}



