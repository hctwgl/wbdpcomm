<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<head>
	<title>订单详情</title>
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
	<link rel="apple-touch-icon" sizes="76x76" href="img/login_logo_top.png">
	<link rel="icon" type="image/png" sizes="96x96" href="img/web_icon.png">
</head>

<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<!-- 顶部 -->
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="brand">
				<a href="index.html"><img src="assets/img/login_logo_top.png" alt="Klorofil Logo" class="img-responsive logo"></a>
			</div>
			<div class="container-fluid">
				<div class="navbar-btn">
					<!--箭头标识-->
					<!--<button type="button" class="btn-toggle-fullwidth"><i class="lnr lnr-arrow-left-circle"></i></button>-->
				</div>
				<form class="navbar-form navbar-left">
					<!--<div class="input-group">
						<input type="text" value="" class="form-control" placeholder="Search dashboard...">
						<span class="input-group-btn"><button type="button" class="btn btn-primary">Go</button></span>
					</div>-->
				</form>
				<div id="navbar-menu">
					<ul class="nav navbar-nav navbar-right">
						<!--用户头像以及一些操作-->
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><!--<img src="assets/img/user.png" class="img-circle" alt="Avatar">--> <span id="user">系统管理员1</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
							<ul class="dropdown-menu">
								<li><a href="#"><i class="lnr lnr-cog"></i> <span>个人中心</span></a></li>
								<li><a href="page-login.html"><i class="lnr lnr-exit"></i> <span>退出登录</span></a></li>
							</ul>
						</li>
						<!-- <li>
							<a class="update-pro" href="#downloads/klorofil-pro-bootstrap-admin-dashboard-template/?utm_source=klorofil&utm_medium=template&utm_campaign=KlorofilPro" title="Upgrade to Pro" target="_blank"><i class="fa fa-rocket"></i> <span>UPGRADE TO PRO</span></a>
						</li> -->
					</ul>
				</div>
			</div>
		</nav>
		<!-- END NAVBAR -->
		<!-- 左边导航栏 -->
		<div id="sidebar-nav" class="sidebar">
			<div class="sidebar-scroll">
				<nav>
					<ul class="nav">
						<li><a href="customer.jsp" class=""><i class="lnr lnr-dice"></i> <span>客户管理</span></a></li>
						<li><a href="goods.jsp" class=""><i class="lnr lnr-text-format"></i> <span>商品管理</span></a></li>
						<li><a href="customerblacklist.jsp" class=""><i class="lnr lnr-text-format"></i> <span>客户黑名单管理</span></a></li>
						<li><a href="order.jsp" class="active"><i class="lnr lnr-text-format"></i> <span>订单管理</span></a></li>
					</ul>
				</nav>
			</div>
		</div>
		<!--<iframe src="left.html" scrolling="no"></iframe>-->
		<!--<iframe src="left.html" scrolling="no"></iframe>-->
		<!-- END LEFT SIDEBAR -->
		<!-- MAIN -->
		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<h2 class="" style="margin-top:20px ; display: block; float: left;">订单详情</h2>
				<form class="navbar-form navbar-right">
					<div class="input-group" style="float: right;">
						<input type="text" value="" class="form-control" placeholder="输入查询条件">
						<span class="input-group-btn"><button type="button" class="btn btn-primary">查询</button></span>
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
												<th>订单号</th>
												<th>商品名称</th>
												<th>数量</th>
												<th>价格</th>
												<th>时间</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>1</td>
												<td>6165165</td>
												<td>华为荣耀8</td>
												<td>10</td>
												<td>2000</td>
												<td>2017.07.07</td>
											</tr>
											<tr>
												<td>2</td>
												<td>6165165</td>
												<td>华为荣耀8</td>
												<td>10</td>
												<td>2000</td>
												<td>2017.07.07</td>
											</tr>
											<tr>
												<td>3</td>
												<td>6165165</td>
												<td>华为荣耀8</td>
												<td>10</td>
												<td>2000</td>
												<td>2017.07.07</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<!-- END BASIC TABLE -->
						</div>
					</div>
					<div class="row">
					</div>
				</div>
			</div>
			<!-- END MAIN CONTENT -->
		</div>
		<!-- END MAIN -->
	</div>
	<!-- END WRAPPER -->
	<!-- Javascript -->
	<script src="assets/vendor/jquery/jquery.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/scripts/klorofil-common.js"></script>
	<script src="myjs/customer.js"></script>
</body>

</html>
