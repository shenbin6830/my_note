function registAction()
				{
					var username=$("#regist_username").val().trim();
					var nick=$("#nickname").val().trim();
					var password=$("#regist_password").val().trim();
					var passwordagain=$("#final_password").val().trim();
					var ok=true;
					$("#warning_1 span").html("");
					$("#warning_2 span").html("");
					$("#warning_3 span").html("");
					if(username=="")
						{
							$("#warning_1 span").html("用户名不能为空");
							$("#warning_1").show();
							ok=false;
						}
					if(password=="")  
						{      
							$("#warning_2 span").html("密码不能为空");
							$("#warning_2").show();
							ok=false;
						}else if(password.length<6)
							{
							$("#warning_2 span").html("密码长度过短");
							$("#warning_2").show();
							}
					if(passwordagain!=password)
						{
							$("#warning_3").html("密码不一致");
							$("warning_3").show();
							ok=false;
						}
				 	$.ajax({
						url:path+"/user/regist.do",
						type:"post",
						dataType:"json",
						data:{"username":username,"nick":nick,"password":password},
						success:function(result){
							if (result.state == 0) {
								var user = result.data;
								//返回登录页面，相当于按了返回按钮
								$("#back").click();
								//设置用户名输入域数据
								$("#count").val(user.name);
								//密码输入域获得焦点
								$("#password").focus();
								//alert("注册成功");
							}else if (result.state == 2) {
								$("#warning_1 span").html(result.message);
								$("#warning_1").show();
							}else if (result.state == 3) {
								$("#warning_2 span").html("这是什么情况，按道理讲不可能发生");
								$("#warning_2").show();
							}
						},
						error:function()
						{
							alert("注册失败");
						}
					}) 		
	}