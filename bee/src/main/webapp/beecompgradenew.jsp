<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="zh-CN">

<head>
	<title>公司信用管理</title>
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
	var compid;
	//调用评级模态框
			function model(id){
				compid=id;
				$('#myModal2').modal({
				  keyboard: false
				}) 
			
		}
	$(function(){
		$("#inserGrade").click(function(){
			var insertVal = $("#insertVal").val();
			if(insertVal==null||insertVal==""){
				alert("评级不能为空");
				return false;
			}else{
			$.ajax({
				url:"insertAndUpdateBeeCompGrade",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	data: {company:company,grade:insertVal,type:1},
		      	success:function(data){
		      		if(data==1){
		      			window.location.href="listBeeCompGrade?pageNum=1";
		      		}
		      	},
		      	error:function(){
		           alert("访问失败！！！");
		           return false;
		      	}
		    }); 
			}
			
		})
		
				
		$("#updateGrade").click(function(){
			var updateVal = $("#updateVal").val();
			if(updateVal==null||updateVal==""){
				alert("评级不能为空");
				return false;
			}else{
			$.ajax({
				url:"updateBeeCompGrade",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	data: {id:compid,grade:updateVal},
		      	success:function(data){
		      	if(data.data==1){
		      		window.location.href="listBeeCompany?pageNum=1";
		      	}
		      	},
		      	error:function(){
		           alert("访问失败！！！");
		           return false;
		      	}
		    }); 
			}
			
		})
	});
	
	var grade;
	
	var blackCom ;
	var comPanyid;
	//调用拉黑模态框
	function black(comp,id){
	blackCom = comp;
	comPanyid = id;
		$('#myModal3').modal({
		  keyboard: false
		})  
	}
	//拉入黑名单
	 function addBlack(){
		if(confirm("确认拉黑该公司？")){
		var reason = $("#blackReason").val();
			$.ajax({
			url:"blackBeeComp",
	      	type:"post",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	//contentType: "application/json",
	      	data:{company:blackCom,blackReason:reason,id:comPanyid},
	      	success:function(data){
	      	if(data==1){
	      		alert("已拉黑该公司,可在公司黑名单管理查看");
	      		window.location.href="listBeeCompany?pageNum=1";
	      	}
	      	},
	      	error:function(){
	           alert("访问失败！！！");
	           return false;
	      	}
	    }); 
		}
	} 
	function grade(){
	if(grade==null||grade==""){
		if(confirm("确定要为该公司评级？")){
			$.ajax({
			url:"insertAndUpdateBeeCompGrade",
	      	type:"post",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {company:company,grade:2,type:1},
	      	success:function(data){
	      	alert("返回数据："+data);
	      	},
	      	error:function(){
	           alert("访问失败！！！");
	           return false;
	      	}
	    }); 
		}else{
			return fasle;
		}
	}else{
		if(confirm("确定要为该公司重新评级？")){
			$.ajax({
			url:"insertAndUpdateBeeCompGrade",
	      	type:"post",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {company:company,grade:1,type:2},
	      	success:function(data){
	      	alert("你当前的试算额度为："+data.data);
	      	},
	      	error:function(){
	           alert("访问失败！！！");
	           return false;
	      	}
	    }); 
		}else{
			return false;
		}
	}
		
	}
	//订单通过审核
	function approved(id,checkStatus){
		var msg = confirm("确认审核？");
		if(msg==true){
		var reason = null;
		if(checkStatus==4){
			reason = prompt("请输入未通过理由");
			if(reason==null){
				return false;
			}
		}
		//var grade = $("#grade").html();
			$.ajax({
				url:"companyYseOrNo",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	data: {id:id,checkStatus:checkStatus,checkReason:reason},
		      	success:function(data){
		      	   if(data===1){
		      	    location.href="listBeeCompany?pageNum=1";
		      	   }
		      	},
		      	error:function(){
		           alert("审核失败！！！");
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
					<h2 class="" style="margin-top:20px ; display: block; float: left;">公司信用管理</h2>
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
												<th>公司名称</th>
												<th>信用代码</th>
												<th>公司地址</th>
												<th>联系人</th>
												<th>手机号</th>
												<th>信用等级</th>
												<th>状态</th>
												<th>录入人</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
										  <c:forEach var="item" items="${data}" begin="0" end="${fn:length(data)}" varStatus="status">
											<tr>
												<td>${status.index+1}</td>
												<c:choose>
													<c:when test="${item.company==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.company}</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${item.companyCode==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.companyCode}</td>
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
												<c:choose>
													<c:when test="${item.linkMan==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.linkMan}</td>
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
													<c:when test="${item.grade==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.grade}</td>
													</c:otherwise>
												</c:choose> 
												<c:choose>
													<c:when test="${item.checkStatus==null}">
														<td>暂无数据</td>
													</c:when>
													<c:when test="${item.checkStatus==1}">
														<td>等待确认</td>
													</c:when>
													<c:when test="${item.checkStatus==2}">
														<td>已确认，等待审核</td>
													</c:when>
													<c:when test="${item.checkStatus==3}">
														<td>已审核</td>
													</c:when>
													<c:when test="${item.checkStatus==4}">
														<td>${item.checkReason}</td>
													</c:when>
													<c:otherwise>
														<td>${item.grade}</td>
													</c:otherwise>
												</c:choose> 
												<c:choose>
													<c:when test="${item.creatBy==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.creatBy}</td>
													</c:otherwise>
												</c:choose> 
												<td>
												<c:choose>
													<c:when test="${sessionScope.userType==2}">
														<c:choose>
														 <c:when test="${item.checkStatus==2}">
															<a href="reviewCompany?id=${item.id}"  class="btn btn-primary btn-xs">审核</a>
														</c:when> 
														<c:when test="${item.checkStatus==3}">
															<button onclick="model('${item.id}')" type="button" class="btn btn-primary btn-xs" style="background-color:#449d44; border:1px solid #398439">评级</button>
														</c:when>
														</c:choose>
														<%-- <button onclick="model('${item.id}')" type="button" class="btn btn-primary btn-xs" style="background-color:#449d44; border:1px solid #398439">评级</button> --%>
													<button onclick="black('${item.company}','${item.id}')" type="button" class="btn btn-danger btn-xs" >拉黑</button> 
													</c:when>
													 <c:when test="${sessionScope.userType==4&&item.checkStatus==1}">
														<%-- <button onclick="approved('${item.id}',2)"  type="button" class="btn btn-primary btn-xs" >通过</button>
														<button onclick="approved('${item.id}',4)"  type="button" class="btn btn-primary btn-xs" >未通过</button> --%>
														<a href="comfirmCompany?id=${item.id}"  class="btn btn-primary btn-xs">点击确认</a>
													</c:when> 
													<c:when test="${sessionScope.userType==3}">
														<a href="toupdateCompany?id=${item.id}"  class="btn btn-primary btn-xs">修改</a>
													</c:when>
													
												</c:choose>
												<c:choose>
													<c:when test="${item.checkStatus==3}">
														<a href="companyDetail?id=${item.id}"  class="btn btn-primary btn-xs">详情</a>
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
									<a href="addcompany.jsp"  class="btn btn-primary">上传公司资料</a>
								</c:when>
							</c:choose>
								
							</div>
					<!--分页组件  -->
					<!-- <div class="M-box"></div> -->
					<!--分页组件-->
					<div style=" margin-left: 600px;margin-bottom: 50px">
						<nav aria-label="Page navigation">
						  <ul class="pagination">
						    <c:choose>
						    	<c:when test="${sessionScope.pageNow==1}">
						    		<li></li>
						    	</c:when>
						    	<c:when test="${sessionScope.pageNow!=1}">
						    		<li>
						    		<a href="listBeeCompany?pageNum=${sessionScope.pageNow-1}" aria-label="Previous">
						        		<span aria-hidden="true">&laquo;</span>
						      		</a>
						      		</li>
						    	</c:when>
						    </c:choose>
						    <c:forEach var="item" begin="1" end="${sessionScope.pages}" >
						    	<li><a href="listBeeCompany?pageNum=${item}">${item}</a></li>
						    </c:forEach>
						    <c:choose>
						    	<c:when test="${sessionScope.pageNow==sessionScope.pages}">
						    		
						    	</c:when>
						    	<c:when test="${sessionScope.pageNow<=sessionScope.pages}">
						    		<li>
						    		<a href="listBeeCompany?pageNum=${sessionScope.pageNow+1}" aria-label="Previous">
						        		<span aria-hidden="true">&raquo;</span>
						      		</a>
						      		</li>
						    	</c:when>
						    </c:choose>
						    
						  </ul>
						</nav>
					</div>
							</div>
						</div>
					</div>
					<!-- 新增 -->
					<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
					  <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					        <h4 class="modal-title" id="myModalLabel">确认新增信用等级?</h4>
					      </div>
					      <div class="modal-body">
					        <input id="insertVal" type="number" required="required" class="form-control" style="display: block;width: 50%"> 
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					        <button id="inserGrade" type="button" class="btn btn-primary">确定</button>
					      </div>
					    </div>
					  </div>
					</div>
					<!-- 修改 -->
					<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
					  <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					        <h4 class="modal-title" id="myModalLabel">确认修改信用等级?</h4>
					      </div>
					      <div class="modal-body">
					         <input id="updateVal" type="number" required="required" class="form-control"  style="display: block;width: 50%"> 
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					        <button id="updateGrade"  type="button" class="btn btn-primary">确定</button>
					      </div>
					    </div>
					  </div>
					</div>
					<!--拉黑 -->
					<div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
					  <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					        <h4 class="modal-title" id="myModalLabel">填写拉黑原因</h4>
					      </div>
					      <div class="modal-body">
					         <input id="blackReason" type="text" required="required" class="form-control"  style="display: block;width: 50%"> 
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					        <button  id="addBlack" onclick="addBlack()"  type="button" class="btn btn-primary">确定</button>
					      </div>
					    </div>
					  </div>
					</div>
</body>

</html>
