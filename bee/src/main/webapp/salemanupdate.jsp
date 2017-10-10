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
<!--获取从上一页面传来的数据 -->
		<%-- <%
		request.setCharacterEncoding("utf-8");
		String name=new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
		String id=request.getParameter("id");
		String phone=new String(request.getParameter("phone").getBytes("ISO-8859-1"),"UTF-8");
		String saleManCard=request.getParameter("saleManCard");
		String operator=new String(request.getParameter("operator").getBytes("ISO-8859-1"),"UTF-8");
		String city=new String(request.getParameter("city").getBytes("ISO-8859-1"),"UTF-8");
		%>  --%>
		<script type="text/javascript">
			$(function(){
			$("#city").click(function (e) {
				SelCity(this,e);
			});
			})
			
		</script>
<script type="text/javascript">
	$(function(){
		//页面初始化给对应input赋值
		 $("#name").html();
		$("#phone").val();
		$("#card").val();
		$("#oper").val();
		$("#city").val(); 
		<%-- if("<%=operator%>"==="中国移动"){
			$("#operator").append("<option selected='selected' value='"+ "<%=operator %>"+"'>"+ "<%=operator %>" +"</option>"+
				"<option  value='"+ "中国联通"+"'>"+ "中国联通" +"</option>"+"<option  value='"+ "中国电信"+"'>"+ "中国电信" +"</option>"
			);
		}
		if("<%=operator%>"==="中国联通"){
			$("#operator").append("<option selected='selected' value='"+ "<%=operator %>"+"'>"+ "<%=operator %>" +"</option>"+
				"<option  value='"+ "中国移动"+"'>"+ "中国移动" +"</option>"+"<option  value='"+ "中国电信"+"'>"+ "中国电信" +"</option>"
			);
		}
		if("<%=operator%>"==="中国电信"){
			$("#operator").append("<option selected='selected' value='"+ "<%=operator %>"+"'>"+ "<%=operator %>" +"</option>"+
				"<option  value='"+ "中国移动"+"'>"+ "中国移动" +"</option>"+"<option  value='"+ "中国联通"+"'>"+ "中国联通" +"</option>"
			);
		} --%>
		 /*点击提交按钮事件  */
		 $("#submit").click(function(){
		 	//先对内容进行判空
		 	 var id = $("#saleManid").val();
		 	var name = $("#name").val();
		  	var phone = $("#phone").val();
		  	var saleManCard = $("#card").val();
		  	//var salemancard = $("#salemancard").val();
		  	var city = $("#city").val();
		  	var operator = $("#operator").val();
		  	if(id!=""&&phone!=""&&name!=""&&saleManCard!=""&&city!=""&&operator!=""){
		  		submit(id,phone,name,saleManCard,city,operator);
		  		return true;
		  	}else{
		  		alert("提交的资料中不允许有空值,请重新提交");
		  	}
		 });
		 function submit(id,phone,name,saleManCard,city,operator){
		 	var str = saleManCard.substring(6,14);
		 	if(str==="********"){
		 		saleManCard=null;
		 	}else{
		 		//身份证正则
		 		var isIDCard1=/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/; 
		 		var strCard = saleManCard.match(isIDCard1);
		 		if(strCard==null){
					$("#cardCheck").html("请输入正确格式身份证号码");
					return false;
				} 
		 	}
		 	//手机号正则表达式
			var reqPhone = /^1[34578]\d{9}$/;
			var strPhone = phone.match(reqPhone);
			if(strPhone==null){
				$("#phoneCheck").html("请输入正确格式手机号码");
				return false;
			} 
		 	 $.ajax({
				url:"updateSaleman",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	data: {id:id,phone:phone,name:name,saleManCard:saleManCard,city:city,operator:operator},
		      	success:function(data){
		      	if(data===1){
		      		window.location.href="selectAllSaleman?pageNum=1";
		      	}
		      	},
		      	error:function(){
		           alert("新增失败，请仔细检查您提交的资料!!!");
		           return false;
		      	}
		    }); 
		 }
		 //获取供应商数据
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
		      			$("#supplierid").append("<option value='"+ data.data[i].id+"'>"+ data.data[i].supplier +"</option>");
		      			} 
		      	},
		      	error:function(){
		           alert("新增失败，请仔细检查您提交的资料!!!");
		           return false;
		      	}
		    }); */
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
			<h3 class="panel-title">编辑客户经理</h3>
		</div>
		<div class="panel-body" >
			<form action="userInsertAndUpdate" method="post">
				 <label style="float: left;width: 80px;line-height: 34px">姓名</label>
				 <input id="saleManid" type="hidden" value="${data.id}">
				 <input id="name" type="text" value="${data.name}" required="required" class="form-control" placeholder="姓名" style="display: block;width: 50%">
				 <br> 
				 <br>
				 <br>
				<div style="float:left"><label style="float: left;width: 80px;line-height: 34px">手机号码</label></div>
				 <input id="phone" type="text" value="${data.phone}" required="required" class="form-control" placeholder="手机号码" style="display: block;width: 50%">
				<div style="float:left;"><label id="phoneCheck" style="line-height: 34px;color: red"></label></div>
				<!-- <input id="username"  type="text" required="required" class="form-control" placeholder="品牌" name="username" style="display: block;width: 50%"> -->
				<br> 
				 <br>
				 <br>
				<label style="float: left;width: 85px;line-height: 34px">身份证</label>
				<input id="card" type="text" value="${data.saleManCard}" required="required" class="form-control" placeholder="身份证" style="display: block;width: 50%">
				<div style="float:left;"><label id="cardCheck" style="line-height: 34px;color: red"></label></div>
				 <br> 
				 <br> 
				 <br> 
				<div style="float:left"><label style="float: left;width: 80px;line-height: 34px">运营商</label></div>
				 <select id="operator" class="form-control"style="width: 374px" name="userType" style="display: block;width: 100%">
				 <c:choose>
				 	<c:when test="${data.operator=='中国移动'}">
				 		<option selected="selected" value="${data.operator}">${data.operator}</option>
				 		<option value="中国联通">中国联通</option>
				 		<option value="中国电信">中国电信</option>
				 	</c:when>
				 	<c:when test="${data.operator=='中国联通'}">
				 		<option selected="selected" value="${data.operator}">${data.operator}</option>
				 		<option value="中国移动">中国移动</option>
				 		<option value="中国电信">中国电信</option>
				 	</c:when>
				 	<c:when test="${data.operator=='中国电信'}">
				 		<option selected="selected" value="${data.operator}">${data.operator}</option>
				 		<option value="中国移动">中国移动</option>
				 		<option value="中国联通">中国联通</option>
				 	</c:when>
				 </c:choose>
				</select>
				 <br> 
				<div style="float:left"><label style="float: left;width: 80px;line-height: 34px">城市</label></div>
				  <input id="city" type="text" value="${data.city}"  required="required" class="form-control" placeholder="城市" style="display: block;width: 50%">
				 <br> <br> <br>
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
