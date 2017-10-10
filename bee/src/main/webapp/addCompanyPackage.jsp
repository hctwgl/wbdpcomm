<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="zh-CN">

<head>
<title>新增套餐</title>
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
<!--获取从上一页面传来的数据 -->
		<%
		request.setCharacterEncoding("utf-8");
		Long id=Long.parseLong(request.getParameter("id"));
		Integer userType=Integer.parseInt(request.getParameter("userType"));
		Integer quota= 0;
		if(request.getParameter("quota")!=null&&!"".equals(request.getParameter("quota"))){
			quota=Integer.parseInt(request.getParameter("quota"));
		}
		String clientWX = request.getParameter("beeWX");
		%> 
<script type="text/javascript">
	$(function(){
	//上一页面传来的客户ID
	var id = <%=id %>;
	//上一页面传来的客户可用额度
	var quota = <%=quota %>;
	//上一页面传来的用户类型
	var userType = <%=userType%>;
	//上一页面传来的用户微信号
	var clientWX= <%=clientWX%>;
	$("#quota").val(quota);
			 //获取供应商信息
		   /*  $.ajax({
				url:"listSupplier",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	contentType: "application/json",
		      	success:function(data){
		      		 var lenth = data.data.length;
		      		 var str = "";
		      		  for(var i=0 ;i<=lenth;i++){
		      			$("#supplier").append("<option value='"+ data.data[i].id+"'>"+ data.data[i].supplier +"</option>");
		      			} 
		      	},
		      	error:function(){
		           alert("访问失败");
		           return false;
		      	}
		    });  */
		
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
		  
		  /* 点击计算总价按钮 */
		 $("#count").click(function(){
		 	//先对内容进行判空
		  	var pacPeriods = $("#pacPeriods").val();
		  	var packageMouth = $("#packageMouth").val();
		  	//var bill = $("#bill").val();
		  	if(pacPeriods!=""&&packageMouth!=""){
		  		//套餐总价
				var Totalprice;
				Totalprice=(parseInt(pacPeriods*packageMouth));
		  		$("#totalprice").val(Totalprice);
		  		return true;
		  	}else{
		  		alert("提交的资料中不允许有空值！！！");
		  	}
		 });
		 /*点击提交按钮事件  */
		 $("#submit").click(function(){
		 	//先对内容进行判空
		 	//期数
		 	var pacPeriods = $("#pacPeriods").val();
		 	//月付
		  	var packageMouth = $("#packageMouth").val();
		  	var phoneModel = $("#phoneModel").val();
		  	var totalprice = $("#totalprice").val();
		  	if(userType===2){
		  		if(totalprice>quota){
		  			alert("套餐总价不允许超过客户可用额度");
		  			return false;
		  		}
		  	}
		  	if(pacPeriods!=""&&packageMouth!=""&&phoneModel!=""&&totalprice!=""){
		  		submit(pacPeriods,packageMouth,phoneModel,totalprice);
		  		return true;
		  	}else{
		  		alert("提交的资料中不允许有空值！！！");
		  		return false;
		  	}
		  	/* alert(totalprice+":"+quota);
		  	return false; */
		 });
		 //提交新增套餐资料
		 function submit(pacPeriods,packageMouth,phoneModel,totalprice){
		 	$.ajax({
				url:"insertBeePackage",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	data: {beeID:id,pacPeriods:pacPeriods,pacMonthlyPrice:packageMouth,phoneModel:phoneModel,pacPrice:totalprice,clientWx:clientWX,userType:userType},
		      	success:function(data){
		      	if(data.status==="SUCCESS"){
		      		$("#packagemsg").html("客户套餐信息保存完成");
		      		window.location.href="beeList?pageNum=1";
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
			<h3 class="panel-title">新增套餐</h3>
		</div>
		<div class="panel-body" >
			<form action="userInsertAndUpdate" method="post">
			 	<label style="float: left;width: 80px;line-height: 34px">可用额度</label>
				 <input id="quota" type="text" disabled="disabled"  required="required" class="form-control" placeholder="可用额度" style="display: block;width: 50%">
				 <br> 
				 <br>
				 <br>
				 <label style="float: left;width: 80px;line-height: 34px">套餐月付</label>
				 <input id="packageMouth" type="text" required="required" class="form-control" placeholder="套餐月付" style="display: block;width: 50%">
				 <br> 
				 <br>
				 <br>
				  <label style="float: left;width: 85px;line-height: 34px">期数(月)</label>
				<select id="pacPeriods" class="form-control"style="width: 374px" name="userType" style="display: block;width: 100%">
					 	<option selected="selected" value="12">12</option>
					  	<option value="24">24</option>
				</select>
				 <br> 
				 <label style="float: left;width: 80px;line-height: 34px">手机型号</label>
				 <input id="phoneModel" type="text"  required="required" class="form-control" placeholder="手机型号" style="display: block;width: 50%">
				 <br> 
				 <br>
				 <br>
				 <label style="float: left;width: 80px;line-height: 34px">套餐总价</label>
				 <input id="totalprice" type="text"  required="required" class="form-control" placeholder="套餐总价" style="display: block;width: 50%">
				  <button id="count" type="button" class="btn btn-info" style="width: 150px;">计算套餐总价</button>
				 <br> 
				 <br>
				
				 <!-- <label style="float: left;width: 80px;line-height: 34px">套餐详情</label>
				<textarea id="details"  rows="4" cols="20" class="form-control" style="display: block;width: 50%"></textarea>
				 <br> 
				 <br> -->
				<button id="submit" type="button" class="btn btn-info" style="width: 200px;">保存套餐信息</button>
				<span id="packagemsg" style="color:green;"></span>
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
