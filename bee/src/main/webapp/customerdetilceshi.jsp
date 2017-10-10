<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="zh-CN">

<head>
	<title>客户详情</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<!-- VENDOR CSS -->
	<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
	<!-- MAIN CSS -->
	<link rel="stylesheet" href="assets/css/main.css">
	<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
	<link rel="stylesheet" href="assets/css/demo.css">
	<!-- GOOGLE FONTS -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
	<!-- ICONS -->
	<link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">
		<script src="assets/vendor/jquery/jquery.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/scripts/klorofil-common.js"></script>
</head>

<body>
				<c:forEach var="item" items="${data.data}">
					<h3 class="page-title">${item.beename}(${item.phone})</h3>
				</c:forEach>
					<div class="row">
						<div class="col-md-6">
							<!-- BASIC TABLE -->
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">常用地址</h3>
								</div>
								<div class="panel-body">
									<table class="table">
										<thead>
											<tr>
												<th>编号</th>
												<th>详细地址</th>
												<th>类型</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach var="item" items="${data.data}" varStatus="status">
											<c:forEach var="item" items="${item.beeaddressList}" varStatus="status">
												<tr>
													<td>${status.index+1}</td>
													<td>${item.province}${item.city}${item.area}${item.detaddress}</td>
													<c:choose>
														<c:when test="${item.type==0}">
															<td>公司地址</td>
														</c:when>
														<c:when test="${item.type==1}">
															<td>家庭地址</td>
														</c:when>
														<c:when test="${item.type==2}">
															<td>收货地址</td>
														</c:when>
													</c:choose>
													
												</tr>
											</c:forEach>
										</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<!-- END BASIC TABLE -->
						</div>
						<div class="col-md-6">
							<!-- TABLE NO PADDING -->
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">常用联系人</h3>
								</div>
								<div class="panel-body">
									<table class="table">
										<thead>
											<tr>
												<th>编号</th>
												<th>联系人姓名</th>
												<th>关系</th>
												<th>手机号</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach var="item" items="${data.data}" varStatus="status">
											<c:forEach var="item" items="${item.linkmanList}" varStatus="status">
												<tr>
													<td>${status.index+1}</td>
													<td>${item.linkmanname}</td>
													<td>${item.relation}</td>
													<td>${item.phone}</td>
												</tr>
											</c:forEach>
										</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<!-- END TABLE NO PADDING -->
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<!-- TABLE STRIPED -->
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">银行卡</h3>
								</div>
								<div class="panel-body">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>编号</th>
												<th>银行名称</th>
												<th>开户行</th>
												<th>银行卡号</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach var="item" items="${data.data}" varStatus="status">
											<c:forEach var="item" items="${item.bankcardList}" varStatus="status">
												<tr>
													<td>${status.index+1}</td>
													<td>${item.bank}</td>
													<td>${item.openbank}</td>
													<td>${item.bankcard}</td>
												</tr>
											</c:forEach>
										</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<!-- END TABLE STRIPED -->
						</div>
						<div class="col-md-6">
							<!-- TABLE HOVER -->
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">社保资料</h3>
								</div>
								<div class="panel-body">
									<table class="table table-hover">
										<thead>
											<tr>
												<th>编号</th>
												<th>社保账号</th>
												<th>缴纳基数（元）</th>
												<th>余额（元）</th>
												<th>单位</th>
												<th>持续时间（月）</th>
												<th>社保账号状态</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach var="item" items="${data.data}" varStatus="status">
											<c:forEach var="item" items="${item.socialdataList}" varStatus="status">
												<tr>
													<td>${status.index+1}</td>
													<td>${item.socialaccount}</td>
													<td>${item.base}</td>
													<td>${item.balance}</td>
													<td>${item.company}</td>
													<td>${item.paytime}</td>
													<c:choose>
														<c:when test="${item.socialstate==0}">
															<td>正常</td>
														</c:when>
														<c:when test="${item.socialstate==1}">
															<td>停缴</td>
														</c:when>
														<c:when test="${item.socialstate==2}">
															<td>销户</td>
														</c:when>
													</c:choose>
												</tr>
											</c:forEach>
										</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<!-- END TABLE HOVER -->
						</div>
					</div>
	</body>
</html>
