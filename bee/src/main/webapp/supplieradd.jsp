<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="zh-CN">

<head>
<title>新增供应商</title>
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
<script src="js/ajaxfileupload.js"></script>
<script src="js/jquery.pagination.min.js"></script>
</head>
<script type="text/javascript">
	$(function(){
		 /*点击提交按钮事件  */
		 $("#submit").click(function(){
		 	//先对内容进行判空
		 	var suppliername = $("#suppliername").val();
		  	var contacts = $("#contacts").val();
		  	var phone = $("#phone").val();
		  	var email = $("#email").val();
		  	var city = $("#city").val();
		  	var address = $("#address").val();
		  	if(suppliername!=""&&contacts!=""&&phone!=""&&email!=""&&city!=""&&address!=""){
		  		submit(suppliername,contacts,phone,email,city,address);
		  		return true;
		  	}else{
		  		alert("提交的资料中不允许有空值,请重新提交");
		  	}
		 });
		 function submit(suppliername,contacts,phone,email,city,address){
		 	$.ajax({
				url:"supplierInsertAndUpdate",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	data: {supplier:suppliername,contacts:contacts,phone:phone,email:email,city:city,address:address,type:1},
		      	success:function(data){
		      	if(data.data===1){
		      		alert("新增成功");
		      		window.location.href="supplierList?pageNum=1";
		      	}
		      	},
		      	error:function(){
		           alert("新增失败，请仔细检查您提交的资料!!!");
		           return false;
		      	}
		    });
		 }
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
			<h3 class="panel-title">新增供应商</h3>
		</div>
		<div class="panel-body" >
			<form action="userInsertAndUpdate" method="post">
				 <label style="float: left;width: 80px;line-height: 34px">供应商</label>
				 <input id="suppliername" type="text" required="required" class="form-control" placeholder="供应商" style="display: block;width: 50%">
				 <br> 
				 <br>
				 <br>
				<div style="float:left"><label style="float: left;width: 80px;line-height: 34px">联系人</label></div>
				 <input id="contacts" type="text" required="required" class="form-control" placeholder="联系人" style="display: block;width: 50%">
				<!-- <input id="username"  type="text" required="required" class="form-control" placeholder="品牌" name="username" style="display: block;width: 50%"> -->
				<br> 
				 <br>
				 <br>
				<div style="float:left"><label style="float: left;width: 80px;line-height: 34px">联系电话</label></div>
				 <input id="phone" type="text" required="required" class="form-control" placeholder="联系电话" style="display: block;width: 50%">
				<br> 
				 <br>
				 <br>
				<div style="float:left"><label style="float: left;width: 80px;line-height: 34px">邮箱</label></div>
				 <input id="email" type="email" required="required" class="form-control" placeholder="邮箱" style="display: block;width: 50%">
				 <br> 
				 <br>
				 <br>
				<div style="float:left"><label style="float: left;width: 80px;line-height: 34px">城市</label></div>
				 <input id="city" type="email" required="required" class="form-control" placeholder="城市" style="display: block;width: 50%">
				 <br> 
				 <br>
				 <br>
				<div style="float:left"><label style="float: left;width: 80px;line-height: 34px">地址</label></div>
				 <input id="address" type="text" required="required" class="form-control" placeholder="地址" style="display: block;width: 50%">
				 <br>
				 <br>
				<button id="submit" type="button" class="btn btn-info" style="width: 200px;">保存供应商信息</button>
				<span id="goodsmsg" style="color:green;"></span>
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
