<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="zh-CN">

<head>
	<title>客户管理</title>
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
	$(function(){

	});
	function detil(id){
		window.location.href="beeListOfID?pageNum=1&id="+id;
	}
	function pollen(id){
		$.ajax({
			url:"getPollenTest",
	      	type:"post",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {beeid:id},
	      	success:function(data){
	      	alert("该客户当前的试算额度为："+data.data.total);
	      	},
	      	error:function(){
	           alert("访问失败！！！");
	           return false;
	      	}
	    }); 
	}
	function black(id,phone,beename,beecard){
	if(confirm("确认拉黑该客户？")){
		$.ajax({
				url:"insertBlackbee",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	data: {beeid:id,phone:phone,beename:beename,beecard:beecard},
		      	success:function(data){
		      	if(data.data===1){
		      		alert("该客户已拉黑");
		      		window.location.href="beeList?pageNum=1";
		      	}else{
		      		alert("拉黑失败，请重试")
		      	}	
		      	},
		      	error:function(){
		           alert("访问失败，请仔细检查您提交的资料!!!");
		           return false;
		      	}
		    }); 
	}else{
		return false;
	}
	}
</script>
</head>

<body>
					<h2 class="" style="margin-top:20px ; display: block; float: left;">客户管理</h2>
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
												<th>客户编号</th>
												<th>客户姓名</th>
												<th>手机号</th>
												<th>身份证号码</th>
												<th>公司名称</th>
												<th>花粉信用</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
										  <c:forEach var="item" items="${data.data}" begin="0" end="${fn:length(data.data)}" varStatus="status">
											<tr>
												<td>${status.index+1}</td>
												<c:choose>
													<c:when test="${item.BeeName==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.BeeName}</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${item.Phone==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.Phone}</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${item.BeeCard==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.BeeCard}</td>
													</c:otherwise>
												</c:choose>
												<%-- <c:forEach var="social" items="${item.socialdataList}"> --%>
												<c:choose>
													<c:when test="${item.Company==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.Company}</td>
													</c:otherwise>
												</c:choose>
												<%-- </c:forEach> --%>
												<%-- <c:forEach var="pollen" items="${data.data.pollenList}">
													<td>${pollen.company}</td>
												</c:forEach> --%>
												<td>${item.CreditLimit}</td>
												<td>
													<button  onclick="pollen(${item.ID})" type="button" class="btn btn-primary btn-xs" style="background-color:#D9534F; border:1px solid #D43F3A">额度</button>
													<button  onclick="black(${item.ID},${item.Phone} ,'${item.BeeName}','${item.BeeCard}')" type="button" class="btn btn-primary btn-xs" style="background-color:#D9534F; border:1px solid #D43F3A">拉黑</button>
													<button  onclick="detil(${item.ID})" type="button" class="btn btn-primary btn-xs" style="background-color:#449d44; border:1px solid #398439">详情</button>
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
								<a href="clientimport.jsp"  class="btn btn-primary">导入集团客户</a>
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
						    		<a href="beeList?pageNum=${sessionScope.pageNow-1}" aria-label="Previous">
						        		<span aria-hidden="true">&laquo;</span>
						      		</a>
						    	</c:when>
						    </c:choose>
						    </li>
						    <c:forEach var="item" begin="1" end="${sessionScope.pages}" >
						    	<li><a href="beeList?pageNum=${item}">${item}</a></li>
						    </c:forEach>
						    <li>
						    <c:choose>
						    	<c:when test="${sessionScope.pageNow==sessionScope.pages}">
						    	
						    		
						    	</c:when>
						    	<c:when test="${sessionScope.pageNow<=sessionScope.pages}">
						    		<a href="beeList?pageNum=${sessionScope.pageNow+1}" aria-label="Previous">
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
