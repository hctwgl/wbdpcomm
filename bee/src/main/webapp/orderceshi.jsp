<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="zh-CN">

<head>
	<title>订单管理</title>
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

	//上传征信
	function upload(id,money){
		  $.ajax({
			url:"",
	      	type:"post",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {beeid:id,money:money},
	      	success:function(data){
	      	if(data.data===1){
	      	alert("上传成功");
	      		window.location.href="beeList?pageNum=1";
	      	}else{
	      	alert("拉黑失败，请仔细检查您提交的资料")
	      	}	
	      	},
	      	error:function(){
	           alert("访问失败，请仔细检查您提交的资料!!!");
	           return false;
	      	}
	    }); 
		
	}
</script>
</head>

<body>
					<h2 class="" style="margin-top:20px ; display: block; float: left;">订单管理</h2>
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
												<th>客户</th>
												<th>手机号</th>
												<th>下单时间</th>
												<th>商品</th>
												<th>订单金额</th>
												<th>期数</th>
												<th>业务员推荐码</th>
												<th>状态</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
										  <c:forEach var="item" items="${data}" begin="0" end="${fn:length(data)}" varStatus="status">
										  <tr>
											<td>${status.index+1}</td>
												<c:choose>
													<c:when test="${item.bee.beename==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.bee.beename}</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${item.bee.phone==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.bee.phone}</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${item.orderDate==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.orderDate}</td>
													</c:otherwise>
												</c:choose>
												
												<td>
													${item.goods.goodsname }
												</td>
												<c:choose>
													<c:when test="${item.money==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.money}</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${item.periods==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.periods}</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${item.salesManCode==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.salesManCode}</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${item.orderState==null}">
														<td>暂无数据</td>
													</c:when>
													<c:when test="${item.orderState==0}">
														<td>未审核</td>
													</c:when>
													<c:when test="${item.orderState==1}">
														<td>已通过</td>
													</c:when>
													<c:when test="${item.orderState==2}">
														<td>审核未通过</td>
													</c:when>
												</c:choose>
												<c:choose>
													<c:when test="${item.orderState==null}">
														<td><button onclick="upload(${item.bee.id},${item.money})" type="button" class="btn btn-primary btn-xs" style="background-color:#449d44; border:1px solid #398439">上传</button></td>
													</c:when>
													<c:when test="${item.orderState==0}">
														<td>
														<button onclick="upload(${item.bee.id},${item.money})" type="button" class="btn btn-primary btn-xs" style="background-color:#449d44; border:1px solid #398439">上传</button>
														<a href="reviewOrder?beeid=${item.bee.id}&id=${item.id}"  class="btn btn-primary btn-xs" style="background-color:#449d44; border:1px solid #398439">审核</a>	
														</td>
													</c:when>
													<c:when test="${item.orderState==1}">
														<td><button onclick="upload(${item.bee.id},${item.money})" type="button" class="btn btn-primary btn-xs" style="background-color:#449d44; border:1px solid #398439">上传</button></td>
													</c:when>
													<c:when test="${item.orderState==2}">
														<td><button onclick="upload(${item.bee.id},${item.money})" type="button" class="btn btn-primary btn-xs" style="background-color:#449d44; border:1px solid #398439">上传</button></td>
													</c:when>
												</c:choose>
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
						    		<a href="listOrder?pageNum=${pageNow-1}" aria-label="Previous">
						        		<span aria-hidden="true">&laquo;</span>
						      		</a>
						    	</c:when>
						    </c:choose>
						    </li>
						    <c:forEach var="item" begin="1" end="${pages}">
						    	<li><a href="listOrder?pageNum=${item}">${item}</a></li>
						    </c:forEach>
						    <li>
						    <c:choose>
						    	<c:when test="${pageNow==pages}">
						    	
						    		
						    	</c:when>
						    	<c:when test="${pageNow<=pages}">
						    		<a href="listOrder?pageNum=${pageNow+1}" aria-label="Previous">
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
