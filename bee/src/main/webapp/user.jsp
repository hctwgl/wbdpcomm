<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="zh-CN">

<head>
	<title>用户管理</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<!-- VENDOR CSS -->
	<link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.min.css">
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
	function clean(id){
		if(confirm("确认注销该用户？")){
			//修改用户
			$.ajax({
				url:"userInsertAndUpdate",
					      	type:"post",
					      	crossDomain:true,
					      	async:true,
					      	dataType:"json",
					      	data: {id:id,userstate:1,type:2},
					      	success:function(data){
					      	if(data.data===1){
					      		window.location.href="userList?pageNum=1";
					      	}else{
					      		alert("注销失败，请仔细检查您提交的资料");
					      	}	
					      	},
					      	error:function(){
					           alert("请求失败");
					      	}
			})
		}else{
			return false;
		}
		 
	}
	function recovery(id){
		if(confirm("确认恢复该用户？")){
			 //修改用户
			$.ajax({
				url:"userInsertAndUpdate",
					      	type:"post",
					      	crossDomain:true,
					      	async:true,
					      	dataType:"json",
					      	data: {id:id,userstate:0,type:2},
					      	success:function(data){
					      	if(data.data===1){
					      		window.location.href="userList?pageNum=1";
					      	}else{
					      		alert("注销失败，请仔细检查您提交的资料");
					      	}	
					      	},
					      	error:function(){
					           alert("请求失败");
					      	}
			})
		}else{
			return false;
		}
		
	}
</script>
</head>

<body>
					<h2 class="" style="margin-top:20px ; display: block; float: left;">用户管理</h2>
				<form class="navbar-form navbar-right">
					<div class="input-group" style="float: right;margin-right: 100px;">
						<!-- <input type="text" value="" class="form-control" placeholder="输入查询条件">
						<span class="input-group-btn"><button type="button" class="btn btn-primary" >查询</button></span> -->
					</div>
				</form>
					<div class="row">
							<div class="col-md-2" style="width: 100%;">
							<!-- BASIC TABLE -->
							<div class="panel">
								<!--<div class="panel-heading">
									<h3 class="panel-title">Basic Table</h3>
								</div>-->
								<div class="panel-body" style="margin-top: 20px;">
									<table class="table">
										<thead>
											<tr>
												<th>用户编号</th>
												<th>用户名</th>
												<th>联系电话</th>
												<th>所属公司</th>
												<th>用户类型</th>
												<th>用户状态</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
										  <c:forEach var="item" items="${data}" varStatus="status" begin="0" end="${fn:length(data)}">
											<tr>
												<td>${status.index+1}</td>
												<td>${item.username}</td>
												<td>${item.phone}</td>
												<td>${item.company}</td>
												<td>
													<c:choose >
														<c:when test="${item.usertype==1}">
															系统管理员
														</c:when>
														<c:when test="${item.usertype==2}">
															客服
														</c:when>
														<c:when test="${item.usertype==3}">
															业务员
														</c:when>
														<c:when test="${item.usertype==4}">
															业务员
														</c:when>
													</c:choose> 
												</td>
												<td>
													<c:choose >
														<c:when test="${item.userstate==0}">
															正常
														</c:when>
														<c:when test="${item.userstate==1}">
															注销
														</c:when>
													</c:choose> 
												</td>
												<td>
													<c:choose >
														<c:when test="${item.userstate==0}">
															<button id="${item.id}" value="${item.id}" onclick="clean(this.id)"  type="button" class="btn btn-primary btn-xs" style="background-color:#D9534F; border:1px solid #D43F3A">注销</button>
															 <a href="toUpdatePage?id=${item.id}"  class="btn btn-success btn-xs" style="background-color:#D9534F; border:1px solid #D43F3A">修改</a>
															<!--  <a href="adduser.jsp"  class="btn btn-success btn-xs" style="background-color:#D9534F; border:1px solid #D43F3A">重置密码</a>  -->
															 
														</c:when>
														<c:when test="${item.userstate==1}">
															<button id="${item.id}" value="${item.id}" onclick="recovery(this.id)"  type="button" class="btn btn-success btn-xs" style="background-color:#449d44; border:1px solid #398439">恢复</button>
														</c:when>
													</c:choose>
													<%-- <c:choose>
														<c:when test="${item.usertype!=1}">
															<a href="tousermodel?userID=${item.id}" class="btn btn-success btn-xs" style="background-color:#449d44; border:1px solid #398439">权限</a>
														</c:when>
													</c:choose> --%>
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
						<a href="adduser.jsp"  class="btn btn-primary" style="background-color: #00BFFF;border-color: #00BFFF">添加用户</a>
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
						    		<a href="userList?pageNum=${pageNow-1}" aria-label="Previous">
						        		<span aria-hidden="true">&laquo;</span>
						      		</a>
						    	</c:when>
						    </c:choose>
						    </li>
						    <c:forEach var="item" begin="1" end="${pages}" >
						    	<li><a href="userList?pageNum=${item}">${item}</a></li>
						    </c:forEach>
						    <li>
						    <c:choose>
						    	<c:when test="${pageNow==pages}">
						    	
						    		
						    	</c:when>
						    	<c:when test="${pageNow<=pages}">
						    		<a href="userList?pageNum=${pageNow+1}" aria-label="Previous">
						        		<span aria-hidden="true">&raquo;</span>
						      		</a>
						    	</c:when>
						    </c:choose>
						    </li>
						  <%--   &nbsp<li><span>第${pageNow}页</span></li> --%>
						  </ul>
						</nav>
					</div>
							</div>
						</div>
					</div>
					
</body>

</html>
