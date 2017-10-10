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
	function black(id){
	if(confirm("确认删除该客户？")){
		$.ajax({
				url:"delBee",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	data: {id:id},
		      	success:function(data){
		      	if(data.data===1){
		      		alert("删除成功");
		      		window.location.href="beeList?pageNum=1";
		      	}else{
		      		alert("删除失败，请重试")
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

												<th>可用额度</th>
												<th>客户类型</th>
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
												<%-- <c:choose>
													<c:when test="${item.Sex==null}">
														<td>暂无数据</td>
													</c:when>
													<c:when test="${item.Sex==0}">
														<td>女</td>
													</c:when>
													<c:when test="${item.Sex==1}">
														<td>男</td>
													</c:when>
												</c:choose> --%>
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
												<%-- <c:choose>
													<c:when test="${item.Marriage==null}">
														<td>暂无数据</td>
													</c:when>
													<c:when test="${item.Marriage==0}">
														<td>已婚</td>
													</c:when>
													<c:when test="${item.Marriage==1}">
														<td>未婚</td>
													</c:when>
												</c:choose> --%>
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
												<%-- <c:choose>
													<c:when test="${item.Education==null}">
														<td>暂无数据</td>
													</c:when>
													<c:when test="${item.Education==1}">
														<td>本科</td>
													</c:when>
													<c:when test="${item.Education==2}">
														<td>专科</td>
													</c:when>
													<c:when test="${item.Education==3}">
														<td>硕士及以上</td>
													</c:when>
													<c:when test="${item.Education==4}">
														<td>高中及以下</td>
													</c:when>
													<c:otherwise>
														<td>${item.Education}</td>
													</c:otherwise>
												</c:choose> --%>
												<%-- <c:forEach var="pollen" items="${data.data.pollenList}">
													<td>${pollen.company}</td>
												</c:forEach> --%>
												<c:choose>
													<c:when test="${item.CreditLimit==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.CreditLimit}</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${item.BeeType==null}">
														<td>暂无数据</td>
													</c:when>
													<c:when test="${item.BeeType==0}">
														<td>普通客户</td>
													</c:when>
													<c:when test="${item.BeeType==1}">
														<td>公对公客户</td>
													</c:when>
													<c:when test="${item.BeeType==2}">
														<td>公拉私客户</td>
													</c:when>
												</c:choose>
												<td>
												<c:choose>
													<c:when test="${sessionScope.userType==2}">
														<%-- <button  onclick="pollen(${item.ID})" type="button" class="btn btn-primary btn-xs" style="background-color:#D9534F; border:1px solid #D43F3A">额度</button> --%>
													<button  onclick="black(${item.ID})" type="button" class="btn btn-primary btn-xs" style="background-color:#D9534F; border:1px solid #D43F3A">删除</button>
													<%-- <button  onclick="detil(${item.ID})" type="button" class="btn btn-primary btn-xs" style="background-color:#449d44; border:1px solid #398439">详情</button> --%>
													
													</c:when>
													<c:when test="${sessionScope.userType==3}">
														<c:choose>
															<c:when test="${item.BeeType==2||item.BeeType==1}">
																<a  href="addCompanyPackage.jsp?id=${item.ID}&quota=${item.CreditLimit}&userType=${item.BeeType}&beeWX='${item.BeeWX}'" type="button" class="btn btn-primary btn-xs" style="background-color:#449d44; border:1px solid #398439">添加套餐</a>
															</c:when>
														</c:choose>
														
													</c:when>
												</c:choose>
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
							<c:choose>
								<c:when test="${sessionScope.userType==3}">
								<a href="clientimport.jsp"  class="btn btn-primary">导入集团客户</a>
								<a href="download?fileName=bee.xlsx"  class="btn btn-primary">下载集团客户模板</a>
								<a href="beedownload.jsp"  class="btn btn-primary">下载集团客户数据</a>
								<a href="addBee.jsp"  class="btn btn-primary">新增客户</a>
								<br><br>
								<!-- <a href="download?fileName=yifenqizonghe.xlsx"  class="btn btn-primary">下载综合数据模板</a>
								<a href="download?fileName=yifenqi.xlsx"  class="btn btn-primary">导入综合数据</a> -->
								<!-- <a href="addCompre.jsp"  class="btn btn-primary">添加综合数据</a> -->
								</c:when>
							</c:choose>
								
							</div>
					<!--分页组件  -->
					<!-- <div class="M-box"></div> -->
					<!--分页组件-->
					<div style="text-align: center;margin: 0 auto;">
						<nav aria-label="Page navigation" style="text-align: center;margin: 0 auto;">
						  <ul class="pagination" style="text-align: center;margin: 0 auto;">
						    <li>
						    <c:choose>
						    	<c:when test="${pageNow==1}">
						    		
						    	</c:when>
						    	<c:when test="${pageNow!=1}">
						    		<a href="beeList?pageNum=${pageNow-1}" aria-label="Previous">
						        		<span aria-hidden="true">&laquo;</span>
						      		</a>
						    	</c:when>
						    </c:choose>
						    </li>
						    <c:forEach var="item" begin="1" end="${data.pages}" >
						    	<li><a href="beeList?pageNum=${item}">${item}</a></li>
						    </c:forEach>
						    <li>
						    <c:choose>
						    	<c:when test="${pageNow==data.pages}">
						    	
						    		
						    	</c:when>
						    	<c:when test="${pageNow<=data.pages}">
						    		<a href="beeList?pageNum=${pageNow+1}" aria-label="Previous">
						        		<span aria-hidden="true">&raquo;</span>
						      		</a>
						    	</c:when>
						    </c:choose>
						    </li>
						  </ul>
						</nav>
					</div>
							</div>
						</div>
					</div>
					
</body>

</html>
