/**
 * 注册
 */
	$(document).ready(function(){
		$("#nameMsg").html("<font color='red' size=1>*</font>");
		$("#sexMsg").html("<font color='red' size=1>*</font>");
		$("#passwordMsg").html("<font color='red' size=1>*</font>");	
		$("#confirmPassMsg").html("<font color='red' size=1>*</font>");
		$("#hobbys").html("<font color='red' size=1>*</font>");
	});
	
	function checkValid(str){
		if(str=="username"){
			var userName=$("#username").val(); 
			//alert("val-->"+userName);
			var data="msg="+userName;
			if(userName==""||userName==null){
				$("#nameMsg").html("<font color='red' size=1>请输入用户名</font>");
				return false;
			}else {							//  验证用户名是否存在，
				var result;
				$.ajax({
					type:"GET",
					data:data,
					 async: false,
					url:"checkValid.action",
					dataType:"json",
					success:function(data){
						var str=JSON.stringify(data);
						//alert("-->"+str+"-->"+data.msg);
						var respCode=data.code;
						if(respCode==200){
							$("#nameMsg").html("<font color='green' size=1>用户名正确</font>")
							result=true;
						}else if(respCode==209){
							$("#nameMsg").html("<font color='red' size=1>用户已存在，请&nbsp;<a href='/sxpro/login.jsp'>登录</a>&nbsp或重新输入用户名</font>")
							result=false;
						}
					}
				});
				return result;
			}
		}
	
	}
	
	/**
	 * 验证表单信息是否正确
	 * @returns
	 */
	function submitMsg(){
		var checkValue=$('input:radio[name="sex"]:checked').val();
		if(checkValue==null){
			$('#sexMsg').html('<font color="red" size=1>请选择性别</font>')
			return false;
		}else{
			$('#sexMsg').html('<font color="green" size=1>√</font>')
		}
		var username=checkValid("username")
		var checkResult=checkPassWord();
		if(checkResult==true&&username==true){
			alert("提交表单");
			$("form").submit();
		}else {
			alert("正在验证");
		}
	}
	/**
	 * 验证密码格式是否正确
	 * @returns
	 */
	function checkPassWord(){
		var password=$('input:password[name="password"]').val();
		var ruled=/^\d{6,12}$/;
		var ruleD=/^\D{6,12}$/;
		if(password==null){
			$("#passwordMsg").html('<font color="red" size=1>请输入密码</font>');
			$('input:password[name="password"]').focus();
			return false;
		}else if(password.length<6||password.length>12){
			$("#passwordMsg").html('<font color="red" size=1>请输入6-12位密码</font>');
			$('input:password[name="password"]').focus();
			return false;
		}else if(ruled.test(password)||ruleD.test(password)){
			$("#passwordMsg").html('<font color="red" size=1>密码应该包含数字与字符</font>');
			$('input:password[name="password"]').focus();
			return false;
		}else {
			$("#passwordMsg").html('<font color="green" size=1>√</font>');
		}
		return true;
	}
	
	function checkConfirPassword(){
		var password=$('input:password[name="password"]').val();
		var confirmPassword=$('input:password[name="confirmPassword"]').val();
		if(confirmPassword==null){
			$("#confirmPassMsg").html('<font color="red" size=1>验证密码不能为空</font>');
		}else if(confirmPassword!=password){
			$("#confirmPassMsg").html('<font color="red" size=1>密码不一致，请重新输入</font>');
			$('input:password[name="confirmPassword"]').focus();
			$('input:password[name="password"]').focus();
		}else{
			$("#confirmPassMsg").html('<font color="green" size=1>√</font>');
		}
	}
	
	
	
	
	
	
	
	
	
	
	