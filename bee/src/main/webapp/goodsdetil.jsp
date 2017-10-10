<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-CN">

<head>
	<title>商品详情</title>
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
</head>

<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<!-- 头部 -->
		<!-- <nav class="navbar navbar-default navbar-fixed-top">
			<div class="brand">
				<a href="index.html"><img src="assets/img/login_logo_top.png" alt="Klorofil Logo" class="img-responsive logo"></a>
			</div>
			<div class="container-fluid">
				<div class="navbar-btn">
					箭头标识
					<button type="button" class="btn-toggle-fullwidth"><i class="lnr lnr-arrow-left-circle"></i></button>
				</div>
				<form class="navbar-form navbar-left">
					<div class="input-group">
						<input type="text" value="" class="form-control" placeholder="Search dashboard...">
						<span class="input-group-btn"><button type="button" class="btn btn-primary">Go</button></span>
					</div>
				</form>
				<div id="navbar-menu">
					<ul class="nav navbar-nav navbar-right">
						用户头像以及一些操作
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="assets/img/user.png" class="img-circle" alt="Avatar"> <span id="user">系统管理员1</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
							<ul class="dropdown-menu">
								<li><a href="#"><i class="lnr lnr-cog"></i> <span>个人中心</span></a></li>
								<li><a href="page-login.html"><i class="lnr lnr-exit"></i> <span>退出登录</span></a></li>
							</ul>
						</li>
						<li>
							<a class="update-pro" href="#downloads/klorofil-pro-bootstrap-admin-dashboard-template/?utm_source=klorofil&utm_medium=template&utm_campaign=KlorofilPro" title="Upgrade to Pro" target="_blank"><i class="fa fa-rocket"></i> <span>UPGRADE TO PRO</span></a>
						</li>
					</ul>
				</div>
			</div>
		</nav> -->
		<jsp:include page="top.jsp" flush="true" />
		<!-- END NAVBAR -->
		<!-- LEFT SIDEBAR -->
		<!-- <div id="sidebar-nav" class="sidebar">
			<div class="sidebar-scroll">
				<nav>
					<ul class="nav">
						<li><a href="customer.jsp" class=""><i class="lnr lnr-dice"></i> <span>客户管理</span></a></li>
						<li><a href="goods.jsp" class="active"><i class="lnr lnr-text-format"></i> <span>商品管理</span></a></li>
						<li><a href="customerblacklist.jsp" class=""><i class="lnr lnr-text-format"></i> <span>客户黑名单管理</span></a></li>
						<li><a href="order.jsp" class=""><i class="lnr lnr-text-format"></i> <span>订单管理</span></a></li>
					</ul>
				</nav>
			</div>
		</div> -->
		<jsp:include page="left.jsp" flush="true" />
		<!-- END LEFT SIDEBAR -->
		<!-- MAIN -->
		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<h3 class="page-title">李娜（13714318834）</h3>
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
											<tr>
												<td>1</td>
												<td>广东省深圳市龙岗区龙平街道城投商务中心903</td>
												<td>公司地址</td>
											</tr>
											<tr>
												<td>1</td>
												<td>广东省深圳市龙岗区龙平街道城投商务中心903</td>
												<td>家庭地址</td>
											</tr>
											<tr>
												<td>1</td>
												<td>广东省深圳市龙岗区龙平街道城投商务中心903</td>
												<td>收货地址</td>
											</tr>
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
											<tr>
												<td>1</td>
												<td>李娜</td>
												<td>同事</td>
												<td>110</td>
											</tr>
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
											<tr>
												<td>1</td>
												<td>浦发银行</td>
												<td>浦发银行龙城支行</td>
												<td>16516516165161616191e</td>
											</tr>
											<tr>
												<td>2</td>
												<td>浦发银行</td>
												<td>浦发银行龙城支行</td>
												<td>16516516165161616191e</td>
											</tr>
											<tr>
												<td>3</td>
												<td>浦发银行</td>
												<td>浦发银行龙城支行</td>
												<td>16516516165161616191e</td>
											</tr>
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
											<tr>
												<td>1</td>
												<td>6165165165</td>
												<td>500</td>
												<td>5000</td>
												<td>维泽数据</td>
												<td>5</td>
												<td>正常</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<!-- END TABLE HOVER -->
						</div>
					</div>
					<div class="row">
					</div>
				</div>
			</div>
			<!-- END MAIN CONTENT -->
		</div>
		<!-- END MAIN -->
		<div class="clearfix"></div>
		<footer>
			<div class="container-fluid">
				<p class="copyright">&copy; 2017 <a href="#" target="_blank">Theme I Need</a>. All Rights Reserved. More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>
			</div>
		</footer>
	</div>
	<!-- END WRAPPER -->
	<!-- Javascript -->
	<script src="assets/vendor/jquery/jquery.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/scripts/klorofil-common.js"></script>
</body>

</html>
