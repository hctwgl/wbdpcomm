<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  --%>
<!doctype html>
<html lang="zh-CN" class="fullscreen-bg">
<head>
	<title>用户登录</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<!-- VENDOR CSS -->
	<script src="js/jquery.min.js"></script>
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
	<!-- MAIN CSS -->
	<link rel="stylesheet" href="assets/css/main.css">
	<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
	<link rel="stylesheet" href="assets/css/demo.css">
	<!-- GOOGLE FONTS -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
	<!-- ICONS -->
	<link rel="apple-touch-icon" sizes="76x76" href="img/yifenqis.png">
	<link rel="icon" type="image/png" sizes="96x96" href="img/yifenqis.png">
	<!--引入我的js与css文件  -->
	
	<!--  <link rel="stylesheet" href="css/bootstrap.min.css">  -->
	<!-- <script src="js/bootstrap.min.js"></script> -->
	<script type="text/javascript">
		//ajax页面初始化加载
		$(function(){
			    $("#login").click(function(){
				//得到登录的用户类型
				var usertype = $("#userType").val();
				var username = $("#username").val();
				var password = $("#password").val();
				$.ajax({
					url:"userLogin",
			      	type:"post",
			      	crossDomain:true,
			      	async:true,
			      	dataType:"json",
			      	data: {userName:username,userType:usertype,passWord:password},
			      	success:function(data){
			      		if(data.status==="SUCCESS"){
			      			window.location.href="toPage";
			      		}else{
			      			alert("登录失败，请检查用户名和密码是否正确");
			      		}
			      	},
			      	error:function(){
			           alert("登录失败，请检查用户名和密码");
			           return false;
			      	}
		    });
			})    
		});
	</script>
	<script type="text/javascript">
    	if (window != top)
		top.location.href = location.href;
	</script>
	<script type="text/javascript">
		function keyLogin(){
			 if (event.keyCode==13){ //回车键的键值为13
			   //得到登录的用户类型
				var usertype = $("#userType").val();
				var username = $("#username").val();
				var password = $("#password").val();
				$.ajax({
					url:"userLogin",
			      	type:"post",
			      	crossDomain:true,
			      	async:true,
			      	dataType:"json",
			      	data: {userName:username,userType:usertype,passWord:password},
			      	success:function(data){
			      		if(data.status==="SUCCESS"){
			      			window.location.href="toPage";
			      		}else{
			      			alert("登录失败，请检查用户名和密码是否正确");
			      		}
			      	},
			      	error:function(){
			           alert("登录失败，请检查用户名和密码");
			           return false;
			      	}
		    });
		   }
		}
	</script>
</head>

<body onkeydown="keyLogin();">
	<!-- WRAPPER -->
	<div id="wrapper">
		<div class="vertical-align-wrap">
			<div class="vertical-align-middle">
				<div class="auth-box ">
				<div class="left">
						<div class="content">
							<div class="header">
								<div class="logo text-center"><img src="img/web_icon.png" alt="Klorofil Logo"></div>
								<p class="lead">登录你的账号</p>
							</div>
							<form class="form-auth-small" action="index.php">
								<div class="form-group">
									<label for="signin-email" class="control-label sr-only">用户名</label>
									<input type="text" class="form-control" id="username"  placeholder="用户名或手机号">
								</div>
								<div class="form-group">
									<label for="signin-password" class="control-label sr-only">密码</label>
									<input type="password" class="form-control" id="password"   placeholder="密码">
								</div>
								<div class="form-group clearfix">
									<!-- <label class="fancy-checkbox element-left">
										<select class="input-md form-control" name="userType" id="userType">
											<option value="1">系统管理员</option>
											<option value="2">客服</option>
											<option value="3">客户经理</option>
											<option value="4">客户管理员</option>
										</select> 
									</label> -->
								</div>
								<button id="login" type="button" class="btn btn-primary btn-lg btn-block">登录</button>
								<div class="bottom">
									<!-- <span class="helper-text"><i class="fa fa-lock"></i> <a href="#"></a></span> -->
								</div>
							</form>
						</div>
					</div>
					<%-- <div class="left">
						<div class="content">
							<div class="header">
								<div class="logo text-center"><img src="img/yifenqi.png" alt="Klorofil Logo"></div>
								<p class="lead">用户登录</p>
							</div>
							 <form class="form-auth-small" action="userLogin" method="post"> 
								<div class="form-group">
									<label for="signin-email" class="control-label sr-only">用户名</label>
									<input id="username" type="text"  name = "userName"  class="form-control"   placeholder="用户名或手机号">
								</div>
								<div class="form-group">
									<label for="signin-password" class="control-label sr-only">密码</label>
									<input id="password" type="password"  name = "passWord"  class="form-control"   placeholder="密码">
								</div>
								<div class="form-group clearfix">
									<label class="fancy-checkbox element-left" style="margin-bottom: 5px">
										<input type="text" style="border: 0;color: red;" value="${msg}">
										<!-- <label for="signin-email" class="control-label sr-only">选择用户类型：</label> -->
										 <select class="input-md form-control" name="userType" id="userType">
											<option value="1">系统管理员</option>
											<option value="2">客服</option>
											<option value="3">客户经理</option>
											<option value="4">客户管理员</option>
										</select> 
									</label>
								</div>
								<button id="login" type="submit" class="btn btn-primary btn-lg btn-block" >登录</button>
								<!-- <a href="main.jsp" class="btn btn-primary btn-lg btn-block">登录</a> -->
								<div class="bottom">
									
								</div>
							 </form> 
						</div>
					</div> --%>
					<div class="right">
						<div class="overlay"></div>
						<div class="content text">
							<h1 class="heading">维泽金融消费大数据平台</h1>
							<p>with you forever</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
