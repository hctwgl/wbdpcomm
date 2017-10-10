<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="zh-CN">

<head>
<title>添加集团客户</title>
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
<script src="js/bootstrap.min.js"></script>
<!-- <script src="js/jquery.min.js"></script>
<script src="js/jquery.form.js/"></script>
<script src="js/jquery.pagination.min.js"></script> -->
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="http://malsup.github.io/jquery.form.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
	//ajax 方式上传文件操作  
	$(document).ready(function() {
		$('#btn').click(function() {
			if (checkData()) {
				$('#form1').ajaxSubmit({
					url : "excel",
					dataType : 'text',
					success : resutlMsg,
					error : errorMsg
				});
				function resutlMsg(msg) {
					//alert(msg[1]);
					var jsonobj=eval('('+msg+')');
					
					//alert(jsonobj[0][0]);  
					$("#upfile").val("");
					//表格头部
					var html = "<table><tr><td>姓名</td><td>身份证号</td><td>手机号</td><td>部门</td><td>套餐价格（每月）</td><td>期数</td><td>手机型号</td><td>套餐总价</td></tr>";
					 for(var i=0;i<jsonobj.length;i++){
					 	html+="<tr>"
						 for(var j=0;j<jsonobj[i].length;j++){
						 html+="<td>";
						 html+=jsonobj[i][j];
						 html+="</td>";
							//alert("第"+i+"行 第"+j+"列 的值为:"+jsonobj[i][j]);
						}
						html+="</tr>";
					} 
					html+="</table>";
					//$("#mytable").append(html);;
					//alert(html);
					document.getElementById('mytable').innerHTML=html;
					model(1,2);
				}
				function errorMsg() {
					alert("导入excel出错！");
				}
			}
		});
			//用户确认信息后提交Excel数据
		$("#insertclient").click(function(){
			var company = $("#company").val();
			var uploadType = $("#uploadType").val();
			if(uploadType==1){
			 $.ajax({
					url:"confirm",
			      	type:"post",
			      	crossDomain:true,
			      	async:true,
			      	dataType:"json",
			      	data:{company:company},
			      	success:function(data){
			      		if(data.data==1){
			      			window.location.href="beeList?pageNum=1";
			      		}else if(data.data==2){
			      			alert("上传出错,请检查是否选择了正确上传类型");
			      		}
			      	},
			      	error:function(){
			           alert("访问失败！！！");
			           return false;
			      	}
			    }); 
			}else{
				 $.ajax({
					url:"confirmPrivate",
			      	type:"post",
			      	crossDomain:true,
			      	async:true,
			      	dataType:"json",
			      	data:{company:company},
			      	success:function(data){
			      		if(data.data==1){
			      			window.location.href="beeList?pageNum=1";
			      		}
			      	},
			      	error:function(){
			           alert("访问失败！！！");
			           return false;
			      	}
			    }); 
			}
				//alert("点击了确认按钮");
				
			})
	});
	//调用模态框
	function model(comp, gra) {
		if (gra == 1 || gra == 2) {
			$('#myModal1').modal({
				keyboard : false
			})
		} else {
			company = comp;
			grade = gra;
			$('#myModal2').modal({
				keyboard : false
			})
		}

	}
	
	//JS校验form表单信息  
	function checkData() {
		var fileDir = $("#upfile").val();
		var suffix = fileDir.substr(fileDir.lastIndexOf("."));
		if ("" == fileDir) {
			alert("选择需要导入的Excel文件！");
			return false;
		}
		if (".xls" != suffix && ".xlsx" != suffix) {
			alert("选择Excel格式的文件导入！");
			return false;
		}
		return true;
	}
	$(function(){
		//获取公司信息
		$.ajax({
				url:"listCompany",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	//contentType: "application/json",
		      	//data: {company:company},
		      	success:function(data){
		      		 var lenth = data.data.length;
		      		 for(var i=0 ;i<=lenth;i++){
		      			$("#company").append("<option value='"+ data.data[i].company+"'>"+ data.data[i].company +"</option>");
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
	<div class="panel"
		style="width: 800px;height:600px;margin-left: 100px;margin-top: 10px">
		<div class="panel-heading">
			<h3 class="panel-title">导入集团客户</h3>
		</div>
		<div class="panel-body">
			</br>
			<form method="POST" enctype="multipart/form-data" id="form1"
				action="excel">
				<table>
					<tr>
						<td>上传文件:</td>
						<td><input id="upfile" type="file" name="excel"
							style="width: 453px"></td>
					</tr>
				</table>
				<br>
				<span>选择公司：</span>
				<select  id="company" class="input-md form-control" style="width: 200px">
					
				</select>
				<br>
				<span>类型：</span>
				<select  id="uploadType" class="input-md form-control" style="width: 200px">
					<option value="1">公对公</option>
					<option value="2">公拉私</option>
				</select>
				<input class="btn btn-primary" style="width:400px;margin-top: 20px" type="button" value="提交" id="btn" name="btn">
			</form>
		</div>
	</div>
			<!-- 展示导入数据 -->
			<!-- Button trigger modal -->
			<!-- 新增 -->
			<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content" style="width: 800px">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">确认用户信息</h4>
			      </div>
			      	<div id="mytable" class="modal-body">
			      	</div>
			      	<div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			        <button id="insertclient" type="button" class="btn btn-primary">确定</button>
			      </div>
			    </div>
			  </div>
			</div>
</body>

</html>
