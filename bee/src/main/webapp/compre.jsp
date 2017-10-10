<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="zh-CN">

<head>
	<title>公司集团套餐管理</title>
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
	function deletesale(id){
		if(confirm("确定删除业务员？")){
			$.ajax({
				url:"salemanDelete",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	data: {id:id},
		      	success:function(data){
		      		if(data.data==1){
		      			alert("删除成功");
		      			window.location.href="salemanList?pageNum=1";
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
	function updateSale(id,name,phone,saleManCard,operator,city){
		//alert("salemanupdate.jsp?id="+id+"&name="+name+"&phone="+phone+"&saleManCard="+saleManCard+"&operator="+operator+"&city="+city);
		window.location.href="selectSaleman?id="+id;
	}
	</script>
</head>

<body>
					<h2 class="" style="margin-top:20px ; display: block; float: left;">公司集团套餐管理</h2>
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
												<th>总金额</th>
												<th>手机数量</th>
												<th>期数</th>
												<th>客户经理</th>
												<th>公司</th>
												<th>时间</th>
											</tr>
										</thead>
										<tbody>
										  <c:forEach var="item" items="${data.data}" begin="0" end="${fn:length(data.data)}" varStatus="status">
										  	<tr>
										  		<td>${status.index+1}</td>
										  		<c:choose>
										  			<c:when test="${item.Price==null}">
										  				<td>暂无数据</td>
										  			</c:when>
										  			<c:otherwise>
										  				<td>${item.Price}</td>
										  			</c:otherwise>
										  		</c:choose>
										  		<c:choose>
										  			<c:when test="${item.PhoneNumber==null}">
										  				<td>暂无数据</td>
										  			</c:when>
										  			<c:otherwise>
										  				<td>${item.PhoneNumber}</td>
										  			</c:otherwise>
										  		</c:choose>
										  		<c:choose>
										  			<c:when test="${item.Periods==null}">
										  				<td>暂无数据</td>
										  			</c:when>
										  			<c:otherwise>
										  				<td>${item.Periods}</td>
										  			</c:otherwise>
										  		</c:choose>
										  		<c:choose>
										  			<c:when test="${item.Manager==null}">
										  				<td>暂无数据</td>
										  			</c:when>
										  			<c:otherwise>
										  				<td>${item.Manager}</td>
										  			</c:otherwise>
										  		</c:choose>
										  		<c:choose>
										  			<c:when test="${item.Company==null}">
										  				<td>暂无数据</td>
										  			</c:when>
										  			<c:otherwise>
										  				<td>${item.Company}</td>
										  			</c:otherwise>
										  		</c:choose>
										  		<c:choose>
										  			<c:when test="${item.CreateDate==null}">
										  				<td>暂无数据</td>
										  			</c:when>
										  			<c:otherwise>
										  				<td>${item.CreateDate}</td>
										  			</c:otherwise>
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
							<div class="navbar-form navbar-left">
							<c:choose>
								<c:when test="${sessionScope.userType==3}">
								 <a href="download?fileName=beezonghe.xlsx"  class="btn btn-primary">下载集团套餐模板</a>
								<a href="zongheimport.jsp"  class="btn btn-primary">导入集团套餐数据</a> 
								<a href="addCompre.jsp"  class="btn btn-primary">添加集团套餐</a>
								</c:when>
							</c:choose>
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
						    		<a href="compreList?pageNum=${sessionScope.pageNow-1}" aria-label="Previous">
						        		<span aria-hidden="true">&laquo;</span>
						      		</a>
						    	</c:when>
						    </c:choose>
						    </li>
						    <c:forEach var="item" begin="1" end="${sessionScope.pages}">
						    	<li><a href="compreList?pageNum=${item}">${item}</a></li>
						    </c:forEach>
						    <li>
						    <c:choose>
						    	<c:when test="${sessionScope.pageNow==sessionScope.pages}">
						    	
						    		
						    	</c:when>
						    	<c:when test="${sessionScope.pageNow<=sessionScope.pages}">
						    		<a href="compreList?pageNum=${sessionScope.pageNow+1}" aria-label="Previous">
						        		<span aria-hidden="true">&raquo;</span>
						      		</a>
						    	</c:when>
						    </c:choose>
						    </li>
						   <%--  &nbsp<li><span>第${sessionScope.pageNow}页</span></li> --%>
						  </ul>
						</nav>
					</div>
							</div>
						</div>
					</div>
					
</body>

</html>
