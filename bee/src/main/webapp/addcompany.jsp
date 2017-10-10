<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="zh-CN">

<head>
<title>添加公司信息</title>
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
<script src="js/cityJson.js"></script>
<script src="js/citySet.js"></script>
<script src="js/Popt.js"></script> 
<script src="js/bootstrap.min.js"></script>
<script src="js/ajaxfileupload.js"></script>
<script src="js/jquery.pagination.min.js"></script>
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
	$("#address").click(function (e) {
		SelCity(this,e);
	});
	})
	
</script>
<script type="text/javascript">
	$(function(){
		var licenseImg ;
		var contract;
		 $("#submit").click(function(){
			var company = $("#company").val();
			var companyCode = $("#companyCode").val();
			var address = $("#address").val();
			var addressDetail = $("#addressDetail").val();
			//var dauditPassWord = $("#dauditPassWord").val();
			var linkMan = $("#linkMan").val();
			var phone = $("#phone").val();
			var email = $("#email").val();
			var business = $("#business").val();
			var image = $("#image").val();
			var maxAmount = $("#maxAmount").val();
			if(maxAmount==""){
				maxAmount=10000;
			}
			//var licenseImg = $("#licenseImg").val();
			//var contract = $("#contract").val();
			if(company!=""&&companyCode!=""&&phone!=""&&address!=""&&linkMan!=""&&email!=""&&business!=""&&image!=""&&addressDetail!=""){
			//统一社会信用代码正则表达式
			var reqCode = /[0-9A-HJ-NPQRTUWXY]{2}\d{6}[0-9A-HJ-NPQRTUWXY]{10}/;
			//手机号正则表达式
			var reqPhone = /^1[34578]\d{9}$/;
			//邮箱地址正则表达式
			var reqEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
			var strCode = companyCode.match(reqCode);
			var strPhone = phone.match(reqPhone);
			var strEmail = email.match(reqEmail);
			 if(strCode==null){
				$("#codeCheck").html("请输入正确格式统一社会信用代码");
				return false;
			} 
			if(strPhone==null){
				$("#phoneCheck").html("请输入正确格式手机号码");
				return false;
			} 
			if(strEmail==null){
				$("#emailCheck").html("请输入正确格式邮箱地址");
				return false;
			}
			address = address+"-"+addressDetail;
			//第一张图片上传
			$.ajaxFileUpload({
                    url: "imgUpload", 
                    type: "post",
                    /* data :{goodsID:goodsID},  */
                    beforeSend: function (XMLHttpRequest){
				      //("loading");  
				    },
                    secureuri: false, //一般设置为false
                    fileElementId: "business", // 上传文件的id、name属性名
                    dataType: "JSON", //返回值类型，一般设置为json、application/json  这里要用大写  不然会取不到返回的数据
                    success: function(data){ 
                    	if(data!=null){
                    		//$("#msg").html("图片上传完成");
                    		licenseImg = data;
                    		//第二张图片上传
                    			$.ajaxFileUpload({
					                    url: "conUpload", 
					                    type: "post",
					                    /* data :{goodsID:goodsID},  */
					                    beforeSend: function (XMLHttpRequest){
									      //("loading");  
									    },
					                    secureuri: false, //一般设置为false
					                    fileElementId: "image", // 上传文件的id、name属性名
					                    dataType: "JSON", //返回值类型，一般设置为json、application/json  这里要用大写  不然会取不到返回的数据
					                    success: function(data){ 
					                    	if(data!=null){
					                    		//$("#msg").html("图片上传完成");
					                    		contract = data;
					          					//验证成功后发送数据到后台
												    $.ajax({
															url:"insertCompany",
													      	type:"post",
													      	crossDomain:true,
													      	async:true,
													      	dataType:"json",
													      	data: {company:company,companyCode:companyCode,address:address,linkMan:linkMan,phone:phone,email:email,licenseImg:licenseImg,contract:contract,maxAmount:maxAmount},
													      	success:function(data){
													      	if(data.data===1){
													      		$("#msg").html("新增成功");
													      		window.setTimeout(window.location.href="listBeeCompany?pageNum=1",5000);
													      		 //setInterval(window.location.href="listBeeCompany?pageNum=1",50000000);
													      		//window.location.href="listBeeCompany?pageNum=1";
													      	}else{
													      		alert(data.msg);
													      	}	
													      	},
													      	error:function(){
													           alert("新增失败，请仔细检查您提交的资料!!!");
													           return false;
													      	}
													    });	
					                    		//$("#msg").html("");
					                    	}
					                    },
					                    error: function(data, status, e){ 
					                        alert("请求失败！！");
					                    }
					                });
                    	}
                    },
                    error: function(data, status, e){ 
                        alert("请求失败！！");
                    }
                });
			return true;
			}else{//company!=""&&companyCode!=""&&phone!=""&&address!=""&&linkMan!=""&&email!=""&&business!=""&&image!=""&&addressDetail!=""
				if(company==""){
					$("#companyCheck").html("公司名称不能为空");
					return false;
				}
				if(companyCode==""){
					$("#codeCheck").html("信用代码不能为空");
					return false;
				}
				if(address==""){
					$("#addressCheck").html("公司地址不能为空");
					return false;
				}
				if(addressDetail==""){
					$("#detailCheck").html("详细地址不能为空");
					return false;
				}
				if(linkMan==""){
					$("#manCheck").html("联系人不能为空");
					return false;
				}
				if(phone==""){
					$("#phoneCheck").html("联系电话不能为空");
					return false;
				}
				if(email==""){
					$("#emailCheck").html("邮箱不能为空");
					return false;
				}
				if(business==""){
					$("#licCheck").html("营业执照不能为空");
					return false;
				}
				if(image==""){
					$("#conCheck").html("合同不能为空");
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
		 $("#upload").click(function(){
		 	$.ajaxFileUpload({
                    url: "imgUpload", 
                    type: "post",
                    /* data :{goodsID:goodsID},  */
                    beforeSend: function (XMLHttpRequest){
				      //("loading");  
				    },
                    secureuri: false, //一般设置为false
                    fileElementId: "image", // 上传文件的id、name属性名
                    dataType: "JSON", //返回值类型，一般设置为json、application/json  这里要用大写  不然会取不到返回的数据
                    success: function(data){ 
                    	if(data!=null){
                    		$("#msg").html("图片上传完成");
                    		imgUrl = data;
                    		//$("#msg").html("");
                    	}
                    },
                    error: function(data, status, e){ 
                        alert("请求失败！！");
                    }
                });
               }) 
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
			<h3 class="panel-title">新增公司信息</h3>
		</div>
		<div class="panel-body" >
			<form action="userInsertAndUpdate" method="post">
				<div style="float:left"><label style="float: left;width: 80px;line-height: 34px">公司名称</label></div>
				<input id="company"  type="text" required="required" class="form-control" placeholder="公司名称" name="username" style="display: block;width: 50%">
				<div style="float:left"><label id="companyCheck" style="line-height: 34px;color: red"></label></div>
				<br>
				<br>
				<label style="float: left;width: 80px ;line-height: 34px">信用代码</label>
				<input id="companyCode" type="text" required="required" class="form-control" placeholder="公司统一社会信用代码" name="password" style="display: block;width: 50%"> 
				<div style="float:left"><label id="codeCheck" style="line-height: 34px;color: red"></label></div>
				<br> 
				<br>
				<label style="float: left;width: 80px;line-height: 34px">公司地址</label>
				<input id="address" type="text" class="form-control" placeholder="公司地址" style="display: block;width: 50%"> 
				<div style="float:left"><label id="addressCheck" style="line-height: 34px;color: red"></label></div>
				<br>
				<br> 
				<label style="float: left;width: 80px;line-height: 34px">详细地址</label>
				<input id="addressDetail" type="text" class="form-control" placeholder="详细地址" style="display: block;width: 50%"> 
				<div style="float:left"><label id="detailCheck" style="line-height: 34px;color: red"></label></div>
				<br>
				<br> 
				<label style="float: left;width: 80px;line-height: 34px">联系人</label>
				<input id="linkMan" type="text" required="required" class="form-control"placeholder="联系人" name="recomCode" style="display: block;width: 50%">
				<div style="float:left"><label id="manCheck" style="line-height: 34px;color: red"></label></div>
				<!-- <br> 
				 <br> 
				 <label style="float: left;width: 80px;line-height: 34px">确认</label>
				 <input id="dauditPassWord" type="password" required="required" class="form-control" placeholder="确认审核密码" style="display: block;width: 50%"> -->
				 <br> 
				 <br>
				 <label style="float: left;width: 80px;line-height: 34px">联系电话</label>
				 <input id="phone" type="text" required="required" class="form-control" placeholder="联系电话" style="display: block;width: 50%">
				<div style="float:left"><label id="phoneCheck" style="line-height: 34px;color: red"></label></div>
				 <br> 
				 <br> 
				 <label style="float: left;width: 80px;line-height: 34px">邮箱</label>
				 <input id="email" type="email"  required="required"  pattern="" class="form-control" placeholder="邮箱" style="display: block;width: 50%">
				 <div style="float:left"><label id="emailCheck" style="line-height: 34px;color: red"></label></div>
				  <br> 
				 <br> 
				 <label style="float: left;width: 80px;line-height: 34px">最高额度</label>
				 <input id="maxAmount" type="email"  required="required"  pattern="" class="form-control" placeholder="最高额度，不填则默认为10000元" style="display: block;width: 50%">
				 <div style="float:left"><label id="emailCheck" style="line-height: 34px;color: red"></label></div>
				 <br> 
				 <br> 
				 <label style="float: left;width: 80px;line-height: 34px">营业执照</label>
				 <input id="business"  name="business" required="required" class="form-control" type="file"  placeholder="图片" style="display: block;width: 50%">
				 <!-- <button id="upload"  type="button" class="btn btn-info" style="width: 100px;">上传营业执照</button> -->
				<div style="float:left"><label id="licCheck" style="line-height: 34px;color: red"></label></div>
				 <br> 
				 <br> 
				 <label style="float: left;width: 80px;line-height: 34px">合同</label>
				 <input id="image"  name="image" required="required" class="form-control" type="file"  placeholder="图片" style="display: block;width: 50%">
				 <!-- <button id="upload"  type="button" class="btn btn-info" style="width: 100px;">上传图片</button> -->
				<div style="float:left"><label id="conCheck" style="line-height: 34px;color: red"></label></div>
				 <br> 
				 <br>
				 <br>
				<button id="submit" type="button" class="btn btn-primary" style="width: 400px;margin-left: 30px">新增</button><span id="msg" style="color:green;"></span>
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
