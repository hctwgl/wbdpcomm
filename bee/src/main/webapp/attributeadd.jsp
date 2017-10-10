<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="zh-CN">

<head>
<title>添加用户</title>
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
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/ajaxfileupload.js"></script>
<script src="js/jquery.pagination.min.js"></script>
</head>
<script type="text/javascript">
	$(function(){
		 	 $.ajax({
				url:"getattribute",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	contentType: "application/json",
		      	success:function(data){
		      		 var lenth = data.data.length;
		      		 for(var i=0 ;i<=lenth;i++){
		      			$("#attribute").append("<option value='"+ data.data[i].id+"'>"+ data.data[i].attribute +"</option>");
		      			}
		      	},
		      	error:function(){
		           alert("获取数据失败");
		           return false;
		      	}
		    }); 
		    
		    /*  $.ajax({
				url:"attributeAndValue",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	contentType: "application/json",
		      	data: JSON.stringify({attribute:"运营商"}),
		      	success:function(data){
		      		 var lenth = data.data.length;
		      		 for(var i=0 ;i<=lenth;i++){
		      			$("#operator").append("<option value='"+ data.data[i].id+"'>"+ data.data[i].attributevalue +"</option>");
		      			}
		      		 
		      	},
		      	error:function(){
		           alert("新增失败，请仔细检查您提交的资料!!!");
		           return false;
		      	}
			}); */
			/*  $.ajax({
				url:"attributeAndValue",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	contentType: "application/json",
		      	data: JSON.stringify({attribute:"品牌"}),
		      	success:function(data){
		      		 var lenth = data.data.length;
		      		 var str = "";
		      		  for(var i=0 ;i<=lenth;i++){
		      			$("#brand").append("<option value='"+ data.data[i].id+"'>"+ data.data[i].attributevalue +"</option>");
		      			} 
		      	},
		      	error:function(){
		           alert("新增失败，请仔细检查您提交的资料!!!");
		           return false;
		      	}
		    });  */
		    
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
		           alert("新增失败，请仔细检查您提交的资料!!!");
		           return false;
		      	}
		    }); */
		var imgUrl;
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
		  
		  
		 /*点击提交按钮事件  */
		/*  $("#submit").click(function(){
		 	//先对内容进行判空
		 	var goodsname = $("#goodsname").val();
		  	var brand = $("#brand").val();
		  	var model = $("#model").val();
		  	var color = $("#color").val();
		  	var memory = $("#memory").val();
		  	var price = $("#price").val();
		  	var operator = $("#operator").val();
		  	var image = $("#image").val();
		  	var supid = $("#supplier").val();
		  	
		  	if(goodsname!=""&&brand!=""&&model!=""&&color!=""&&memory!=""&&price!=""&&operator!=""&&image!=""&&supid!=""){
		  		submit(goodsname,brand,model,color,memory,price,operator,supid);
		  		return true;
		  	}else{
		  		alert("提交的资料中不允许有空值！！！");
		  	}
		 }); */
		
		  /*点击提交按钮事件  */
		 $("#auto").click(function(){
		 	$("input[type='hidden']").attr('type','text');
		 });
		  /*点击提交按钮事件  */
		 $("#submit").click(function(){
		 	var attribute = $("#attribute").val();
		 	var autoattribute = $("#autoatt").val();
		 	var attrivalue = $("#attrivalue").val();
		 	var data;
		 	if(autoattribute!=null&&autoattribute!=""&&autoattribute!=undefined ){
		 		 data = {attribute:autoattribute,attributeValue:attrivalue,type:1};
		 	}else{
		 		 data = {attribute:attribute,attributeValue:attrivalue,type:2};
		 	}
		 	if(attrivalue!=null&&attrivalue!=""){
		 		$.ajax({
				url:"addattributeView",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	data: data,
		      	success:function(data){
		      	if(data.data===1){
		      		alert("新增成功");
		      		window.location.href="attributeView?pageNum=1";
		      	}else{
		      		alert("新增失败，请仔细检查您提交的资料")
		      	}	
		      	},
		      	error:function(){
		           alert("新增失败，请仔细检查您提交的资料!!!");
		           return false;
		      	}
		    });
		 	}else{
		 	alert("属性值不能为空");
		 		return false;
		 	}
		 	 
		 });
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
			<h3 class="panel-title">属性管理</h3>
		</div>
		<div class="panel-body" >
			<form action="userInsertAndUpdate" method="post">
				 <label style="float: left;width: 80px;line-height: 34px">属性名称</label>
				 <select id="attribute" class="form-control"style="width: 374px" name="userType" style="display: block;width: 50%">
					
				</select> 
				<br>
				<button id="auto" style="float: left;margin-right: 10px" type="button" class="btn btn-info">自定义属性名称</button>
				 <input id="autoatt" type="hidden" required="required" class="form-control" placeholder="示例：品牌" style="display: block;width: 50%">
				 <br>
				 <br>
				 <br>
				<!-- <input id="username"  type="text" required="required" class="form-control" placeholder="品牌" name="username" style="display: block;width: 50%"> -->
				
				<label style="float: left;width: 80px ;line-height: 34px">属性值</label>
				<!-- <select id="model" class="form-control"style="width: 150px" name="userType" style="display: block;width: 50%">
					<option value="1" selected="selected">品牌</option>
				</select> -->
				 <input id="attrivalue" type="text" required="required" class="form-control" placeholder="属性值"name="password" style="display: block;width: 50%"> 
				 <span style="line-height: 34px">示例：华为</span>
				 <br>
				 <br>
				<button id="submit" type="button" class="btn btn-info" style="width: 200px;">保存属性信息</button>
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
