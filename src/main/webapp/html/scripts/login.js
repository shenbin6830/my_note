function loginAction() {
			var name = $("#count").val().trim();
			var password = $("#password").val().trim();
			//alert(name+","+password);
			var ok = true;
			$("#count_span").html("");
			$("#password_span").html("");
			if (name == "") {
				$("#count_span").html("用户名为空");
				ok = false;
			}
			if (password == "") {
				$("#password_span").html("密码不能为空");
				ok = false;
			}
			if (ok)
				$.ajax({
					url : path + "/user/login.do",
					type : "post",
					dataType : "json",
					data : {
						"name" : name,
						"password" : password
					},
					success : function(result) {
						
						if (result.state == 0) {
							var user = result.data;
							addCookie("userId", user.id, 2);//是为了后面再首页加载笔记名字的时候从cookie拿到userid
							window.location = "edit.html";
						} else if (result.state == 2) {
							$("#count_span").html(result.message);
						} else if (result.state == 3) {
							$("#password_span").html(result.message);
						}
					},
					error : function() {
						alert("登录失败  ");
					}
				});
	
		}