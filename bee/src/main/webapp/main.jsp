<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="zh-CN">

<head>
	<title>蜜蜂花易后台管理系统</title>
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
		<!--引入我的js与css文件-->
	<script src="js/jquery.min.js"></script>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<script src="js/bootstrap.min.js"></script>
	<!-- GOOGLE FONTS -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
	<!--引入icon图标css文件-->
	<link rel="stylesheet" href="iconfont_wisebee/iconfont.css">
	<!-- ICONS -->
	<link rel="apple-touch-icon" sizes="76x76" href="img/login_logo_top.png">
	<link rel="icon" type="image/png" sizes="96x96" href="img/yifenqis.png">
	<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/scripts/klorofil-common.js"></script>
	<script src="myjs/customer.js"></script>
</head>
<script type="text/javascript">
	$(function(){  
        	
    });  
	$(function(){
			var bwidth = $("body").width();
       		var bheight = $("body").height();
       		var lwidth = $("#sidebar-nav").width();
       		var lheight = $("#sidebar-nav").height();
       		var theight = $("#mytop").height();
       		var twidth = $("#mytop").width();
       		$("#myiframe").width(twidth-lwidth);
      		$("#myiframe").height(lheight-theight+73);
	/*  var width = $(".main").width();
         var height = $(".main").height();
      // $("#mainIframe").height(height);
       $("#mainIframe").width(width);
        $("#mainIframe").height(height); */
	/* $("#customerceshi").click(function(){
		 var aclass = $("a").attr("class");
  		 var width = $(".main-content").width();
         var height = $(".main-content").height();
      // $("#mainIframe").height(height);
       $("#mainIframe").width(width);
	});
	$("#goodsceshi").click(function(){
		 var aclass = $("a").attr("class");
  		 var width = $(".main-content").width();
         var height = $(".main-content").height();
      // $("#mainIframe").height(height);
       $("#mainIframe").width(width);
	});
	$("#orderceshi").click(function(){
		 var aclass = $("a").attr("class");
  		 var width = $(".main-content").width();
         var height = $(".main-content").height();
      // $("#mainIframe").height(height);
       $("#mainIframe").width(width);
	});
	$("#customerblacklistceshi").click(function(){
		 var aclass = $("a").attr("class");
  		 var width = $(".main-content").width();
         var height = $(".main-content").height();
      // $("#mainIframe").height(height);
       $("#mainIframe").width(width);
	}); */
		/* alert("页面初始化");
		$("a").click(func)
		 var width = $(".main-content").width();
         var height = $(".main-content").height();
       $(".mainIframe").height(height);
       $(".mainIframe").width(width);
       alert(height); 
       alert(width);  */
       /*  setInterval(review,300000);
       //定时刷新获取订单未审核消息
		  function review(){
		   $.ajax({
			url:"brushCount",
	      	type:"get",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	success:function(data){
	      	if(data!=0){
	      		$("#notice").html(data);
	      	}else{
	      		$("#notice").html("");
	      	}	
	      	},
	      	error:function(){
	           alert("定时刷新获取审核信息失败!!!");
	           return false;
	      	}
	    }); 
		 }   */
	});
</script>
<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<!-- 顶部 -->
		<nav class="navbar navbar-default navbar-fixed-top" id="mytop">
			<div class="brand">
				<a href="javascript:void(0)"><img src="img/72441795853177167.png" alt="Klorofil Logo" class="img-responsive logo"></a>
			</div>
			<div class="container-fluid">
				<div class="navbar-btn">
					<!--箭头标识-->
					<!--<button type="button" class="btn-toggle-fullwidth"><i class="lnr lnr-arrow-left-circle"></i></button>-->
				</div>
				
				<div id="navbar-menu">
					 <ul class="nav navbar-nav navbar-right">
					 <li class="dropdown">
							<!-- <a href="brushListWait?pageNum=1" onclick="clean()" target="myIframe"  >
								<i class="lnr lnr-alarm"></i>
								<span id="notice" class="badge bg-danger" style="background-color: red"></span>
							</a> -->
						</li>
						<!-- 用户头像以及一些操作 -->
						<li class="dropdown">
							<a href="#"  class="dropdown-toggle" data-toggle="dropdown"><!-- <img src="assets/img/user.png" class="img-circle" alt="Avatar"> --> <span id="user">${sessionScope.username}</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
							<ul class="dropdown-menu">
								
								<li><a href="logout"><i class="lnr lnr-exit"></i> <span>退出登录</span></a></li>
							</ul>
						</li>
						<!-- <li>
							<a class="update-pro" href="#downloads/klorofil-pro-bootstrap-admin-dashboard-template/?utm_source=klorofil&utm_medium=template&utm_campaign=KlorofilPro" title="Upgrade to Pro" target="_blank"><i class="fa fa-rocket"></i> <span>UPGRADE TO PRO</span></a>
						</li> -->
					</ul>
					</div>
				</div>
		</nav>
		<!--引入顶部  -->
		<!-- END NAVBAR -->
		<!-- 左边导航栏 -->
		 <div id="sidebar-nav" class="sidebar">
			<div class="sidebar-scroll">
				<nav>
					<ul id="authmodel" class="nav">
						<!-- <li><a href="beeList?pageNum=1" target="myIframe" id="customerceshi" class=""><i class="iconfont icon-customer"></i> <span>客户管理</span></a></li> -->
						<!-- <li><a href="goodslist?pageNum=1" target="myIframe" id="goodsceshi" class=""><i class="iconfont icon-cf-c45"></i> <span>商品管理</span></a></li> -->
						<!-- <li>
							<a href="#subPages" data-toggle="collapse" class="collapsed"><i class="iconfont icon-cf-c45"></i> <span>商品管理</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="subPages" class="collapse ">
								<ul class="nav">
									<li><a href="goodslist?pageNum=1" target="myIframe" id="goodsceshi" class=""><i class=""></i> <span>手机</span></a></li>
									<li><a href="goodslist?pageNum=1" target="myIframe" id="goodsceshi" class=""><i class=""></i> <span>话费</span></a></li>
									<li><a href="goodslist?pageNum=1" target="myIframe" id="goodsceshi" class=""><i class=""></i> <span>流量卡</span></a></li>
									<li><a href="goodslist?pageNum=1" target="myIframe" id="goodsceshi" class=""><i class=""></i> <span>维泽车宝</span></a></li>
									<li><a href="goodslist?pageNum=1" target="myIframe" id="goodsceshi" class=""><i class=""></i> <span>行车记录仪</span></a></li>
									<li><a href="goodslist?pageNum=1" target="myIframe" id="goodsceshi" class=""><i class=""></i> <span>维泽WiFi</span></a></li>
									<li><a href="goodslist?pageNum=1" target="myIframe" id="goodsceshi" class=""><i class=""></i> <span>加油卡</span></a></li>
								</ul>
							</div>
						</li> -->
							<c:forEach var="item" items="${data}">
								<li><a href="${item.modelUrl}"  target="myIframe" class=""><i class="${item.iconFont}"></i> <span>${item.modelName}</span></a></li>
							</c:forEach>
							<li><a href="resetpassword.jsp" target="myIframe"><i class="lnr lnr-cog"></i> <span>重置密码</span></a></li> 
							<!-- <li><a href="blackbeeAllList?pageNum=1"  target="myIframe" class=""><i class="iconfont icon-heimingdanshezhi"></i> <span>客户黑名单管理</span></a></li>
							<li><a href="listOrder?pageNum=1" target="myIframe"  class=""><i class="iconfont icon-dingdan3"></i> <span>订单管理</span></a></li>
							<li><a href="listShoping?pageNum=1" target="myIframe"  class=""><i class="iconfont icon-gm-gouwuche"></i><span>购物车管理</span></a></li> 
						    <li><a href="salemanList?pageNum=1" target="myIframe"  class=""><i class="iconfont icon-userpro"></i><span>业务员管理</span></a></li> 
							<li><a href="supplierList?pageNum=1" target="myIframe" id="orderceshi" class=""><i class="iconfont icon-yunyingshangfenbu2"></i> <span>供应商管理</span></a></li>
						    <li><a href="listBeeCompGrade?pageNum=1" target="myIframe"  class=""><i class="iconfont icon-dingdan3"></i> <span>公司信用管理</span></a></li> 
							<li><a href="companyblacklist?pageNum=1" target="myIframe"  class=""><i class="iconfont icon-waibuheimingdan"></i> <span>公司黑名单管理</span></a></li> 
							<li><a href="listPackage?pageNum=1" target="myIframe"  class=""><i class="iconfont icon-xiangmu"></i><span>套餐管理</span></a></li>
							<li><a href="userList?pageNum=1" target="myIframe"  class=""><i class="iconfont icon-yewurenyuanxinxiguanli"></i><span>系统用户管理</span></a></li> -->
					</ul>
				</nav>
			</div>
		</div> 
		<!--引入左部菜单栏  -->
		<!--<iframe src="left.html" scrolling="no"></iframe>-->
		<!--<iframe src="left.html" scrolling="no"></iframe>-->
		<!-- END LEFT SIDEBAR -->
		<!-- MAIN -->
		 <div class="main">
			<div class="main-content" style="padding-bottom: 10px;min-height: 600px">
				<div class="container-fluid" style="min-height: 600px"> 
				<!--引入中部内容  -->
				<c:choose>
					<c:when test="${sessionScope.userType==1}">
						<iframe id="myiframe" src="userList?pageNum=1" name="myIframe" scrolling="no" height="500px" border="0" frameborder="no"></iframe>
					</c:when>
					<c:otherwise>
						 <iframe id="myiframe" src="beeList?pageNum=1" name="myIframe" scrolling="no" height="500px" border="0" frameborder="no"></iframe>
					</c:otherwise>
				</c:choose>
				</div>
			</div>
			<!-- END MAIN CONTENT -->
		</div>
		<!-- END MAIN -->
	</div>
	<!-- END WRAPPER -->
	<!-- Javascript -->
	<!-- <script src="assets/vendor/jquery/jquery.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/scripts/klorofil-common.js"></script>
	<script src="myjs/customer.js"></script> -->
</body>

</html>
