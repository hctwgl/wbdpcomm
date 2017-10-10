<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="zh-CN">

<head>
	<title>供应商管理</title>
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
	function deletesup(id){
		if(confirm("确定删除供应商？")){
			$.ajax({
				url:"supplierDelete",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	data: {id:id},
		      	success:function(data){
		      		if(data.data==1){
		      			alert("删除成功");
		      			window.location.href="supplierList?pageNum=1";
		      		}else{
		      			alert("删除失败，请重试！！");
		      		}
		      	},
		      	error:function(){
		           alert("访问失败！！！");
		           return false;
		      	}
	    }); 
		}else{
		}
	}
	 function updatesup(id,supplier,contacts,phone,email,address,city){
	 	alert("点击了按钮");
	 }
	</script>
	<script type="text/javascript">
	</script>
</head>

<body>
					<h2 class="" style="margin-top:20px ; display: block; float: left;">供应商管理</h2>
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
												<th>供应商</th>
												<th>联系人</th>
												<th>联系电话</th>
												<th>邮箱</th>
												<th>地址</th>
												<th>操作</th> 
											</tr>
										</thead>
										<tbody>
										 <c:forEach var="item" items="${data}" varStatus="status" begin="0" end="${fn:length(data)}">
											<tr>
												<td>${status.index+1}</td>
												<td>${item.supplier}</td> 
												<c:choose>
													<c:when test="${item.contacts==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.contacts}</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${item.phone==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.phone}</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${item.email==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.email}</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${item.address==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.address}</td>
													</c:otherwise>
												</c:choose>
												 		<td>
												 			<button id="${item.id}"  onclick="deletesup(this.id)" type="button" class="btn btn-primary btn-xs" style="background-color:#D9534F; border:1px solid #D43F3A">删除</button>
												 			<a  href="supplierupdate.jsp?id=${item.id}&sup=${item.supplier}&cont=${item.contacts}&phone=${item.phone}&email=${item.email}&address=${item.address}&city=${item.city}" 
												 			type="button" class="btn btn-success btn-xs">修改</a>
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
						<a href="supplieradd.jsp"  class="btn btn-primary">新增供应商</a>
					</div>
					<!--分页组件  -->
					<!-- <div class="M-box"></div> -->
					<!--分页组件-->
					<div style=" margin-left: 600px;margin-bottom: 50px">
						<nav aria-label="Page navigation">
						  <ul class="pagination">
						    <li>
						    <c:choose>
						    	<c:when test="${sessionScope.pageNow==1}">
						    		
						    	</c:when>
						    	<c:when test="${sessionScope.pageNow!=1}">
						    		<a href="supplierList?pageNum=${sessionScope.pageNow-1}" aria-label="Previous">
						        		<span aria-hidden="true">&laquo;</span>
						      		</a>
						    	</c:when>
						    </c:choose>
						    </li>
						    <c:forEach var="item" begin="1" end="${pages}" >
						    	<li><a href="supplierList?pageNum=${item}">${item}</a></li>
						    </c:forEach>
						    <li>
						    <c:choose>
						    	<c:when test="${pageNow==pages}">
						    	
						    		
						    	</c:when>
						    	<c:when test="${sessionScope.pageNow<=pages}">
						    		<a href="supplierList?pageNum=${sessionScope.pageNow+1}" aria-label="Previous">
						        		<span aria-hidden="true">&raquo;</span>
						      		</a>
						    	</c:when>
						    </c:choose>
						    </li>
						    &nbsp<li><span>第${sessionScope.pageNow}页</span></li>
						  </ul>
						</nav>
					</div>
							</div>
						</div>
					</div>
					
</body>

</html>
