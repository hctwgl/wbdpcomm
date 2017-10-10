<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="zh-CN">

<head>
<title>用户权限设置</title>
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
			var password = $("#password").val();
			var dpassword = $("#dpassword").val();
			var auditPassWord = $("#auditPassWord").val();
			var dauditPassWord = $("#dauditPassWord").val();
			var username = $("#username").val();
			var usertype = $("#select").val();
			var phone = $("#phone").val();
			var officephone = $("#officePhone").val();
			var company = $("#company").val();
			if(password!=""&&username!=""&&phone!=""){
			if(password==dpassword && auditPassWord==dauditPassWord ){
					//验证成功后发送数据到后台
				    $.ajax({
							url:"userInsertAndUpdate",
					      	type:"post",
					      	crossDomain:true,
					      	async:true,
					      	dataType:"json",
					      	data: {username:username,password:password,phone:phone,officephone:officephone,usertype:usertype,company:company,type:1},
					      	success:function(data){
					      	if(data.data===1){
					      	alert("新增成功");
					      		window.location.href="userList?pageNum=1";
					      	}else{
					      	alert("新增失败，请仔细检查您提交的资料")
					      	}	
					      	},
					      	error:function(){
					           alert("新增失败，请仔细检查您提交的资料!!!");
					           return false;
					      	}
					    });
			return true;
			}else if(password!=dpassword){
			alert("两次密码输入不一致！！！");
			return false;
			}else if(auditPassWord!=dauditPassWord){
			alert("两次审核密码输入不一致！！！");
			return false;
			}
			}else{
				alert("请勿提交空白资料");
				return false;
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
			<h3 class="panel-title">用户权限设置</h3>
		</div>
		<div class="panel-body">
			
				<!-- <ul>
					<li>
			            <input type="checkbox" name="check-box" /> <span>Pop music</span>
			        </li>
			        <li>
			            <input type="checkbox" name="check-box" /> <span>Rock music</span>
			        </li>
			        <li>
			            <input type="checkbox" name="check-box" /> <span>Rap music</span>
			        </li>
			        <li>
			            <input type="checkbox" name="check-box" /> <span>Hiphop music</span>
			        </li>
			        </ul> -->
			<form action="setModel" method="post">
			<input type="hidden" value="${userID}" name="userID">
			 <ul>
				<c:forEach var="item" items="${data}">
					<c:choose>
						<c:when test="${item.modelStatus==1}">
							<li style="margin-bottom: 20px">
				            <input style="line-height: 5px;height: 20px;width: 20px" type="checkbox" value="${item.id}" checked="checked" name="check-box" /><span style="line-height: 20px">${item.modelName}</span>
				            </li>
						</c:when>
						<c:otherwise>
							<li style="margin-bottom: 20px">
					            <input style="line-height: 5px;height: 20px;width: 20px" type="checkbox" value="${item.id}"  name="check-box" /><span style="line-height: 20px">${item.modelName}</span>
				            </li>
						</c:otherwise>
					</c:choose>
			    </c:forEach>
			</ul>
			<input type="button" name="but" onclick="submit()" class="btn btn-primary" value="提交" style="width:100px"/>
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
