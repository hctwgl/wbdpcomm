<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="zh-CN">

<head>
<title>重置密码</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<!-- VENDOR CSS -->
<link rel="stylesheet"
	href="assets/vendor/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
<!-- MAIN CSS -->
<link rel="stylesheet" href="assets/css/main.css">
<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
<link rel="stylesheet" href="assets/css/demo.css">
<!-- GOOGLE FONTS -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700"
	rel="stylesheet">
<!-- ICONS -->
<script src="js/jquery.min.js"></script>
<link rel="apple-touch-icon" sizes="76x76" href="img/login_logo_top.png">
<link rel="icon" type="image/png" sizes="96x96" href="img/web_icon.png">
<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="assets/scripts/klorofil-common.js"></script>
<script src="myjs/customer.js"></script>
<!--引入我的js与css文件-->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/pagination.css">
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.pagination.min.js"></script>
</head>
<script type="text/javascript">
	$(function(){
		 $("#submit").click(function(){
			var passWordold = $("#passWordold").val();
			var passWordnew = $("#passWordnew").val();
			var comfirm = $("#comfirm").val();
			if(passWordold!=""&&passWordnew!=""&&comfirm!=""){
			if(passWordnew==comfirm){
					//验证成功后发送数据到后台
				    $.ajax({
							url:"resetPassWord",
					      	type:"post",
					      	crossDomain:true,
					      	async:true,
					      	dataType:"json",
					      	data: {passWordold:passWordold,passWordnew:passWordnew},
					      	success:function(data){
					      	if(data.data===1){
					      		alert("修改成功");
					      		window.parent.frames.location.href ="toLogout";
					      		//window.history.back(-1);
					      	}else{
					      		alert("修改失败，请检查您输入的密码")
					      	}	
					      	},
					      	error:function(){
					           alert("新增失败，请仔细检查您提交的资料!!!");
					           return false;
					      	}
					    });
				return true;
			}else if(passWordnew!=comfirm){
			$("#doubleCheck").html("两次密码输入不一致");
		  			return false;
			}else if(auditPassWord!=dauditPassWord){
			alert("两次审核密码输入不一致！！！");
			return false;
			}
			}else{
				if(username==""){
					$("#nameCheck").html("用户名不能为空");
		  			return false;
				}
				if(password==""){
					$("#passCheck").html("密码不能为空");
		  			return false;
				}
				if(phone==""){
					$("#phoneCheck").html("电话不能为空");
		  			return false;
				}
				if(company==""){
					$("#companyCheck").html("公司 不能为空");
		  			return false;
				}
			}
		}) 
		/*  function checkUser(){
		 	var password = $("#password").attr("value");
			var dpassword = $("#dpassword").attr("value");
			var auditPassWord = $("#auditPassWord").attr("value");
			var dauditPassWord = $("#dauditPassWord").attr("value");
			if(password==dpassword && auditPassWord==dauditPassWord){
			alert("两次输入一致");
			return true;
			}else{
			alert("两次输入不一致");
			return false
			}
		 } */
	});
</script>
<body>
	<!-- <form action="">
	<div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label" style="width: 100px">用户名：</label>
    <div class="col-sm-10">
      <input type="email" class="form-control" id="inputEmail3" placeholder="用户名" style="width: 500px">
    </div><br><br>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label" style="width: 100px">密码：</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="inputPassword3" placeholder="密码" style="width: 500px">
    </div>
  </div>
  </form> -->
  <!--表单  -->
	 <div class="panel" style="width: 800px;height:600px;margin-left: 100px;margin-top: 10px">
		<div class="panel-heading">
			<h3 class="panel-title">重置密码</h3>
		</div>
		<div class="panel-body" >
			<form action="userInsertAndUpdate" method="post">
				<div style="float:left"><label style="float: left;width: 80px;line-height: 34px">旧密码</label></div>
				<input id="passWordold"  type="password" required="required" class="form-control" placeholder="旧密码" name="username" style="display: block;width: 50%">
				<div style="float:left;"><label id="nameCheck" style="line-height: 34px;color: red"></label></div>
				<br>
				<br>
				<label style="float: left;width: 80px ;line-height: 34px">新密码</label>
				<input id="passWordnew" type="password" required="required" class="form-control" placeholder="新密码"name="password" style="display: block;width: 50%"> 
				<div style="float:left;"><label id="passCheck" style="line-height: 34px;color: red"></label></div>
				<br> 
				<br>
				<label style="float: left;width: 80px;line-height: 34px">确认</label>
				<input id="comfirm" type="password" class="form-control" placeholder="确认密码" style="display: block;width: 50%"> 
				<div style="float:left;"><label id="doubleCheck" style="line-height: 34px;color: red"></label></div>
				<br>
				<br>
				<button id="submit" type="button" class="btn btn-primary" style="width: 400px;margin-left: 30px">重置</button>
				<!-- <input id="submit" type="submit" class="btn btn-primary" value="新增"> -->
			</form>
		</div>
	</div> 
	<!-- <form action="userInsertAndUpdate">
		<input id="username" type="text" class="form-control" placeholder="用户名" name="username"><br>
		<input id="password" type="password" class="form-control" placeholder="密码"name="password"> 
		<input id="submit" type="submit" class="btn btn-primary">新增用户</button>
	</form> -->
</body>

</html>
