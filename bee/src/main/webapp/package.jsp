<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="zh-CN">

<head>
	<title>套餐管理</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<!-- VENDOR CSS -->
	<link rel="stylesheet" href="${basePath }assets/vendor/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
	<!-- MAIN CSS -->
	<link rel="stylesheet" href="assets/css/main.css">
	<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
	<link rel="stylesheet" href="assets/css/demo.css">
	<!-- GOOGLE FONTS -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
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
	<script type="text/javascript">
	$(function(){

	});
	function detil(id){
		window.location.href="beeList?pageNum=1&id="+id;
	}
	//删除套餐
	function deletePac(id){
		if(confirm("确认删除该套餐？")){
			$.ajax({
					url:"deletePackage",
			      	type:"post",
			      	crossDomain:true,
			      	async:true,
			      	dataType:"json",
			      	contentType: "application/json",
			      	data: JSON.stringify({id:id}),
			      	success:function(data){
			      		if(data.data==1){
			      			window.location.href="listPackage?pageNum=1";
			      		}else{
			      			alert("删除失败，请重试！！");
			      		}
			      	},
			      	error:function(){
			           alert("访问失败！！！");
			           return false;
			      	}
			    }); 
		}
	}
	//修改套餐
	function updatePac(id,callPackage,supplier,supid,details){
			$.ajax({
					url:"packageupdate.jsp",
			      	type:"post",
			      	crossDomain:true,
			      	async:true,
			      	/* dataType:"json",
			      	contentType: "application/json", */
			      	data: JSON.stringify({id:id,callPackage:callPackage,supplier:supplier,supid:supid,details:details}),
			      	success:function(data){
			      		if(data.data==1){
			      			window.location.href="listPackage?pageNum=1";
			      		}else{
			      			alert("删除失败，请重试！！");
			      		}
			      	},
			      	error:function(){
			           alert("访问失败！！！");
			           return false;
			      	}
			    }); 
	}
</script>
</head>

<body>
					<h2 class="" style="margin-top:20px ; display: block; float: left;">套餐管理</h2>
				<form class="navbar-form navbar-right">
					<div class="input-group" style="float: right;margin-right: 100px;">
						<!-- <input type="text" value="" class="form-control" placeholder="输入查询条件">
						<span class="input-group-btn"><button type="button" class="btn btn-primary" >查询</button></span> -->
					</div>
				</form>
					<div class="row">
							<div class="col-md-6" style="width: 100%;">
							<!-- BASIC TABLE -->
							<div class="panel">
								<!--<div class="panel-heading">
									<h3 class="panel-title">Basic Table</h3>
								</div>-->
								<div class="panel-body" style="margin-top: 20px;">
									<table class="table">
										<thead>
											<tr>
												<th>编号</th>
												<th>套餐</th>
												<th>供应商</th>
												<th>详情</th>
												<th>时间</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
										 	<c:forEach var="item" items="${data}" varStatus="status" begin="0" end="${fn:length(data)}">
											<tr>
												<td>${status.index+1}</td>
												<td>${item.callPackage}</td>
												<td>${item.supplier.supplier}</td>
												<td>${item.details}</td>
												<td>${item.createDate}</td>
												<td>
						<button  onclick="deletePac(${item.id})" type="button" class="btn btn-primary btn-xs" style="background-color:#D9534F; border:1px solid #D43F3A">删除</button>
						<a href="packageupdate.jsp?id=${item.id}&callPackage=${item.callPackage}&supplier=${item.supplier.supplier}&supid=${item.supplier.id}&details=${item.details}" class="btn btn-primary btn-xs">修改</a>
												</td>
											</tr>
										</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<!-- END BASIC TABLE -->
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<!-- BASIC TABLE -->
							<div class="panel">
							<div class="navbar-form navbar-left">
								<a href="packageadd.jsp"  class="btn btn-primary">新增套餐</a>
								<!-- <a href="packageupload.jsp"  class="btn btn-primary">导入套餐</a> -->
							</div>
					<!--分页组件  -->
					<!-- <div class="M-box"></div> -->
					<!--分页组件-->
					<div style=" margin-left: 600px;margin-bottom: 50px">
						<nav aria-label="Page navigation">
						  <ul class="pagination">
						    <li>
						    <c:choose>
						    	<c:when test="${pageNow==1}">
						    		
						    	</c:when>
						    	<c:when test="${pageNow!=1}">
						    		<a href="listPackage?pageNum=${pageNow-1}" aria-label="Previous">
						        		<span aria-hidden="true">&laquo;</span>
						      		</a>
						    	</c:when>
						    </c:choose>
						    </li>
						    <c:forEach var="item" begin="1" end="${pages}">
						    	<li><a href="listPackage?pageNum=${item}">${item}</a></li>
						    </c:forEach>
						    <li>
						    <c:choose>
						    	<c:when test="${pageNow==pages}">
						    	
						    		
						    	</c:when>
						    	<c:when test="${pageNow<=pages}">
						    		<a href="listPackage?pageNum=${pageNow+1}" aria-label="Previous">
						        		<span aria-hidden="true">&raquo;</span>
						      		</a>
						    	</c:when>
						    </c:choose>
						    </li>
						    &nbsp<li><span>第${pageNow}页</span></li>
						  </ul>
						</nav>
					</div>
							</div>
						</div>
					</div>
					
</body>

</html>
