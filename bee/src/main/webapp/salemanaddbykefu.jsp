<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="zh-CN">

<head>
<title>新增客户经理</title>
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
<script src="js/cityJson.js"></script>
<script src="js/citySet.js"></script>
<script src="js/Popt.js"></script> 
<style type="text/css">
ul, ol, dl { list-style: none; }
._citys { width: 450px; display: inline-block; border: 2px solid #eee; padding: 5px; position: relative; }
._citys span { color: #56b4f8; height: 15px; width: 15px; line-height: 15px; text-align: center; border-radius: 3px; position: absolute; right: 10px; top: 10px; border: 1px solid #56b4f8; cursor: pointer; }
._citys0 { width: 100%; height: 34px; display: inline-block; border-bottom: 2px solid #56b4f8; padding: 0; margin: 0; background-color: white;}
._citys0 li { display: inline-block; line-height: 34px; font-size: 15px; color: #888; width: 80px; text-align: center; cursor: pointer; }
.citySel { background-color: #56b4f8; color: #fff !important; }
._citys1 { width: 100%; display: inline-block; padding: 10px 0;background-color: white; }
._citys1 a { width: 83px; height: 35px; display: inline-block; background-color: #f5f5f5; color: #666; margin-left: 6px; margin-top: 3px; line-height: 35px; text-align: center; cursor: pointer; font-size: 13px; overflow: hidden; }
._citys1 a:hover { color: #fff; background-color: #56b4f8; }
.AreaS { background-color: #56b4f8 !important; color: #fff !important; }
</style>
</head>
	<script type="text/javascript">
				$(function(){
				$("#city").click(function (e) {
					SelCity(this,e);
				});
				})
		</script>
<script type="text/javascript">
	$(function(){
		 /*点击提交按钮事件  */
		 $("#submit").click(function(){
		 	//先对内容进行判空
		 	var name = $("#name").val();
		  	var phone = $("#phone").val();
		  	var oper = $("#oper").val();
		  	var card = $("#card").val();
		  	//var code = $("#code").val();
		  	var city = $("#city").val();
		  	var manager = $("#manager").val();
		  	var passWord = $("#passWord").val();
		  	var confirm = $("#confirm").val();
		  	if(name!=""&&phone!=""&&oper!=""&&card!=""&&city!=""&&manager!=""&&confirm!=""){
		  		if(passWord!=confirm){
		  			$("#confirmCheck").html("两次输入密码不一致");
		  			return false;
		  		}
		  		submit(name,phone,oper,card,city,manager,passWord);
		  		return true;
		  	}else{
		  		if(name==""){
		  			$("#nameCheck").html("姓名不能为空");
		  			return false;
		  		}
		  		if(phone==""){
		  			$("#phoneCheck").html("手机号不能为空");
		  			return false;
		  		}
		  		if(card==""){
		  			$("#cardCheck").html("身份证不能为空");
		  			return false;
		  		}
		  		if(city==""){
		  			$("#cityCheck").html("城市不能为空");
		  			return false;
		  		}
		  		if(passWord==""){
		  			$("#passCheck").html("密码不能为空");
		  			return false;
		  		}
		  	}
		 });
		 function submit(name,phone,oper,card,city,manager,passWord){
		 //手机号正则表达式
			var reqPhone = /^1[34578]\d{9}$/;
			var strPhone = phone.match(reqPhone);
			var phoneStatus;
			if(strPhone==null){
				alert("请输入正确格式手机号码");
				return false;
			}
			$.ajax({
					url:"creatCode",
			      	type:"post",
			      	crossDomain:true,
			      	async:true,
			      	dataType:"json",
			      	data: {phone:phone},
			      	success:function(data){
			      		if(data===2){
			      			$("#phoneCheck").html("该手机号码已存在");
			      			phoneStatus = data;
			      			return false;
			      		}else{
			      			return true;
			      		}
			      		
			      	},
			      	error:function(){
			           alert("获取失败");
			           return false;
			      	}
			    });
			//身份证正则
	 		var isIDCard1=/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/; 
	 		var strCard = card.match(isIDCard1);
	 		if(strCard==null){
				$("#cardCheck").html("请输入正确格式身份证号码");
				return false;
			} 
			if(phoneStatus===2){
				return false;
			}
		 	$.ajax({
				url:"insertSalemanBykefu",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	data: {name:name,phone:phone,saleManCard:card,operator:oper,city:city,creatBy:manager,passWord:passWord},
		      	success:function(data){
		      	if(data===1){
		      		window.location.href="selectAllSaleman?pageNum=1";
		      	}else if(data===2){
		      		alert("该手机号码已存在");
		      	}
		      	},
		      	error:function(){
		           return false;
		      	}
		    });
		 }
		  /*生成推荐码  */
		 $("#creatCode").click(function(){
		 	var phone = $("#phone").val();
		 	//手机号正则表达式
			var reqPhone = /^1[3|4|5|8][0-9]\d{4,8}$/;
			var strPhone = phone.match(reqPhone);
			if(strPhone==null){
				$("#phoneCheck").html("请输入正确格式手机号码");
				return false;
			}
		 	if(phone!=" "||phone!=null){
			 		$.ajax({
					url:"creatCode",
			      	type:"post",
			      	crossDomain:true,
			      	async:true,
			      	dataType:"json",
			      	data: {phone:phone},
			      	success:function(data){
			      		if(data===2){
			      			$("#phoneCheck").html("该手机号码已存在");
			      			return false;
			      		}else{
			      		$("#phoneCheck").html(" ");
			      		 $("#code").val(data);
			      		}
			      		
			      	},
			      	error:function(){
			           alert("获取失败");
			           return false;
			      	}
			    });
		 	}else{
		 		alert("请先输入手机号码");
		 	}
		 });
	});
	$(function(){
		//客户管理员信息
		$.ajax({
				url:"listSaleman",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	//contentType: "application/json",
		      	//data: {company:company},
		      	success:function(data){
		      		 var lenth = data.data.length;
		      		 for(var i=0 ;i<=lenth;i++){
		      			$("#manager").append("<option value='"+ data.data[i].name+"'>"+ data.data[i].name +"</option>");
		      			}
		      		 /* $.each( data,function(){
						$("#brand").append("<option value='"+ this.data.id+"'>"+ this.data.attributevalue +"</option>");
						});   */
		      	},
		      	error:function(){
		           alert("新增失败，请仔细检查您提交的资料!!!");
		           return false;
		      	}
			});
	})
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
			<h3 class="panel-title">新增客户经理</h3>
		</div>
		<div class="panel-body" >
			<form action="userInsertAndUpdate" method="post">
				 <label style="float: left;width: 80px;line-height: 34px">姓名</label>
				 <input id="name" type="text" required="required" class="form-control" placeholder="姓名" style="display: block;width: 50%">
				 <div style="float:left;margin-right: 200px"><label id="nameCheck" style="line-height: 34px;color: red"></label></div>
				 <br> 
				 <br>
				 <br>
				<div style="float:left"><label style="float: left;width: 80px;line-height: 34px">手机号码</label></div>
				 <input id="phone" type="text" required="required" class="form-control" placeholder="手机号码" style="display: block;width: 50%">
				 <div style="float:left;margin-right: 130px"><label id="phoneCheck" style="line-height: 34px;color: red"></label></div>
				<!-- <input id="username"  type="text" required="required" class="form-control" placeholder="品牌" name="username" style="display: block;width: 50%"> -->
				<br> 
				 <br>
				 <br>
				 <div style="float:left"><label style="float: left;width: 80px;line-height: 34px">身份证</label></div>
				 <input id="card" type="text" required="required" class="form-control" placeholder="身份证" style="display: block;width: 50%">
				 <div style="float:left;"><label id="cardCheck" style="line-height: 34px;color: red"></label></div>
				 <br> 
				 <br>
				 <br>
				 <div style="float:left"><label style="float: left;width: 80px;line-height: 34px">城市</label></div>
				 <input id="city" type="text" required="required" class="form-control" placeholder="城市" style="display: block;width: 50%">
				 <div style="float:left;"><label id="cityCheck" style="line-height: 34px;color: red"></label></div>
				 <br> 
				 <br>
				 <br>
				 <div style="float:left"><label style="float: left;width: 80px;line-height: 34px">密码</label></div>
				 <input id="passWord" type="password" required="required" class="form-control" placeholder="密码" style="display: block;width: 50%">
				 <div style="float:left;"><label id="passCheck" style="line-height: 34px;color: red"></label></div>
				 <br> 
				 <br>
				 <br>
				 <div style="float:left"><label style="float: left;width: 80px;line-height: 34px">确认密码</label></div>
				 <input id="confirm" type="password" required="required" class="form-control" placeholder="确认密码" style="display: block;width: 50%">
				 <div style="float:left;"><label id="confirmCheck" style="line-height: 34px;color: red"></label></div>
				 <br> 
				 <br>
				 <br>
				 <!-- <div style="float:left"><label style="float: left;width: 80px;line-height: 34px">推荐码</label></div>
				 <input id="code" type="text" disabled="disabled" required="required" class="form-control" placeholder="推荐码" style="display: block;width: 50%">
				<button id="creatCode" type="button" class="btn btn-info" style="width: 200px;">生成推荐码</button>
				<div style="float:right;margin-right: -20px"><label id="codeCheck" style="line-height: 34px;color: red"></label></div>
				 <br>
				 <br> -->
				<label style="float: left;width: 85px;line-height: 34px">上级管理员</label>
				<select id="manager" class="form-control"style="width: 374px" name="saleManType" style="display: block;width: 50%">
					 
				</select>
				 <br>
				<label style="float: left;width: 85px;line-height: 34px">运营商</label>
				<select id="oper" class="form-control"style="width: 374px" name="userType" style="display: block;width: 50%">
					 <option value="中国移动" >中国移动</option>
					 <option value="中国联通" >中国联通</option>
					 <option value="中国电信" >中国电信</option>
				</select>
				<!-- <div style="float:left"><label style="float: left;width: 80px;line-height: 34px">推荐码</label></div>
				 <input id="recommend" type="text" required="required" class="form-control" placeholder="推荐码" style="display: block;width: 50%">
				 <br> 
				 <br>
				 <br> -->
				 <br> 
				<button id="submit" type="button" class="btn btn-info" style="width: 200px;">保存信息</button>
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
